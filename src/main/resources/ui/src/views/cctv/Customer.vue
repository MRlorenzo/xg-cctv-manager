<template>
  <div class="app-container">
    <el-form :inline="true">
      <!--卡号-->
      <el-form-item label="卡号">
        <el-input v-model="q.cardNumber" placeholder="No" />
      </el-form-item>
      <!--位置-->
      <el-form-item label="位置">
        <el-input v-model="q.location" placeholder="No" />
      </el-form-item>

      <!--状态-->
      <el-form-item label="状态">
        <el-radio v-model="q.status" :label="0" border>禁用</el-radio>
        <el-radio v-model="q.status" :label="1" border>正常</el-radio>
      </el-form-item>

      <el-form-item>
        <!-- 搜索按钮 -->
        <el-button type="primary" @click="doSearch = true">
          Search
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 重置按钮 -->
        <el-button @click="resetQueryData">
          Reset
        </el-button>
      </el-form-item>

      <el-form-item>
        <!-- 新增用户按钮 -->
        <el-button type="info" @click="handleAdd">
          Add
        </el-button>
      </el-form-item>
    </el-form>

    <customer-page
      :query="q"
      :do-search.sync="doSearch"
      :handle-edit="handleEdit"
      :handle-delete="handleDelete"
    />

    <el-dialog :visible.sync="showMark" :title="dialogType==='edit'?'Edit':'New'">
      <el-form :model="d" label-width="80px" label-position="left">
        <!--名称-->
        <el-form-item label="名称">
          <el-input v-model="d.name" placeholder="No" />
        </el-form-item>
        <!--卡号-->
        <el-form-item label="卡号">
          <el-input v-model="d.cardNumber" placeholder="No" />
        </el-form-item>
        <!--位置-->
        <el-form-item label="位置">
          <el-input v-model="d.location" placeholder="No" />
        </el-form-item>
        <!--平均下注-->
        <el-form-item label="平均下注">
          <el-input v-model="d.avgBetting" placeholder="No" />
        </el-form-item>
        <!--国籍-->
        <el-form-item label="国籍">
          <el-input v-model="d.nationality" placeholder="No" />
        </el-form-item>
        <!--照片-->
        <el-form-item label="照片">
          <avatar-image :url.sync="d.imageUrl"/>
        </el-form-item>
        <!--状态-->
        <el-form-item label="状态">
          <el-radio v-model="d.status" :label="0" border>禁用</el-radio>
          <el-radio v-model="d.status" :label="1" border>正常</el-radio>
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="showMark=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirm">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import CustomerPage from './components/CustomerPage'
import AvatarImage from '@/components/Upload/AvatarImage'
export default {
  name: 'Customer',
  components: { CustomerPage , AvatarImage},
  data() {
    return {
      q: {},
      d: { imageUrl: '' },
      doSearch: true,
      showMark: false,
      dialogType: 'edit' // 'edit' or 'new'
    }
  },
  methods: {
    resetQueryData() {},
    handleAdd() {
      this.showMark = true
    },
    handleEdit(scope) {

    },
    handleDelete({ $index, row }) {

    },
    confirm() {
      console.log(this.d)
    }
  }
}
</script>

<style scoped>
  .app-container {

  }

</style>
