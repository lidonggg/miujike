const app = getApp();
const api = require("../../utils/httpRequest.js");
const innerAudioContext = wx.createInnerAudioContext();

// pages/musicPlayer/musicPlayer.js
Page({

  /**
   * Page initial data
   */
  data: {
    curMusicInfo:{

    }
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    if(options.index){
      this.setData({
        curMusicInfo: app.globalData.musicPlayList[options.index]
      })
    }else{
      wx.showModal({
        title: '提示',
        content: '播放失败',
        showCancel:false,
        success(){
          wx.navigateBack({})
        }
      })
    }
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  onNext(e){
    let that = this;
    let lastId = this.data.curMusicInfo.musicId;
    api.fetch({
      url:"apigateway-works/api/v1/works/music/getNext"
    })
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