<template>
  <div>
    <el-table :data="dataList"style="width: 100%;margin-top:30px;" border>

      <!--事件标题-->
      <el-table-column align="center" :label="$t('cctv.subject')" >
        <template slot-scope="scope">
          {{ scope.row.subjectId }}
        </template>
      </el-table-column>
      <!--代码-->
      <el-table-column align="center" :label="$t('cctv.code')" >
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <!--详细-->
      <el-table-column align="center" :label="$t('cctv.details')" >
        <template slot-scope="scope">
          {{ scope.row.text }}
        </template>
      </el-table-column>

      <!--创建时间-->
      <el-table-column align="center" :label="$t('cctv.createTime')" >
        <template slot-scope="scope">
          {{ scope.row.createTime| dateTimeFilter}}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations" >
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">
            {{ $t('cctv.edit') }}
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">
            {{ $t('cctv.delete') }}
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
  import { getTtitleListItemForPage } from "@/api/title-liist-item";
    export default {
      name: "title-list-page",
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
          const res = await getTtitleListItemForPage(Object.assign({
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
