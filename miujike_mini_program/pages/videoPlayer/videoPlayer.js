// pages/videoPlayer/videoPlayer.js
Page({

  /**
   * Page initial data
   */
  data: {
    videoInfo: {
      duration: "03:55",
      playTimes: 310,
      singer: "聂家成",
      title: "圣诞节",
      originalSinger: "李东"
    },
    videoRecommended: [{
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
    }],
    commentList: [{
        commentId: 1,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！yayayayayayayyyyyyyyyyyyyyytingtinghgghghjjahgj",
        createTime: "2018-01-01"
      },
      {
        commentId: 1,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！",
        createTime: "2018-01-01"
      },
      {
        commentId: 1,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！",
        createTime: "2018-01-01"
      },
      {
        commentId: 1,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！",
        createTime: "2018-01-01"
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