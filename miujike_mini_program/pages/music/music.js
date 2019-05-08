// pages/music/music.js
Page({

  /**
   * Page initial data
   */
  data: {
    searchValue:""
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
   * 输入
   */
  doInput(e){
    this.setData({
      searchValue:e.detail.value
    })
  },
  /**
   * 搜索
   */
  doSearch(e){
    let that = this;
    wx.showToast({
      title: that.data.searchValue
    })
  }
})