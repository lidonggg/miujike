const app = getApp();
const api = require("../../utils/httpRequest.js");
const util = require("../../utils/util.js");
const qiniuUploader = require("../../utils/qiniuUploader.js");
const recorderManager = wx.getRecorderManager();
const innerAudioContext = wx.createInnerAudioContext();

Page({

  /**
   * Page initial data
   */
  data: {
    coverImgPath: "../../resources/imgs/worksUploader/add_cover.png",
    coverImgUrl:"",
    recording:false,
    recorded:false,
    audioTempPath:"",
    musicUrl:"",
    percent:0,
    duration:0,
    uploading:false,
    userId:""
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    this.setData({
      userId: app.globalData.userInfo.userId
    })
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function () {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function () {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function () {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  },
  onAddCover(e) {
    let that = this;
    console.log("cover:" + e.detail);

    this.setData({
      coverImgUrl: e.detail
    })
  },
  doRecord(){
    let that = this;
    if(this.data.recording){
      this.stopRecord();
    }else{
      wx.getSetting({
        success(res) {
          if (!res.authSetting['scope.record']) {
            wx.authorize({
              scope: 'scope.record',
              success() {
                that.startRecord();
              }
            })
          } else{
            that.startRecord();
          }
        }
      })
    }
  },
  startRecord(){
    const options = {
      duration: 600000, //指定录音的时长，单位 ms，最大为10分钟（600000），默认为1分钟（60000）
      sampleRate: 16000, //采样率
      numberOfChannels: 2, //录音通道数
      encodeBitRate: 96000, //编码码率
      format: 'mp3', //音频格式，有效值 aac/mp3
      frameSize: 50, //指定帧大小，单位 KB
    }
    //开始录音
    recorderManager.start(options);
    recorderManager.onStart(() => {
      this.setData({
        recording: true,
        recorded: false
      })
      console.log('。。。开始录音。。。')
    });
  },
  stopRecord(){
    let that = this;
    //结束
    recorderManager.stop();
    recorderManager.onStop((res) => {
      that.setData({
        recorded: true,
        recording: false,
        audioTempPath: res.tempFilePath,
        duration:parseInt(res.duration)
      })
      console.log('。。停止录音。。', res.tempFilePath);
      // wx.showModal({
      //   title: '提示',
      //   content: '录音结束，是否上传',
      // })
    });
  },
  /**
   * 试听
   */
  doListen(){
    innerAudioContext.autoplay = true;
    innerAudioContext.src = this.data.audioTempPath;
    innerAudioContext.play();
    wx.showModal({
      title: '提示',
      content: '试听中,按确定结束试听',
      showCancel:false,
      success(res){
        innerAudioContext.pause();
      }
    })
  },
  /**
   * 上传
   */
  doUpload(){
    this.setData({
      uploading: true
    })
    let filePath = this.data.audioTempPath;
    let that = this;
    console.log("filePath"+this.data.audioTempPath);
    // 交给七牛上传
    qiniuUploader.upload(filePath, (res) => {

      that.setData({
        musicUrl: res.fileUrl,
      });
      that.setData({
        percent: 100
      })
      wx.showToast({
        title: '上传成功',
        duration: 1500,
        mask: false
      })
      console.log("urllll:" + res.fileUrl)
    }, (error) => {
      console.log('error: ' + error);
    }, {
        region: 'ECN',
        domain: app.globalData.qiniuDomain,
        key: 'miujike/' + (new Date()).getTime() + "-" + util.generateMixed(8) + "." + util.getSuffix(filePath),
        // 以下方法三选一即可，优先级为：uptoken > uptokenURL > uptokenFunc
        // uptoken: '[yourTokenString]', // 由其他程序生成七牛 uptoken
        uptokenURL: app.globalData.requestUrl + '/apigateway-works/api/v1/upload/getToken', // 从指定 url 通过 HTTP GET 获取 uptoken，返回的格式必须是 json 且包含 uptoken 字段，例如： {"uptoken": "[yourTokenString]"}
        // uptokenFunc: function () { return '[yourTokenString]'; }
      }, (res) => {
        console.log('上传进度', res.progress)
        console.log('已经上传的数据长度', res.totalBytesSent)
        console.log('预期需要上传的数据总长度', res.totalBytesExpectedToSend)
        that.setData({
          percent: res.progress
        })
      }, () => {
        // 取消上传
      }, () => {
        // `before` 上传前执行的操作
      }, (err) => {
        // `complete` 上传接受后执行的操作(无论成功还是失败都执行)
      });
  },
  submitMusic(e){
    let values = e.detail.value
    console.log(e.detail.value)
    if (!values.musicUrl || !values.title || !values.singer || !values.detail || !values.cover) {
      wx.showModal({
        title: '提示',
        content: '请保证信息完整',
        showCancel: false
      })
    } else {
      api.fetch({
        url: "apigateway-works/api/v1/works/music/upload",
        method: "post",
        data: values
      }).then(res => {
        if (res.data.code == 200) {
          wx.showToast({
            title: '发布成功',
          })
          let newMusic = res.data.data;
          // 刷新父页面列表
          let pages = getCurrentPages();
          // 上一个页面
          let prevPage = pages[pages.length - 2];
          let preMusics = prevPage.data.musicList;
          preMusics.unshift(newMusic)
          prevPage.setData({
            musicList: preMusics
          })
          wx.navigateBack({})
        }
      })
    }
  }
})