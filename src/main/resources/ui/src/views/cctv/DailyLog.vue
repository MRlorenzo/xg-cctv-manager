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
        />
      </el-form-item>

      <!--台号-->
      <el-form-item :label="$t('cctv.tableCode')">
        <el-input v-model="q.tableCode" :placeholder="$t('cctv.pe_tableCode')" />
      </el-form-item>
      <!--主题-->
      <el-form-item :label="$t('cctv.subject')">
        <el-input v-model="q.subject" :placeholder="$t('cctv.pe_subject')" />
      </el-form-item>

      <!--部门-->
      <el-form-item :label="$t('cctv.department')">
        <el-input v-model="q.departmentName" :placeholder="$t('cctv.pe_department')" />
      </el-form-item>
      <!--监控部-->
      <el-form-item :label="$t('cctv.monitor')">
        <el-input v-model="q.monitor" :placeholder="$t('cctv.pe_monitor')" />
      </el-form-item>

    </el-form>

    <el-form :inline="true">
      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          {{ $t('cctv.search') }}
        </el-button>
      </el-form-item>

      <!-- 导出按钮 -->
      <el-form-item>
        <el-button type="info" @click="handleExcel">
          {{ $t('cctv.exportExcel') }}
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
    <!--列表-->
    <daily-log-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit Daily Log':'New Daily Log'">
      <el-form :ref="formName" :model="d" :rules="rules" label-width="80px" label-position="left">
        <!--日期-->
        <el-form-item :label="$t('cctv.date')">

          <el-col :span="11">
            <el-form-item prop="date">
              <el-date-picker
                v-model="d.date"
                type="date"
                value-format="yyyy-MM-dd"
                :placeholder="$t('cctv.ps_date')"
              />
            </el-form-item>
          </el-col>

          <el-col class="line" :span="2">-</el-col>

          <el-col :span="11">
            <el-form-item prop="time">
              <el-time-picker
                v-model="d.time"
                value-format="HH:mm:ss"
                :placeholder="$t('cctv.ps_time')"
                style="width: 100%;"
              />
            </el-form-item>
          </el-col>

        </el-form-item>

        <!--台号-->
        <el-form-item :label="$t('cctv.tableCode')" prop="tableCode">
          <el-input v-model="d.tableCode" :placeholder="$t('cctv.pe_tableCode')" />
        </el-form-item>
        <!--主题-->
        <el-form-item :label="$t('cctv.subject')" prop="subject">
          <el-input v-model="d.subject" :placeholder="$t('cctv.pe_subject')" />
        </el-form-item>
        <!--细节-->
        <el-form-item :label="$t('cctv.details')" prop="details">
          <el-input v-model="d.details" type="textarea" :placeholder="$t('cctv.pe_details')" />
        </el-form-item>
        <!--通知人-->
        <el-form-item :label="$t('cctv.alerterName')" prop="alerterName">
          <el-input v-model="d.alerterName" :placeholder="$t('cctv.pe_alerterName')" />
        </el-form-item>
        <!--部门-->
        <el-form-item :label="$t('cctv.department')" prop="departmentId">
          <el-select v-model="d.departmentId" filterable :placeholder="$t('cctv.pe_department')">
            <el-option
              v-for="item in departmentList"
              :key="item.departmentId"
              :label="item.departmentCode"
              :value="item.departmentId"
            />
          </el-select>
        </el-form-item>
        <!--监控部-->
        <el-form-item :label="$t('cctv.monitor')" prop="monitor">
          <el-input v-model="d.monitor" :placeholder="$t('cctv.pe_monitor')" />
        </el-form-item>
        <!--结论-->
        <el-form-item :label="$t('cctv.ending')" prop="conclusion">
          <el-input v-model="d.conclusion" :placeholder="$t('cctv.pe_ending')" />
        </el-form-item>

        <!--图片-->
        <el-form-item :label="$t('cctv.image')">
          <multiple-images :urls.sync="d.urls" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
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

  </div>
</template>

<script>
import DailyLogPage from './components/DailyLogPage'
import MultipleImages from '@/components/Upload/MultipleImages'
import { saveDailyLog, exportDailyLogExcel, updateDailyLog, deleteFormDailyLogById } from '@/api/daily-log'
import { downloadExcelByKey, deepClone } from '@/utils'
import { getDepartments } from '@/api/department'
import { getCurrentTime, getCurrentDay, getTomorrowDay} from './common/common'

const data = {
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
const queryData = {
  needImg: true,
  tableCode: null,
  subject: null,
  departmentName: null,
  monitor: null,
  startDate: getCurrentDay(),
  endDate: getTomorrowDay()
}
export default {
  name: 'DailyLog',
  components: { DailyLogPage, MultipleImages },
  data() {
    return {
      q: deepClone(queryData),
      d: deepClone(data),
      departmentList: [],
      searchTime: [getCurrentDay(), getTomorrowDay()],
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        date: [{ required: true, message: 'not null', trigger: 'blur' }],
        time: [{ required: true, trigger: 'blur', message: 'not null' }],
        tableCode: [{ required: true, trigger: 'blur', message: 'not null' }],
        subject: [{ required: true, trigger: 'blur', message: 'not null' }],
        details: [{ required: true, trigger: 'blur', message: 'not null' }],
        alerterName: [{ required: true, trigger: 'blur', message: 'not null' }],
        departmentId: [
          { required: true, trigger: 'blur', message: 'not null' },
          { type: 'number', trigger: 'blur', message: '必须是数字' }
        ],
        monitor: [{ required: true, trigger: 'blur', message: 'not null' }],
        conclusion: [{ required: true, trigger: 'blur', message: 'not null' }]
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
          startDate,
          endDate
        })
      }
    }
  },
  created() {
    this.initDepartmentList()
  },
  methods: {
    resetQueryData() {
      this.searchTime = [getCurrentDay(), getTomorrowDay()];
      this.q = deepClone(queryData)
    },
    reset() {
      if (this.$refs[this.formName] != null) {
        this.$refs[this.formName].resetFields()
      }
      this.d = deepClone(data)
      this.d.date = getCurrentDay()
      this.d.time = getCurrentTime()
    },
    handleAdd() {
      this.reset()
      this.showMark = true
      this.dialogType = 'new'
    },
    async handleExcel() {
      const res = await exportDailyLogExcel(this.q)
      if (res.code === 0) {
        downloadExcelByKey(res.key)
      }
    },
    handleEdit(scope) {
      const clone = deepClone(scope.row)
      this.$set(this, 'd', clone)
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
      if (this.d.id) {
        res = await updateDailyLog(this.d)
      } else {
        res = await saveDailyLog(this.d)
      }
      if (res.code === 0) {
        this.showMark = false
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
    async initDepartmentList() {
      const res = await getDepartments()
      if (res.code === 0) {
        this.departmentList = res.data
      }
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
