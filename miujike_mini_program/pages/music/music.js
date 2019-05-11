// pages/music/music.js
Page({

  /**
   * Page initial data
   */
  data: {
    searchValue: "",
    newMusicList: [{
        musicId: 1,
        title: "圣诞节,,,........",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 1000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 2,
        title: "圣诞节,,,,,",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 1000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 3,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 1000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
    ],
    popularMusicList: [{
        musicId: 1,
        title: "圣诞节,,,........",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 1000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 2,
        title: "圣诞节,,,,,",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 1000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 3,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 10000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
      ,
      {
        musicId: 4,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 10000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
      ,
      {
        musicId: 5,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 10000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 6,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 10000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      },
      {
        musicId: 7,
        title: "圣诞节",
        singer: "ld",
        originalSinger: "dongdong",
        duration: "03:00",
        playTimes: 10000,
        cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
      }
    ]
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
    setTimeout(function() {
      wx.stopPullDownRefresh();
    }, 2000)
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