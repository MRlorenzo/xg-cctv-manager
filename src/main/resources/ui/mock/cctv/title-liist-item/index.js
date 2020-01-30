export default [
  {
    url: '/titleListItem/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleListItem/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleListItem/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  }
]
