<template>
  <div>
    <el-table :data="dataList" border>
      <!--序号-->
      <el-table-column type="index" align="center" :label="$t('cctv.no')">
        <template slot-scope="scope">
          <!--(当前页 - 1) * 当前显示数据条数 + 当前行数据的索引 + 1-->
          {{(currPage - 1) * pageLimit + scope.$index + 1}}
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
          {{ scope.row.time }}
        </template>
      </el-table-column>

      <!--台号-->
      <el-table-column align="center" :label="$t('cctv.tableCode')">
        <template slot-scope="scope">
          {{ scope.row.tableCode }}
        </template>
      </el-table-column>

      <!--事件-->
      <el-table-column align="center" :label="$t('cctv.eventCode')">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <!--事件主题-->
      <el-table-column align="center" :label="$t('cctv.subject')">
        <template slot-scope="scope">
          {{ scope.row.codeTitle }}
        </template>
      </el-table-column>
      <!--币种-->
      <el-table-column align="center" :label="$t('cctv.coinType')">
        <template slot-scope="scope">
          {{ scope.row.coinCode }}
        </template>
      </el-table-column>
      <!--金额-->
      <el-table-column align="center" :label="$t('cctv.total')">
        <template slot-scope="scope">
          {{ scope.row.total | formatCurrency }}
        </template>
      </el-table-column>
      <!--报告-->
      <el-table-column align="center" :label="$t('cctv.report')" width="150">
        <template slot-scope="scope">
          {{ scope.row.report }}
        </template>
      </el-table-column>
      <!--涉事员工-->
      <el-table-column align="center" :label="$t('cctv.involveEmp')">
        <template slot-scope="scope">
          {{ scope.row.staffs | staffsText }}
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
      <!--备注-->
      <el-table-column align="center" :label="$t('cctv.remarks')">
        <template slot-scope="scope">
          {{ scope.row.remarks }}
        </template>
      </el-table-column>
      <!--图片-->
      <el-table-column align="center" :label="$t('cctv.image')">
        <template slot-scope="scope">
          <el-image
            style="width: 100px; height: 100px"
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
import { getIncidentLogForPage } from '@/api/incident-log'
export default {
  name: 'IncidentLogPage',
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
      const res = await getIncidentLogForPage(Object.assign({
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
