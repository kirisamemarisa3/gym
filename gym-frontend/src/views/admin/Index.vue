<template>
  <div class="home">
    <el-card  shadow="hover">
      <div class="header">
        <div class="page-header">
          <el-page-header @back="goBack" content="主页">
          </el-page-header>
        </div>

      </div>
    </el-card>
    <span style="font-size: 60px;color: #f6692b; ">欢迎使用健身房后台管理系统！</span>
  </div>


</template>

<script>

export default {
  name: "Index",
  data() {
    return{
      Keyword:'',
    }
  },
  mounted() {
    this.drawLine()
  },
  methods:{
    goBack() {
      this.$router.back();
    },
    drawLine() {
      // 初始化echarts实例
      const myChart = this.$echarts.init(document.getElementById('myChart'))
      // 绘制图表
      myChart.setOption({
        // 标题
        title: { text: 'Echarts入门示例' },
        // 提示框组件
        tooltip: {},
        // x轴
        xAxis: {
          data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
        },
        // y轴
        yAxis: {},
        // 系列，用于管理数据
        series: [{
          // 图例的名字
          name: '销量',
          // 图像类型
          type: 'bar',
          // 数值
          data: [5, 20, 36, 10, 10, 20]
        }]
      })
    }
  },
}
</script>

<style scoped>


.header {
  display: flex;
  justify-content: space-between;
}

.page-header {
  width: 20%;
}

.buttons {
  margin: 0px 0 10px 0;

}

.search {
  margin-right: 10px;
  width: 50%;
  float: left;
}


.chart {
  width: 100%;
  height: 700px;
}

.chart .chart-top {
  height: 200px;
  padding:17px;
}

.top-card {
  float: left;
  width: 380px;
  height: 130px;
  margin-right: 30px;
  margin-top: 20px;
  border: #00c996 solid 1px;
  border-radius: 5px;
}

.chart .chart-bottom {
  width: 100%;
  height: 500px;
  padding: 30px;
}

.chart .chart-bottom .chart-bottom-card {
  float: left;
  width: 560px;
  height: 400px;
  margin-right: 50px;
  margin-top: 20px;
  border: #00c996 solid 1px;
  border-radius: 5px;
  padding: 30px;
}


.search-input input{
  width: 110px;
}


</style>
