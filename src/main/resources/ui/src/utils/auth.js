// import Cookies from 'js-cookie'
const storage = window.sessionStorage
// 修改存储介质
const Cookies = {
  get(key) {
    return storage.getItem(key)
  },
  set(key, value) {
    storage.setItem(key, value)
  },
  remove(key) {
    storage.removeItem(key)
  }
}

const TokenKey = 'sid'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
