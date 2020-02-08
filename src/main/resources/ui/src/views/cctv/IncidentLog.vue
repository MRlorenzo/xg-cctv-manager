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
        <el-input v-model="q.tableCode" placeholder="No" />
      </el-form-item>
      <!--事件编码-->
      <el-form-item label="事件编码">
        <el-input v-model="q.code" placeholder="No" />
      </el-form-item>
      <!--币种-->
      <el-form-item label="币种">
        <el-input v-model="q.coinCode" placeholder="No" />
      </el-form-item>

      <!--报告-->
      <el-form-item label="报告">
        <el-input v-model="q.report" placeholder="No" />
      </el-form-item>
      <!--涉及员工-->
      <el-form-item label="涉及员工">
        <el-input v-model="q.involveUid" placeholder="No" />
      </el-form-item>
      <!--部门id-->
      <el-form-item label="部门">
        <el-input v-model="q.departmentId" placeholder="No" />
      </el-form-item>
      <!--监控部-->
      <el-form-item label="监控部">
        <el-input v-model="q.monitor" placeholder="No" />
      </el-form-item>
      <!--备注-->
      <el-form-item label="备注">
        <el-input v-model="q.remarks" placeholder="No" />
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          Search
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
      <el-form :model="d" label-width="80px" label-position="left">
        <!--日期-->
        <el-form-item label="日期">
          <el-date-picker
            v-model="d.date"
            type="datetime"
            placeholder="选择日期"
          />
        </el-form-item>
        <!--台号-->
        <el-form-item label="台号">
          <el-input v-model="d.tableCode" placeholder="No" />
        </el-form-item>
        <!--事件编码-->
        <el-form-item label="事件编码">
          <el-input v-model="d.code" placeholder="No" />
        </el-form-item>
        <!--币种-->
        <el-form-item label="币种">
          <el-input v-model="d.coinCode" placeholder="No" />
        </el-form-item>
        <!--金额-->
        <el-form-item label="金额">
          <el-input v-model="d.total" placeholder="No" />
        </el-form-item>
        <!--报告-->
        <el-form-item label="报告">
          <el-input v-model="d.report" placeholder="No" />
        </el-form-item>
        <!--涉及员工-->
        <el-form-item label="涉及员工">
          <el-input v-model="d.involveUid" placeholder="No" />
        </el-form-item>
        <!--部门id-->
        <el-form-item label="部门">
          <el-input v-model="d.departmentId" placeholder="No" />
        </el-form-item>
        <!--监控部-->
        <el-form-item label="监控部">
          <el-input v-model="d.monitor" placeholder="No" />
        </el-form-item>
        <!--备注-->
        <el-form-item label="备注">
          <el-input v-model="d.remarks" placeholder="No" />
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
import IncidentLogPage from './components/IncidentLogPage'
import MultipleImages from '@/components/Upload/MultipleImages'
export default {
  name: 'IncidentLog',
  components: { IncidentLogPage, MultipleImages },
  data() {
    return {
      q: {},
      searchTime: [],
      d: { urls: ''},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
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
    resetQueryData() {},
    handleAdd() {},
    handleEdit(scope) {

    },
    handleDelete({ $index, row }) {

    },
    confirm() {}
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
