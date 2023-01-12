<template>
  <div :id="id" class="blog-line-chat"></div>
</template>
<script>
const echarts = require("echarts/lib/echarts");
require("echarts/lib/component/grid");
require("echarts/lib/chart/line");
require("echarts/lib/component/tooltip");
import { getBlogCountByTime } from "@/api/blog";
import dateUtils from "@/utils/date.js";
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
      params: {
        userId: this.$store.state.userId,
        startTime: new Date(),
        endTime: new Date(),
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
  watch: {},
  mounted() {
    this.initChart();
    this.getBlogCountData();
    setTimeout(() => {
      this.option && this.myChart
        ? this.option && this.myChart.setOption(this.option)
        : "";
    }, 500);
  },
  methods: {
    /**
     * @return {*}
     * @Description: 按时间获取博客数据并初始化图表数据
     */
    getBlogCountData() {
      let date = new Date();
      let preDate = dateUtils.getPreMonthDate(date, 5); //5个月前
      this.params.startTime = dateUtils.getWeekStartDay(new Date(preDate));
      this.params.endTime = dateUtils.getWeekEndDay(date);
      getBlogCountByTime(this.params).then((res) => {
        console.log("按时间获取", res);
        let data = res.data;
        let dateList = []; //横轴数据
        let blogCountList = [];
        for (let i = 0; i < 6; i++) {
          let dateIndex = dateUtils
            .getPreMonthDate(new Date(), i)
            .substring(0, 7);
          dateList.push(dateIndex);
          blogCountList[dateIndex] = 0;
        }
        data.forEach((blog) => {
          let blogTime = new Date(blog.blogCreateTime);
          let start = blogTime.getFullYear();
          let end = blogTime.getMonth() + 1 + "";
          if (end.length < 2) {
            end = "0" + end;
          }
          let blogDate = start + "-" + end;
          blogCountList[blogDate]++;
        });
        let countList = [];
        Object.keys(blogCountList).forEach((e) => {
          countList.push(blogCountList[e]);
        });
        this.option.xAxis.data = dateList.reverse();
        this.option.series[0].data = countList.reverse();
      });
    },

    /**
     * @return {*}
     * @Description:初始化图表
     */
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