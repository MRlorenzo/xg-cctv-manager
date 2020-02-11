<template>
  <el-upload
    class="upload-demo"
    action=""
    :on-remove="handleRemove"
    :file-list="fileList"
    list-type="picture"
    :http-request="uploadFileupload"
  >

    <el-button size="small" type="primary">{{$t('cctv.clickToUpload')}}</el-button>
    <div slot="tip" class="el-upload__tip">{{$t('cctv.uploadTip')}}</div>
  </el-upload>
</template>

<script>
  import { uploadFile } from '@/api/oss'
  export default {
    name: "multiple-images",
    props: {
      urls: {
        type: String,
        default: ''
      }
    },
    data(){
      return {
        uploadedFileMap: {}, // 已上传的文件映射 key: fileName, value: url
        fileList: []
      }
    },
    watch: {
      uploadedFileMap: {
        handler( map ){
          this.toBeChangeURLS(Object.values(map).filter(e=>e).join(','))
        },
        deep: true
      },
      urls: {
        handler( str ){
          if (typeof str !== 'string'){
            this.uploadedFileMap = {}
            this.fileList = []
          }else if (typeof str === 'string' && str.length === 0 && Object.keys(this.uploadedFileMap).length){
            this.uploadedFileMap = {}
            this.fileList = []
          }else if (typeof str === 'string' &&str.trim().length && Object.keys(this.uploadedFileMap).length === 0){
            let urllist = str.split(',')
            this.fileList = urllist.map(url =>{
              let lastIndex = url.lastIndexOf('/')
              if (!~lastIndex){
                lastIndex = url.lastIndexOf('\\')
              }
              let index = lastIndex + 1
              let name = url.substring(index)
              return {
                name: name,
                url: 'file' + url
              }
            })
          }
        },
        deep: true
      }
    },
    created(){
      let str = this.urls
      if (typeof str === 'string' &&str.trim().length && Object.keys(this.uploadedFileMap).length === 0){
        let urllist = str.split(',')
        this.fileList = urllist.map(url =>{
          let lastIndex = url.lastIndexOf('/')
          if (!~lastIndex){
            lastIndex = url.lastIndexOf('\\')
          }
          let index = lastIndex + 1
          let name = url.substring(index)
          this.$set(this.uploadedFileMap , name , url)
          return {
            name: name,
            url: 'file' + url
          }
        })
      }
    },
    methods: {
      toBeChangeURLS( urls ){
        this.$emit('update:urls' , urls)
      },
      // upload
      handleRemove(file, fileList) {
        console.log(file)
        this.$delete(this.uploadedFileMap , file.name)
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
            this.$set(this.uploadedFileMap , option.file.name , res.url)
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

<style scoped>

</style>
