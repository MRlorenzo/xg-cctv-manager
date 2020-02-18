<template>
  <div class="app-container">

    <el-form :inline="true">

      <el-form-item >
        <el-input v-model="q.username" placeholder="User Name" />
      </el-form-item>

      <el-form-item>
        <el-date-picker
          v-model="searchTime"
          size="mini"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="Status">
        <el-switch
          v-model="q.status"
          active-value="0"
          inactive-value="1"
          active-text="禁用"
          inactive-text="正常">
        </el-switch>
      </el-form-item>

      <!--部门-->
      <el-form-item label="Dep">
        <el-select v-model="q.departmentId" placeholder="Department">
          <el-option
            v-for="dep in departmentList"
            :key="dep.departmentId"
            :label="dep.departmentCode"
            :value="dep.departmentId">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          Search
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 重置按钮 -->
        <el-button @click="resetQueryData" >
          Reset
        </el-button>
      </el-form-item>

      <el-form-item >
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          {{ $t('permission.addUser') }}
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 用户列表 -->
    <user-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"/>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit User':'New User'">
      <el-form :model="user" label-width="80px" label-position="left">

        <!--序号-->
        <el-form-item label="No">
          <el-input v-model="user.no" />
        </el-form-item>

        <!--用户名-->
        <el-form-item label="account">
          <el-input v-model="user.username" placeholder="User Name" />
        </el-form-item>

        <!--工号-->
        <el-form-item label="工号">
          <el-input v-model="user.workNo" placeholder="User Name" />
        </el-form-item>

        <!--名称-->
        <el-form-item label="Name">
          <el-input v-model="user.nickName" />
        </el-form-item>

        <!--密码-->
        <el-form-item label="Pass">
          <el-input type="password" v-model="user.password" placeholder="Password" />
        </el-form-item>

        <!--部门-->
        <el-form-item label="Dep">
          <el-select v-model="user.departmentId" placeholder="Department">
            <el-option
              v-for="dep in departmentList"
              :key="dep.departmentId"
              :label="dep.departmentCode"
              :value="dep.departmentId">
            </el-option>
          </el-select>
        </el-form-item>

        <!--角色们-->
        <el-form-item label="Roles">
          <el-select
            v-model="roleIds"
            :multiple="true"
            placeholder="Roles">
            <el-option
              v-for="role in rolesList"
              :key="role.id"
              :label="role.name"
              :value="role.id">
            </el-option>
          </el-select>
        </el-form-item>

        <!--聘用日期-->
        <el-form-item label="聘用日期">
          <el-input type="password" v-model="user.hireDate" />
        </el-form-item>
        <!--职位-->
        <el-form-item label="职位">
          <el-input type="password" v-model="user.position" />
        </el-form-item>
        <!--国籍-->
        <el-form-item label="国籍">
          <el-input type="password" v-model="user.nationality" />
        </el-form-item>
        <!--照片-->
        <el-form-item label="照片">
          <avatar-image :url.sync="user.avatar"/>
        </el-form-item>
        <!--状态  0：禁用   1：正常-->
        <el-form-item label="状态">
          <el-input type="password" v-model="user.status" />
        </el-form-item>

        <!-- 描述-->
        <el-form-item label="Desc">
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
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
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
  import { delUser, updateUser , addUser} from "@/api/user";
  import { getRoles } from '@/api/role'
  import { getDepartments } from '@/api/department'
  import BackToTop from '@/components/BackToTop'
  import UserPage from './components/UserPage'
  import AvatarImage from '@/components/Upload/AvatarImage'

  const defaultUser = {
    username: '',
    password: '',
    freePwd: '',
    status: 1,
    description: '',
    departmentId: 1,
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
    name: "users",
    components: { BackToTop , UserPage, AvatarImage},
    data(){
      return {
        user: Object.assign({} ,defaultUser),
        doSearch: true,
        dialogVisible: false,
        dialogType: 'new',
        queryText: '',
        departmentList: [],
        rolesList: [],
        roleIds: [],
        // 查询参数
        q: Object.assign({} , defaultQueryData),
        searchTime: [],
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

    watch:{
      searchTime( times ){
        if (times == null){
          return
        }
        let [startDate , endDate] = times
        if ( startDate && endDate){
          Object.assign(this.q , {
            startTime: startDate,
            endTime: endDate
          })
        }
      }
    },
    created(){
      this.getRoles()
      this.getDepartments()
    },
    methods: {
      async getRoles() {
        const res = await getRoles()
        this.rolesList = res.data
      },
      async getDepartments(){
        const res = await getDepartments()
        this.departmentList = res.data
      },
      handleAdd(){
        this.roleIds = [];
        this.user = Object.assign({}, defaultUser)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit( scope ){
        this.dialogType = 'edit'
        this.dialogVisible = true
        let cloneUser = deepClone(scope.row)
        cloneUser.password = ''
        cloneUser.freePwd = ''
        if (Array.isArray(cloneUser.roles)){
          this.roleIds = cloneUser.roles.map(role=>role.id)
        }
        this.user = cloneUser
      },
      handleDelete({ $index, row }){
        this.$confirm('Confirm to remove the User?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        })
          .then(async() => {
            await delUser(row.userId)
            this.doSearch = true
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          })
          .catch(err => { console.error(err) })
      },
      async confirm(){
        const isEdit = this.dialogType === 'edit'
        this.user.roles = this.roleIds.map(id=>({id}))
        // 此处不需要传routes,department
        this.user.routes = null
        this.user.department = null

        if (isEdit) {
          await updateUser(this.user)

        } else {
          await addUser(this.user)
        }

        if (this.currPage === 1 ){
          this.doSearch = true
        }

        const { description, username } = this.user
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>User Nmae: ${username}</div>
            <div>Description: ${description}</div>
          `,
          type: 'success'
        })
        this.roleIds = []
      },
      resetQueryData(){
        this.q = Object.assign({} , defaultQueryData)
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
