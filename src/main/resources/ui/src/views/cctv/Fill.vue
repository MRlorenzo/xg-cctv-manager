<template>
  <div class="app-container">
    <el-form :inline="true">
      <!--开始时间，结束时间-->
      <el-form-item>
        <el-date-picker
          v-model="searchTime"
          size="mini"
          type="daterange"
          :range-separator="$t('cctv.to')"
          :start-placeholder="$t('cctv.startDate')"
          :end-placeholder="$t('cctv.endDate')"
          value-format="yyyy-MM-dd"
        >
        </el-date-picker>
      </el-form-item>

      <!--序号-->
      <el-form-item :label="$t('cctv.no')">
        <el-input v-model="q.no" :placeholder="$t('cctv.pe_no')" />
      </el-form-item>

      <!--台号-->
      <el-form-item :label="$t('cctv.tableCode')">
        <el-input v-model="q.tableCode" :placeholder="$t('cctv.pe_tableCode')" />
      </el-form-item>

      <!--币种-->
      <el-form-item :label="$t('cctv.coinType')">
        <el-select v-model="q.coinCode" filterable :placeholder="$t('cctv.ps_coinType')">
          <el-option
            v-for="c in coinList"
            :key="c.id"
            :label="c.label"
            :value="c.code"
          >
          </el-option>
        </el-select>
      </el-form-item>

    </el-form>

    <el-form :inline="true">
      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          {{$t('cctv.search')}}
        </el-button>
      </el-form-item>

      <!-- 导出按钮 -->
      <el-form-item>
        <el-button type="info" @click="handleExcel">
          {{$t('cctv.exportExcel')}}
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 重置按钮 -->
        <el-button @click="resetQueryData">
          {{$t('cctv.reset')}}
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          {{$t('cctv.new')}}
        </el-button>
      </el-form-item>
    </el-form>

    <fill-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" :ref="formName" :rules="rules" label-width="80px" label-position="left">
        <!--序号-->
        <el-form-item :label="$t('cctv.no')" prop="no">
          <el-input v-model="d.no" :placeholder="$t('cctv.pe_no')" />
        </el-form-item>
        <!--日期-->
        <el-form-item :label="$t('cctv.date')">

          <el-col :span="11">
            <el-form-item prop="date">
              <el-date-picker
                v-model="d.date"
                type="date"
                :placeholder="$t('cctv.ps_date')"
              />
            </el-form-item>
          </el-col>

          <el-col class="line" :span="2">-</el-col>

          <el-col :span="11">
            <el-form-item prop="time">
              <el-time-picker
                :placeholder="$t('cctv.ps_time')"
                value-format="HH:mm:ss"
                v-model="d.time"
                style="width: 100%;"/>
            </el-form-item>
          </el-col>

        </el-form-item>

        <!--台号-->
        <el-form-item :label="$t('cctv.tableCode')" prop="tableCode">
          <el-input v-model="d.tableCode" :placeholder="$t('cctv.pe_tableCode')" />
        </el-form-item>
        <!--金额-->
        <el-form-item :label="$t('cctv.total')" prop="total">
          <el-input v-model="d.total" :placeholder="$t('cctv.pe_total')" />
        </el-form-item>
        <!--币种-->
        <el-form-item :label="$t('cctv.coinType')" prop="coinCode">
          <el-select v-model="d.coinCode" filterable :placeholder="$t('cctv.ps_coinType')">
            <el-option
              v-for="c in coinList"
              :key="c.id"
              :label="c.label"
              :value="c.code"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!--通知人-->
        <el-form-item :label="$t('cctv.alerterName')" prop="alerterName">
          <el-input v-model="d.alerterName" :placeholder="$t('cctv.pe_alerterName')" />
        </el-form-item>
        <!--监控部-->
        <el-form-item :label="$t('cctv.monitor')" prop="monitor">
          <el-input v-model="d.monitor" :placeholder="$t('cctv.pe_monitor')" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
          {{ $t('cctv.cancel') }}
        </el-button>
        <el-button type="info" @click="reset">
          {{$t('cctv.reset')}}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('cctv.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import FillPage from './components/FillPage'
import {saveFillAndCredit , deleteFillAndCreditById , updateFillAndCredit , exportFillAndCreditExcel} from '@/api/fill-and-credit'
import { downloadExcelByKey, deepClone } from "@/utils"
import coinList from './common/coin-list'
const data = {
  type: 1, // 1.加彩 2.缴码
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
  name: 'Fill',
  components: { FillPage },
  data() {
    return {
      q: { type: 1},
      d: deepClone(data),
      searchTime: [],
      coinList: deepClone(coinList), // 币种列表
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        no: [{ required: true, trigger: 'blur' , message:'not null'}],
        date: [{ required: true, message: 'not null', trigger: 'blur' }],
        time: [{ required: true, trigger: 'blur' , message:'not null'}],
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
      this.q = { type: 1}
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
