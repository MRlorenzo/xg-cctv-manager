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

    <high-action-page
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
        <!--时间-->
        <el-form-item label="时间">
          <el-input v-model="d.time" placeholder="No" />
        </el-form-item>
        <!--台号-->
        <el-form-item label="台号">
          <el-input v-model="d.tableCode" placeholder="No" />
        </el-form-item>
        <!--编码-->
        <el-form-item label="编码">
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
        <!--上下水报告-->
        <el-form-item label="上下水报告">
          <el-input v-model="d.report" placeholder="No" />
        </el-form-item>
        <!--涉及员工-->
        <el-form-item label="涉及员工">
          <el-input v-model="d.involveUid" placeholder="No" />
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
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
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
import HighActionPage from './components/HighActionPage'
export default {
  name: 'HighAction',
  components: { HighActionPage },
  data() {
    return {
      q: {},
      d: {},
      doSearch: true,
      showMark: false,
      dialogType: 'edit', // 'edit' or 'new'
      fileList: []
    }
  },
  methods: {
    resetQueryData() {},
    handleAdd() {},
    handleEdit(scope) {

    },
    handleDelete({ $index, row }) {

    },
    confirm() {},
    // upload
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
</style>
