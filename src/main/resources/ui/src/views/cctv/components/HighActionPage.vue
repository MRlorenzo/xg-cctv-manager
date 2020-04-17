<template>
  <div>
    <el-table :data="dataList" @cell-dblclick="cellDblClick" border>
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
      <!--编码-->
      <el-table-column align="center" :label="$t('cctv.code')">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>

      <!--事件主题-->
      <el-table-column align="center" :label="$t('cctv.subject')" width="200">
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
      <!--上下水报告-->
      <el-table-column align="center"
                       :show-overflow-tooltip="true"
                       :label="$t('cctv.report')"
                       width="200"
                       height="100%"
                       prop="report"
      >
        <template slot-scope="scope">
          {{ scope.row.report }}
        </template>
      </el-table-column>
      <!--涉及员工-->
      <!--涉事员工-->
      <el-table-column align="center" :label="$t('cctv.involveEmp')" width="200">
        <template slot-scope="scope">
          {{ scope.row.staffs | staffsText }}
        </template>
      </el-table-column>
      <!--<el-table-column align="center" :label="$t('cctv.involveEmp')">-->
      <!--<template slot-scope="scope">-->
      <!--{{ scope.row.username }}-->
      <!--</template>-->
      <!--</el-table-column>-->
      <!--监控部-->
      <el-table-column align="center" :label="$t('cctv.monitor')">
        <template slot-scope="scope">
          {{ scope.row.monitor }}
        </template>
      </el-table-column>
      <!--备注-->
      <el-table-column align="center" :label="$t('cctv.remarks')">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.remarks.toLowerCase().trim() === 'solved' ? '' :'danger'"
            effect="dark"
          >
            {{ scope.row.remarks }}
          </el-tag>
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
import { getHighActionsForPage } from '@/api/high-action'
export default {
  name: 'HighActionPage',
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
      const res = await getHighActionsForPage(Object.assign({
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
    },
    // 双击复制
    cellDblClick (row, column, cell, event) {
      // console.log(row, column, cell, event)
      if (!column.property) return;
      try{
        let copyText = row[column.property];
        let newInput = document.createElement('input');     //创建一个隐藏input（重要！）
        newInput.value = copyText;    //赋值
        document.body.appendChild(newInput);
        newInput.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        newInput.className = 'newInput';
        newInput.style.display='none';
        this.$message.success("copy text success")
      }catch (e) {
        this.$message.error("copy text fail")
      }
    }
  }
}
</script>

<style scoped>

</style>
