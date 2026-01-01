<template>
  <div class="app-container">
    <div class="the-container">
      <!--      对div节点设置样式-->
      <div id="myChart" :style="{width: '500px', height: '500px'}" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return{

    }
  },
  mounted() {
    // 模版渲染后调用绘制图表的方法
    this.drawLine()
  },
  methods: {
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
  }
}
</script>

<style scoped>
.app-container{
  height: 100%;
  background-color: #f1f1f1;
}
.the-container{
  height: 100%;
  background-color: #FFF;
  padding: 20px;
}
</style>