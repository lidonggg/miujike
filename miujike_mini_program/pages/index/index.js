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
    wx.stopPullDownRefresh()
  },
  onSwiper(e) {
    let curIndex = e.detail.current;
    this.setData({
      curSwiperItemIndex: curIndex
    })
  },
  onLoad: function() {
    this.getInfo()
  },
  getInfo: function() {
    var that = this;
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          that.setData({
            userInfo: wx.getStorageSync(app.globalData.tapp),
            authBtnShow: false
          });
          app.globalData.userInfo = wx.getStorageSync(app.globalData.tapp)
          if (!wx.getStorageSync(app.globalData.tapp)) {
            wx.hideLoading();
            that.doLogin();
          }
        } else {
          wx.hideLoading()
        }
      }
    })
  },
  onReachBottom() {
    wx.showToast({
      title: '到底了',
    })
  },
  doLogin: function() {
    var that = this;
    wx.login({
      success: res => {
        var code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function(result) {
              console.log(result)
              api.fetch({
                url: '/apigateway-user/api/v1/user/login/doLogin',
                method: 'post',
                data: {
                  encryptedData: result.encryptedData,
                  iv: result.iv,
                  code: code,
                  tapp: app.globalData.tapp
                },
                showLoading: false
              }).then((res) => {
                console.log(res.data.data)
                app.globalData.userInfo = res.data.data;
                wx: wx.setStorageSync(app.globalData.tapp, res.data.data)
              })
              if (app.userInfoReadyCallback) {
                app.userInfoReadyCallback(result)
              }
            },
            fail: function(failData) {
              console.info("用户拒绝登录");
            }
          })
        }
      }
    })
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