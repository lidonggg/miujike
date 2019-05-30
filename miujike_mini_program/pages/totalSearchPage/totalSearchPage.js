const app = getApp();
const api = require("../../utils/httpRequest.js");

// pages/totalSearchPage/totalSearchPage.js
Page({

  /**
   * Page initial data
   */
  data: {
    musicList: [],
    videoList: [],
    keyword: "",
    videoTipShow: false,
    musictipShow: false,
    worksTip: ""
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    if (options.keyword) {
      this.setData({
        keyword: options.keyword,
      })
    }
    this.fetchSearch(0);
  },
  fetchSearch(lastId) {
    if (this.data.keyword.length == 0) {
      wx.showToast({
        title: '缺少关键词',
        icon: "none"
      })
    } else {
      let keyword = this.data.keyword;
      let target = this.data.target;
      let that = this;
      that.setData({
        keyword: that.data.keyword
      })
      api.fetch({
        url: "apigateway-works/api/v1/works/both/searchByKeyword",
        data: {
          keyword: keyword,
          lastId: lastId
        },
        method: "post"
      }).then(res => {
        wx.stopPullDownRefresh();
        if (lastId == 0) {
          that.data.resultList = [];
        }
        console.log(res.data);
        that.setData({
          videoList: res.data.data.videoList,
          musicList: res.data.data.musicList
        });
        if (res.data.data.musicList.length == 0 && res.data.data.videoList == 0) {
          that.setData({
            worksTip: "暂时没有所要查找的作品哦～"
          })
        } else {
          that.setData({
            worksTip: "目前就只有这么多了哦～"
          })
        }
        that.setData({
          videoTipShow: true
        })
      });
    }
  },

  doSearch(e) {
    this.fetchSearch(0);
  },

  doSearchInput(e) {
    this.data.keyword = e.detail.value;
    // if (e.detail.value.length > 2){
    //   this.fetchSearch(0);
    // }
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
    this.fetchSearch(0)
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