const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/music/music.js
Page({

  /**
   * Page initial data
   */
  data: {
    searchValue: "",
    tipShow:false,
    worksTip:"暂时没有更多音乐了哦~",
    newMusicList: [{
        musicId: 1,
        title: "圣诞节,,,........",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 2,
        title: "圣诞节,,,,,",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 3,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
    ],
    popularMusicList: [{
        musicId: 1,
        title: "圣诞节,,,........",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 2,
        title: "圣诞节,,,,,",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 3,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 4,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 5,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 6,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 7,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        playTimes: 100,
        duration: 235,
        durationShow: "03:55",
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
    ]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.fetchNewMusics();
    this.fetchPopularMusics(0);
  },

  /**
   * 拉取最新的音乐
   */
  fetchNewMusics() {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/new",

    }).then(res => {
      wx.stopPullDownRefresh();
      that.setData({
        newMusicList: res.data.data
      })
    })
  },

  /**
   * 拉取音乐
   */
  fetchPopularMusics(lastId) {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/list/0",
      data: {
        lastId: lastId
      }
    }).then(res => {
      wx.stopPullDownRefresh();
      console.log(res.data);
      if (res.data.code == 200) {
        that.setData({
          popularMusicList: res.data.data
        });
        if (res.data.data.length < app.globalData.fetchNum) {
          that.setData({
            tipShow: true
          })
        }
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
    this.fetchPopularMusics(0);
  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function() {
    if (!this.data.tipShow) {
      let lastId = this.data.popularMusicList[this.data.popularMusicList.length - 1].musicId;
      this.fetchPopularMusics(lastId);
    }
  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function() {

  },
  /**
   * 输入
   */
  doInput(e) {
    this.setData({
      searchValue: e.detail.value
    })
  },
  /**
   * 搜索
   */
  doSearch(e) {
    let that = this;
    wx.showToast({
      title: that.data.searchValue
    })
  }
})