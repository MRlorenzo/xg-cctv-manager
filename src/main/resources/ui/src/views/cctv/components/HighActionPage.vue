<template>
  <div>
    <el-table :data="dataList" height="500" border>
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-image v-for="url in getUrls(scope.row.urls)" :key="url" :src="url" lazy></el-image>
        </template>
      </el-table-column>

      <!--序号-->
      <el-table-column align="center" :label="$t('cctv.no')" >
        <template slot-scope="scope">
          {{ scope.row.no }}
        </template>
      </el-table-column>
      <!--日期-->
      <el-table-column align="center" :label="$t('cctv.date')" >
        <template slot-scope="scope">
          {{ scope.row.date | dateTimeFilter}}
        </template>
      </el-table-column>

      <!--台号-->
      <el-table-column align="center" :label="$t('cctv.tableCode')" >
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>
      <!--编码-->
      <el-table-column align="center" :label="$t('cctv.code')" >
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>

      <!--币种-->
      <el-table-column align="center" :label="$t('cctv.coinType')" >
        <template slot-scope="scope">
          {{ scope.row.coinCode }}
        </template>
      </el-table-column>
      <!--金额-->
      <el-table-column align="center" :label="$t('cctv.total')" >
        <template slot-scope="scope">
          {{ scope.row.total }}
        </template>
      </el-table-column>
      <!--上下水报告-->
      <el-table-column align="center" :label="$t('cctv.report')" >
        <template slot-scope="scope">
          {{ scope.row.report }}
        </template>
      </el-table-column>
      <!--涉及员工-->
      <el-table-column align="center" :label="$t('cctv.involveEmp')" >
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" :label="$t('cctv.monitor')" >
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>
      <!--备注-->
      <el-table-column align="center" :label="$t('cctv.remarks')" >
        <template slot-scope="scope">
          {{ scope.row.remarks }}
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
          this.$emit('update:doSearch' , bl)
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
        },
        getUrls( urlsText ){
          if (typeof urlsText !== 'string'){
            return []
          }
          return urlsText.split(',').map(url => {
            return 'file'+url
          })
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
