const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/allComing/allComing.js
Page({

  /**
   * Page initial data
   */
  data: {

  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {

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