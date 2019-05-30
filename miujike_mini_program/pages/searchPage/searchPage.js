const app = getApp();
const api = require("../../utils/httpRequest.js");

// pages/searchPage/searchPage.js
Page({

  /**
   * Page initial data
   */
  data: {
    target: "", //1 -- music 2 -- video 0 -- both
    keyword: "",
    resultList: [],
    worksTip: "目前就只有这么多了哦～",
    tipShow: false
  },

  /**
   * Lifecycle function--Called when page load
   */
  onLoad: function(options) {
    if (options.keyword) {
      this.setData({
        keyword: options.keyword,
        target: options.target
      })
    }
    this.fetchSearch(0);
  },

  fetchSearch(lastId) {
    let keyword = this.data.keyword;
    let target = this.data.target;
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/" + target + "/search",
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
        resultList: that.data.resultList.concat(res.data.data)
      });
      if (res.data.data.length == 0) {
        that.setData({
          worksTip: "暂时没有所要查找的作品哦～"
        })
      } else {
        worksTip: "目前就只有这么多了哦～"
      }
      that.setData({
        tipShow: true,
        keyword: that.data.keyword
      })
    });
  },

  doSearch(e) {
    this.fetchSearch(0);
  },

  doInput(e) {
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