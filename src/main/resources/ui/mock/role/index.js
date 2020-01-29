import Mock from 'mockjs'
import { deepClone } from '../../src/utils/index.js'
import { asyncRoutes, constantRoutes } from '../static_data/routes.js'
import db_roles from '../static_data/roles'
const routes = deepClone([...constantRoutes, ...asyncRoutes])

export default [
  // mock get all routes form server
  {
    url: '/sysPermission/routes',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: asyncRoutes
      }
    }
  },
  {
    url: '/sysPermission/find/roleId/[A-Za-z0-9]',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        data: asyncRoutes
      }
    }
  },

  // mock get all roles form server
  {
    url: '/sysRole/list',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: db_roles
      }
    }
  },

  // add role
  {
    url: '/sysRole/save',
    type: 'post',
    response: {
      code: 0,
      data: {
        key: Mock.mock('@integer(300, 5000)')
      }
    }
  },

  // update role
  {
    url: '/sysRole/save',
    type: 'post',
    response: {
      code: 0,
      data: {
        status: 'success'
      }
    }
  },

  // delete role
  {
    url: '/sysRole/delete/[A-Za-z0-9]',
    type: 'post',
    response: {
      code: 0,
      data: {
        status: 'success'
      }
    }
  }
]
