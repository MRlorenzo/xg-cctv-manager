import request from '@/utils/request'

export function saveTitleListItem( data ) {
  return request({
    url: '/titleListItem/save',
    method: 'post',
    data
  })
}

export function deleteTtitleListItemById( id ) {
  return request({
    url: '/titleListItem/delete/' + id,
    method: 'post'
  })
}

export function getTtitleListItemForPage( data ) {
  return request({
    url: '/titleListItem/page',
    method: 'get',
    params: data
  })
}

export function updateTtitleListItem( data ) {
  return request({
    url: '/titleListItem/save',
    method: 'post',
    data
  })
}
