export default [
  {
    url: '/incidentLog/save',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/incidentLog/delete/[A-Za-z0-9]',
    type: 'post',
    response: config => {
      return {
        code: 0
      }
    }
  },
  {
    url: '/incidentLog/page',
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: []
      }
    }
  }
]
