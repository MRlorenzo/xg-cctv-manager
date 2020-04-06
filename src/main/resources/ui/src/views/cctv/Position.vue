<template>
  <div class="app-container">
    <!-- 新增部门按钮 -->
    <el-button type="primary" @click="handleAddPosition">
      {{ $t('cctv.new') }}
    </el-button>

    <!-- 部门列表 -->
    <el-table :data="PositionList" style="width: 100%;margin-top:30px;" border>
      <!-- 代号 -->
      <el-table-column align="center" label="Code" width="220">
        <template slot-scope="scope">
          {{ scope.row.positionCode }}
        </template>
      </el-table-column>
      <!-- 名称 -->
      <el-table-column align="center" label="Name" width="220">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <!-- 描述 -->
      <el-table-column align="header-center" label="Description">
        <template slot-scope="scope">
          {{ scope.row.description }}
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

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'Edit Position':'New Position'">
      <el-form :model="Position" label-width="80px" label-position="left">

        <!--代号-->
        <el-form-item label="Code">
          <el-input v-model="Position.positionCode" placeholder="Position Code" />
        </el-form-item>
        <!--名称-->
        <el-form-item label="Name">
          <el-input v-model="Position.name" placeholder="Position Name" />
        </el-form-item>

        <!-- 描述-->
        <el-form-item label="Desc">
          <el-input
            v-model="Position.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="Position Description"
          />
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">
          {{ $t('permission.cancel') }}
        </el-button>
        <el-button type="primary" @click="confirmPosition">
          {{ $t('permission.confirm') }}
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { deepClone } from '@/utils'
import { getPositions, addPosition, updatePosition, delPosition } from '@/api/position'

const defaultPosition = {
  positionId: null,
  name: '',
  description: '',
  positionCode: '',
  sort: 0
}

export default {
  name: 'Position',
  data() {
    return {
      Position: Object.assign({}, defaultPosition),
      PositionList: [],
      dialogVisible: false,
      dialogType: 'new'
    }
  },
  created() {
    this.getPositions()
  },
  methods: {
    async getPositions() {
      const res = await getPositions()
      this.PositionList = res.data
    },
    handleAddPosition() {
      this.Position = Object.assign({}, defaultPosition)
      this.dialogType = 'new'
      this.dialogVisible = true
    },
    handleEdit(scope) {
      this.dialogType = 'edit'
      this.dialogVisible = true
      this.Position = deepClone(scope.row)
    },
    handleDelete({ $index, row }) {
      this.$confirm('Confirm to remove the Position?', 'Warning', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel',
        type: 'warning'
      })
        .then(async() => {
          await delPosition(row.positionId)
          this.PositionList.splice($index, 1)
          this.$message({
            type: 'success',
            message: 'Delete succed!'
          })
        })
        .catch(err => { console.error(err) })
    },
    async confirmPosition() {
      const isEdit = this.dialogType === 'edit'
      if (isEdit) {
        await updatePosition(this.Position)
        for (let index = 0; index < this.PositionList.length; index++) {
          if (this.PositionList[index].name === this.Position.name) {
            this.PositionList.splice(index, 1, Object.assign({}, this.Position))
            break
          }
        }
      } else {
        await addPosition(this.Position)
        this.PositionList.unshift(this.Position)
      }
      const { description, name } = this.Position
      this.dialogVisible = false
      this.$notify({
        title: 'Success',
        dangerouslyUseHTMLString: true,
        message: `
            <div>Position Name: ${name}</div>
            <div>Description: ${description}</div>
          `,
        type: 'success'
      })
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
