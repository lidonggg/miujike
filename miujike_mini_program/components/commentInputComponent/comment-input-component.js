const api = require("../../utils/httpRequest.js")
const app = getApp()

Component({
  behaviors: [],

  properties: {
    targetId: String,
    targetType: Number
  },
  data: {
    commentValue: ""
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
    //输入聚焦
    foucus(e) {
      var that = this;
      that.setData({
        bottom: e.detail.height
      })
    },
    //失去聚焦
    blur(e) {
      var that = this;
      that.setData({
        bottom: 0
      })
    },
    doComment(e) {
      let that = this;
      that.setData({
        commentValue: ""
      })
      api.fetch({
        url: "apigateway-behavior/api/v1/behavior/comment/addComment",
        method:"post",
        data: {
          fromUserId: app.globalData.userInfo.userId,
          targetType: that.data.targetType,
          content: e.detail.value,
          targetId: that.data.targetId
        }
      }).then(res => {
        if(res.data.code == 200){
          that.triggerEvent('addcomment', res.data.data, { bubbles: false });
        }
      })
    }
  }
})