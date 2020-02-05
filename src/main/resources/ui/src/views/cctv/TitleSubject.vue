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

    <title-subject-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" label-width="80px" label-position="left">
        <!--序号-->
        <!--<el-form-item label="">
          <el-input v-model="d.no" placeholder="No" />
        </el-form-item>-->
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
import TitleSubjectPage from './components/TitleSubjectPage'
export default {
  name: 'TitleList',
  components: { TitleSubjectPage },
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
