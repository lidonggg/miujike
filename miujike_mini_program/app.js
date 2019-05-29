//app.js
App({
  onLaunch: function(options) {
    console.log("options.path:",options.path);
    let path = options.path;
    let paths = path.split("/")
    let tpath = encodeURIComponent(options.path);

    // for (let r = 1; r < paths.length; r++) {
    //   tpath += "%" + paths[r];
    // }
    console.log("tpath:" + tpath);
    let opt = "";
    let that = this;
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          if (wx.getStorageSync(that.globalData.tapp)) {
            that.globalData.userInfo = wx.getStorageSync(that.globalData.tapp);
            console.log("store:" + that.globalData.userInfo.userId)
            that.globalData.load = 1
            // }
          } else {
            that.doLogin(opt, path);
          }
        } else {
          console.log("未授权");
          wx.reLaunch({
            url: '../../pages/authorize/authorize?tpath=' + tpath ,
          })
          that.globalData.load = 1
        }
        wx.hideLoading();
      }
    })
  },
  doLogin: function (opt, path) {
    let that = this;
    wx.login({
      success: res => {
        var code = res.code;
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          console.log("得到了code")
          wx.getUserInfo({
            success: function (result) {
              console.log("得到了用户信息")
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
                  console.log(res.data.data)
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
    qiniuDomain:"http://ps8xnh0n1.bkt.clouddn.com/",
    mediaPlay: {}
  }
})