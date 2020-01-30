import request from '@/utils/request'

export function saveHighAction( data ) {
  return request({
    url: '/highAction/save',
    method: 'post',
    data
  })
}

export function deleteHighAction( id ) {
  return request({
    url: '/highAction/delete/'+ id,
    method: 'post'
  })
}

export function getHighActionsForPage( data ) {
  return request({
    url: '/highAction/page',
    method: 'get',
    params: data
  })
}

export function updateHighAction( data ) {
  return request({
    url: '/highAction/save',
    method: 'post',
    data
  })
}
