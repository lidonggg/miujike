//index.js
//获取应用实例
const app = getApp()
const api = require("../../utils/httpRequest.js")

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }

    console.log(app.globalData.userInfo)

    api.fetch({
      url: "/apigateway-user/api/v1/user/sayhello"
    }).then((res)=>{
      console.log(res.data);
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  doLogin: function () {
    var that = this;
    wx.login({
      success: res => {
        console.log(res.code);
        var code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function (result) {
              console.log(result.encryptedData);
              console.log(result.iv);
              wx.request({
                url: app.globalData.requestUrl + '/api/login/getLogin',
                method: 'post',
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                data: {
                  encryptedData: result.encryptedData,
                  iv: result.iv,
                  code: code,
                  tapp: app.globalData.myApp
                },
                success: function (dataBack) {
                }
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
