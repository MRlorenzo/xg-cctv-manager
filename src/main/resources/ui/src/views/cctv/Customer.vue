<template>
  <div class="app-container">
    <el-form :inline="true">
      <!--卡号-->
      <el-form-item label="卡号">
        <el-input v-model="q.cardNumber" placeholder="No" />
      </el-form-item>
      <!--位置-->
      <el-form-item label="位置">
        <el-input v-model="q.location" placeholder="No" />
      </el-form-item>

      <!--状态-->
      <el-form-item label="状态">
        <el-radio v-model="q.status" :label="0" border>禁用</el-radio>
        <el-radio v-model="q.status" :label="1" border>正常</el-radio>
      </el-form-item>

    </el-form>

    <el-form :inline="true">
      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          Search
        </el-button>
      </el-form-item>

      <!-- 导出按钮 -->
      <el-form-item>
        <el-button type="info" @click="handleExcel">
          Excel
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 重置按钮 -->
        <el-button @click="resetQueryData">
          Reset
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          Add
        </el-button>
      </el-form-item>
    </el-form>

    <customer-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" :ref="formName" :rules="rules" label-width="80px" label-position="left">
        <!--名称-->
        <el-form-item label="名称" prop="name">
          <el-input v-model="d.name" placeholder="" />
        </el-form-item>
        <!--卡号-->
        <el-form-item label="卡号" prop="cardNumber">
          <el-input v-model="d.cardNumber" placeholder="" />
        </el-form-item>
        <!--位置-->
        <el-form-item label="位置" prop="location">
          <el-input v-model="d.location" placeholder="" />
        </el-form-item>
        <!--平均下注-->
        <el-form-item label="平均下注" prop="avgBetting">
          <el-input v-model="d.avgBetting" placeholder="" />
        </el-form-item>
        <!--国籍-->
        <el-form-item label="国籍" prop="nationality">
          <el-input v-model="d.nationality" placeholder="" />
        </el-form-item>
        <!--照片-->
        <el-form-item label="照片">
          <avatar-image :url.sync="d.imageUrl"/>
        </el-form-item>
        <!--状态-->
        <el-form-item label="状态">
          <el-radio v-model="d.status" :label="0" border>禁用</el-radio>
          <el-radio v-model="d.status" :label="1" border>正常</el-radio>
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="info" @click="reset">
          {{'reset'}}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import CustomerPage from './components/CustomerPage'
import AvatarImage from '@/components/Upload/AvatarImage'
import {saveCustomer , deleteCustomerById , updateCustomer , exportCustomerExcel} from '@/api/customer'
import { downloadExcelByKey, deepClone } from "@/utils"
const data = {
  name: null,
  cardNumber: null,
  location: null,
  avgBetting: null,
  nationality: null,
  imageUrl: '',
  status: 0
}
export default {
  name: 'Customer',
  components: { CustomerPage , AvatarImage},
  data() {
    return {
      q: {},
      d: deepClone(data),
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        name: [{ required: true, trigger: 'blur' , message:'not null'}],
        cardNumber: [{ required: true, trigger: 'blur' , message:'not null'}],
        location: [{ required: true, trigger: 'blur' , message:'not null'}],
        avgBetting: [{ required: true, trigger: 'blur' , message:'not null'}],
        nationality: [{ required: true, trigger: 'blur' , message:'not null'}],
        status: [{ required: true, trigger: 'blur' , message:'not null'}]
      }
    }
  },
  methods: {
    resetQueryData() {
      this.q = {}
    },
    reset(){
      if (this.$refs[this.formName] != null){
        this.$refs[this.formName].resetFields()
      }
      this.d = deepClone(data)
    },
    handleAdd() {
      this.reset()
      this.showMark = true
      this.dialogType = 'new'
    },
    async handleExcel(){
      const res = await exportCustomerExcel(this.q)
      if (res.code === 0){
        downloadExcelByKey(res.key)
      }
    },
    handleEdit(scope) {
      let clone = deepClone(scope.row)
      this.d = clone
      this.showMark = true
      this.dialogType = 'edit'
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm ?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          const res = await deleteCustomerById(row.id)
          if (res.code === 0){
            this.doSearch = true
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          }
        })
        .catch(err => { console.error(err) })
    },
    async submit(){
      let res
      if (this.d.id){
        res = await updateCustomer(this.d)
      }else {
        res = await saveCustomer(this.d)
      }
      if (res.code === 0){
        this.showMark = false
        this.doSearch = true
        this.$message.success('提交成功')
      }
    },
    confirm() {
      this.$refs[this.formName].validate((valid) => {
        if (valid) {
          this.submit();
        }else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>
  .app-container {

  }

</style>
