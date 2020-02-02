<template>
  <div>
    <el-table :data="dataList" style="width: 100%;margin-top:30px;" border>

      <!--序号-->
      <!--
      <el-table-column align="center" label="" width="220">
        <template slot-scope="scope">
          {{ scope.row }}
        </template>
      </el-table-column>
      -->
      <!--日期-->
      <el-table-column align="center" label="date" width="220">
        <template slot-scope="scope">
          {{ scope.row.date }}
        </template>
      </el-table-column>
      <!--时间-->
      <!--
      <el-table-column align="center" label="" width="220">
        <template slot-scope="scope">
          {{ scope.row }}
        </template>
      </el-table-column>
      -->
      <!--台号-->
      <el-table-column align="center" label="tableCode" width="220">
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>
      <!--主题-->
      <el-table-column align="center" label="subject" width="220">
        <template slot-scope="scope">
          {{ scope.row.subject }}
        </template>
      </el-table-column>
      <!--相关信息-->
      <el-table-column align="center" label="details" width="220">
        <template slot-scope="scope">
          {{ scope.row.details }}
        </template>
      </el-table-column>
      <!--通知人-->
      <el-table-column align="center" label="alerterName" width="220">
        <template slot-scope="scope">
          {{ scope.row.alerterName }}
        </template>
      </el-table-column>
      <!--部门-->
      <el-table-column align="center" label="departmentId" width="220">
        <template slot-scope="scope">
          {{ scope.row.departmentId }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" label="monitor" width="220">
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>
      <!--图片-->
      <!--
      <el-table-column align="center" label="" width="220">
        <template slot-scope="scope">
          {{ scope.row }}
        </template>
      </el-table-column>
      -->

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
  import { getDailyLogsForPage } from "@/api/daily-log";
    export default {
      name: "daily-log-page",
      props: {
        query: {
          type: Object,
          required: true
        },
        doSearch:{
          type: Boolean,
          required: true,
          default: false
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
          const res = await getDailyLogsForPage(Object.assign({
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
