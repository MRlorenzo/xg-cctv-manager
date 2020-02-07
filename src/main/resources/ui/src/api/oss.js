import request from '@/utils/request'
import bitRequest from '@/utils/bitRequest'
import { h5elementDownload } from '@/utils'
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

export function test() {
  return request({
    url: '/sysOss/test',
    method: 'get'
  })
}

export function downloadExcelByKey( key ) {
  let url = process.env.VUE_APP_BASE_API + '/sysOss/download/excel/' + key

  h5elementDownload(url)
}

export async function downloadFileByKey( key , filename) {
  const out = await bitRequest({
    url: '/sysOss/download/file/' + key,
    method: 'get'
  })
  // new Blob([data])用来创建URL的file对象或者blob对象
  let url = window.URL.createObjectURL(new Blob([out]))
  h5elementDownload(url , filename)
}
