// Just a mock data
export const constantRoutes = [
  {
    path: '/redirect',
    component: 'layout/Layout',
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: 'views/redirect/index'
      }
    ]
  },
  {
    path: '/login',
    component: 'views/login/index',
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: 'views/login/auth-redirect',
    hidden: true
  },
  {
    path: '/404',
    component: 'views/error-page/404',
    hidden: true
  },
  {
    path: '/401',
    component: 'views/error-page/401',
    hidden: true
  },
  {
    path: '',
    component: 'layout/Layout',
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: 'views/dashboard/index',
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/documentation',
    component: 'layout/Layout',
    children: [
      {
        path: 'index',
        component: 'views/documentation/index',
        name: 'Documentation',
        meta: { title: 'documentation', icon: 'documentation', affix: true }
      }
    ]
  },
  {
    path: '/guide',
    component: 'layout/Layout',
    redirect: '/guide/index',
    children: [
      {
        path: 'index',
        component: 'views/guide/index',
        name: 'Guide',
        meta: { title: 'guide', icon: 'guide', noCache: true }
      }
    ]
  }
]

export const asyncRoutes = [
  // 权限配置页
  {
    "id": 53,
    "path": "/permission",
    "pid": 0,
    "isDel": 0,
    "sort": 0,
    "perms": null,
    "description": "权限相关"
  },
  {
    "id": 54,
    "path": "role",
    "pid": 53,
    "isDel": 0,
    "sort": 0,
    "perms": null,
    "description": "编辑角色"
  },
  {
    "id": 55,
    "path": "department",
    "pid": 53,
    "isDel": 0,
    "sort": 0,
    "perms": null,
    "description": "编辑部门"
  },
  {
    "id": 56,
    "path": "users",
    "pid": 53,
    "isDel": 0,
    "sort": 0,
    "perms": null,
    "description": "编辑账号"
  },
  {
    "id": 57,
    "path": "menus",
    "pid": 53,
    "isDel": 0,
    "sort": 0,
    "perms": null,
    "description": "编辑菜单，同时编辑权限"
  },
  {
    id: 58,
    path: '/icon'
  },
  {
    id: 59,
    pid: 58,
    path: 'index',
    name: 'Icons'
  },
  {
    id: 60,
    path: '/error'
  },
  {
    id: 61,
    pid: 60,
    path: '401'
  },
  {
    id: 62,
    pid: 60,
    path: '404'
  },

  {
    id: 63,
    path: '/error-log'
  },
  {
    id: 64,
    path: 'log'
  },

  {
    id: 65,
    path: '/excel'
  },
  {
    id: 66,
    pid: 65,
    path: 'export-excel'
  },
  {
    id: 67,
    pid: 65,
    path: 'export-selected-excel'
  },
  {
    id: 68,
    pid: 65,
    path: 'export-merge-header'
  },
  {
    id: 69,
    pid: 65,
    path: 'upload-excel'
  },

  {
    id: 70,
    path: '/zip'
  },
  {
    id: 71,
    pid: 70,
    path: 'download'
  },

  {
    id: 72,
    path: '/pdf',
  },
  {
    id: 73,
    path: 'index'
  },
  {
    id: 74,
    path: '/theme'
  },
  {
    id: 75,
    pid: 76,
    path: 'index'
  },

  {
    id: 77,
    path: '/clipboard'
  },
  {
    id: 78,
    pid: 77,
    path: 'index'
  },

  {
    id: 79,
    path: '/i18n'
  },
  {
    id: 80,
    pid: 79,
    path: 'index'
  },
  {
    id: 81,
    path: '/components'
  },
  {
    id: 82,
    pid: 81,
    path: 'tinymce'
  },
  {
    id: 83,
    pid: 81,
    path: 'markdown'
  },
  {
    id: 84,
    pid: 81,
    path: 'json-editor'
  },
  {
    id: 85,
    pid: 81,
    path: 'split-pane'
  },
  {
    id: 86,
    pid: 81,
    path: 'avatar-upload'
  },
  {
    id: 87,
    pid: 81,
    path: 'dropzone'
  },
  {
    id: 88,
    pid: 81,
    path: 'sticky'
  },
  {
    id: 89,
    pid: 81,
    path: 'count-to'
  },
  {
    id: 90,
    pid: 81,
    path: 'mixin'
  },
  {
    id: 91,
    pid: 81,
    path: 'back-to-top'
  },
  {
    id: 92,
    pid: 81,
    path: 'drag-dialog'
  },
  {
    id: 93,
    pid: 81,
    path: 'drag-select'
  },
  {
    id: 94,
    pid: 81,
    path: 'drag-kanban'
  }
]
