<!--
 * @Description: 数据统计 
-->
<template>
  <div class="page-main statistic-main">
    <el-row class="statistic-container">
      <div :span="5" class="statistic-left bounceInRight animated">
        <blog-timeline></blog-timeline>
      </div>
      <div :span="12" class="statistic-center bounceInLeft animated">
        <el-row><blog-count-chart></blog-count-chart></el-row>
        <el-row><blog-type-radar></blog-type-radar></el-row>
        <el-row><blog-type-chart></blog-type-chart></el-row>
      </div>
      <div :span="5" class="statistic-right bounceInLeft animated">
        <update-time-line></update-time-line>
      </div>
    </el-row>
  </div>
</template>
<script setup lang="ts">
import blogCountChart from './components/blogCountChart.vue';
import BlogTimeline from './components/blogTimeline.vue';
import blogTypeChart from './components/blogTypeChart.vue';
import blogTypeRadar from './components/blogTypeRadar.vue';
import UpdateTimeLine from './components/updateTimeLine.vue';
</script>
<style lang="scss">
@include theme() {
  .statistic-container {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;
    .el-col,
    .el-row {
      background: get('background');
      box-shadow: get('box-shadow');
      color: get('font-color');
    }
    .statistic-left,
    .statistic-right {
      background: get('background');
      box-shadow: get('box-shadow');
      height: calc(100% - 15px);
      border-radius: 15px;
      width: 300px;
    }
    .statistic-left {
      margin: 10px 20px 10px 0px;
    }
    .statistic-right {
      margin: 10px 0px 10px 20px;
    }
    .statistic-center {
      width: calc(100% - 640px);
      height: calc(100% - 15px);
      box-shadow: none;
      background: transparent;
      margin-top: 10px;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .el-row {
        min-height: 150px;
        margin-top: 10px;
        min-width: 600px;
        height: 33%;
        border-radius: 15px;
      }
      .el-row:nth-child(1) {
        margin-top: 0px;
      }
    }
    .el-timeline-item__tail {
      display: block !important;
    }
  }
}
.statistic-main {
  height: 100%;
}
</style>
