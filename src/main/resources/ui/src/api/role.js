import request from '@/utils/request'

export function getRoutes() {
  return request({
    url: '/sysPermission/routes',
    method: 'get'
  })
}

export function getRoutesByRoleId( roleId ) {
  return request({
    url: '/sysPermission/find/roleId/' + roleId,
    method: 'get'
  })
}

export function getRoles() {
  return request({
    url: '/sysRole/list',
    method: 'get'
  })
}

export function addRole(data) {
  return request({
    url: '/sysRole/save',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: `/sysRole/save`,
    method: 'post',
    data
  })
}

export function deleteRole(id) {
  return request({
    url: `/sysRole/delete/${id}`,
    method: 'post'
  })
}
