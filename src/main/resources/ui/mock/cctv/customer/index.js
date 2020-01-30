export default [
  {
    url: '/customer/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/customer/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/customer/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  }
]
