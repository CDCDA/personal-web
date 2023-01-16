<!--
 * @Author: chenyd
 * @Date: 2023-01-12 17:18:41
 * @LastEditTime: 2023-01-16 09:21:12
 * @Description: 环状图
-->
<template>
  <div class="ring-chart" :id="id"></div>
</template>
<script>
const echarts = require("echarts/lib/echarts");
require("echarts/lib/component/tooltip");
require("echarts/lib/component/legend");
require("echarts/lib/chart/pie");

export default {
  props: ["ringData"],
  watch: {},
  data() {
    return {
      id: "ring" + Math.random().toString(36).slice(-8),
      chartDom: null,
      myChart: null,
      option: {
        tooltip: {
          trigger: "item",
        },
        legend: {
          //   type: "scroll",
          orient: "center",
          right: -30,
          top: 23,
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "80%",
            avoidLabelOverlap: false,
            label: {
              show: false,
              position: "center",
            },
            center: ["20%", "50%"],
            grid: {
              left: 0,
              top: 0,
              right: 0,
              bottom: 0,
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "40",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 1048, name: "Search Engine" },
              { value: 735, name: "Direct" },
              { value: 580, name: "Email" },
              { value: 484, name: "Union Ads" },
              { value: 300, name: "Video Ads" },
            ],
          },
        ],
      },
    };
  },
  mounted() {
    this.initChart();
    this.option && this.myChart
      ? this.option && this.myChart.setOption(this.option)
      : "";
  },
  methods: {
    initChart() {
      this.chartDom = document.getElementById(this.id);
      this.myChart = echarts.init(this.chartDom);
    },
  },
};
</script>
<style lang="scss">
.ring-chart {
  //   canvas {
  //     margin-left: -200px !important;
  //     left: 150px !important;
  //   }
}
.ring-chart {
  width: 100%;
  height: 100%;
  margin-left: 30px;
}
</style>