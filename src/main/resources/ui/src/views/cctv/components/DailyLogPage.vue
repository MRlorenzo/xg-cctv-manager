<template>
  <div>
    <el-table :data="dataList" border>

      <!--序号-->
      <el-table-column type="index" align="center" :label="$t('cctv.no')">
        <template slot-scope="scope">
          <!-- 总页数-((当前页 - 1) * 当前显示数据条数 + 当前行数据的索引) -->
          {{ total - ((currPage - 1) * pageLimit + scope.$index) }}
          <!--scope.row.id -->
        </template>
      </el-table-column>
      <!--日期-->
      <el-table-column align="center" :label="$t('cctv.date')">
        <template slot-scope="scope">
          {{ scope.row.date | dateTimeFilter }}
        </template>
      </el-table-column>
      <!--时间-->
      <el-table-column align="center" :label="$t('cctv.time')">
        <template slot-scope="scope">
          {{ scope.row.time | timeFilter}}
        </template>
      </el-table-column>
      <!--台号-->
      <el-table-column align="center" :label="$t('cctv.tableCode')">
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>
      <!--主题-->
      <el-table-column align="center" :label="$t('cctv.subject')">
        <template slot-scope="scope">
          {{ scope.row.subject }}
        </template>
      </el-table-column>
      <!--相关信息-->
      <el-table-column align="center" :label="$t('cctv.details')">
        <template slot-scope="scope">
          {{ scope.row.details }}
        </template>
      </el-table-column>
      <!--通知人-->
      <el-table-column align="center" :label="$t('cctv.alerterName')">
        <template slot-scope="scope">
          {{ scope.row.alerterName }}
        </template>
      </el-table-column>
      <!--部门-->
      <el-table-column align="center" :label="$t('cctv.department')">
        <template slot-scope="scope">
          {{ scope.row.departmentCode }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" :label="$t('cctv.monitor')">
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>
      <!--图片-->
      <el-table-column align="center" :label="$t('cctv.image')">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
            v-if="getUrls(scope.row.urls).length>0"
            :src="getUrls(scope.row.urls)[0]"
            :preview-src-list="getUrls(scope.row.urls)"
          />
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
import { getDailyLogsForPage } from '@/api/daily-log'
export default {
  name: 'DailyLogPage',
  props: {
    query: {
      type: Object,
      required: true
    },
    doSearch: {
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
      const res = await getDailyLogsForPage(Object.assign({
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
