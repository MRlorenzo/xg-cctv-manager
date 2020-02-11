import request from '@/utils/request'

export function saveException( data ) {
  return request({
    url: '/jsException/save',
    method: 'post',
    data
  })
}
