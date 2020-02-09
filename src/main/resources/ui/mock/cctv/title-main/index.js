const mains = [
  {
    id: 1,
    subject: '主要事件'
  },
  {
    id: 2,
    subject: '不当行为'
  },
  {
    id: 3,
    subject: 'Review回顾确认'
  },
  {
    id: 4,
    subject: '错误'
  },
  {
    id: 5,
    subject: 'Violation违规'
  },
  {
    id: 6,
    subject: 'High Action高额活动'
  }
]
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
        data: {
          "records": mains ,
          "total": mains.length,
          "size": 10,
          "current": 1,
          "searchCount": true,
          "pages": 1
        }
      }
    }
  },
  {
    url: '/titleMain/list',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: mains
      }
    }
  }
]
