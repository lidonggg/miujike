const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/myLikes/myLikes.js
Page({

  dataScroll: {
    scrollTop: 0,  //滚动到的位置
    startScroll: 0, // 滚动前的位置
    touchDown: 0,   // 触摸时候的 Y 的位置
    innerHeight: 0,
  },

  /**
   * Page initial data
   */
  data: {
    curTab: 0,
    navtabs: ["视频", "音乐"],
    loaded: true,
    worksTip: "去为更多优秀的作品加鸡蛋吧～",
    musicList: [],
    videoList: [],
    videoTipShow:false,
    musicTipShow:false
  },

  // start: 触摸开始
  startFn: function (e) {
    let that = this;
    let touchDown = e.touches[0].clientY;
    this.dataScroll.touchDown = touchDown;
    // 获取 inner-wrap 的高度
    wx.createSelectorQuery().select('#the-inner').boundingClientRect(function (rect) {
      that.dataScroll.innerHeight = rect.height;
    }).exec();

    // 获取 scroll-wrap 的高度和当前的 scrollTop 位置
    wx.createSelectorQuery().select('#the-scroll').fields({
      scrollOffset: true,
      size: true
    }, function (rect) {
      that.dataScroll.startScroll = rect.scrollTop;
      that.dataScroll.height = rect.height;
    }).exec();
  },
  endFn: function (e) {
    let currentY = e.changedTouches[0].clientY;
    let that = this;
    let { startScroll, innerHeight, height, touchDown } = this.dataScroll;
    console.log(currentY, touchDown, startScroll, innerHeight, height)
    if (currentY > touchDown && currentY - touchDown > 20 && startScroll == 0) {
      wx.showLoading({
        title: '加载中',
      })
      this.fetchVideoList(0);
    } else if (currentY < touchDown && touchDown - currentY > 20 && innerHeight - height == 0) {
      if (!this.data.loading && !this.data.tipShow) {

        this.fetchVideoList(this.data.videoList[this.data.videoList.length - 1].videoId);
      }
    }
  },

  /**
   * 这里代码应该可以进行优化
   */
  // start: 触摸开始
  startFn2: function (e) {
    let that = this;
    let touchDown = e.touches[0].clientY;
    this.dataScroll.touchDown = touchDown;
    // 获取 inner-wrap 的高度
    wx.createSelectorQuery().select('#the-inner-2').boundingClientRect(function (rect) {
      that.dataScroll.innerHeight = rect.height;
    }).exec();

    // 获取 scroll-wrap 的高度和当前的 scrollTop 位置
    wx.createSelectorQuery().select('#the-scroll-2').fields({
      scrollOffset: true,
      size: true
    }, function (rect) {
      that.dataScroll.startScroll = rect.scrollTop;
      that.dataScroll.height = rect.height;
    }).exec();
  },
  endFn2: function (e) {
    let currentY = e.changedTouches[0].clientY;
    let that = this;
    let { startScroll, innerHeight, height, touchDown } = this.dataScroll;
    console.log(currentY, touchDown, startScroll, innerHeight, height)
    if (currentY > touchDown && currentY - touchDown > 20 && startScroll == 0) {
      wx.showLoading({
        title: '加载中',
      })
      this.fetchMusicList(0);
    } else if (currentY < touchDown && touchDown - currentY > 20 && innerHeight - height == 0) {
      if (!this.data.loading && !this.data.tipShow) {

        this.fetchMusicList(this.data.videoList[this.data.videoList.length - 1].videoId);
      }
    }
  },

  /**
   * 加载视频
   */
  fetchVideoList(lastId) {
    let that = this;
    this.data.loading = true;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/listLike/" + app.globalData.userInfo.userId,
      data: {
        lastId: lastId
      },
      showLoading: false
    }).then(res => {
      console.log(res.data);
      if (lastId == 0) {
        that.data.videoList = [];
      }
      that.setData({
        videoList: that.data.videoList.concat(res.data.data)
      })
      if (res.data.data.length < app.globalData.fetchNum) {
        that.setData({
          videoTipShow: true
        })
      }
    })
  },

  /**
   * 音乐列表
   */
  fetchMusicList(lastId) {
    let that = this;
    this.data.loading = true;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/listLike/" + app.globalData.userInfo.userId,
      data: {
        lastId: lastId
      },
      showLoading: false
    }).then(res => {
      console.log(res.data);
      if (lastId == 0) {
        that.data.musicList = [];
      }
      that.setData({
        musicList: that.data.musicList.concat(res.data.data)
      })
      if (res.data.data.length < app.globalData.fetchNum) {
        that.setData({
          musicTipShow: true
        })
      }
    })
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.fetchVideoList(0);
    this.fetchMusicList(0);
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