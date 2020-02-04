import request from '@/utils/request'

export const ossURL = '/sysOss/upload'

export function uploadOne(file) {
  return request({
    url: '/sysOss/upload',
    method: 'post',
    data: { file }
  })
}

