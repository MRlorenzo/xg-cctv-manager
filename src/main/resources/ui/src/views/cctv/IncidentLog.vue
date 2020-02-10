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
      <!--事件编码-->
      <el-form-item label="事件编码">
        <el-select v-model="q.code" filterable>
          <el-option
            v-for="t in titleSubjectList"
            :key="t.id"
            :label="t.code"
            :value="t.code"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!--币种-->
      <el-form-item label="币种">
        <el-select v-model="q.coinCode" filterable>
          <el-option
            v-for="c in coinList"
            :key="c.id"
            :label="c.label"
            :value="c.code"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!--报告-->
      <el-form-item label="报告">
        <el-input v-model="q.report" placeholder="" />
      </el-form-item>
      <!--涉及员工-->
      <el-form-item label="涉及员工">
        <el-select
          v-model="q.involveUid"
          filterable
          remote
          reserve-keyword
          placeholder="请输入关键词"
          :remote-method="remoteMethod"
          :loading="loading">
          <el-option
            v-for="emp in empList"
            :key="emp.userId"
            :label="emp.username"
            :value="emp.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <!--部门id-->
      <el-form-item label="部门">
        <el-input v-model="q.departmentId" placeholder="" />
      </el-form-item>
      <!--监控部-->
      <el-form-item label="监控部">
        <el-input v-model="q.monitor" placeholder="" />
      </el-form-item>
      <!--备注-->
      <el-form-item label="备注">
        <el-input v-model="q.remarks" placeholder="" />
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

    <incident-log-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" :ref="formName" :rules="rules" label-width="80px" label-position="left">
        <!--日期-->
        <el-form-item label="日期">

          <el-col :span="11">
            <el-form-item prop="date">
              <el-date-picker
                v-model="d.date"
                type="date"
                placeholder="选择日期"
              />
            </el-form-item>
          </el-col>

          <el-col class="line" :span="2">-</el-col>

          <el-col :span="11">
            <el-form-item prop="time">
              <el-time-picker
                placeholder="选择时间"
                value-format="HH:mm:ss"
                v-model="d.time"
                style="width: 100%;"/>
            </el-form-item>
          </el-col>

        </el-form-item>
        <!--台号-->
        <el-form-item label="台号" prop="tableCode">
          <el-input v-model="d.tableCode" placeholder="" />
        </el-form-item>
        <!--事件编码-->
        <el-form-item label="事件编码" prop="code">
          <el-select v-model="d.code" filterable>
            <el-option
              v-for="t in titleSubjectList"
              :key="t.id"
              :label="t.code"
              :value="t.code"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!--币种-->
        <el-form-item label="币种" prop="coinCode">
          <el-select v-model="d.coinCode" filterable>
            <el-option
              v-for="c in coinList"
              :key="c.id"
              :label="c.label"
              :value="c.code"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!--金额-->
        <el-form-item label="金额" prop="total">
          <el-input type="number" v-model="d.total" placeholder="" />
        </el-form-item>
        <!--报告-->
        <el-form-item label="报告" prop="report">
          <el-input v-model="d.report" placeholder="" />
        </el-form-item>
        <!--涉及员工-->
        <el-form-item label="涉及员工" prop="involveUid">
          <el-select
            v-model="d.involveUid"
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词"
            :remote-method="remoteMethod"
            :loading="loading">
            <el-option
              v-for="emp in empList"
              :key="emp.userId"
              :label="emp.username"
              :value="emp.userId">
            </el-option>
          </el-select>
        </el-form-item>
        <!--部门id-->
        <el-form-item label="部门" prop="departmentId">
          <el-select v-model="d.departmentId" filterable placeholder="请选择">
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
        <!--备注-->
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="d.remarks" placeholder="" />
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
import IncidentLogPage from './components/IncidentLogPage'
import MultipleImages from '@/components/Upload/MultipleImages'
import {saveIncidentLog , deleteIncidentLogById , updateIncidentLog , exportIncidentLogExcel} from '@/api/incident-log'
import { downloadExcelByKey , deepClone } from "@/utils"
import { getDepartments } from '@/api/department'
import { getTtitleListItemList } from '@/api/title-liist-item'
import coinList from './common/coin-list'
import { findUserLikeName } from '@/api/user'
const data = {
  date: null,
  time: '',
  tableCode: null,
  code: null,
  coinCode: null,
  total: null,
  report: null,
  involveUid: null,
  departmentId: null,
  monitor: null,
  remarks: null,
  urls: null
}
export default {
  name: 'IncidentLog',
  components: { IncidentLogPage, MultipleImages },
  data() {
    return {
      q: {},
      searchTime: [],
      departmentList: [],
      titleSubjectList: [], // 事件列表
      coinList: deepClone(coinList), // 币种列表
      empList: [], // 员工列表
      loading: false,
      d: { urls: ''},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        date: [{ required: true, message: 'not null', trigger: 'blur' }],
        time: [{ required: true, trigger: 'blur' , message:'not null'}],
        tableCode: [{ required: true, trigger: 'blur' , message:'not null'}],
        code: [{ required: true, trigger: 'blur' , message:'not null'}],
        coinCode: [{ required: true, trigger: 'blur' , message:'not null'}],
        total: [{ required: true, trigger: 'blur' , message:'not null'}],
        report: [{ required: true, trigger: 'blur' , message:'not null'}],
        involveUid: [
          { required: true, trigger: 'blur' , message:'not null'},
          { type: 'number', trigger: 'blur' , message:'必须是数字'}
        ],
        departmentId: [{ required: true, trigger: 'blur' , message:'not null'}],
        monitor: [{ required: true, trigger: 'blur' , message:'not null'}],
        remarks: [{ required: true, trigger: 'blur' , message:'not null'}],
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
      const res = await exportIncidentLogExcel(this.q)
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
          const res = await deleteIncidentLogById(row.id)
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
        res = await updateIncidentLog(this.d)
      }else {
        res = await saveIncidentLog(this.d)
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
          this.submit()
        }else {
          return false
        }
      });
    },
    async initDepartmentList(){
      const res = await getDepartments()
      if (res.code === 0){
        this.departmentList = res.data
      }
    },
    async initTitleSubjectList(){
      const res = await getTtitleListItemList()
      if (res.code === 0){
        this.titleSubjectList = res.data
      }
    },
    async remoteMethod( name ){
      this.loading = true
      const res = await findUserLikeName(name)
      if (res.code === 0){
        this.loading = false
        this.empList = res.data
      }
    }
  },
  created(){
    this.initDepartmentList()
    this.initTitleSubjectList()
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
