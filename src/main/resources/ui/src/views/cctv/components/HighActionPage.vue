<template>
  <div>
    <el-table :data="dataList" style="width: 100%;margin-top:30px;" border>

      <!--序号-->
      <el-table-column align="center" label="no" width="220">
        <template slot-scope="scope">
          {{ scope.row.no }}
        </template>
      </el-table-column>
      <!--日期-->
      <el-table-column align="center" label="date" width="220">
        <template slot-scope="scope">
          {{ scope.row.date }}
        </template>
      </el-table-column>
      <!--时间-->
      <el-table-column align="center" label="time" width="220">
        <template slot-scope="scope">
          {{ scope.row.time }}
        </template>
      </el-table-column>
      <!--台号-->
      <el-table-column align="center" label="tableCode" width="220">
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>
      <!--编码-->
      <el-table-column align="center" label="code" width="220">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <!--主题-->
      <!--
      <el-table-column align="center" label="" width="220">
        <template slot-scope="scope">
          {{ scope.row }}
        </template>
      </el-table-column>
      -->
      <!--币种-->
      <el-table-column align="center" label="coinCode" width="220">
        <template slot-scope="scope">
          {{ scope.row.coinCode }}
        </template>
      </el-table-column>
      <!--金额-->
      <el-table-column align="center" label="total" width="220">
        <template slot-scope="scope">
          {{ scope.row.total }}
        </template>
      </el-table-column>
      <!--上下水报告-->
      <el-table-column align="center" label="report" width="220">
        <template slot-scope="scope">
          {{ scope.row.report }}
        </template>
      </el-table-column>
      <!--涉及员工-->
      <el-table-column align="center" label="involveUid" width="220">
        <template slot-scope="scope">
          {{ scope.row.involveUid }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" label="monitor" width="220">
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>
      <!--备注-->
      <el-table-column align="center" label="remarks" width="220">
        <template slot-scope="scope">
          {{ scope.row.remarks }}
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
  import { getHighActionsForPage } from "@/api/high-action";
    export default {
      name: "high-action-page",
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
          this.$emit('update:toSearch' , bl)
        },
        async loadData(){
          this.toBeChangeSearch(false)
          const res = await getHighActionsForPage(Object.assign({
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
      }
    }
</script>

<style scoped>

</style>
