import request from '@/utils/request'

/**
 * 查询职位列表
 */
export function getPositions() {
  return request({
    url: '/sysPosition/list',
    method: 'get'
  })
}

/**
 * 新增职位
 * @param data
 */
export function addPosition(data) {
  return request({
    url: '/sysPosition/save',
    method: 'post',
    data
  })
}

/**
 * 更新职位
 * @param data
 */
export function updatePosition(data) {
  return request({
    url: '/sysPosition/save',
    method: 'post',
    data
  })
}

/**
 * 删除一个职位
 * @param id
 */
export function delPosition(id) {
  return request({
    url: `/sysPosition/delete/${id}`,
    method: 'post'
  })
}
