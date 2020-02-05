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

    <customer-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" label-width="80px" label-position="left">
        <!--名称-->
        <el-form-item label="名称">
          <el-input v-model="d.name" placeholder="No" />
        </el-form-item>
        <!--卡号-->
        <el-form-item label="卡号">
          <el-input v-model="d.cardNumber" placeholder="No" />
        </el-form-item>
        <!--位置-->
        <el-form-item label="位置">
          <el-input v-model="d.location" placeholder="No" />
        </el-form-item>
        <!--平均下注-->
        <el-form-item label="平均下注">
          <el-input v-model="d.avgBetting" placeholder="No" />
        </el-form-item>
        <!--国籍-->
        <el-form-item label="国籍">
          <el-input v-model="d.nationality" placeholder="No" />
        </el-form-item>
        <!--照片-->
        <el-form-item label="照片">
          <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="d.imageUrl" :src="d.imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <!--状态-->
        <el-form-item label="状态">
          <el-input v-model="d.status" placeholder="No" />
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
import CustomerPage from './components/CustomerPage'
export default {
  name: 'Customer',
  components: { CustomerPage },
  data() {
    return {
      q: {},
      d: { imageUrl: '' },
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
    confirm() {},
    handleAvatarSuccess(res, file) {
      this.d.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style scoped>
  .app-container {

  }
  .avatar-uploader {
    .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .el-upload:hover {
      border-color: #409EFF;
    }
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
