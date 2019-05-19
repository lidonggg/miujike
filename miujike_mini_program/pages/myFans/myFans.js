const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/myFans/myFans.js
Page({

  /**
   * Page initial data
   */
  data: {
    fromWhere:"fans",
    theTip: "去发布更多作品让小伙伴关注你吧~", 
    tipShow: false,
    userList:[]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    this.fetchFans(0, true);
  },
  /**
   * 拉取用户列表
   */
  fetchFans: function (lastId, showLoading, pullDown) {
    let that = this;
    api.fetch({
      url: "apigateway-user/api/v1/user/fan/list/" + app.globalData.userInfo.userId,
      data: {
        lastId: lastId,
      },
      showLoading: showLoading
    }).then(res => {
      wx.hideLoading();
      wx.stopPullDownRefresh();
      if (pullDown) {
        that.setData({
          userList: res.data.data
        })
      } else {
        that.setData({
          userList: that.data.userList.concat(res.data.data)
        })
      }
      if (res.data.data.length < app.globalData.fetchNum) {
        that.setData({
          tipShow: true
        })
      }
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
    this.fetchFans(0, true, true);
  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {
    wx.showToast({
      title: '到底了',
    })
    if (!tipShow) {
      let taht = this;
      this.fetchFans(that.data.userList[that.data.userList.length - 1].fanId, true, true);
    }
  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  }
})