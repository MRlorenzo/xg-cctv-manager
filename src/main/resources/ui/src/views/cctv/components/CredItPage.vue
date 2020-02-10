<template>
  <div>
    <el-table :data="dataList" height="500" border>
      <!--序号-->
      <el-table-column align="center" label="no" >
        <template slot-scope="scope">
          {{ scope.row.no }}
        </template>
      </el-table-column>
      <!--日期-->
      <el-table-column align="center" label="date" >
        <template slot-scope="scope">
          {{ scope.row.date | dateTimeFilter }}
        </template>
      </el-table-column>
      <!--时间-->
      <el-table-column align="center" label="time" >
        <template slot-scope="scope">
          {{ scope.row.time }}
        </template>
      </el-table-column>
      <!--台号-->
      <el-table-column align="center" label="tableCode" >
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>
      <!--币种-->
      <el-table-column align="center" label="coinCode" >
        <template slot-scope="scope">
          {{ scope.row.coinCode }}
        </template>
      </el-table-column>
      <!--金额-->
      <el-table-column align="center" label="total" >
        <template slot-scope="scope">
          {{ scope.row.total }}
        </template>
      </el-table-column>
      <!--序列号-->
      <el-table-column align="center" label="no" >
        <template slot-scope="scope">
          {{ scope.row.no }}
        </template>
      </el-table-column>
      <!--通知人-->
      <el-table-column align="center" label="alerterName" >
        <template slot-scope="scope">
          {{ scope.row.alerterName }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" label="monitor" >
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations" >
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">
            {{ 'edit' }}
          </el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">
            {{ 'delete' }}
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
  import { getFillAndCreditsForPage } from "@/api/fill-and-credit";
    export default {
      name: "credit-page",
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
          const res = await getFillAndCreditsForPage(Object.assign({
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
