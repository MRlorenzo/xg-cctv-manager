<template>
  <div class="app-container">
    <el-form :inline="true">
      <!--开始时间，结束时间-->
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

      <!--序号-->
      <el-form-item label="序号">
        <el-input v-model="q.no" placeholder="No" />
      </el-form-item>

      <!--台号-->
      <el-form-item label="台号">
        <el-input v-model="q.tableCode" placeholder="No" />
      </el-form-item>

      <!--币种-->
      <el-form-item label="币种">
        <el-input v-model="q.coinCode" placeholder="No" />
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

    <credit-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" :ref="formName" :rules="rules" label-width="80px" label-position="left">
        <!--序号-->
        <el-form-item label="序号" prop="no">
          <el-input v-model="d.no" placeholder="No" />
        </el-form-item>
        <!--日期-->
        <el-form-item label="日期">

          <el-col :span="11">
            <el-form-item prop="date1">
              <el-date-picker
                v-model="d.date"
                type="date"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>

          <el-col class="line" :span="2">-</el-col>

          <el-col :span="11">
            <el-form-item prop="date2">
              <el-time-picker
                placeholder="选择时间"
                v-model="d.time"
                style="width: 100%;"/>
            </el-form-item>
          </el-col>

        </el-form-item>
        <!--台号-->
        <el-form-item label="台号" prop="tableCode">
          <el-input v-model="d.tableCode" placeholder="" />
        </el-form-item>
        <!--金额-->
        <el-form-item label="金额" prop="total">
          <el-input v-model="d.total" placeholder="" />
        </el-form-item>
        <!--币种-->
        <el-form-item label="币种" prop="coinCode">
          <el-input v-model="d.coinCode" placeholder="" />
        </el-form-item>
        <!--通知人-->
        <el-form-item label="通知人" prop="alerterName">
          <el-input v-model="d.alerterName" placeholder="" />
        </el-form-item>
        <!--监控部-->
        <el-form-item label="监控部" prop="monitor">
          <el-input v-model="d.monitor" placeholder="" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import CreditPage from './components/CredItPage'
import {saveFillAndCredit , deleteFillAndCreditById , updateFillAndCredit , exportFillAndCreditExcel} from '@/api/fill-and-credit'
import { downloadExcelByKey, deepClone } from "@/utils"

const data = {
  no: null,
  date: '',
  time: '',
  tableCode: null,
  total: null,
  coinCode: null,
  alerterName: null,
  monitor: null
}
export default {
  name: 'Credit',
  components: { CreditPage },
  data() {
    return {
      q: {},
      d: deepClone(data),
      searchTime: [],
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        no: [{ required: true, trigger: 'blur' , message:'not null'}],
        date: [{ required: true, trigger: 'blur' , message:'not null'}],
        tableCode: [{ required: true, trigger: 'blur' , message:'not null'}],
        total: [{ required: true, trigger: 'blur' , message:'not null'}],
        coinCode: [{ required: true, trigger: 'blur' , message:'not null'}],
        alerterName: [{ required: true, trigger: 'blur' , message:'not null'}],
        monitor: [{ required: true, trigger: 'blur' , message:'not null'}]
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
  methods: {
    resetQueryData() {
      this.q = {}
    },
    handleAdd() {
      if (this.$refs[this.formName] != null){
        this.$refs[this.formName].resetFields()
      } else {
        this.d = deepClone(data)
      }
      this.showMark = true
      this.dialogType = 'new'
    },
    async handleExcel(){
      const res = await exportFillAndCreditExcel(this.q)
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
          const res = await deleteFillAndCreditById(row.id)
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
        res = await updateFillAndCredit(this.d)
      }else {
        res = await saveFillAndCredit(this.d)
      }
      if (res.code === 0){
        this.showMark = false
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
