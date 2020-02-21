import serverUsers from '../../static_data/server_users'
import { deepClone } from '../../../src/utils/index'

export default [
  // user login
  {
    url: '/sysUser/login',
    type: 'post',
    response: config => {
      const token = 'this is token'

      // mock error
      if (!token) {
        return {
          code: 60204,
          message: 'Account and password are incorrect.'
        }
      }

      return {
        code: 0,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/sysUser/info',
    type: 'get',
    response: config => {

      // 假设我们登陆的账号是admin
      const users = deepClone(serverUsers)
      let [info] = users
      // mock error
      if (!info) {
        return {
          code: 50008,
          msg: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 0,
        data:{
          ...info
        }
      }
    }
  },

  {
    url: '/sysUser/page',
    type: 'get',
    response: config => {
      let {current , size} = config.query
      current = current || 1
      size = size || 10

      let offset = (current - 1) * size
      let end = offset + size
      let pages = Math.ceil(serverUsers.length / size)
      let records = serverUsers.filter((item , index) => index >= offset && index < end)
      return {
        code: 0,
        data: {
          "records": records ,
          "total": serverUsers.length,
          "size": 10,
          "current": 1,
          "searchCount": true,
          "pages": pages
        }
      }
    }
  },
  {
    url: '/sysUser/save',
    type: 'post',
    response: {
      code: 0
    }
  },

  // user logout
  {
    url: '/sysUser/logout',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: 'success'
      }
    }
  },
  {
    url: '/sysUser/find/[A-Za-z0-9]',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        data: serverUsers
      }
    }
  } ,
  {
    url: '/sysUser/like/[A-Za-z0-9]',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        data: serverUsers
      }
    }
  }
]
