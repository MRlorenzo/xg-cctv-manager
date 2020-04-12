
Date.prototype.Format = function(fmt) { // author: hh
  const o = {
    'M+': this.getMonth() + 1, // 月份
    'd+': this.getDate(), // 日
    'h+': this.getHours(), // 小时
    'm+': this.getMinutes(), // 分
    's+': this.getSeconds(), // 秒
    'q+': Math.floor((this.getMonth() + 3) / 3), // 季度
    'S': this.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length))
  for (const k in o) { if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length))) }
  return fmt
}

function getUTCTime(offset, s) {
  const d = new Date(s) // 创建一个Date对象
  const localTime = d.getTime()
  const localOffset = d.getTimezoneOffset() * 60000 // 获得当地时间偏移的毫秒数
  const utc = localTime + localOffset // utc即GMT时间

  const utcTime = utc + (3600000 * offset)
  return new Date(utcTime)
}

export function dateFormat(fmt) {
  return function(dateTime) {
    return dateTime ? dateTime && (getUTCTime(7, dateTime).Format(fmt)) || 0 : '-'
  }
}

// 字符串转date对象   yyyy-mm-dd HH:MM:SS
export function stringToDateTime(dateString) {
  dateString = dateString.replace(/-/g, '/')
  return new Date(dateString)
}

// 获取系统时间
export function getDateTimeBySystem() {
  return new Date()
}

// 今天
export function getCurrentDay() {
  return dateFormat('yyyy-MM-dd')(getDateTimeBySystem())
}

export function getCurrentTime() {
  return dateFormat('hh:mm:ss')(getDateTimeBySystem())
}
