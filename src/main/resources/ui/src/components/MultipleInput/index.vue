<template>
  <div>
    <el-form-item
      v-for="(domain, index) in values"
      :label="label + (index + 1)"
      :key="domain.key"
    >
      <el-input v-model="domain.value" @input="onChangeHandle" :placeholder="placeholder"></el-input>
      <el-button @click.prevent="removeDomain(domain)">删除</el-button>
    </el-form-item>
  </div>
</template>

<script>
  import {createUniqueString } from '@/utils'
  export default {
    name: "MultipleInput",
    props: {
      value: {
        type: String
      },
      label: {
        type: String,
        default: ''
      },
      placeholder: {
        type: String,
        default: ''
      }
    },
    data(){
      return {
        values: [{
          key: createUniqueString(),
          value: ''
        }]
      }
    },
    watch: {
      value( str ){
        this.string2values(str)
      },
      values(){
        this.onChangeHandle()
      }
    },
    methods: {
      doChange( value ){
        this.$emit('input', value)
      },
      onChangeHandle( ){
        let vals = this.values
        this.doChange(vals.filter(v=>v.value&&v.value.trim()).map(v=>v.value).join(','))
      },
      removeDomain(item) {
        if (this.values.length > 1)
          this.values = this.values.filter(v => v.key !== item.key)
      },
      string2values( str ){
        if (str){
          let values = str.split(',').map(v=>{
            return {
              key: createUniqueString(),
              value: v
            }
          })
          values.push({
            value: '',
            key: createUniqueString()
          })
          this.values = values
        }else {
          this.values = [{
            value: '',
            key: createUniqueString()
          }]
        }
      }
    },
    created(){
      let str = this.value
      this.string2values(str)
    }
  }
</script>

<style scoped>

</style>
