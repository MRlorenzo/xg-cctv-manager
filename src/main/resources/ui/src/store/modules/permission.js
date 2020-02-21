import { asyncRoutes, constantRoutes } from '@/router'

/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
// 是否存在服务器加载的路由
function has(   remoteRoutes , route ) {
  let entity

  for (const remote of remoteRoutes){
    if(remote.path === route.path){
      entity = {... remote}
      break
    }
  }

  return entity
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param remoteRoutes
 */
function filterAsyncRoutes(routes = [], remoteRoutes) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    const remote = has( remoteRoutes , tmp  )
    if ( remote ) {
      const remoteChildren = remoteRoutes.filter(r => r.pid === remote.id)
      if (remoteChildren.length > 0) {

        tmp.children = filterAsyncRoutes( tmp.children , remoteRoutes)
      }
      res.push(tmp)
    }
  })

  return res
}


const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, remoteRoutes) {
    return new Promise(resolve => {
      let accessedRoutes= filterAsyncRoutes(asyncRoutes, remoteRoutes)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
