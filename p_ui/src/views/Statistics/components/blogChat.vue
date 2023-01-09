<template>
  <div :id="id" class="blog-line-chat"></div>
</template>
<script>
const echarts = require("echarts/lib/echarts");
require("echarts/lib/component/grid");
require("echarts/lib/chart/line");
require("echarts/lib/component/tooltip");
export default {
  data() {
    return {
      data: {
        id: "",
        outId: "",
        list: {
          asd: "",
        },
      },
      id: "line" + Math.random().toString(36).slice(-8),
      chartDom: null,
      myChart: null,
      dateList: [
        "2022-9-12",
        "2022-9-13",
        "2022-9-14",
        "2022-9-15",
        "2022-9-16",
        "2022-9-17",
        "2022-9-18",
      ],
      option: {
        textStyle: {
          fontSize: 12,
          color: "#FFFFFF",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        xAxis: {
          type: "category",
          data: [],
          name: "日期",
        },
        yAxis: {
          type: "value",
          name: "订单数",
        },
        grid: {
          x: 35,
          y: 50,
          x2: 100,
          y2: 30,
        },
        series: [
          {
            data: [92, 0, 5, 72, 3, 8, 60],
            type: "line",
          },
        ],
      },
    };
  },
  props: ["lineData"],
  watch: {
    // lineData(val) {
    //   this.dateList = [];
    //   this.option.series[0].data = [];
    //   val.forEach((element) => {
    //     this.dateList.push(element.date);
    //     this.option.series[0].data.push(element.num);
    //   });
    //   // this.dateList = JSON.parse(JSON.stringify(this.dateList));
    //   this.option.xAxis.data = this.dateList;
    //   // console.log("FFF", this.dateList);
    //   this.myChart.setOption(this.option);
    // },
  },
  mounted() {
    this.initChart();
    this.dateList = JSON.parse(JSON.stringify(this.dateList));
    this.option.xAxis.data = this.dateList;
    setTimeout(() => {
      // console.log("SS",this.option)
      this.option && this.myChart
        ? this.option && this.myChart.setOption(this.option)
        : "";
    }, 1000);
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
// #line-chart {
//   canvas {
//     margin-right: -100px !important;
//   }
// }
.blog-line-chat {
  width: 100%;
  height: 100%;
  margin-left: 30px;
}
</style>