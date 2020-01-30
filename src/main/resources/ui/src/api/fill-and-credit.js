import request from '@/utils/request'

export function saveFillAndCredit( data ) {
  return request({
    url: '/fillCreditLog/save',
    method: 'post',
    data
  })
}

export function deleteFillAndCreditById( id ) {
  return request({
    url: '/fillCreditLog/delete/' + id,
    method: 'post'
  })
}

export function getFillAndCreditsForPage( data ) {
  return request({
    url: '/fillCreditLog/page',
    method: 'get',
    params: data
  })
}

export function updateFillAndCredit( data ) {
  return request({
    url: '/fillCreditLog/save',
    method: 'post',
    data
  })
}
