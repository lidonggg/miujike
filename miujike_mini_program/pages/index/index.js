//index.js
//获取应用实例
const app = getApp()
const api = require("../../utils/httpRequest.js")

Page({
  data: {
    autoplay:true,
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    curSwiperItemIndex: 0,
    newVideoList: [],
    videoList: [],
    worksTip:"已经划到底了哦~",
    tipShow:false
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
  onShow(){
    this.setData({
      autoplay: true
    })
  },
  /**
   * 获取新视频
   */
  fetchNewVideos(){
    let that = this;
    api.fetch({
      url: "apigateway-works/api/v1/works/video/new"
    }).then(res => {
      wx.stopPullDownRefresh();
      if (res.data.data && res.data.data.length > 0){
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
      url:"apigateway-works/api/v1/works/video/list/0",
      data:{
        lastId:lastId
      }
    }).then(res => {
      console.log(res.data);
      if(res.data.code == 200){
        that.setData({
          videoList: res.data.data
        });
        if(res.data.data.length < app.globalData.fetchNum){
          that.setData({
            tipShow:true
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
  },
  onUnload(){
    this.setData({
      autoplay:false
    })
  },
  onReachBottom(){
    console.log("dodile");
    if(!this.data.tipShow){
      this.fetchVideos(this.data.videoList[this.data.videoList.length].videoId)
    }
  }
})