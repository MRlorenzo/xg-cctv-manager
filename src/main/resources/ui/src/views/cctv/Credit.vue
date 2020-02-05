<template>
  <div class="app-container">
    <el-form :inline="true">

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

    <credit-page
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
          <el-input v-model="d.tableCode" placeholder="No" />
        </el-form-item>
        <!--金额-->
        <el-form-item label="金额">
          <el-input v-model="d.total" placeholder="No" />
        </el-form-item>
        <!--币种-->
        <el-form-item label="币种">
          <el-input v-model="d.coinCode" placeholder="No" />
        </el-form-item>
        <!--通知人-->
        <el-form-item label="通知人">
          <el-input v-model="d.alerterName" placeholder="No" />
        </el-form-item>
        <!--监控部-->
        <el-form-item label="监控部">
          <el-input v-model="d.monitor" placeholder="No" />
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
export default {
  name: 'Credit',
  components: { CreditPage },
  data() {
    return {
      q: {},
      d: {},
      doSearch: true,
      showMark: false,
      dialogType: 'edit' // 'edit' or 'new'
    }
  },
  methods: {
    resetQueryData() {},
    handleAdd() {
      this.showMark = true
    },
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
