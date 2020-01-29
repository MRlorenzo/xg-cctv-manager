<template>
    <div class="app-container">
      <!-- 新增部门按钮 -->
      <el-button type="primary" @click="handleAddDepartment">
        {{ $t('permission.addDepartment') }}
      </el-button>

      <!-- 部门列表 -->
      <el-table :data="departmentList" style="width: 100%;margin-top:30px;" border>
        <!-- 名称 -->
        <el-table-column align="center" label="Name" width="220">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <!-- 代号 -->
        <el-table-column align="center" label="Code" width="220">
          <template slot-scope="scope">
            {{ scope.row.departmentCode }}
          </template>
        </el-table-column>
        <!-- 描述 -->
        <el-table-column align="header-center" label="Description">
          <template slot-scope="scope">
            {{ scope.row.description }}
          </template>
        </el-table-column>
        <!--操作-->
        <el-table-column align="center" label="Operations">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope)">
              {{ $t('permission.editDepartment') }}
            </el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope)">
              {{ $t('permission.delete') }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Department':'New Department'">
        <el-form :model="department" label-width="80px" label-position="left">

          <!--名称-->
          <el-form-item label="Name">
            <el-input v-model="department.name" placeholder="Department Name" />
          </el-form-item>

          <!--代号-->
          <el-form-item label="Code">
            <el-input v-model="department.departmentCode" placeholder="Department Code" />
          </el-form-item>

          <!-- 描述-->
          <el-form-item label="Desc">
            <el-input
              v-model="department.description"
              :autosize="{ minRows: 2, maxRows: 4}"
              type="textarea"
              placeholder="Department Description"
            />
          </el-form-item>


        </el-form>
        <div style="text-align:right;">
          <el-button type="danger" @click="dialogVisible=false">
            {{ $t('permission.cancel') }}
          </el-button>
          <el-button type="primary" @click="confirmDepartment">
            {{ $t('permission.confirm') }}
          </el-button>
        </div>
      </el-dialog>

    </div>
</template>

<script>
  import { deepClone } from '@/utils'
  import { getDepartments, addDepartment, updateDepartment, delDepartment } from '@/api/department'

  const defaultDepartment = {
    departmentId: null,
    name: '',
    description: '',
    departmentCode: '',
    sort: 0
  }

  export default {
    name: "department",
    data(){
      return {
        department: Object.assign({} ,defaultDepartment),
        departmentList: [],
        dialogVisible: false,
        dialogType: 'new',
      }
    },
    created(){
      this.getDepartment()
    },
    methods: {
      async getDepartment(){
        const res = await getDepartments()
        this.departmentList = res.data
      },
      handleAddDepartment(){
        this.department = Object.assign({}, defaultDepartment)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit( scope ){
        this.dialogType = 'edit'
        this.dialogVisible = true
        this.department = deepClone(scope.row)
      },
      handleDelete({ $index, row }){
        this.$confirm('Confirm to remove the Department?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        })
          .then(async() => {
            await delDepartment(row.departmentId)
            this.departmentList.splice($index, 1)
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          })
          .catch(err => { console.error(err) })
      },
      async confirmDepartment(){
        const isEdit = this.dialogType === 'edit'
        if (isEdit) {
          await updateDepartment(this.department)
          for (let index = 0; index < this.departmentList.length; index++) {
            if (this.departmentList[index].name === this.department.name) {
              this.departmentList.splice(index, 1, Object.assign({}, this.department))
              break
            }
          }
        } else {
          await addDepartment(this.department)
          this.departmentList.unshift(this.department)
        }
        const { description, name } = this.department
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>Department Nmae: ${name}</div>
            <div>Description: ${description}</div>
          `,
          type: 'success'
        })
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
