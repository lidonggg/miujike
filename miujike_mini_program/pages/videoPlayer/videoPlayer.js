const app = getApp()

// pages/videoPlayer/videoPlayer.js
Page({

  /**
   * Page initial data
   */
  data: {
    curVideoInfo: {},
    videoRecommended: [{
      videoId: 1,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 100,
      duration: 235,
      durationShow: "03:55",
      cover: "../../resources/imgs/worksUploader/add_cover.png",
      videoUrl: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400"
    }, {
      videoId: 2,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 130,
      duration: 235,
      durationShow: "03:55",
      cover: "../../resources/imgs/worksUploader/add_cover.png",
      videoUrl: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400"
    }, {
      videoId: 3,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 120,
      duration: 235,
      durationShow: "03:55",
      cover: "../../resources/imgs/worksUploader/add_cover.png",
      videoUrl: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400"
    }, {
      videoId: 4,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 110,
      duration: 235,
      durationShow: "03:55",
      cover: "../../resources/imgs/worksUploader/add_cover.png",
      videoUrl: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400"
    }, {
      videoId: 5,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      playTimes: 200,
      duration: 235,
      durationShow: "03:55",
      cover: "../../resources/imgs/worksUploader/add_cover.png",
      videoUrl: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400"
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
        commentId: 2,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！",
        createTime: "2018-01-01"
      },
      {
        commentId: 3,
        fromUserId: 1,
        nickname: "njc",
        avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLL5ym66XicGOK5BmFvhhD8Bey8fF8tFyI6OD97XiczNNTm7z1rquXlW3G6Dk2JQxHvfrTF24atVN2w/132",
        content: "真好听！！！",
        createTime: "2018-01-01"
      },
      {
        commentId: 4,
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
    this.setData({
      curVideoInfo:app.globalData.mediaPlay
    })
    app.globalData.mediaPlay = {};
    this.videoContext = wx.createVideoContext('the-video');
    this.videoContext.play();
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

  },
  changeVideo(e) {
    let index = e.currentTarget.dataset.index;
    console.log(e.currentTarget);
    let that = this;
    if (this.data.videoRecommended[index].videoId != this.data.videoInfo.videoId) {
      // this.videoContext.stop();
      this.setData({
        videoInfo: that.data.videoRecommended[index]
      })
      this.videoContext.start();

    }
  },
  fetchComments(){
    let that = this;
    api.fetch({
      url:"apigateway-behavior/api/v1/comment/list/"+that.videoInfo.videoId,
      data:{
        type: 2
      }
    }).then(res => {
      
    })
  }
})