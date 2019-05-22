const app = getApp()
const api = require("../../utils/httpRequest.js")

// pages/authorize/authorize.js
Page({

  /**
   * Page initial data
   */
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    wxCode: "",
    btnFontSize: 32,
    setInter: '',
    addSize: true,
    infoImg: "",
    toUserId: "",
    tpath: ""
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function (options) {
    if (options != null) {
      
      let paths = options.tpath.split("%")
      let tpath = paths[0];

      for (let r = 1; r < paths.length; r++) {
        tpath += "/" + paths[r];
      }
      this.setData({
        tpath: tpath
      })
    }
    console.log("tpath" + this.data.tpath);
  },

  onGotUserInfo: function (e) {
    var that = this;
    console.log(e);
    if (e.detail.errMsg == "getUserInfo:ok") {
      if (!wx.getStorageSync(app.globalData.tapp)) {
        console.log("需要登录")
        that.doLogin();
      } else {
        console.log("不需要登录");
        that.setData({
          hasUserInfo: true,
        })
        app.globalData.userInfo = wx.getStorageSync(app.globalData.tapp);
        console.log(app.globalData.userInfo)
        wx.switchTab({
          url: '../../' + that.data.tpath,
        })
      }
    } else {
      console.log("取消授权")
    }
  },
  doLogin: function () {
    let that = this;
    wx.login({
      success: res => {
        let code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function (result) {
              console.log(result)
              api.fetch({
                url: 'apigateway-user/api/v1/user/login/doLogin',
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
                wx: wx.setStorageSync(app.globalData.tapp, res.data.data);
                wx.switchTab({
                  url: '../../' + that.data.tpath,
                })
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

  /**
   * Lifecycle function--Called when page is initially rendered
   */
  onReady: function () {

  },

  /**
   * Lifecycle function--Called when page show
   */
  onShow: function () {

  },

  /**
   * Lifecycle function--Called when page hide
   */
  onHide: function () {

  },

  /**
   * Lifecycle function--Called when page unload
   */
  onUnload: function () {

  },

  /**
   * Page event handler function--Called when user drop down
   */
  onPullDownRefresh: function () {

  },

  /**
   * Called when page reach bottom
   */
  onReachBottom: function () {

  },

  /**
   * Called when user click on the top right corner to share
   */
  onShareAppMessage: function () {

  }
})