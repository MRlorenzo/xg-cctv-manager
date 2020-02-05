<template>
  <el-upload
    action=""
    class="avatar-uploader"
    :show-file-list="false"
    :multiple="false"
    :on-success="handleAvatarSuccess"
    :http-request="uploadFileupload"
  >
    <img v-if="url" :src="preViewUrl" class="avatar">
    <i v-else class="el-icon-plus avatar-uploader-icon" />
  </el-upload>
</template>

<script>
import { uploadFile } from '@/api/oss'
export default {
  name: "avatar-image",
  props: {
    url: String
  },
  data(){
    return {
      preViewUrl: ''
    }
  },
  methods: {
    toBeChangeImageUrl( url ){
      this.$emit('update:url' , url)
    },
    handleAvatarSuccess(res, file) {
      this.preViewUrl = URL.createObjectURL(file.raw)
    },
    async uploadFileupload(option) {

      // 创建一个FormData 对象
      const formData = new FormData();
      //用户设置了上传时附带的额外参数时
      if (option.data) {
        Object.keys(option.data).forEach(key => {
          // 添加一个新值到 formData 对象内的一个已存在的键中，如果键不存在则会添加该键。
          formData.append(key, option.data[key]);
        });
      }

      formData.append(option.filename, option.file, option.file.name);
      // option.onProgress
      // option.onError
      // option.onSuccess
      try {
        const res = await uploadFile(formData , option.onProgress)
        if (res.code === 0){
          option.onSuccess(res)
          this.toBeChangeImageUrl(res.url)
        }else {
          option.onError(new Error(res.msg))
        }
      } catch (e){
        option.onError(e)
      }

    }
  }
}
</script>

<style >
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
