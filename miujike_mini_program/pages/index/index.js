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
    newVideoList: [{
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
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    this.getInfo()
  },
  getInfo: function () {
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
  doLogin: function () {
    var that = this;
    wx.login({
      success: res => {
        var code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function (result) {
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
                showLoading:false
              }).then((res)=>{
                console.log(res.data.data)
                app.globalData.userInfo = res.data.data;
                wx: wx.setStorageSync(app.globalData.tapp, res.data.data)
              })
              if (app.userInfoReadyCallback) {
                app.userInfoReadyCallback(result)
              }
            },
            fail: function (failData) {
              console.info("用户拒绝登录");
            }
          })
        }
      }
    })
  },
})
