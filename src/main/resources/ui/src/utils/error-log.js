import Vue from 'vue'
import store from '@/store'
import { isString, isArray } from '@/utils/validate'
import settings from '@/settings'
// import ErrorParser from 'error-stack-parser'

// you can set in settings.js
// errorLog:'production' | ['production', 'development']
const { errorLog: needErrorLog } = settings

function checkNeed() {
  const env = process.env.NODE_ENV
  if (isString(needErrorLog)) {
    return env === needErrorLog
  }
  if (isArray(needErrorLog)) {
    return needErrorLog.includes(env)
  }
  return false
}
// const errorFn = console.error
// console.error = function () {
//   let args =[].slice.call(arguments);
//   // 中间可以做一些事情
//   errorFn.apply(this , args);
// }

if (checkNeed()) {
  Vue.config.errorHandler = function(err, vm, info) {
  // Don't ask me why I use Vue.nextTick, it just a hack.
  // detail see https://forum.vuejs.org/t/dispatch-in-vue-config-errorhandler-has-some-problem/23500
    const username = store.getters.name
    Vue.nextTick(() => {
      store.dispatch('errorLog/addErrorLog', {
        username,
        stack: err.stack,
        message: err.message,
        err,
        vm,
        info,
        url: window.location.href
      })
    })
    console.error(err)
    // console.log(ErrorParser.parse(err))
  }
}
