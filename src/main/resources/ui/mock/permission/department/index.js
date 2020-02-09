
const departments = [
  {
    "departmentId": 1,
    "name": "董事长",
    "description": "董事长",
    "departmentCode": "President",
    "sort": 1
  },
  {
    "departmentId": 2,
    "name": "人事",
    "description": "人事",
    "departmentCode": "HR",
    "sort": 1
  }
]

export default [
  {
    url: '/sysDepartment/list',
    type: 'get',
    response: _ => {
      return {
        code: 0,
        data: departments
      }
    }
  },
  {
    url: '/sysDepartment/save',
    type: 'post',
    response: {
      code: 0
    }
  },
  {
    url: '/sysDepartment/save',
    type: 'post',
    response: {
      code: 0
    }
  },
  {
    url: '/sysDepartment/delete/[A-Za-z0-9]',
    type: 'get',
    response:{
      code:0
    }
  }
]
