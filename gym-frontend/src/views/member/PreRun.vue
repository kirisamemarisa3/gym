<template>
  <div class="preRun">
    <!-- 页面标题 -->
    <h2 class="page-title">跑步机信息</h2>
    
    <!-- 跑步机列表表格 -->
    <el-table
      :data="machineList" 
      style="width: 100%; margin-bottom: 20px"
      v-loading="loading" 
    >

      <el-table-column
        label="操作"
        width="80"
        fixed="left"
      >
        <template slot-scope="scope">
          <el-button
            type="danger"
            size="mini"
            @click="handleDelete(scope.row.id)"
            icon="el-icon-delete"
            circle
          ></el-button>
        </template>
      </el-table-column>

      <!-- 原有列（编号、设备编号、状态） -->
      <el-table-column
        prop="id"
        label="编号"
        width="200">
      </el-table-column>
      <el-table-column
        prop="equipmentId"
        label="设备编号"
        width="350">
      </el-table-column>
      <el-table-column
        prop="status"
        label="当前状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
            {{ scope.row.status === 1 ? '空闲' : '使用中' }}
          </el-tag>
        </template>
      </el-table-column>
      
      <!-- ✅ 新增：最右边的预约按钮（只对空闲状态显示） -->
      <el-table-column
        label="预约"
        width="100"
        fixed="right"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === 1"
            type="success"
            size="mini"
            @click="handleReserve(scope.row.id)"
            icon="el-icon-check"
            circle
          ></el-button>
          <span v-else style="color: #909399">已预约</span>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      @size-change="handleSizeChange"  
      @current-change="handleCurrentChange"  
      :current-page="page"  
      :page-sizes="[5, 10, 20, 50]"  
      :page-size="size"  
      layout="total, sizes, prev, pager, next, jumper"  
      :total="total" 
      style="text-align: right" 
    >
    </el-pagination>
  </div>
</template>

<script>
import { getMachineList, deleteMachine, reserveMachine } from "@/api/allApi"; // ✅ 确保导入了预约API

export default {
  name: "PreRun",
  data() {
    return {
      machineList: [],
      loading: false,
      page: 1,
      size: 10,
      total: 0
    };
  },
  mounted() {
    this.getMachineList();
  },
  methods: {
    getMachineList() {
      this.loading = true;
      getMachineList({ page: this.page, size: this.size })
        .then(res => {
          const result = res.data;
          this.machineList = result.data.records;
          this.total = result.data.total;
          this.loading = false;
        })
        .catch(err => {
          console.error('获取跑步机列表失败', err);
          this.$message.error('无法加载跑步机信息');
          this.loading = false;
        });
    },
    
    handleSizeChange(val) {
      this.size = val;
      this.getMachineList();
    },
    
    handleCurrentChange(val) {
      this.page = val;
      this.getMachineList();
    },
    

    handleDelete(id) {
      this.$confirm('确定要删除该跑步机吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteMachine(id).then(() => {
          this.$message.success('删除成功');
          this.getMachineList();
        }).catch(err => {
          this.$message.error('删除失败: ' + (err.message || '未知错误'));
        });
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },
    

    handleReserve(id) {
      this.$confirm('确定要预约该跑步机吗？', '预约确认', {
        confirmButtonText: '确认预约',
        cancelButtonText: '取消',
        type: 'success'
      }).then(() => {
        reserveMachine(id).then(() => {
          this.$message.success('预约成功');
          this.getMachineList(); // 刷新列表
        }).catch(err => {
          this.$message.error('预约失败: ' + (err.message || '未知错误'));
        });
      }).catch(() => {
        this.$message.info('已取消预约');
      });
    }
  }
};
</script>

<style scoped>
.preRun {
  padding: 20px;
}

.page-title {
  text-align: center;
  margin-bottom: 20px;
  color: #303133;
}
</style>