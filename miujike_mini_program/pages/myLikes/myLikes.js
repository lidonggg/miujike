// pages/myLikes/myLikes.js
Page({

  /**
   * Page initial data
   */
  data: {
    curTab: 0,
    navtabs: ["视频", "音乐"],
    loaded: true,
    worksTip: "去为更多优秀的作品加鸡蛋吧～",
    musicList: [{
      uploadBtnShow: false,
      musicId: 1,
      title: "圣诞结圣诞结圣诞结圣诞结",
      originalSinger: "陈奕迅",
      singer: "聂家成ccccccccccc",
      playTimes: 100,
      duration: "03:00"
    }, {
      musicId: 2,
      title: "圣诞结",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 100,
      duration: "03:00"
    }, {
      musicId: 3,
      title: "圣诞结",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 100,
      duration: "03:00"
    }, {
      musicId: 4,
      title: "圣诞结",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 100,
      duration: "03:00"
    }, {
      musicId: 5,
      title: "圣诞结",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 100,
      duration: "03:00"
    }],
    videoList: [{
      videoId: 1,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
    }, {
      videoId: 2,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
    }, {
      videoId: 3,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
    }, {
      videoId: 4,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
    }, {
      videoId: 5,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://pq3gqpelo.bkt.clouddn.com/2019-05-04-46707c55-1105-420a-b8f4-dd908bb09b67.png"
    }]
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
    console.log("下拉刷新")
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
   * 切换tab
   */
  switchNav(e) {
    this.setData({
      curTab: e.currentTarget.dataset.idx
    })
  },
  switchTab(e) {
    this.setData({
      curTab: e.detail.current
    });
  }
})