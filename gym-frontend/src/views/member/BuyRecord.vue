<template>
  <div class="buyRecord">
    <el-table
        :data="tableData"
        stripe
        height="520px"
        style="width: 100%;height: 85%">
      <el-table-column
          type="index"
          :index="indexMethod"
          label="序号"
          style="height:50px">
      </el-table-column>
      <el-table-column
          prop="courseName"
          label="课程名称">
      </el-table-column>
      <el-table-column
          prop="courseTime"
          label="开课时间">
      </el-table-column>
      <el-table-column
          prop="courseDuration"
          label="课程时长">
      </el-table-column>
      <el-table-column
          prop="employeeName"
          label="教练">
      </el-table-column>
      <el-table-column
          prop="employeePhone"
          label="教练手机号">
      </el-table-column>
      <el-table-column
          prop="memberPhone"
          label="会员手机号">
      </el-table-column>
    </el-table>



  </div>

</template>

<script>
import {getAllRegister, getRegisterByMemberNo, totalRegister} from "@/api/allApi";
import moment from "moment";

export default {
  data() {
    return {
      tableData: [],
      admin:{

      },
    }
  },
  computed: {
    indexMethod(){

    }
  },
  filters:{
    dataFormat(value){
      return moment(value).format("YYYY-MM-DD")
    }
  },
  created() {
    this.admin = JSON.parse(window.localStorage.getItem('access-member'))
  },
  mounted() {
    this.getRegisterByMemberNo()
  },
  methods:{
    getRegisterByMemberNo(){
      let _this = this
      getRegisterByMemberNo({
        memberNo:_this.admin.memberNo,
      }).then(res=>{
        this.tableData = res.data
      }).catch(err=>{
        console.log(err.message)
      })
    }
  }
}
</script>
<style scoped>
.buyRecord {
  padding: 40px;
  width: 1000px;
  height: 650px;
}
</style>