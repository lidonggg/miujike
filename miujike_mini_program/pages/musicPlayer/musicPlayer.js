const app = getApp();
const api = require("../../utils/httpRequest.js");
let innerAudioContext = wx.createInnerAudioContext();

// pages/musicPlayer/musicPlayer.js
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
    curMusicInfo: {

    },
    toTop:false,
    curIndex: -1,
    percent: 0,
    playMode: "circulate", // 播放模式，循环(circulate)，单曲循环(singleCirculate)，乱序(random)等等，这里功能上还没用到,默认为循环
    playing: true, //0 -- 暂停，1 -- 播放
    commentList: [],
    tipShow: false,
    commentTip: "暂时没有更多评论了哦～"
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
   * 监听滑动过程
   */
  onScrolling(e){
    let scrollDetails = e.detail;
    let that = this;
    wx.createSelectorQuery().select('#the-operations').boundingClientRect(function (rect) {
      let curTop = rect.top;
      // 上滑
      if(curTop<5 && scrollDetails.deltaY < 0){
        that.setData({
          toTop:true
        })
      } else if (scrollDetails.deltaY > 0 && scrollDetails.scrollTop > 375){
        that.setData({
          toTop: true
        })
      } else{
        that.setData({
          toTop: false
        })
      }
    }).exec();
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    let that = this;
    if (options.index) {
      this.setData({
        curMusicInfo: app.globalData.musicPlayList[options.index],
        curIndex: options.index
      })
      innerAudioContext.src = this.data.curMusicInfo.musicUrl;
      innerAudioContext.play();
    } else {
      wx.showModal({
        title: '提示',
        content: '播放失败',
        showCancel: false,
        success() {
          wx.navigateBack({})
        }
      })
    }
    this.fetchCommentList(0);
    innerAudioContext.onPlay(() => {
      console.log('开始播放', new Date().getSeconds());
      console.log(innerAudioContext.src);
    });

    // setInterval(() => {
    innerAudioContext.onTimeUpdate(() => {
      console.log(innerAudioContext.duration) //总时长
      console.log(innerAudioContext.currentTime) //当前播放进度
      that.setData({
        percent: Math.ceil((innerAudioContext.currentTime / innerAudioContext.duration) * 100) + 5
      })
    })
    // }, 10)
    innerAudioContext.onPause(() => {
      console.log('播放暂停', new Date().getSeconds());

    })
    innerAudioContext.onEnded(() => {
      console.log('播放结束', new Date().getSeconds());
      that.setData({
        percent: 100
      })
      // 下一首
      that.onNext();
    })
  },

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function() {

  },
  /**
   * 播放或暂停
   */
  onPauseOrPlay() {
    let curStatus = this.data.playing;
    this.setData({
      playing: !curStatus
    })
    if (curStatus) {
      innerAudioContext.pause();
    } else {
      innerAudioContext.play();
    }
  },

  /**
   * 下一首
   */
  onNext(e) {
    let curIndex = this.data.curIndex;
    // 最后一首且是循环模式返回到第一首
    if (this.data.playMode === "circulate" && curIndex == app.globalData.musicPlayList.length - 1) {
      this.data.curIndex = 0;
    } else {
      this.data.curIndex++;
    }
    this.changeView();
  },
  /**
   * 上一首
   */
  onPre(e) {
    let curIndex = this.data.curIndex;
    console.log("cur",curIndex);
    // 第一首且是循环模式则去到最后一首
    if (this.data.playMode == "circulate" && curIndex == 0) {
      console.log("第一首")
      this.data.curIndex = app.globalData.musicPlayList.length - 1;
    } else {
      this.data.curIndex--;
    }
    this.changeView();
  },
  /**
   * 渲染视图
   */
  changeView() {
    let index = this.data.curIndex;
    console.log(app.globalData.musicPlayList.length,index);
    let curMusicInfo = app.globalData.musicPlayList[index];
    this.setData({
      curMusicInfo: curMusicInfo
    });
    this.fetchCommentList(0);
    innerAudioContext.src = curMusicInfo.musicUrl;
    innerAudioContext.play();
  },
  /**
   * 拉取评论列表
   */
  fetchCommentList(lastId) {
    this.setData({
      tipShow: false
    })
    if (lastId === 0) {
      this.data.commentList = [];
    }
    let that = this;
    if (!this.data.loading) {
      this.data.loading = true;
      api.fetch({
        url: "apigateway-behavior/api/v1/behavior/comment/list/" + that.data.curMusicInfo.musicId,
        data: {
          targetType: 1,
          lastId: lastId
        }
      }).then(res => {
        wx.hideLoading();
        console.log(res.data.data);
        that.setData({
          loading: false,
        });
        if (res.data.data) {
          that.setData({
            commentList: that.data.commentList.concat(res.data.data)
          })
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
  /**
   * 评论完成，动态渲染
   */
  onAddComment(e) {
    let that = this;
    console.log(e.detail);
    let newComment = e.detail;
    newComment.avatarUrl = app.globalData.userInfo.avatarUrl;
    newComment.nickname = app.globalData.userInfo.nickname;
    this.data.commentList.unshift(newComment)
    this.setData({
      commentList: that.data.commentList
    })
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
    this.setData({
      playing: false
    })
    innerAudioContext.pause();
  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function() {
    this.setData({
      playing: false,
      curIndex: -1,
      percent: 0
    })
    innerAudioContext.stop();
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