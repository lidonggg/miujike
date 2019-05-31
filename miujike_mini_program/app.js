//app.js
App({
  onLaunch: function(options) {
    let path = options.path;
    let paths = path.split("/")
    let tpath = encodeURIComponent(options.path);
    let opt = "";
    let that = this;
    if (wx.setInnerAudioOption) {
      console.log("设置全局微信播放声音")
      // 设置全局微信播放声音
      wx.setInnerAudioOption({
        obeyMuteSwitch: false,
        success() {
          console.log("设置全局微信播放声音成功")
        }
      })
    } else {
      // 如果希望用户在最新版本的客户端上体验您的小程序，可以这样子提示
      wx.showModal({
        title: '提示',
        content: '当前微信版本过低，无法使用该功能，请升级到最新微信版本后重试。'
      })
    }
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          if (wx.getStorageSync(that.globalData.tapp)) {
            that.globalData.userInfo = wx.getStorageSync(that.globalData.tapp);
            that.globalData.load = 1
            // }
          } else {
            that.doLogin(opt, path);
          }
        } else {
          wx.reLaunch({
            url: '../../pages/authorize/authorize?tpath=' + tpath ,
          })
          that.globalData.load = 1
        }
        wx.hideLoading();
      }
    })
    console.log("caniuse" + (typeof wx.setInnerAudioOption === 'function'));
  },
  doLogin: function (opt, path) {
    let that = this;
    wx.login({
      success: res => {
        var code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.getUserInfo({
            success: function (result) {
              wx.request({
                url: that.globalData.requestUrl + '/apigateway-user/api/v1/user/login/doLogin',
                method: 'post',
                data: {
                  encryptedData: result.encryptedData,
                  iv: result.iv,
                  code: code,
                  tapp: that.globalData.tapp
                },
                success(res){
                  that.globalData.userInfo = res.data.data;
                  wx: wx.setStorageSync(that.globalData.tapp, res.data.data);
                }
              })
            },
            fail: function (failData) {
              console.info("用户拒绝登录");
            }
          })
        }
      }
    })
  },
  globalData: {
    load: 0,
    userInfo: {},
    requestUrl: "http://192.168.1.5:9000",
    tapp: "miujike-app",
    fetchNum: 15,
    qiniuDomain:"http://file.yfjiaoyu.com/",
    musicPlayList: []
  }
})