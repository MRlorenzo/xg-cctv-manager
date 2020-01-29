import request from '@/utils/request'

/**
 * 查询部门列表
 */
export function getDepartments() {
  return request({
    url: '/sysDepartment/list',
    method: 'get'
  })
}

/**
 * 新增部门
 * @param data
 */
export function addDepartment( data ){
  return request({
    url: '/sysDepartment/save',
    method: 'post',
    data
  })
}

/**
 * 更新部门
 * @param data
 */
export function updateDepartment( data ) {
  return request({
    url: '/sysDepartment/save',
    method: 'post',
    data
  })
}

/**
 * 删除一个部门
 * @param id
 */
export function delDepartment( id ) {
  return request({
    url: `/sysDepartment/delete/${id}`,
    method: 'get'
  })
}
