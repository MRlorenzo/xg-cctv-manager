import request from '@/utils/request'

/*
* 获取菜单的分页数据
* */
export function getDataPage( data ){
  return request({
    url: '/sysPermission/page',
    method: 'get',
    params: data
  })
}

/**
 * 根据pid查询路由列表
 * @param pid
 */
export function getRoutesByPid( pid ) {
  return request({
    url: '/sysPermission/find/pid/' + pid,
    method: 'get'
  })
}

/*
* 获取根菜单的列表
* */
export function getRootMenuList(){
  return request({
    url: '/sysPermission/root',
    method: 'get'
  })
}

/**
 * 获取服务器上存储的所有菜单
 */
export function getMenuList(){
  return request({
    url: '/sysPermission/routes',
    method: 'get'
  })
}

/*
* 添加菜单
* */
export function addMenu( data ){
  return request({
    url: '/sysPermission/save',
    method: 'post',
    data
  })
}

/*
* 更新菜单
* */
export function updateMenu( data ){
  return request({
    url: '/sysPermission/save',
    method: 'post',
    data
  })
}

/*
* 根据id删除菜单
* */
export function delMenu( id ){
  return request({
    url: `/sysPermission/delete/${id}`,
    method: 'post'
  })
}
