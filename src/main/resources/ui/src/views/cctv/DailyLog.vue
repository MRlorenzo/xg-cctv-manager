<template>
  <div class="app-container">
    <el-form :inline="true">

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

      <el-form-item>
        <!-- test -->
        <el-button type="info" @click="handleTest">
          test
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
      <el-form :model="d" label-width="80px" label-position="left">
        <!--序号-->
        <el-form-item label="序号">
          <el-input v-model="d.no" placeholder="No" />
        </el-form-item>
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
          <el-input v-model="d.tableCode" placeholder="" />
        </el-form-item>
        <!--主题-->
        <el-form-item label="主题">
          <el-input v-model="d.subject" placeholder="" />
        </el-form-item>
        <!--细节-->
        <el-form-item label="细节">
          <el-input v-model="d.details" placeholder="" />
        </el-form-item>
        <!--通知人-->
        <el-form-item label="通知人">
          <el-input v-model="d.alerterName" placeholder="" />
        </el-form-item>
        <!--部门-->
        <el-form-item label="部门">
          <el-input v-model="d.departmentId" placeholder="" />
        </el-form-item>
        <!--监控部-->
        <el-form-item label="监控部">
          <el-input v-model="d.monitor" placeholder="" />
        </el-form-item>
        <!--结论-->
        <el-form-item label="结论">
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
import {test , downloadExcelByKey } from "@/api/oss";

export default {
  name: 'DailyLog',
  components: { DailyLogPage, MultipleImages },
  data() {
    return {
      q: {},
      d: { urls: ''},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
    }
  },
  methods: {
    resetQueryData() {

    },
    handleAdd() {
      this.showMark = true
    },
    handleEdit(scope) {

    },
    handleDelete({ $index, row }) {

    },
    confirm() {
    },
    async handleTest(){
      const res = await test()
      if (res.code === 0){
        downloadExcelByKey(res.key)
      }
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
