import request from '@/utils/request'

export function saveDailyLog( data ) {
  return request({
    url: '/dailyLog/save',
    method: 'post',
    data
  })
}

export function deleteFormDailyLogById( id ) {
  return request({
    url: '/dailyLog/delete/'+ id,
    method: 'post'
  })
}

export function getDailyLogsForPage( data ) {
  return request({
    url: '/dailyLog/page',
    method: 'get',
    params: data
  })
}

export function updateDailyLog( data ) {
  return request({
    url: '/dailyLog/save',
    method: 'post',
    data
  })
}

export function exportDailyLogExcel( data ) {
  return request({
    url: '/dailyLog/excel',
    method: 'get',
    params: data
  })
}
