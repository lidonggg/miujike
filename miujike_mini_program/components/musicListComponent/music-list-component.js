const api = require("../../utils/httpRequest.js")
const app = getApp()
/**
 * 其实musicId和videoId可以统一命名为id，
 * 这样在搜索的时候会方便很多
 */
Component({
  behaviors: [],

  properties: {
    musicList: { // 属性名
      type: Array, // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: [], // 属性初始值（可选），如果未指定则会根据类型选择一个
      observer: function(newVal, oldVal) {} // 属性被改变时执行的函数（可选），也可以写成在methods段中定义的方法名字符串, 如：'_propertyChange'
    },
    worksTip: String, // 简化的定义方式
    tipShow: Boolean
  },
  data: {
    canThumb: true
  },

  lifetimes: {
    // 生命周期函数，可以为函数，或一个在methods段中定义的方法名
    attached: function() {},
    moved: function() {},
    detached: function() {},
  },

  // 生命周期函数，可以为函数，或一个在methods段中定义的方法名
  attached: function() {}, // 此处attached的声明会被lifetimes字段中的声明覆盖
  ready: function() {},

  pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function() {},
  },

  methods: {
    doShare() {
      
    },
    goPlayPage(e) {
      let that = this;
      let index = e.currentTarget.dataset.index;
      let musicId = e.currentTarget.dataset.musicid;
      app.globalData.musicPlayList = this.data.musicList;
      wx.navigateTo({
        url: '../../pages/musicPlayer/musicPlayer?musicId=' + musicId+"&index="+index,
        success(){
          api.fetch({
            url: "apigateway-works/api/v1/works/music/addPlayTimes",
            data: {
              musicId: musicId
            }
          }).then(res => {
            if (res.data.code == 200) {
              let changeKey = "musicList[" + index + "].playTimes";
              let playTimes = parseInt(that.data.musicList[index].playTimes)
              that.setData({
                [changeKey]: playTimes + 1
              })
            }
          })
        }
      })
    },
    doThumb(e) {
      if (this.data.canThumb) {
        this.data.canThumb = false;
        let musicId = e.currentTarget.dataset.musicid;
        let that = this;
        api.fetch({
          url: "apigateway-behavior/api/v1/behavior/thumb/doThumb",
          method: "post",
          data: {
            fromUserId: app.globalData.userInfo.userId,
            targetType: 1,
            eggs: 1,
            targetId: musicId
          }
        }).then(res => {
          this.data.canThumb = true;
          if (res.data.code == 200) {
            wx.showToast({
              title: '鸡蛋-1',
            })
          }
        })
      } else {
        wx.showToast({
          title: '操作太频繁～',
        })
      }
    }
  }
})