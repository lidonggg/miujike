const innerAudioContext = wx.createInnerAudioContext();
// pages/test/test.js
Page({

  /**
   * Page initial data
   */
  data: {

  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    innerAudioContext.autoplay = true;
    console.log("http://ps8xnh0n1.bkt.clouddn.com/miujike/1559124442032-OAZZTRLZ.mp3")
    innerAudioContext.src = "http://ps8xnh0n1.bkt.clouddn.com/miujike/1559124442032-OAZZTRLZ.mp3";
    innerAudioContext.play();
    innerAudioContext.onPlay(() => {
      console.log('开始播放')
    });
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

  }
})