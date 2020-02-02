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
        <el-button @click="resetQueryData" >
          Reset
        </el-button>
      </el-form-item>

      <el-form-item >
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          Add
        </el-button>
      </el-form-item>
    </el-form>

    <fill-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"/>

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit User':'New User'">
      <el-form :model="d" label-width="80px" label-position="left">

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
  import FillPage from './components/FillPage'
  export default {
    name: "fill",
    components: { FillPage },
    data(){
      return {
        q: {},
        d: {},
        doSearch: true,
        showMark: false,
        dialogType: 'edit' , // 'edit' or 'new'
      }
    },
    methods: {
      resetQueryData(){},
      handleAdd(){},
      handleEdit( scope ){

      },
      handleDelete({ $index, row }){

      },
      confirm(){}
    }
  }
</script>

<style scoped>
  .app-container {

  }
</style>
