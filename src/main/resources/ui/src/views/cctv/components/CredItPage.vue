<template>
  <div>
    <el-table :data="dataList" height="500" border>
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
      <!--币种-->
      <el-table-column align="center" :label="$t('cctv.coinType')">
        <template slot-scope="scope">
          {{ scope.row.coinCode }}
        </template>
      </el-table-column>
      <!--金额-->
      <el-table-column align="center" :label="$t('cctv.total')">
        <template slot-scope="scope">
          {{ scope.row.total }}
        </template>
      </el-table-column>
      <!--通知人-->
      <el-table-column align="center" :label="$t('cctv.alerterName')">
        <template slot-scope="scope">
          {{ scope.row.alerterName }}
        </template>
      </el-table-column>
      <!--监控部-->
      <el-table-column align="center" :label="$t('cctv.monitor')">
        <template slot-scope="scope">
          {{ scope.row.monitor }}
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
import { getFillAndCreditsForPage } from '@/api/fill-and-credit'
export default {
  name: 'CreditPage',
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
      const res = await getFillAndCreditsForPage(Object.assign({
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
    }
  }
}
</script>

<style scoped>

</style>
