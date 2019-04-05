const api = require("../../utils/httpRequest.js")

// pages/mine/mine.js
Page({

  /**
   * Page initial data
   */
  data: {
    menuList:[
      {
        name: "获取鸡蛋",
        path: "pages/getEggs/getEggs"
      },
      {
        name: "我的作品",
        path: "pages/myWorks/myWorks"
      }, {
        name: "我的消息",
        path: "pages/myNotifications/myNotifications"
      }, {
        name: "我的喜欢",
        path: "pages/myLikes/myLikes"
      }, {
        name: "我的评论",
        path: "pages/myComments/myComments"
      }
    ]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {

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

  /**
   * 点击领取鸡蛋
   */
  getEggs(){

  },

  /**
   * 选项条
   */
  getIn(e){
    console.log(e.currentTarget.dataset.path);
    wx.navigateTo({
      url: '../../' + e.currentTarget.dataset.path,
    })
  }
})