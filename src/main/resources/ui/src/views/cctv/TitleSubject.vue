<template>
  <div class="app-container">
    <el-form :inline="true">

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          {{ $t('cctv.search') }}
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

    <title-subject-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit Title Subject':'New Title Subject'">
      <el-form :ref="formName" :model="d" :rules="rules" label-width="80px" label-position="left">
        <!--主题-->
        <el-form-item :label="$t('cctv.subject')" prop="subject">
          <el-input v-model="d.subject" :placeholder="$t('cctv.pe_subject')" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
          {{ $t('cctv.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('cctv.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import TitleSubjectPage from './components/TitleSubjectPage'
import { saveTtitleMain, deleteTtitleMainById, updateTtitleMain } from '@/api/title-main'
import { deepClone } from '@/utils'

export default {
  name: 'TitleList',
  components: { TitleSubjectPage },
  data() {
    return {
      q: {},
      d: {},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        subject: [{ required: true, trigger: 'blur', message: 'not null' }]
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
          const res = await deleteTtitleMainById(row.id)
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
        res = await updateTtitleMain(this.d)
      } else {
        res = await saveTtitleMain(this.d)
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
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
