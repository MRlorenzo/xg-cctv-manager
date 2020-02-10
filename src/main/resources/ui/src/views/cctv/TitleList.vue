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
      <el-form :model="d" :ref="formName" :rules="rules" label-width="80px" label-position="left">

        <!--主题id-->
        <el-form-item label="主题" prop="subjectId">
          <el-select v-model="d.subjectId" placeholder="请选择">
            <el-option
              v-for="item in mainList"
              :key="item.id"
              :label="item.subject"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>

        <!--内容-->
        <el-form-item label="内容" prop="text">
          <el-input v-model="d.text" placeholder="" />
        </el-form-item>

        <!--代码-->
        <el-form-item label="代码" prop="code">
          <el-input v-model="d.code" placeholder="" />
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
import TitleListPage from './components/TitleListPage'
import { getTtitleMainList } from '@/api/title-main'
import {saveTitleListItem , deleteTtitleListItemById , updateTtitleListItem} from '@/api/title-liist-item'
import {deepClone} from "@/utils";

export default {
  name: 'TitleList',
  components: { TitleListPage },
  data() {
    return {
      q: {},
      d: {},
      mainList: [],
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      formName: 'form',
      rules: {
        subjectId: [
          { required: true, trigger: 'blur' , message: 'not null'},
          { type: 'number', trigger: 'blur' , message: '必须是数字'}
        ],
        text: [{ required: true, trigger: 'blur' , message:'not null'}],
        code: [{ required: true, trigger: 'blur' , message:'not null'}]
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
      this.$confirm('Confirm ?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          const res = await deleteTtitleListItemById(row.id)
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
        res = await updateTtitleListItem(this.d)
      }else {
        res = await saveTitleListItem(this.d)
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
    async initMainList(){
      const res = await getTtitleMainList()
      if (res.code === 0){
        this.mainList = res.data
      }
    }
  },
  created(){
    this.initMainList()
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
