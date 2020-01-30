//incidentLog
import request from '@/utils/request'

export function saveIncidentLog( data ) {
  return request({
    url: '/incidentLog/save',
    method: 'post',
    data
  })
}

export function deleteIncidentLogById( id ) {
  return request({
    url: '/incidentLog/delete/' + id,
    method: 'post'
  })
}

export function getIncidentLogForPage( data ) {
  return request({
    url: '/incidentLog/page',
    method: 'get',
    params: data
  })
}

export function updateIncidentLog( data ) {
  return request({
    url: '/incidentLog/save',
    method: 'post',
    data
  })
}
