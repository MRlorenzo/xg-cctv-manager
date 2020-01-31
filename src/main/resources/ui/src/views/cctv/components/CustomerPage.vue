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
      <!--姓名-->
      <el-table-column align="center" label="name" width="220">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <!--卡号-->
      <el-table-column align="center" label="cardNumber" width="220">
        <template slot-scope="scope">
          {{ scope.row.cardNumber }}
        </template>
      </el-table-column>
      <!--位置-->
      <el-table-column align="center" label="location" width="220">
        <template slot-scope="scope">
          {{ scope.row.location }}
        </template>
      </el-table-column>
      <!--平均下注-->
      <el-table-column align="center" label="avgBetting" width="220">
        <template slot-scope="scope">
          {{ scope.row.avgBetting }}
        </template>
      </el-table-column>
      <!--国籍-->
      <el-table-column align="center" label="nationality" width="220">
        <template slot-scope="scope">
          {{ scope.row.nationality }}
        </template>
      </el-table-column>
      <!--照片-->
      <el-table-column align="center" label="photo" width="220">
        <template slot-scope="scope">
          {{ scope.row.photo }}
        </template>
      </el-table-column>
      <!--状态-->
      <el-table-column align="center" label="status" width="220">
        <template slot-scope="scope">
          {{ scope.row.status }}
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
  import { getCustomersForPage } from "@/api/customer";
    export default {
      name: "customer-page",
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
          const res = await getCustomersForPage(Object.assign({
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
