<template>
  <div class="app-container">

    <el-form :inline="true">

      <el-form-item>
        <el-input v-model="q.staffName" placeholder="Staff Name" />
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
          v-model="q.status"
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

    <!-- 员工列表 -->
    <staff-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Staff':'New Staff'">
      <el-form :ref="formName" :model="staff" :rules="rules" label-width="80px" label-position="left">

        <!--姓名-->
        <el-form-item :label="$t('cctv.name')" prop="staffName">
          <el-input v-model="staff.staffName" />
        </el-form-item>

        <!--工号-->
        <el-form-item :label="$t('cctv.workNo')" prop="workNo">
          <el-input v-model="staff.workNo" />
        </el-form-item>

        <!--职位 || 角色-->
        <el-form-item :label="$t('cctv.position')" prop="positionId">
          <el-select
            v-model="staff.positionId"
            value-key="positionId"
            :placeholder="$t('cctv.position')"
          >
            <el-option
              v-for="item in positionList"
              :key="item.positionId"
              :label="item.name"
              :value="item.positionId"
            />
          </el-select>
        </el-form-item>

        <!--部门-->
        <el-form-item :label="$t('cctv.department')" prop="departmentId">
          <el-select v-model="staff.departmentId" :placeholder="$t('cctv.pe_department')">
            <el-option
              v-for="dep in departmentList"
              :key="dep.departmentId"
              :label="dep.departmentCode"
              :value="dep.departmentId"
            />
          </el-select>
        </el-form-item>

        <!--聘用日期-->
        <el-form-item :label="$t('cctv.hireDate')" prop="hireDate">
          <el-date-picker
            v-model="staff.hireDate"
            type="date"
            :placeholder="$t('cctv.ps_date')"
          />
        </el-form-item>

        <!--国籍-->
        <el-form-item :label="$t('cctv.nationality')" prop="nationality">
          <el-input v-model="staff.nationality" />
        </el-form-item>

        <!--照片-->
        <el-form-item :label="$t('cctv.image')">
          <avatar-image :url.sync="staff.avatar" />
        </el-form-item>

        <!--状态  0：禁用   1：正常-->
        <el-form-item :label="$t('cctv.status')" prop="status">
          <el-switch
            v-model="staff.status"
            :active-value="0"
            :inactive-value="1"
            :active-text="$t('cctv.disable')"
            :inactive-text="$t('cctv.normal')"
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
import { delStaff, updateStaff, addStaff } from '@/api/staff'
// import { getRoles } from '@/api/role'
import { getDepartments } from '@/api/department'
import { getPositions } from '@/api/position'
import BackToTop from '@/components/BackToTop'
import StaffPage from './components/StaffPage'
import AvatarImage from '@/components/Upload/AvatarImage'

const defaultStaff = {
  staffName: '',
  hireDate: null,
  workNo: null,
  status: 1,
  nationality: null,
  avatar: '',
  departmentId: 1,
  positionId: null
}

const defaultQueryData = {
  staffName: null,
  startTime: null,
  endTime: null,
  status: 1,
  departmentId: null
}

export default {
  name: 'Staff',
  components: { BackToTop, StaffPage, AvatarImage },
  data() {
    return {
      staff: Object.assign({}, defaultStaff),
      doSearch: true,
      dialogVisible: false,
      dialogType: 'new',
      queryText: '',
      departmentList: [],
      positionList: [],
      // roleIds: [],
      // 查询参数
      q: Object.assign({}, defaultQueryData),
      searchTime: [],
      formName: 'form',
      rules: {
        staffName: [{ required: true, trigger: 'blur', message: 'not null' }],
        workNo: [{ required: true, trigger: 'blur', message: 'not null' }],
        hireDate: [{ required: true, trigger: 'blur', message: 'not null' }],
        status: [{ required: true, trigger: 'blur', message: 'not null' }],
        nationality: [{ required: true, trigger: 'blur', message: 'not null' }],
        departmentId: [{ required: true, trigger: 'blur', message: 'not null' }],
        positionId: [{ required: true, trigger: 'blur', message: 'not null' }]
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
    this.getPositions()
    this.getDepartments()
  },
  methods: {
    reset() {
      if (this.$refs[this.formName] != null) {
        this.$refs[this.formName].resetFields()
      }
      this.staff = deepClone(defaultStaff)
    },
    async getPositions() {
      const res = await getPositions()
      this.positionList = res.data
    },
    async getDepartments() {
      const res = await getDepartments()
      this.departmentList = res.data
    },
    handleAdd() {
      // this.staff.roleIds = []
      this.staff = Object.assign({}, defaultStaff)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      const cloneStaff = deepClone(scope.row)
      // 此处不需要传routes,department
      cloneStaff.department = null
      cloneStaff.position = null
      // cloneStaff.freePwd = ''
      // if (Array.isArray(cloneStaff.roles)) {
      //   cloneStaff.roleIds = cloneStaff.roles.map(role => role.id)
      // }
      this.staff = cloneStaff
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm to remove the Staff?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          const res = await delStaff(row.staffId)
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
      // this.staff.roles = this.staff.positionId.map(id => ({ id }))
      if (this.staff.id) {
        res = await updateStaff(this.staff)
      } else {
        res = await addStaff(this.staff)
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
