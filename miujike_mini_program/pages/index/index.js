//index.js
//获取应用实例
const app = getApp()
const api = require("../../utils/httpRequest.js")

Page({
  data: {
    autoplay: true,
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    curSwiperItemIndex: 0,
    newVideoList: [],
    videoList: [],
    worksTip: "已经划到底了哦~",
    tipShow: false,
    keyword: ""
  },
  onPullDownRefresh() {
    this.fetchNewVideos();
    this.fetchVideos(0);
  },
  onSwiper(e) {
    let curIndex = e.detail.current;
    this.setData({
      curSwiperItemIndex: curIndex
    })
  },
  onLoad: function() {
    this.fetchNewVideos();
    this.fetchVideos(0);
  },
  onShow() {
    this.setData({
      autoplay: true
    })
  },
  /**
   * 获取官方视频
   */
  fetchNewVideos() {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/new",
      data: {
        num: 5
      }
    }).then(res => {
      wx.stopPullDownRefresh();
      if (res.data.data && res.data.data.length > 0) {
        that.setData({
          newVideoList: res.data.data
        })
      }
    })
  },
  /**
   * 拉取音乐
   */
  fetchVideos(lastId) {
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/list/0",
      data: {
        lastId: lastId
      }
    }).then(res => {
      console.log(res.data);
      if (lastId == 0) {
        that.data.videoList = [];
      }
      if (res.data.code == 200) {
        that.setData({
          videoList: that.data.videoList.concat(res.data.data)
        });
        if (res.data.data.length < app.globalData.fetchNum) {
          that.setData({
            tipShow: true
          })
        }
      }
    })
  },
  goPlayPage(e) {
    let that = this;
    let videoId = e.currentTarget.dataset.videoid;
    let index = e.currentTarget.dataset.index;
    app.globalData.mediaPlay = this.data.newVideoList[index];
    wx.navigateTo({
      url: '../../pages/videoPlayer/videoPlayer?videoId=' + that.data.newVideoList[index].videoId,
      success() {
        api.fetch({
          url: "apigateway-works/api/v1/works/video/addPlayTimes",
          data: {
            videoId: videoId
          }
        }).then(res => {
          /**if (res.data.code == 200) {
            let changeKey = "videoList[" + index + "].playTimes";
            let playTimes = parseInt(that.data.videoList[index].playTimes)
            that.setData({
              [changeKey]: playTimes + 1
            })
          }*/
        })
      }
    })
  },
  doSearch() {
    let that = this;
    wx.navigateTo({
      url: '../../pages/searchPage/searchPage?target=video&keyword=' + that.data.keyword,
    })
  },
  doSearchInput(e) {
    this.data.keyword = e.detail.value;
  },
  onUnload() {
    this.setData({
      autoplay: false
    })
  },
  onReachBottom() {
    if (!this.data.tipShow) {
      this.fetchVideos(this.data.videoList[this.data.videoList.length].videoId)
    }
  },
  onShareAppMessage: function(res) {
    let videoId = res.target.dataset.videoid;
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
      return {
        title: '分享',
        path: '/pages/videoPlayer/videoPlayer?videoId=' + videoId,
        imageUrl: '', //用户分享出去的自定义图片大小为5:4,
        // 此回调不会被执行
        success: function(res) {
          // 转发成功
          api.fetch({
            url: "apigateway-behavior/api/v1/behavior/share/doShare",
            method: "post",
            data: {
              fromUserId: app.globalData.userInfo.userId,
              targetType: 2,
              eggs: 1,
              targetId: videoId
            }
          }).then(data => {
            if (data.data.code == 200) {
              wx.showToast({
                title: '鸡蛋+1',
                icon: 'success',
                duration: 2000
              })
            }
          })
        },
        fail: function(res) {
          // 分享失败
        },
      }
    }
  },

})