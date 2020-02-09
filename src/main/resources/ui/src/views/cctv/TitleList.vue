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

    <title-list-page
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
import TitleListPage from './components/TitleListPage'
export default {
  name: 'TitleList',
  components: { TitleListPage },
  data() {
    return {
      q: {},
      d: {},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      rules: {
        no: [{ required: true, trigger: 'blur' , message:''}]
      }
    }
  },
  methods: {
    resetQueryData() {},
    handleAdd() {
      this.d = {}
      this.showMark = true
      this.dialogType = 'new'
    },
    handleEdit(scope) {
      let clone = deepClone(scope.row)
      this.d = clone
      this.showMark = true
      this.dialogType = 'edit'
    },
    handleDelete({ $index, row }) {
      /*this.$confirm('Confirm ?', 'Warning', {
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
        .catch(err => { console.error(err) })*/
    },
    async confirm() {
      /*let res
      if (this.d.id){
        res = await updateDailyLog(this.d)
      }else {
        res = await saveDailyLog(this.d)
      }
      if (res.code === 0){
        this.showMark = false
        this.$message.success('提交成功')
      }*/
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
