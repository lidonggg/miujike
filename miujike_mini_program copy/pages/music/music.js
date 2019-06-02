const app = getApp();
const api = require("../../utils/httpRequest.js");
let innerAudioContext = wx.createInnerAudioContext();

// pages/music/music.js
Page({

  /**
   * Page initial data
   */
  data: {
    searchValue: "",
    tipShow: false,
    worksTip: "暂时没有更多音乐了哦~",
    newMusicList: [],
    popularMusicList: [],
    canThumb: true,
    curPlayId: "",
    curPlayIndex: "",
    percents: [],
    loaded:false,
    keyword:""
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.fetchNewMusics();
    this.fetchPopularMusics(0);
    let that = this;
    // innerAudioContext.autoplay=true;
    innerAudioContext.onPlay(() => {
      console.log('开始播放',new Date().getSeconds());
      console.log(innerAudioContext.src);
    });

    setTimeout(() => {
      innerAudioContext.onTimeUpdate(() => {
        console.log(innerAudioContext.duration) //总时长
        console.log(innerAudioContext.currentTime) //当前播放进度
        let changeKey = "percents[" + that.data.curPlayIndex + "]";
        that.setData({
          [changeKey]: Math.ceil((innerAudioContext.currentTime / innerAudioContext.duration) * 100) + 5
        })
      })
    }, 10)

    // innerAudioContext.onTimeUpdate((res) => {
    //   console.log(res);
    // });
    innerAudioContext.onPause(() => {
      console.log('播放暂停', new Date().getSeconds());
      // that.setData({
      //   curPlayId: ""
      // })
    })
    innerAudioContext.onEnded(() => {
      console.log('播放结束', new Date().getSeconds());
      that.setData({
        curPlayId: "",
        curPlayIndex: -1,
        ["percents[" + that.data.curPlayIndex + "]"]:100
      })
    })
  },

  /**
   * 拉取最新的音乐
   */
  fetchNewMusics() {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/new",
      data: {
        num: 5
      }
    }).then(res => {
      wx.stopPullDownRefresh();
      for (let i = 0; i < res.data.data.length; i++) {
        that.data.percents.push("");
      }
      that.setData({
        newMusicList: res.data.data,
        loaded:true
      });
    })
  },

  /**
   * 拉取音乐
   */
  fetchPopularMusics(lastId) {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/music/listPopular",
      data: {}
    }).then(res => {
      wx.stopPullDownRefresh();
      console.log(res.data);
      if (res.data.code == 200) {
        that.setData({
          popularMusicList: res.data.data
        });
        that.setData({
          tipShow: true
        })
      }
    })
  },
  doPlayNewMusic(e) {
    let that = this;
    let index = e.currentTarget.dataset.index;
    let musicId = e.currentTarget.dataset.musicid;
    console.log(musicId,musicId);
    if (this.data.curPlayId != musicId) {
      that.setData({
        curPlayId: musicId,
        curPlayIndex: index
      });
      innerAudioContext.src = this.data.newMusicList[index].musicUrl;
      innerAudioContext.play();
      console.log("播放" + index);
    } else {
      if (innerAudioContext.paused) {
        innerAudioContext.play();
      } else {
        innerAudioContext.pause();
      }
    }
  },
  doSearch() {
    let that = this;
    wx.navigateTo({
      url: '../../pages/searchPage/searchPage?target=music&keyword=' + that.data.keyword,
    })
  },
  doSearchInput(e) {
    this.data.keyword = e.detail.value;
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
  onShareAppMessage: function(res) {
    let musicId = res.target.dataset.musicid;
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
      return {
        title: '分享',
        path: '/pages/musicPlayer/musicPlayer?musicId=' + musicId,
        imageUrl: '', //用户分享出去的自定义图片大小为5:4,
        // 此回调不会被执行
        success: function(res) {

        },
        fail: function(res) {
          // 分享失败
        },
      }
    }
  },
  doThumb(e) {
    if (this.data.canThumb) {
      this.data.canThumb = false;
      let musicId = e.currentTarget.dataset.musicid;
      let that = this;
      api.fetch({
        url: "apigateway-behavior/api/v1/behavior/thumb/doThumb",
        method: "post",
        data: {
          fromUserId: app.globalData.userInfo.userId,
          targetType: 1,
          eggs: 1,
          targetId: musicId
        }
      }).then(res => {
        this.data.canThumb = true;
        if (res.data.code == 200) {
          wx.showToast({
            title: '鸡蛋-1',
          })
        }
      })
    } else {
      wx.showToast({
        title: '操作太频繁～',
      })
    }
  },
})