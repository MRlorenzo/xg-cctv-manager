<template>
  <div class="app-container">

    <el-form :inline="true">

      <el-form-item label="PID" v-show="!showLocalMenu">
        <el-select v-model="q.pid" placeholder="Parent ID">
          <el-option
            v-for="m in rootMenuList"
            :key="m.id"
            :label="m.path"
            :value="m.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item v-show="!showLocalMenu">
        <el-input v-model="q.path" placeholder="Path used to match" />
      </el-form-item>

      <el-form-item v-show="!showLocalMenu">
        <el-button type="primary" @click="loadData">
          Search
        </el-button>
      </el-form-item>

      <el-form-item v-show="!showLocalMenu">
        <!-- 新增菜单按钮 -->
        <el-button type="info" @click="handleAdd">
          {{ $t('permission.addMenu') }}
        </el-button>
      </el-form-item>

      <el-form-item label="View">
        <el-switch
          v-model="showLocalMenu"
          :active-value="false"
          :inactive-value="true"
          active-text="DB"
          inactive-text="Local">
        </el-switch>
      </el-form-item>

    </el-form>

    <!-- 菜单列表 -->
    <el-table :data="menuList" v-show="!showLocalMenu" style="width: 100%;" border>
      <!--ID-->
      <el-table-column align="center" label="ID" >
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <!-- 路径 -->
      <el-table-column align="center" label="Path" >
        <template slot-scope="scope">
          {{ scope.row.path }}
        </template>
      </el-table-column>

      <!-- 父ID -->
      <el-table-column align="header-center" label="Parent ID">
        <template slot-scope="scope">
          {{ scope.row.pid | parentIdFilter}}
        </template>
      </el-table-column>

      <!-- 描述 -->
      <el-table-column align="header-center" label="Description">
        <template slot-scope="scope">
          {{ scope.row.description  }}
        </template>
      </el-table-column>

      <!--权限字符串-->
      <el-table-column align="header-center" label="permission">
        <template slot-scope="scope">
          {{ scope.row.perms }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations">
        <template slot-scope="scope">
          <el-button type="primary" v-waves size="small" @click="handleEdit(scope)">
            {{ $t('permission.editMenu') }}
          </el-button>
          <el-button type="danger" v-waves size="small" @click="handleDelete(scope)">
            {{ $t('permission.delete') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      v-show="!showLocalMenu"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currPage"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="pageLimit"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total">
    </el-pagination>

    <!-- 本地的未上传的路由（菜单项） -->
    <el-table :data="undefinedMenuList" v-show="showLocalMenu" style="width: 100%;" border>

      <!-- 路径 -->
      <el-table-column align="center" label="Path" >
        <template slot-scope="scope">
          {{ scope.row.path }}
        </template>
      </el-table-column>

      <!-- 父路径 -->
      <el-table-column align="header-center" label="Parent Path">
        <template slot-scope="scope">
          {{ scope.row.parentPath }}
        </template>
      </el-table-column>

      <!-- 名字 -->
      <el-table-column align="header-center" label="Name">
        <template slot-scope="scope">
          {{ scope.row.name  }}
        </template>
      </el-table-column>

      <!--title-->
      <el-table-column align="header-center" label="Title">
        <template slot-scope="scope">
          {{ scope.row.title  }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations">
        <template slot-scope="scope">
          <el-button type="primary" v-waves size="small" @click="handleAddLocalMenu( scope )">
            {{ $t('permission.addMenu') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Menu':'New Menu'">
      <el-form :model="menu" label-width="80px" label-position="left">

        <!--pid-->
        <el-form-item label="PID">
          <el-cascader
            v-model="menu.pid"
            :options="rootMenuList"
            :props="{
              checkStrictly: true,
              emitPath: false,
              value: 'id',
              label: 'path'
            }"
            clearable></el-cascader>
        </el-form-item>

        <!--路径-->
        <el-form-item label="Path">
          <el-input v-model="menu.path" placeholder="Path used to match" />
        </el-form-item>

        <!--权限字符串-->
        <multiple-input v-model="menu.perms" label="Permission"  placeholder="Permission Codes"/>

        <!-- 描述-->
        <el-form-item label="Desc">
          <el-input
            v-model="menu.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="Menu Description"
          />
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <!-- you can add element-ui's tooltip -->
    <el-tooltip placement="top" content="tooltip">
      <back-to-top :custom-style="myBackToTopStyle" :visibility-height="300" :back-position="50" transition-name="fade" />
    </el-tooltip>

  </div>
</template>

<script>
  import waves from '@/directive/waves/index.js' // 水波纹指令
  import { deepClone, filterUndefinedMenus , menus2trees } from '@/utils'
  import { getDataPage , getRoutesByPid , getMenuList, delMenu, updateMenu , addMenu} from "@/api/menu";
  import { asyncRoutes } from '@/router'
  import i18n from '@/lang'
  import BackToTop from '@/components/BackToTop'
  import MultipleInput from '@/components/MultipleInput'
  const defaultMenu = {
    path: '',
    pid: 0,
    sort: 0,
    perms: ''
  }

  const rootMenuMap = {}

  export default {
    name: "menus",
    directives: {
      waves
    },
    components: { BackToTop , MultipleInput },
    data(){
      return {
        menu: Object.assign({} ,defaultMenu),
        pageLimit: 5,
        currPage: 1,
        page: { records: []},
        dialogVisible: false,
        dialogType: 'new',
        rootMenuList: [],
        dbMenuList: [],
        // 查询参数
        q: {
          pid: 0
        },
        showLocalMenu: false,
        // customizable button style, show/hide critical point, return position
        myBackToTopStyle: {
          right: '50px',
          bottom: '50px',
          width: '40px',
          height: '40px',
          'border-radius': '4px',
          'line-height': '45px', // 请保持与高度一致以垂直居中 Please keep consistent with height to center vertically
          background: '#e7eaf1'// 按钮的背景颜色 The background color of the button
        }
      }
    },
    filters: {
      statusText( status ){
        return {
          '0': '禁用',
          '1': '正常'
        }[status]
      },
      parentIdFilter: function ( pid ){
        let path = rootMenuMap[pid]
        if (path) {
          return path
        }
        return pid
      }
    },
    computed: {
      menuList(){
        return this.page.records
      },
      undefinedMenuList(){
        return filterUndefinedMenus(deepClone(asyncRoutes) , deepClone(this.dbMenuList))
          .map(menu =>{
            menu.title = i18n.t(`route.${menu.langKey}`)
            return menu
          })
      }
    },
    watch:{
      currPage() {
        this.loadData()
      },
      pageLimit(){
        this.loadData()
      },
      dbMenuList( menuList ){
        if (menuList){
          this.getRootMenuList()
        }
      }
    },
    created(){
      this.loadData()
      this.getDbMenuList()
    },
    methods: {
      async loadData(){
        const res = await getDataPage(
          Object.assign(
            {
              current: this.currPage ,
              size: this.pageLimit
            } ,
            this.q
          )
        )
        this.page = res.data
      },
      async getDbMenuList(){
        const res = await getMenuList()
        if (res.code === 0){
          this.dbMenuList = res.data
        }
      },
      getRootMenuList(){
        const cloneMenuList = deepClone(this.dbMenuList).filter(m=>m.pid != null)
        let dataList = menus2trees(cloneMenuList)
        dataList.unshift(
          Object.assign(
            deepClone(defaultMenu) ,
            { id: 0 , path : '[root]'}
          )
        )
        dataList.forEach(root =>{
          rootMenuMap[root.id] = root.path
        })
        this.rootMenuList = dataList
      },
      handleAdd(){
        this.menu = Object.assign({}, defaultMenu)
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit( scope ){
        this.dialogType = 'edit'
        this.dialogVisible = true
        // this.menu = deepClone(scope.row)
        this.$set(this , 'menu' , deepClone(scope.row))
      },
      handleAddLocalMenu( scope ){
        let cloneItem = deepClone(scope.row)
        let pid = 0
        if (cloneItem.pid){
          pid = cloneItem.pid
        }else if (cloneItem.parentPath){
          Object.keys(rootMenuMap).forEach(id => {
            let path = rootMenuMap[id]
            if (path === cloneItem.parentPath){
              pid = parseInt(id)
            }
          })
        }

        let menu = Object.assign(deepClone(defaultMenu) , {
          description: cloneItem.title ,
          path: cloneItem.path,
          pid
        })
        this.menu = menu
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleDelete({ $index, row }){
        this.$confirm('Confirm to remove the Menu?', 'Warning', {
          confirmButtonText: 'Confirm',
          cancelButtonText: 'Cancel',
          type: 'warning'
        })
          .then(async() => {
            await delMenu(row.id)
            this.loadData()
            this.$message({
              type: 'success',
              message: 'Delete succed!'
            })
          })
          .catch(err => { console.error(err) })
      },
      async confirm(){
        const isEdit = this.dialogType === 'edit'
        let res
        if (isEdit) {
          res = await updateMenu(this.menu)
        } else {
          res = await addMenu(this.menu)
        }

        if (res.code !== 0){
          this.$notify({
            title: 'error',
            dangerouslyUseHTMLString: true,
            message: res.msg,
            type: 'error'
          })
          return
        }

        if (this.currPage === 1 ){
          this.loadData()
        }

        const { description, path } = this.menu
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>Path: ${path}</div>
            <div>Description: ${description}</div>
          `,
          type: 'success'
        })
        this.getDbMenuList()
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

<style lang="scss" scoped>
  .app-container {
    .roles-table {
      margin-top: 30px;
    }
  }
</style>
