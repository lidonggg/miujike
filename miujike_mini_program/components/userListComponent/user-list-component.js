const app = getApp()
const api = require("../../utils/httpRequest.js")

Component({
  behaviors: [],

  properties: {
    userList: { // 属性名
      type: Array, // 类型（必填），目前接受的类型包括：String, Number, Boolean, Object, Array, null（表示任意类型）
      value: [], // 属性初始值（可选），如果未指定则会根据类型选择一个
      observer: function(newVal, oldVal) {} // 属性被改变时执行的函数（可选），也可以写成在methods段中定义的方法名字符串, 如：'_propertyChange'
    },
    theTip: String, // 简化的定义方式
    tipShow: Boolean,
    fromWhere: String
  },
  data: {

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
    changeRelationship(e) {
      let that = this;
      let index = e.currentTarget.dataset.index;
      let myFollow = this.data.userList[index].myFollow;
      let url = "apigateway-user/api/v1/user/fan/" + (myFollow ? 'unfollow' : 'follow');
      api.fetch({
        url: url,
        data: {
          fromUserId: app.globalData.userInfo.userId,
          toUserId: that.data.userList[index].userId
        }
      }).then(res => {
        if (res.data.data) {
          let theKey = "userList[" + index + "].myFollow";
          that.setData({
            [theKey]: !myFollow
          });
          let toast = !myFollow ? "已关注" : "已取消关注";
          wx.showToast({
            title: toast,
          })
          if (that.data.fromWhere == "follows" && myFollow){
            let userList = that.data.userList;
            userList.splice(index, 1);
            that.setData({
              userList: userList
            });
          } else if (that.data.fromWhere == "fans"){
            let theKey = "userList[" + index + "].fans";
            let newFans = that.data.userList[index].fans + 1;
            if(myFollow){
              newFans = that.data.userList[index].fans - 1
            }
            console.log(newFans)
            that.setData({
              [theKey]: newFans
            });
          }
        }
      })

      console.log(this.data.userList)
    },
    goHisWorksPage(e){
      wx.navigateTo({
        url: '../../pages/hisWorks/hisWorks?userId='+e.currentTarget.dataset.userid,
      })
    }
  }

})