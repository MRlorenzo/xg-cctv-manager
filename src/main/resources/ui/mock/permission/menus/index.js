import rootMenus from '../../static_data/root_menus'
import menus from '../../static_data/menus'
export default [

  {
    url: '/sysPermission/root',
    type: 'get',
    response: config => {

      return {
        code: 0,
        data: rootMenus
      }
    }
  },

  {
    url: '/sysPermission/routes',
    type: 'get',
    response: _=>{
      return {
        code: 0,
        data: menus
      }
    }
  },

  {
    url: '/sysPermission/page',
    type: 'get',
    response: config => {
      let {current , size , pid} = config.query
      current = current || 1
      size = size || 10

      let offset = (current - 1) * size
      let end = parseInt(offset) + parseInt(size)
      let pages = Math.ceil(menus.length / size)
      let searchList = menus
        .filter( item => pid == 0 || pid == item.id || pid == item.pid )
      let records = searchList
        .filter((item , index) => index >= offset && index < end)

      return {
        code: 0,
        data: {
          "records": records,
          "total": menus.length,
          "size": 10,
          "current": 1,
          "searchCount": true,
          "pages": pages
        }
      }
    }
  },

  {
    url: '/sysPermission/save',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  {
    url: '/sysPermission/save',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },

  {
    url: '/sysPermission/delete/[A-Za-z0-9]',
    type: 'post',
    response: _=>{
      return {
        code: 0,
        data: 'success'
      }
    }
  },
]
