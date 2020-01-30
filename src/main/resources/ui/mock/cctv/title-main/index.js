export default [
  {
    url: '/titleMain/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleMain/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/titleMain/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  }
]
