import request from '@/utils/request'

export function saveTtitleMain( data ) {
  return request({
    url: '/titleMain/save',
    method: 'post',
    data
  })
}

export function deleteTtitleMainById( id ) {
  return request({
    url: '/titleMain/delete/' + id,
    method: 'post'
  })
}

export function getTtitleMainForPage( data ) {
  return request({
    url: '/titleMain/page',
    method: 'get',
    params: data
  })
}

export function updateTtitleMain( data ) {
  return request({
    url: '/titleMain/save',
    method: 'post',
    data
  })
}
