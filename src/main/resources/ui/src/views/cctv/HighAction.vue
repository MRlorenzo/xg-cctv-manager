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
      <!--币种-->
      <el-form-item :label="$t('cctv.coinType')">
        <el-select v-model="q.coinCode" filterable :placeholder="$t('cctv.ps_coinType')">
          <el-option
            v-for="c in coinList"
            :key="c.id"
            :label="c.label"
            :value="c.code"
          />
        </el-select>
      </el-form-item>
      <!--编码-->
      <el-form-item :label="$t('cctv.eventCode')">
        <el-select v-model="q.code" filterable :placeholder="$t('cctv.ps_eventCode')">
          <el-option
            v-for="t in titleSubjectList"
            :key="t.id"
            :label="t.code"
            :value="t.code"
          />
        </el-select>
      </el-form-item>

      <!--涉及员工-->
      <el-form-item :label="$t('cctv.involveEmp')">
        <el-select
          v-model="q.involveUid"
          filterable
          remote
          reserve-keyword
          :placeholder="$t('cctv.pe_key')"
          :remote-method="remoteMethod"
          :loading="loading"
        >
          <el-option
            v-for="emp in empList"
            :key="emp.userId"
            :label="emp.username"
            :value="emp.userId"
          />
        </el-select>
      </el-form-item>
      <!--监控部-->
      <el-form-item :label="$t('cctv.monitor')">
        <el-input v-model="q.monitor" :placeholder="$t('cctv.pe_monitor')" />
      </el-form-item>
      <!--备注-->
      <el-form-item :label="$t('cctv.remarks')">
        <el-input v-model="q.remarks" :placeholder="$t('cctv.pe_remarks')" />
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

    <high-action-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :ref="formName" :model="d" :rules="rules" label-width="80px" label-position="left">
        <!--&lt;!&ndash;序号&ndash;&gt;-->
        <!--<el-form-item :label="$t('cctv.no')" prop="no">-->
        <!--<el-input v-model="d.no" :placeholder="$t('cctv.pe_no')" />-->
        <!--</el-form-item>-->
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
                v-model="d.time"
                :placeholder="$t('cctv.ps_time')"
                value-format="HH:mm:ss"
                style="width: 100%;"
              />
            </el-form-item>
          </el-col>

        </el-form-item>
        <!--台号-->
        <el-form-item :label="$t('cctv.tableCode')" prop="tableCode">
          <el-input v-model="d.tableCode" :placeholder="$t('cctv.pe_tableCode')" />
        </el-form-item>
        <!--编码-->
        <el-form-item :label="$t('cctv.eventCode')" prop="code">
          <el-select v-model="d.code" filterable :placeholder="$t('cctv.ps_eventCode')" @change="selectGet">
            <el-option
              v-for="t in titleSubjectList"
              :key="t.id"
              :label="t.code"
              :value="t.code"
            />
          </el-select>
        </el-form-item>
        <!--事件主题-->
        <el-form-item :label="$t('cctv.subject')">
          <el-input v-model="d._subjectTitle" readonly />
        </el-form-item>
        <!--币种-->
        <el-form-item :label="$t('cctv.coinType')" prop="coinCode">
          <el-select v-model="d.coinCode" filterable :placeholder="$t('cctv.ps_coinType')">
            <el-option
              v-for="c in coinList"
              :key="c.id"
              :label="c.label"
              :value="c.code"
            />
          </el-select>
        </el-form-item>
        <!--金额-->
        <el-form-item :label="$t('cctv.total')" prop="total">
          <el-input v-model="d.total" type="number" :placeholder="$t('cctv.pe_total')" />
        </el-form-item>
        <!--上下水报告-->
        <el-form-item :label="$t('cctv.report')" prop="total">
          <el-input v-model="d.report" type="textarea" :placeholder="$t('cctv.pe_report')" />
        </el-form-item>
        <!--涉及员工-->
        <el-form-item :label="$t('cctv.involveEmp')" prop="involveUid">
          <el-select
            v-model="d.involveUid"
            filterable
            remote
            reserve-keyword
            :placeholder="$t('cctv.pe_key')"
            :remote-method="remoteMethod"
            :loading="loading"
          >
            <el-option
              v-for="emp in empList"
              :key="emp.userId"
              :label="emp.username"
              :value="emp.userId"
            />
          </el-select>
        </el-form-item>
        <!--监控部-->
        <el-form-item :label="$t('cctv.monitor')" prop="monitor">
          <el-input v-model="d.monitor" :placeholder="$t('cctv.pe_monitor')" />
        </el-form-item>
        <!--备注-->
        <el-form-item :label="$t('cctv.remarks')" prop="remarks">
          <el-input v-model="d.remarks" :placeholder="$t('cctv.pe_remarks')" />
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
import HighActionPage from './components/HighActionPage'
import MultipleImages from '@/components/Upload/MultipleImages'
import { saveHighAction, deleteHighAction, updateHighAction, exportHighActionExcel } from '@/api/high-action'
import { downloadExcelByKey, deepClone } from '@/utils'
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
  monitor: null,
  remarks: null,
  urls: '',
  _subjectTitle: null
}
const queryData = {
  needImg: true,
  tableCode: null,
  coinCode: null,
  code: null,
  involveUid: null,
  monitor: null,
  remarks: null
}
export default {
  name: 'HighAction',
  components: { HighActionPage, MultipleImages },
  data() {
    return {
      q: deepClone(queryData),
      searchTime: [],
      d: deepClone(data),
      titleSubjectList: [], // 事件列表
      coinList: deepClone(coinList), // 币种列表
      empList: [], // 员工列表
      loading: false,
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        // no: [{ required: true, trigger: 'blur' , message:'not null'}],
        date: [{ required: true, message: 'not null', trigger: 'blur' }],
        time: [{ required: true, trigger: 'blur', message: 'not null' }],
        tableCode: [{ required: true, trigger: 'blur', message: 'not null' }],
        code: [{ required: true, trigger: 'blur', message: 'not null' }],
        coinCode: [{ required: true, trigger: 'blur', message: 'not null' }],
        total: [{ required: true, trigger: 'blur', message: 'not null' }],
        report: [{ required: true, trigger: 'blur', message: 'not null' }],
        involveUid: [
          { required: true, trigger: 'blur', message: 'not null' },
          { type: 'number', trigger: 'blur', message: '必须是数字' }
        ],
        monitor: [{ required: true, trigger: 'blur', message: 'not null' }],
        remarks: [{ required: true, trigger: 'blur', message: 'not null' }]
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
    this.initTitleSubjectList()
  },
  methods: {
    resetQueryData() {
      this.q = deepClone(queryData)
    },
    reset() {
      if (this.$refs[this.formName] != null) {
        this.$refs[this.formName].resetFields()
      }
      this.d = deepClone(data)
    },
    handleAdd() {
      this.reset()
      this.showMark = true
      this.dialogType = 'new'
    },
    async handleExcel() {
      const res = await exportHighActionExcel(this.q)
      if (res.code === 0) {
        downloadExcelByKey(res.key)
      }
    },
    handleEdit(scope) {
      const clone = deepClone(scope.row)
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
          const res = await deleteHighAction(row.id)
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
        res = await updateHighAction(this.d)
      } else {
        res = await saveHighAction(this.d)
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
    async initTitleSubjectList() {
      const res = await getTtitleListItemList()
      if (res.code === 0) {
        this.titleSubjectList = res.data
      }
    },
    async remoteMethod(name) {
      this.loading = true
      const res = await findUserLikeName(name)
      if (res.code === 0) {
        this.loading = false
        this.empList = res.data
      }
    },
    // 下拉框选中事件
    selectGet(vlaue) {
      let obj = {}
      obj = this.titleSubjectList.find((item) => {
        return item.code === vlaue// 筛选出匹配数据
      })
      this.d._subjectTitle = obj.text
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
