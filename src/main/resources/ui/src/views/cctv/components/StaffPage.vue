<template>
  <div>
    <el-table :data="dataList" style="width: 100%;margin-top:30px;" border>
      <!-- 序号 -->
      <el-table-column align="center" :label="$t('cctv.no')" width="100">
        <template slot-scope="scope">
          {{ scope.row.staffId }}
        </template>
      </el-table-column>

      <!-- 名称 -->
      <el-table-column align="center" :label="$t('cctv.name')" width="100">
        <template slot-scope="scope">
          {{ scope.row.staffName }}
        </template>
      </el-table-column>

      <!-- 工号 -->
      <el-table-column align="center" :label="$t('cctv.workNo')" width="100">
        <template slot-scope="scope">
          {{ scope.row.workNo }}
        </template>
      </el-table-column>

      <!-- 职位 -->
      <el-table-column align="center" :label="$t('cctv.position')" width="150">
        <template slot-scope="scope">
          {{ scope.row.position | positionText }}
        </template>
      </el-table-column>

      <!-- 部门 -->
      <el-table-column align="center" :label="$t('cctv.department')" width="150">
        <template slot-scope="scope">
          {{ scope.row.department | departmentText }}
        </template>
      </el-table-column>

      <!-- 聘用时间 -->
      <el-table-column align="center" label="hireDate" width="200">
        <template slot-scope="scope">
          {{ scope.row.hireDate | dateTimeFilter }}
        </template>
      </el-table-column>
      <!--聘用时间-->
      <!--<el-table-column align="header-center" :label="$t{'cctv.hireDate'}" width="220">-->
      <!--<template slot-scope="scope">-->
      <!--{{ scope.row.hireDate }}-->
      <!--</template>-->
      <!--</el-table-column>-->

      <!--国籍-->
      <el-table-column align="center" label="nationality" width="100">
        <template slot-scope="scope">
          {{ scope.row.nationality }}
        </template>
      </el-table-column>

      <!--图片-->
      <el-table-column align="center" :label="$t('cctv.image')">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            :src="getUrls(scope.row.avatar)[0]"
            :preview-src-list="getUrls(scope.row.avatar)"
          />
        </template>
      </el-table-column>

      <!--状态: 0 禁用 1 正常-->
      <el-table-column align="header-center" :label="$t('cctv.status')">
        <template slot-scope="scope">
          {{ scope.row.status | statusText }}
        </template>
      </el-table-column>

      <!--操作-->
      <el-table-column align="center" label="Operations">
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
        :current-page="currPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageLimit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import { getDataPage } from '@/api/staff'
import i18n from '@/lang'
export default {
  name: 'StaffPage',
  filters: {
    statusText(status) {
      return {
        '0': i18n.t('cctv.disable'),
        '1': i18n.t('cctv.normal')
      }[status]
    }
  },
  props: {
    query: {
      type: Object,
      required: true
    },
    doSearch: {
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
  data() {
    return {
      pageLimit: 5,
      currPage: 1,
      page: {
        total: 0,
        records: []
      }
    }
  },
  computed: {
    dataList() {
      return this.page.records
    },
    total() {
      return this.page.total
    }
  },
  watch: {
    currPage() {
      this.toBeChangeSearch(true)
    },
    pageLimit() {
      this.toBeChangeSearch(true)
    },
    doSearch(bl) {
      if (bl) {
        this.loadData()
      }
    }
  },
  created() {
    if (this.doSearch) {
      this.loadData()
    }
  },
  methods: {
    toBeChangeSearch(bl) {
      this.$emit('update:doSearch', bl)
    },
    async loadData() {
      this.toBeChangeSearch(false)
      const res = await getDataPage(Object.assign({
        current: this.currPage,
        size: this.pageLimit
      },
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
    getUrls(urlsText) {
      if (typeof urlsText !== 'string') {
        return []
      }
      return urlsText.split(',').map(url => {
        return 'file' + url
      })
    }
  }
}
</script>

<style scoped>

</style>
