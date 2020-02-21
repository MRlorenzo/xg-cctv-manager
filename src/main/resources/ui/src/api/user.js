import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/sysUser/login',
    method: 'post',
    data
  })
}

export function getInfo( ) {
  return request({
    url: '/sysUser/info',
    method: 'get'
  })
}

//获取用户分页数据
export function getDataPage( data ){
  return request({
    url: '/sysUser/page',
    method: 'get',
    params: data
  })
}

export function delUser( id ){
  return request({
    url: `/sysUser/delete/${id}`,
    method: 'post'
  })
}

export function updateUser( data ){
  return request({
    url: '/sysUser/save',
    method: 'post',
    data
  })
}

export function addUser( data ){
  return request({
    url: '/sysUser/save',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/sysUser/logout',
    method: 'get'
  })
}

export function findUserByName( name ){
  return request({
    url: '/sysUser/find/' + name,
    method: 'get'
  })
}

export function findUserLikeName( name ) {
  return request({
    url: '/sysUser/like/' + name,
    method: 'get'
  })
}
