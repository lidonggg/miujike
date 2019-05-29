const api = require("../../utils/httpRequest.js")
const app = getApp()

Component({
  behaviors: [],

  properties: {
  },
  data: {
    coverImgPath: "../../resources/imgs/worksUploader/add_cover.png",
    coverHeight: 310,
    coverWidth: 310
  },

  lifetimes: {
    // 生命周期函数，可以为函数，或一个在methods段中定义的方法名
    attached: function () { },
    moved: function () { },
    detached: function () { },
  },

  // 生命周期函数，可以为函数，或一个在methods段中定义的方法名
  attached: function () { }, // 此处attached的声明会被lifetimes字段中的声明覆盖
  ready: function () { },

  pageLifetimes: {
    // 组件所在页面的生命周期函数
    show: function () { },
  },

  methods: {
    doUpload() {
      let that = this;
      wx.chooseImage({
        count: 1,
        success: function (res) {
          wx.uploadFile({
            url: app.globalData.requestUrl + '/apigateway-works/api/v1/upload/uploadInMini',
            filePath: res.tempFilePaths[0],
            name: 'file',
            success(result) {
              console.log(JSON.parse(result.data).data);
              wx.showToast({
                title: '上传成功',
                duration: 1500,
                mask: false
              })
              wx.getImageInfo({
                src: res.tempFilePaths[0],
                success(r) {
                  let height = r.height;
                  let width = r.width;
                  that.setData({
                    coverWidth: (that.data.coverHeight / height) * width,
                    coverImgPath: res.tempFilePaths[0]
                  })
                }
              })
              that.triggerEvent('addcover', JSON.parse(result.data).data, { bubbles: false });
            }
          })
        },
      })
    },
  }
})