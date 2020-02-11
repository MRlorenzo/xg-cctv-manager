import {saveException} from "@/api/js-exception"
const state = {
  logs: []
}

const mutations = {
  ADD_ERROR_LOG: (state, log) => {
    state.logs.push(log)
  },
  CLEAR_ERROR_LOG: (state) => {
    state.logs.splice(0)
  }
}

const actions = {
  addErrorLog({ commit }, log) {
    const {  stack , message, info, url , username , err} = log
    let jsException = {
      userName: username,
      url,
      createDate: new Date(),
      stack: stack,
      info,
      message: message
    }
    commit('ADD_ERROR_LOG', log)
    return  new Promise((resolve, reject) => {
      saveException(jsException).then(_ =>{
        resolve()
      }).catch(reject)
    })
  },
  clearErrorLog({ commit }) {
    commit('CLEAR_ERROR_LOG')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
