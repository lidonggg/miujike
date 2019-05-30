const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/videoPlayer/videoPlayer.js
Page({
  dataScroll: {
    scrollTop: 0, //滚动到的位置
    startScroll: 0, // 滚动前的位置
    touchDown: 0, // 触摸时候的 Y 的位置
    innerHeight: 0,
  },
  /**
   * Page initial data
   */
  data: {
    curVideoInfo: {},
    curVideoId:"",
    loading: false,
    tipShow: false,
    commentTip: "暂时没有更多评论了哦～",
    videoRecommended: [],
    commentList: []
  },
  // start: 触摸开始
  startFn: function(e) {
    let that = this;
    let touchDown = e.touches[0].clientY;
    this.dataScroll.touchDown = touchDown;
    // 获取 inner-wrap 的高度
    wx.createSelectorQuery().select('#the-inner').boundingClientRect(function(rect) {
      that.dataScroll.innerHeight = rect.height;
    }).exec();

    // 获取 scroll-wrap 的高度和当前的 scrollTop 位置
    wx.createSelectorQuery().select('#the-scroll').fields({
      scrollOffset: true,
      size: true
    }, function(rect) {
      that.dataScroll.startScroll = rect.scrollTop;
      that.dataScroll.height = rect.height;
    }).exec();
  },
  endFn: function(e) {
    let currentY = e.changedTouches[0].clientY;
    let that = this;
    let {
      startScroll,
      innerHeight,
      height,
      touchDown
    } = this.dataScroll;
    console.log(currentY, touchDown, startScroll, innerHeight, height)
    if (currentY > touchDown && currentY - touchDown > 20 && startScroll == 0) {
      wx.showLoading({
        title: '加载中',
      })
      this.fetchCommentList(0);
    } else if (currentY < touchDown && touchDown - currentY > 20 && innerHeight - height == 0) {
      if (!this.data.loading && !this.data.tipShow) {
        console.log("上拉刷新着中")
        this.fetchCommentList(this.data.commentList[this.data.commentList.length - 1].commentId);
      }
    }
  },
  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    if(options.videoId){
      this.getRecommendList(options.videoId);
      this.setData({
        curVideoId: options.videoId
      });
      let that = this;
      api.fetch({
        url:"apigateway-works/api/v1/works/video/info/"+options.videoId
      }).then(res => {
        if(res.data.code == 200){
          that.setData({
            curVideoInfo:res.data.data
          })
          app.globalData.mediaPlay = {};
          that.videoContext = wx.createVideoContext('the-video');
          that.videoContext.play();
        }
      })
      this.fetchCommentList(0);
    }
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
  doWaiting(){
    wx.showToast({
      title: '加载中',
    })
  },
  doError() {
    wx.showToast({
      title: '出错了',
    })
  },
  getRecommendList(curVideoId){
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/recommend/"+curVideoId,
      data:{
        n:5
      }
    }).then(res => {
      if(res.data.code == 200){
        that.setData({
          videoRecommended:res.data.data
        })
      }
    })
  },
  changeVideo(e) {
    let index = e.currentTarget.dataset.index;
    console.log(index);
    let that = this;
    if (this.data.videoRecommended[index].videoId != this.data.curVideoId) {
      this.videoContext.stop();
      this.setData({
        curVideoInfo: that.data.videoRecommended[index],
        curVideoId: this.data.videoRecommended[index].videoId
      })
      this.videoContext.play();
      this.fetchCommentList(0);
    }
  },
  fetchCommentList(lastId) {
    this.setData({
      tipShow:false
    })
    if (lastId === 0) {
      this.data.commentList = [];
    }
    let that = this;
    if (!this.data.loading) {
      this.data.loading = true;
      api.fetch({
        url: "apigateway-behavior/api/v1/behavior/comment/list/" + that.data.curVideoId,
        data: {
          targetType: 2,
          lastId: lastId
        }
      }).then(res => {
        wx.hideLoading();

        that.setData({
          loading: false,
        });
        if (res.data.data) {
          // if (that.data.commentList.length != 0) {
            that.setData({
              commentList: that.data.commentList.concat(res.data.data)
            })
          // }else{
          //   that.setData({
          //     commentList: res.data.data
          //   })
          // }
          if (res.data.data.length < app.globalData.fetchNum) {
            console.log("加载完了")
            that.setData({
              tipShow: true
            })
          }
        }
      })
    }
  },
  onAddComment(e){
    let that = this;
    console.log(e.detail);
    let newComment = e.detail;
    newComment.avatarUrl = app.globalData.userInfo.avatarUrl;
    newComment.nickname = app.globalData.userInfo.nickname;
    this.data.commentList.unshift(newComment)
    this.setData({
      commentList: that.data.commentList
    })
  }
})