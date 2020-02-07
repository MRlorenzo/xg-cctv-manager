import axios from 'axios'
// create an axios instance
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 50000, // request timeout
  // `responseType` 表示服务器响应的数据类型，
  // 可以是 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
  responseType: 'blob'
})


export default service
