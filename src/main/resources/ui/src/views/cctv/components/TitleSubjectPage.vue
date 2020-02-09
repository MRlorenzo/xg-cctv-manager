<template>
  <div>
    <el-table :data="dataList" style="width: 100%;margin-top:30px;" border>

      <!--事件名称-->
      <el-table-column align="center" label="事件名称" width="220">
        <template slot-scope="scope">
          {{ scope.row.subject }}
        </template>
      </el-table-column>
      <!--创建时间-->
      <el-table-column align="center" label="创建时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">
            {{ $t('permission.editUser') }}
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">
            {{ $t('permission.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageLimit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import { getTtitleSubjectItemForPage } from "@/api/title-liist-item";
    export default {
      name: "title-subject-page",
      props: {
        query: {
          type: Object,
          required: true
        },
        doSearch:{
          type: Boolean,
          required: true
        },
        handleEdit: {
          type: Function,
          required: true
        },
        handleDelete: {
          type: Function,
          required: true
        }
      },
      data(){
        return {
          pageLimit: 5,
          currPage: 1,
          page: {
            total: 0 ,
            records: []
          }
        }
      },
      computed: {
        dataList(){
          return this.page.records
        },
        total(){
          return this.page.total
        }
      },
      watch: {
        currPage() {
          this.toBeChangeSearch(true)
        },
        pageLimit(){
          this.toBeChangeSearch(true)
        },
        doSearch( bl ){
          if (bl){
            this.loadData()
          }
        }
      },
      methods: {
        toBeChangeSearch( bl ){
          this.$emit('update:doSearch' , bl)
        },
        async loadData(){
          this.toBeChangeSearch(false)
          const res = await getTtitleSubjectItemForPage(Object.assign({
              current: this.currPage ,
              size: this.pageLimit
            } ,
            this.query
          ))
          this.page = res.data

        },
        handleSizeChange(val) {
          this.pageLimit = val
        },
        handleCurrentChange(val) {
          this.currPage = val
        }
      },
      created(){
        if ( this.doSearch ){
          this.loadData()
        }
      }
    }
</script>

<style scoped>

</style>
