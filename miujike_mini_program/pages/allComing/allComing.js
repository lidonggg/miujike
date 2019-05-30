const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/allComing/allComing.js
Page({

  /**
   * Page initial data
   */
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    keyword:"",
    newMusicList:[],
    newVideoList:[],
    worksTip:"下拉获取最新的作品吧～",
    tipShow:false
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.fetchNewMusics();
    this.fetchNewVideos();
  },

  /**
   * 拉取最新的音乐
   */
  fetchNewMusics() {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/new",
      data: {
        num: 20
      }
    }).then(res => {
      wx.stopPullDownRefresh();
      that.setData({
        newMusicList: res.data.data,
        loaded: true
      });
    })
  },

  /**
   * 获取新视频
   */
  fetchNewVideos() {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/new",
      data: {
        num: 20
      }
    }).then(res => {
      wx.stopPullDownRefresh();
      if (res.data.data && res.data.data.length > 0) {
        that.setData({
          newVideoList: res.data.data,
          tipShow:true
        })
      }
    })
  },

  doSearch() {
    let that = this;
    wx.navigateTo({
      url: '../../pages/totalSearchPage/totalSearchPage?target=all&keyword=' + that.data.keyword,
    })
  },
  doSearchInput(e) {
    this.data.keyword = e.detail.value;
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function() {

  },

  chooseImg: function() {
    wx.chooseImage({
      count: 1,
      success: function(res) {
        wx.uploadFile({
          url: app.globalData.requestUrl + "/apigateway-works/api/v1/upload/uploadInMini",
          filePath: res.tempFilePaths[0],
          name: "file",
          success(res) {
            console.log("上传成功");
            console.log((JSON.parse(res.data)).url);
          },
          fail() {
            console.log("shangchuanshibai");
          }
        })
      },
    })
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
    this.fetchNewMusics();
    this.fetchNewVideos();
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

  }
})