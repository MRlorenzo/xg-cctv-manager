<template>
  <div class="app-container">

    <el-form :inline="true">

      <el-form-item>
        <el-input v-model="q.username" placeholder="User Name" />
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="searchTime"
          size="mini"
          type="daterange"
          :range-separator="$t('cctv.to')"
          :start-placeholder="$t('cctv.startDate')"
          :end-placeholder="$t('cctv.endDate')"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>

      <el-form-item label="Status">
        <el-switch
          v-model="user.status"
          :active-value="0"
          :inactive-value="1"
          :active-text="$t('cctv.disable')"
          :inactive-text="$t('cctv.normal')"
        />
      </el-form-item>

      <!--部门-->
      <el-form-item label="Dep">
        <el-select v-model="q.departmentId" placeholder="Department">
          <el-option
            v-for="dep in departmentList"
            :key="dep.departmentId"
            :label="dep.departmentCode"
            :value="dep.departmentId"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          {{ $t('cctv.search') }}
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 重置按钮 -->
        <el-button @click="resetQueryData">
          {{ $t('cctv.reset') }}
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          {{ $t('cctv.new') }}
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 用户列表 -->
    <user-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit User':'New User'">
      <el-form :ref="formName" :model="user" :rules="rules" label-width="80px" label-position="left">

        <!--工号||用户名-->
        <el-form-item :label="$t('cctv.workNo')" prop="username">
          <el-input v-model="user.username" />
        </el-form-item>

        <!--职位 || 角色-->
        <el-form-item :label="$t('cctv.position')" prop="_roleIds">
          <el-select
            v-model="user._roleIds"
            value-key="id"
            :multiple="true"
            :placeholder="$t('cctv.position')"
          >
            <el-option
              v-for="role in rolesList"
              :key="role.id"
              :label="role.name"
              :value="role.id"
            />
          </el-select>
        </el-form-item>

        <!--部门-->
        <el-form-item :label="$t('cctv.department')" prop="departmentId">
          <el-select v-model="user.departmentId" :placeholder="$t('cctv.pe_department')">
            <el-option
              v-for="dep in departmentList"
              :key="dep.departmentId"
              :label="dep.departmentCode"
              :value="dep.departmentId"
            />
          </el-select>
        </el-form-item>

        <!--聘用日期-->
        <el-form-item :label="$t('cctv.search')" prop="hireDate">
          <el-date-picker
            v-model="user.hireDate"
            type="date"
            value-format="yyyy-MM-dd"
            :placeholder="$t('cctv.ps_date')"
          />
        </el-form-item>

        <!--国籍-->
        <el-form-item :label="$t('cctv.nationality')" prop="nationality">
          <el-input v-model="user.nationality" />
        </el-form-item>

        <!--照片-->
        <el-form-item :label="$t('cctv.image')">
          <avatar-image :url.sync="user.avatar" />
        </el-form-item>

        <!--密码-->
        <el-form-item :label="$t('login.password')" prop="password">
          <el-input v-model="user.password" type="password" placeholder="Password" />
        </el-form-item>

        <!--状态  0：禁用   1：正常-->
        <el-form-item :label="$t('cctv.status')" prop="status">
          <el-switch
            v-model="user.status"
            :active-value="0"
            :inactive-value="1"
            :active-text="$t('cctv.disable')"
            :inactive-text="$t('cctv.normal')"
          />
        </el-form-item>

        <!-- 描述-->
        <el-form-item label="Desc" prop="description">
          <el-input
            v-model="user.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="User Description"
          />
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('cctv.cancel') }}
        </el-button>
        <el-button type="info" @click="reset">
          {{ $t('cctv.reset') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('cctv.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <!-- you can add element-ui's tooltip -->
    <el-tooltip placement="top" content="tooltip">
      <back-to-top :custom-style="myBackToTopStyle" :visibility-height="300" :back-position="50" transition-name="fade" />
    </el-tooltip>
  </div>
</template>

<script>
import { deepClone } from '@/utils'
import { delUser, updateUser, addUser } from '@/api/user'
import { getRoles } from '@/api/role'
import { getDepartments } from '@/api/department'
import BackToTop from '@/components/BackToTop'
import UserPage from './components/UserPage'
import AvatarImage from '@/components/Upload/AvatarImage'

const defaultUser = {
  username: '',
  password: '',
  hireDate: null,
  status: 1,
  nationality: null,
  avatar: '',
  description: '',
  departmentId: 1,
  _roleIds: [],
  roles: []
}

const defaultQueryData = {
  username: null,
  startTime: null,
  endTime: null,
  status: 1,
  departmentId: null
}

export default {
  name: 'Users',
  components: { BackToTop, UserPage, AvatarImage },
  data() {
    return {
      user: Object.assign({}, defaultUser),
      doSearch: true,
      dialogVisible: false,
      dialogType: 'new',
      queryText: '',
      departmentList: [],
      rolesList: [],
      // 查询参数
      q: Object.assign({}, defaultQueryData),
      searchTime: [],
      formName: 'form',
      rules: {
        username: [{ required: true, trigger: 'blur', message: 'not null' }],
        password: [{ required: true, trigger: 'blur', message: 'not null' }],
        hireDate: [{ required: true, trigger: 'blur', message: 'not null' }],
        status: [{ required: true, trigger: 'blur', message: 'not null' }],
        nationality: [{ required: true, trigger: 'blur', message: 'not null' }],
        departmentId: [{ required: true, trigger: 'blur', message: 'not null' }],
        _roleIds: [{ required: true, trigger: 'blur', message: 'not null' }]
      },
      // customizable button style, show/hide critical point, return position
      myBackToTopStyle: {
        right: '50px',
        bottom: '50px',
        width: '40px',
        height: '40px',
        'border-radius': '4px',
        'line-height': '45px', // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
        background: '#e7eaf1'// 按钮的背景颜色 The background color of the button
      }
    }
  },

  watch: {
    searchTime(times) {
      if (times == null) {
        return
      }
      const [startDate, endDate] = times
      if (startDate && endDate) {
        Object.assign(this.q, {
          startTime: startDate,
          endTime: endDate
        })
      }
    }
  },
  created() {
    this.getRoles()
    this.getDepartments()
  },
  methods: {
    reset() {
      if (this.$refs[this.formName] != null) {
        this.$refs[this.formName].resetFields()
      }
      this.user = deepClone(defaultUser)
    },
    async getRoles() {
      const res = await getRoles()
      this.rolesList = res.data
    },
    async getDepartments() {
      const res = await getDepartments()
      this.departmentList = res.data
    },
    handleAdd() {
      this.user._roleIds = []
      this.user = Object.assign({}, defaultUser)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      const cloneUser = deepClone(scope.row)
      // 此处不需要传routes,department
      cloneUser.routes = null
      cloneUser.department = null
      cloneUser.password = ''
      cloneUser.freePwd = ''
      if (Array.isArray(cloneUser.roles)) {
        cloneUser._roleIds = cloneUser.roles.map(role => role.id)
      }
      this.user = cloneUser
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm to remove the User?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          const res = await delUser(row.userId)
          if (res.code === 0) {
            this.doSearch = true
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          }
        })
        .catch(err => { console.error(err) })
    },
    async submit() {
      let res
      this.user.roles = this.user._roleIds.map(id => ({ id }))
      if (this.user.id) {
        res = await updateUser(this.user)
      } else {
        res = await addUser(this.user)
      }
      if (res.code === 0) {
        this.dialogVisible = false
        this.doSearch = true
        this.$message.success('提交成功')
      }
    },
    confirm() {
      this.$refs[this.formName].validate((valid) => {
        if (valid) {
          this.submit()
        } else {
          return false
        }
      })
    },
    resetQueryData() {
      this.q = Object.assign({}, defaultQueryData)
      this.searchTime = []
    }
  }
}
</script>

<style lang="scss" scoped>
  .app-container {
    .roles-table {
      margin-top: 30px;
    }
  }
</style>
