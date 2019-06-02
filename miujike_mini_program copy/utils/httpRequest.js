const app = getApp();

// 定义网络请求API地址
const baseUrl = app.globalData.requestUrl + "/";
// 封装网络请求开始
const http = ({
  url,
  data,
  method,
  showLoading,
  success,
  ...other
} = {}) => {
  var requestUrl = baseUrl + url;
  if (data === undefined) {
    data = {};
  }
  if (app.globalData.userInfo.userId){
    data.userId = app.globalData.userInfo.userId;
  }
  if (showLoading) {
    // 添加请求加载等待
    wx.showLoading({
      title: '加载中...'
    })
  }

  // Promise封装处理
  return new Promise((resolve, reject) => {
    wx.request({
      // 请求地址拼接
      url: requestUrl,
      data: data,
      // 获取请求头配置
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: method,
      ...other,
      complete: (res) => {
        // 关闭等待
        wx.hideLoading();
        resolve(res);
      },
      success: (res) => {
        if (success) {
          success(res);
        }
      },
      fail: () => {
        wx.hideLoading()
        wx.showModal({
          title: '提示',
          content: '您的网络不太给力哦',
          showCancel: false
        })
      }
    })
  })
}
// 添加请求toast提示
const showToast = title => {
  wx.showToast({
    title: title,
    icon: 'none',
    duration: 1500,
    mask: true
  });
}

// 进行url字符串拼接
const getUrl = url => {
  if (url.indexOf('://') == -1) {
    url = baseURL + url
  }
  return url
}

const getUserInfo = function (success) {
  fetch({
    url: '/api/user/getUserInfo',
    success: function (res) {
      if (res.statusCode === 200) {
        app.globalData.userInfo = res.data;
      }
      success();
    }
  })
}

const fetch = (content) => {
  var method;
  if (content.method) {
    method = content.method
  } else {
    method = "get"
  }
  return http({
    url: content.url,
    data: content.data,
    method: method,
    showLoading: content.showLoading,
    success: content.success
  })
}
module.exports = {
  baseUrl,
  fetch,
  showToast,
  getUserInfo
}