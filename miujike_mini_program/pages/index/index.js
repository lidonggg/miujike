//index.js
//获取应用实例
const app = getApp()
const api = require("../../utils/httpRequest.js")

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    curSwiperItemIndex: 0,
    newVideoList: [{
      videoId: 1,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"

    }, {
      videoId: 2,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 3,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 4,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 5,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }],
    popularVideoList: [{
      videoId: 1,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 2,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 3,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 4,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }, {
      videoId: 5,
      title: "圣诞节",
      originalSinger: "陈奕迅",
      singer: "聂家成",
      listenTimes: 100,
      duration: "03:00",
      cover: "http://file.yfjiaoyu.com/group1.jpg",
      videoUrl: "http://file.yfjiaoyu.com/o_1ctcn6sms6gpn64kopjc69m0ac.mp4"
    }]
  },
  onPullDownRefresh() {
    this.fetchNewVideos();
  },
  onSwiper(e) {
    let curIndex = e.detail.current;
    this.setData({
      curSwiperItemIndex: curIndex
    })
  },
  onLoad: function() {
    this.fetchNewVideos();
  },
  /**
   * 获取新视频
   */
  fetchNewVideos(){
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/new"
    }).then(res => {
      wx.stopPullDownRefresh();
      if (res.data.data && res.data.data.length > 0){
        that.setData({
          newVideoList: res.data.data
        })
      }
    })
  },
  /**
   * 拉取音乐
   */
  fetchPopularVideos() {
    
  },
  goPlayPage(e) {
    let that = this;
    let index = e.currentTarget.dataset.index;
    app.globalData.mediaPlay = this.data.newVideoList[index];
    wx.navigateTo({
      url: '../../pages/videoPlayer/videoPlayer?videoId=' + that.data.newVideoList[index].videoId
    })
  },
  doSearch(){
    this.getInfo();
  }
})