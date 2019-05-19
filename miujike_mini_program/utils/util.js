const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

const judgeTime = data =>{
  let date = data.toString();
  let year = date.substring(0, 4);
  let month = date.substring(4, 6);
  let day = date.substring(6, 8);
  let d1 = new Date(year + '/' + month + '/' + day);
  let dd = new Date();
  let y = dd.getFullYear();
  let m = dd.getMonth() + 1;
  let d = dd.getDate();
  let d2 = new Date(y + '/' + m + '/' + d);
  let iday = parseInt(d2 - d1) / 1000 / 60 / 60 / 24;
  return iday;
}

module.exports = {
  formatTime: formatTime,
  judgeTime: judgeTime
}
