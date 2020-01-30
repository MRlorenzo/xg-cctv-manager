<template>
  <div>
    <el-table :data="dataList" style="width: 100%;margin-top:30px;" border>
      <!-- 名称 -->
      <el-table-column align="center" label="Name" width="220">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>

      <!-- 部门 -->
      <el-table-column align="header-center" label="Department">
        <template slot-scope="scope">
          {{ scope.row.department | departmentText }}
        </template>
      </el-table-column>

      <!-- 角色列表 -->
      <el-table-column align="header-center" label="Roles">
        <template slot-scope="scope">
          {{ scope.row.roles | rolesText}}
        </template>
      </el-table-column>

      <!-- 描述 -->
      <el-table-column align="header-center" label="Description">
        <template slot-scope="scope">
          {{ scope.row.description  }}
        </template>
      </el-table-column>

      <!--状态: 0 禁用 1 正常-->
      <el-table-column align="header-center" label="Status">
        <template slot-scope="scope">
          {{ scope.row.status | statusText}}
        </template>
      </el-table-column>

      <!--创建时间-->
      <el-table-column align="header-center" label="CreateTime">
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
  import { getDataPage } from "@/api/user";
    export default {
      name: "user-list",
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
      filters: {
        statusText( status ){
          return {
            '0': '禁用',
            '1': '正常'
          }[status]
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
          this.$emit('toBeSearch' , bl)
        },
        async loadData(){
          this.toBeChangeSearch(false)
          const res = await getDataPage(Object.assign({
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
