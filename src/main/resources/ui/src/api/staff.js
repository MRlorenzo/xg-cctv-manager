import request from '@/utils/request'

// 获取员工分页数据
export function getDataPage(data) {
  return request({
    url: '/sysStaff/page',
    method: 'get',
    params: data
  })
}

export function delStaff(id) {
  return request({
    url: `/sysStaff/delete/${id}`,
    method: 'post'
  })
}

export function updateStaff(data) {
  return request({
    url: '/sysStaff/save',
    method: 'post',
    data
  })
}

export function addStaff(data) {
  return request({
    url: '/sysStaff/save',
    method: 'post',
    data
  })
}

export function findStaffLikeName(name) {
  return request({
    url: '/sysStaff/like/' + name,
    method: 'get'
  })
}
