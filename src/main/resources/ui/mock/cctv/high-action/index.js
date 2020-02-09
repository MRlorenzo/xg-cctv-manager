export default [
  {
    url: '/highAction/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/highAction/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/highAction/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  },
  {
    url: '/highAction/excel',
    type: 'get',
    response: config => {
      return {
        code: 0,
        key: 'xxxx'
      }
    }
  }
]
