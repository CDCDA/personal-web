<!--
 * @Author: chenyd
 * @Date: 2023-01-12 17:20:08
 * @LastEditTime: 2023-01-12 17:58:00
 * @Description: 
-->

<template>
  <div class="radar-chart" :id="id"></div>
</template>
<script>
export default {
  props: ["radarData"],
  watch: {},
  data() {
    return {
      id: "radar" + Math.random().toString(36).slice(-8),
      chartDom: null,
      myChart: null,
      option: {
        title: {
          text: "Basic Radar Chart",
        },
        legend: {
          data: ["Allocated Budget", "Actual Spending"],
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: "Sales", max: 6500 },
            { name: "Administration", max: 16000 },
            { name: "Information Technology", max: 30000 },
            { name: "Customer Support", max: 38000 },
            { name: "Development", max: 52000 },
            { name: "Marketing", max: 25000 },
          ],
        },
        series: [
          {
            name: "Budget vs spending",
            type: "radar",
            data: [
              {
                value: [4200, 3000, 20000, 35000, 50000, 18000],
                name: "Allocated Budget",
              },
              {
                value: [5000, 14000, 28000, 26000, 42000, 21000],
                name: "Actual Spending",
              },
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
.radar-chart {
  width: 100%;
  height: 100%;
  margin-left: 30px;
}
</style>