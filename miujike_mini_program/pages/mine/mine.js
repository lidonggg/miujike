const app = getApp()
const api = require("../../utils/httpRequest.js")
const util = require("../../utils/util.js")

// pages/mine/mine.js
Page({

  /**
   * Page initial data
   */
  data: {
    signShow: false,
    haveSigned: false,
    userInfo: {},
    signing: false,
    fans:0,
    follows:0,
    menuList: [{
        name: "获取鸡蛋",
        path: "pages/getEggs/getEggs",
        img: "resources/imgs/mine/get_eggs.png"
      },
      {
        name: "我的作品",
        path: "pages/myWorks/myWorks",
        img: "resources/imgs/mine/my_works.png"
      }, {
        name: "我的消息",
        path: "pages/myNotifications/myNotifications",
        img: "resources/imgs/mine/my_notifications.png"
      }, {
        name: "我的喜欢",
        path: "pages/myLikes/myLikes",
        img: "resources/imgs/mine/my_likes.png"
      }
    ]
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    this.setData({
      userInfo: app.globalData.userInfo
    })
    this.getUserInfo(true);
  },

  getUserInfo(showLoading){
    let that = this;
    api.fetch({
      url: "apigateway-user/api/v1/user/info/" + app.globalData.userInfo.userId,
      showLoading: showLoading
    }).then(res => {
      wx.stopPullDownRefresh();
      let resInfo = res.data.data;
      if (res.data.code == 200 && resInfo.userId) {
        that.setData({
          userInfo: resInfo
        })
        let haveSigned = false;
        if (resInfo.lastSignTime) {
          console.log(resInfo.lastSignTime)
          if (util.judgeTime(resInfo.lastSignTime.replace(/-|T|:/g, '')) == 0) {
            console.log("签到过了")
            haveSigned = true
          }
        }
        that.setData({
          signShow: true,
          haveSigned: haveSigned
        });
      }
    });
  },

  init(showLoading) {
    let that = this;
    
    api.fetch({
      url: "apigateway-user/api/v1/user/fan/number/" + app.globalData.userInfo.userId
    }).then(res => {
      wx.stopPullDownRefresh();
      that.setData({
        fans: res.data.data
      })
    });
    api.fetch({
      url: "apigateway-user/api/v1/user/follow/number/" + app.globalData.userInfo.userId
    }).then(res => {
      wx.stopPullDownRefresh();
      that.setData({
        follows: res.data.data
      })
    });
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
    this.getUserInfo(false);
    this.init();
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
    this.init();
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
   * 点击领取鸡蛋
   */
  getEggs() {
    console.log("签到");
    if (!this.data.signing) {
      let that = this;
      this.data.signing = true;
      api.fetch({
        url: "apigateway-user/api/v1/user/sign/" + that.data.userInfo.userId
      }).then(res => {
        console.log(res.data);
        if (res.data.code == 200) {
          wx.showToast({
            title: "鸡蛋 +" + res.data.data.toString()
          });
          let cKey = 'userInfo.eggs';
          that.setData({
            ['userInfo.eggs']: parseInt(that.data.userInfo.eggs) + res.data.data
          })
        } else if (res.data.code == -1) {
          wx.showToast({
            title: "请不要重复签到",
          });
        }
        that.setData({
          haveSigned: true,
          signing: false,
        })
      })
    }
  },

  /**
   * 选项条
   */
  getIn(e) {
    console.log(e.currentTarget.dataset.path);
    wx.navigateTo({
      url: '../../' + e.currentTarget.dataset.path,
    })
  },
  goMyFollowsPage() {
    wx.navigateTo({
      url: '../../pages/myFollows/myFollows',
    })
  },
  goMyFollowPage() {
    wx.navigateTo({
      url: '../../pages/myFollows/myFollows',
    })
  }
})