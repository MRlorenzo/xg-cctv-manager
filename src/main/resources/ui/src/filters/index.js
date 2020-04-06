// import parseTime, formatTime and set to filter
export { parseTime, formatTime } from '@/utils'
import moment from 'moment'
/**
 * Show plural label if time is plural number
 * @param {number} time
 * @param {string} label
 * @return {string}
 */
function pluralize(time, label) {
  if (time === 1) {
    return time + label
  }
  return time + label + 's'
}

/**
 * @param {number} time
 */
export function timeAgo(time) {
  const between = Date.now() / 1000 - Number(time)
  if (between < 3600) {
    return pluralize(~~(between / 60), ' minute')
  } else if (between < 86400) {
    return pluralize(~~(between / 3600), ' hour')
  } else {
    return pluralize(~~(between / 86400), ' day')
  }
}

/**
 * Number formatting
 * like 10000 => 10k
 * @param {number} num
 * @param {number} digits
 */
export function numberFormatter(num, digits) {
  const si = [
    { value: 1E18, symbol: 'E' },
    { value: 1E15, symbol: 'P' },
    { value: 1E12, symbol: 'T' },
    { value: 1E9, symbol: 'G' },
    { value: 1E6, symbol: 'M' },
    { value: 1E3, symbol: 'k' }
  ]
  for (let i = 0; i < si.length; i++) {
    if (num >= si[i].value) {
      return (num / si[i].value).toFixed(digits).replace(/\.0+$|(\.[0-9]*[1-9])0+$/, '$1') + si[i].symbol
    }
  }
  return num.toString()
}

/**
 * 10000 => "10,000"
 * @param {number} num
 */
export function toThousandFilter(num) {
  return (+num || 0).toString().replace(/^-?\d+/g, m => m.replace(/(?=(?!\b)(\d{3})+$)/g, ','))
}

/**
 * Upper case first char
 * @param {String} string
 */
export function uppercaseFirst(string) {
  return string.charAt(0).toUpperCase() + string.slice(1)
}

/**
 * 將部門字段解析成可識別的值
 * @param department
 * @returns {*}
 */
export function departmentText(department) {
  if (department) {
    return department.departmentCode
  } else {
    return department
  }
}

/**
 * 將角职位段解析成可識別的值
 * @param position
 * @returns {*}
 */
export function positionText(position) {
  if (position) {
    return position.name
  } else {
    return position
  }
}

/**
 * 將角色字段解析成可識別的值
 * @param roles
 * @returns {*}
 */
export function rolesText(roles) {
  if (Array.isArray(roles)) {
    return Object.values(roles).map(role => role.name).join(',')
  } else {
    return roles
  }
}

/**
 * 將职位集合字段解析成可識別的值
 * @param staffs
 * @returns {*}
 */
export function staffsText(staffs) {
  if (Array.isArray(staffs)) {
    return Object.values(staffs).map(staff => staff.staffName).join(',')
  } else {
    return staffs
  }
}

export function dateTimeFilter(string) {
  return moment(string).format('YYYY-MM-DD')
}
