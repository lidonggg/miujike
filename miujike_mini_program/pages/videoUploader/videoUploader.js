const app = getApp();
const api = require("../../utils/httpRequest.js");
const util = require("../../utils/util.js");
const qiniuUploader = require("../../utils/qiniuUploader.js");

// pages/videoUploader/videoUploader.js
Page({

  /**
   * Page initial data
   */
  data: {
    coverImgUrl: "",
    userId: "",
    videoUrl: "",
    duration: 0,
    percent: 0,
    canSubmit: false,
    uploading: false
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.setData({
      userId: app.globalData.userInfo.userId
    })
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function() {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function() {

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function() {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function() {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function() {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function() {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function() {

  },
  /**
   * 选择视频并通过七牛云上传
   */
  chooseVideo() {
    let that = this;

    wx.chooseVideo({
      count: 1,
      success: function(res) {
        that.setData({
          duration: parseInt(res.duration * 1000),
          canSubmit: true,
          videoUrl: "",
          uploading:true
        })
        var filePath = res.tempFilePath;
        // 交给七牛上传
        qiniuUploader.upload(filePath, (res) => {
          that.setData({
            videoUrl: res.fileUrl,
          });
          wx.showToast({
            title: '上传成功',
            duration: 1500,
            mask: false
          })
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
      }
    })
  },

  onAddCover(e) {
    let that = this;
    console.log("cover:" + e.detail);

    this.setData({
      coverImgUrl: e.detail
    })
  },
  /**
   * 提交
   */
  submitVideo(e) {
    let values = e.detail.value
    console.log(e.detail.value)
    if (!values.videoUrl || !values.title || !values.singer || !values.detail) {
      wx.showModal({
        title: '提示',
        content: '请保证信息完整',
        showCancel: false
      })
    } else {
      api.fetch({
        url: "apigateway-works/api/v1/works/video/upload",
        method: "post",
        data: values
      }).then(res => {
        if (res.data.code == 200) {
          wx.showToast({
            title: '发布成功',
          })
          let newVideo = res.data.data;
          // 刷新父页面列表
          let pages = getCurrentPages();
          // 上一个页面
          let prevPage = pages[pages.length - 2];
          let preVideos = prevPage.data.videoList;
          preVideos.unshift(newVideo)
          prevPage.setData({
            videoList: preVideos
          });
          wx.navigateBack({})
        }
      })
    }
  }
})