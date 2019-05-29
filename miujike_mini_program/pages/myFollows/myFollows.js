const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/myFollows/myFollows.js
Page({

  /**
   * Page initial data
   */
  data: {
    fromWhere: "follows",
    theTip: "去关注更多感兴趣的小伙伴吧~",
    tipShow: false,
    userList: []
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.fetchFollows(0, true);
  },
  /**
   * 拉取用户列表
   */
  fetchFollows: function(lastId, showLoading, pullDown) {
    let that = this;
    api.fetch({
      url: "apigateway-user/api/v1/user/follow/list/" + app.globalData.userInfo.userId,
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
    this.fetchFollows(0, true, true);
  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function() {
    wx.showToast({
      title: '到底了',
    })
    if (!tipShow) {
      let taht = this;
      this.fetchFollows(that.data.userList[that.data.userList.length - 1].fanId, true, true);
    }
  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function() {
    let userId = res.target.dataset.userid;
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target);
      return {
        title: '分享',
        path: '/pages/hisWorks/hisWorks?userId=' + userId,
        imageUrl: '', //用户分享出去的自定义图片大小为5:4,
        // 此回调不会被执行
        success: function (res) {

        },
        fail: function (res) {
          // 分享失败
        },
      }
    }
  }
})