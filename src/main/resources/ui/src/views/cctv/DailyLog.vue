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

      <!--台号-->
      <el-form-item label="台号">
        <el-input v-model="q.tableCode" placeholder="" />
      </el-form-item>
      <!--主题-->
      <el-form-item label="主题">
        <el-input v-model="q.subject" placeholder="" />
      </el-form-item>

      <!--部门-->
      <el-form-item label="部门">
        <el-input v-model="q.departmentId" placeholder="" />
      </el-form-item>
      <!--监控部-->
      <el-form-item label="监控部">
        <el-input v-model="q.monitor" placeholder="" />
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
    <!--列表-->
    <daily-log-page
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
        <!--主题-->
        <el-form-item label="主题" prop="subject">
          <el-input v-model="d.subject" placeholder="" />
        </el-form-item>
        <!--细节-->
        <el-form-item label="细节" prop="details">
          <el-input v-model="d.details" placeholder="" />
        </el-form-item>
        <!--通知人-->
        <el-form-item label="通知人" prop="alerterName">
          <el-input v-model="d.alerterName" placeholder="" />
        </el-form-item>
        <!--部门-->
        <el-form-item label="部门" prop="departmentId">
          <el-select v-model="d.departmentId" placeholder="请选择">
            <el-option
              v-for="item in departmentList"
              :key="item.departmentId"
              :label="item.departmentCode"
              :value="item.departmentId">
            </el-option>
          </el-select>
        </el-form-item>
        <!--监控部-->
        <el-form-item label="监控部" prop="monitor">
          <el-input v-model="d.monitor" placeholder="" />
        </el-form-item>
        <!--结论-->
        <el-form-item label="结论" prop="conclusion">
          <el-input v-model="d.conclusion" placeholder="" />
        </el-form-item>

        <!--图片-->
        <el-form-item label="图片">
          <multiple-images :urls.sync="d.urls"/>
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
import DailyLogPage from './components/DailyLogPage'
import MultipleImages from '@/components/Upload/MultipleImages'
import { saveDailyLog, exportDailyLogExcel, updateDailyLog ,deleteFormDailyLogById} from '@/api/daily-log'
import { downloadExcelByKey, deepClone } from "@/utils"
import { getDepartments } from '@/api/department'
const data = {
  no: null,
  date: '',
  time: '',
  tableCode: '',
  subject: '',
  details: '',
  alerterName: '',
  departmentId: null,
  monitor: null,
  conclusion: null,
  urls: ''
}
export default {
  name: 'DailyLog',
  components: { DailyLogPage, MultipleImages },
  data() {
    return {
      q: {},
      d: deepClone(data),
      departmentList: [],
      searchTime: [],
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        no: [{ required: true, trigger: 'blur' , message:'not null'}],
        tableCode: [{ required: true, trigger: 'blur' , message:'not null'}],
        subject: [{ required: true, trigger: 'blur' , message:'not null'}],
        details: [{ required: true, trigger: 'blur' , message:'not null'}],
        alerterName: [{ required: true, trigger: 'blur' , message:'not null'}],
        departmentId: [
          { required: true, trigger: 'blur' , message:'not null'},
          { type: 'number', trigger: 'blur' , message:'必须是数字'}
        ],
        monitor: [{ required: true, trigger: 'blur' , message:'not null'}],
        conclusion: [{ required: true, trigger: 'blur' , message:'not null'}]
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
      const res = await exportDailyLogExcel(this.q)
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
          const res = await deleteFormDailyLogById(row.id)
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
        res = await updateDailyLog(this.d)
      }else {
        res = await saveDailyLog(this.d)
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
    },
    async initDepartmentList(){
      const res = await getDepartments()
      if (res.code === 0){
        this.departmentList = res.data
      }
    }
  },
  created(){
    this.initDepartmentList()
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
