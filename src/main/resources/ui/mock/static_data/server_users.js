import { asyncRoutes } from './routes.js'
asyncRoutes.push(null)
export default [
  /*{
    username: 'testhr',
    department: {
      departmentId: 1
    },
    roles: [{id: 2}],
    routes: [
      {
        id: 1,
        path: '/cctv',
        pid: 0
      },
      {
        id: 2,
        path: "users",
        pid: 1
      },
      {
        id: 3,
        path: 'users:save',
        pid: 2
      }
    ]
  },*/
  {
    "userId": 669,
    "username": "user16",
    "password": "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
    "status": 1,
    "departmentId": 0,
    "department": {
      "departmentId": 1,
      "name": "董事长",
      "enName": "President",
      "departmentCode": "President",
      "sort": 1
    },
    "description": null,
    roles: [
      {id: 1}
    ],
    routes: asyncRoutes
  }
]
