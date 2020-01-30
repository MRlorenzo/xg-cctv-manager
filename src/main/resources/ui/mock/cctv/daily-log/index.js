export default [
  {
    url: '/dailyLog/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/dailyLog/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/dailyLog/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  }
]
