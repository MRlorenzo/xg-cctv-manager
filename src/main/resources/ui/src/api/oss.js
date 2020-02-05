import request from '@/utils/request'

export const ossURL = '/sysOss/upload'

export function uploadFile(formData , progressFn) {
  return request({
    url: '/sysOss/upload',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    onUploadProgress: progressFn,
    data: formData
  })
}

