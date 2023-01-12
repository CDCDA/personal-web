<!--
 * @Author: chenyd
 * @Date: 2023-01-05 17:24:42
 * @LastEditTime: 2023-01-12 17:45:42
 * @Description: 
-->
<template>
  <div class="page-main statistic-main">
    <el-row class="statistic-container">
      <el-col :span="6" class="statistic-left bounceInLeft animated">
        <blog-timeline></blog-timeline>
      </el-col>
      <el-col :span="12" class="statistic-center">
        <el-row class="bounceInDown animated"
          ><ring-chart></ring-chart>
        </el-row>
        <el-row> <blog-count-chat></blog-count-chat></el-row>
        <el-row class="bounceInUp animated"> </el-row>
      </el-col>
      <el-col :span="6" class="statistic-right bounceInRight animated">
        <update-time-line></update-time-line>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import blogTimeline from "./components/blogTimeline";
import updateTimeLine from "./components/updateTimeLine";
import blogTypeChat from "./components/blogTypeChat";
import BlogCountChat from "./components/blogCountChat.vue";
import RingChart from "@/components/charts/ringChart.vue";
export default {
  components: {
    blogTimeline,
    updateTimeLine,
    BlogCountChat,
    RingChart,
  },
  data() {
    return {};
  },
};
</script>
<style lang="less">
.statistic-container {
  width: 100%;
  height: 100%;
  .el-col,
  .el-row {
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    color: #fff;
  }
  .statistic-left,
  .statistic-center,
  .statistic-right {
    height: calc(100% - 20px);
    background-color: rgba(29, 32, 33, 0.6);
  }
  .statistic-left {
    // margin: 20px 15px 20px 3%;
    margin: 10px 15px 10px 1%;
    width: 20%;
    border-radius: 5px;
  }
  .statistic-right {
    // margin: 20px 3% 20px 15px;
    margin: 10px 1% 10px 15px;
    width: 20%;
    border-radius: 5px;
  }
  .statistic-center {
    width: calc(58% - 30px);
    box-shadow: none;
    background-color: transparent;
    .el-row {
      margin-bottom: 15px;
      min-height: 240px;
      margin-top: 10px;
      min-width: 600px;
      height: calc(33% - 7px);
      border-radius: 5px;
    }
  }
}
</style>
