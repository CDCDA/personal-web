<!--
 * @Author: chenyd
 * @Date: 2023-01-05 17:24:42
 * @LastEditTime: 2023-07-28 14:45:59
 * @Description: 数据统计 
-->
<template>
  <div class="page-main statistic-main">
    <el-row class="statistic-container">
      <el-col :span="5" class="statistic-left bounceInRight animated">
        <blog-timeline></blog-timeline>
      </el-col>
      <el-col :span="12" class="statistic-center bounceInLeft animated">
        <el-row><blog-count-chart></blog-count-chart></el-row>
        <el-row><blog-type-radar></blog-type-radar></el-row>
        <el-row><blog-type-chart></blog-type-chart></el-row>
      </el-col>
      <el-col :span="5" class="statistic-right bounceInLeft animated">
        <update-time-line></update-time-line>
      </el-col>
    </el-row>
  </div>
</template>
<script setup lang="ts">
import blogTimeline from '@/views/Statistics/components/blogTimeline.vue';
import updateTimeLine from '@/views/Statistics/components/updateTimeLine.vue';
import blogCountChart from './components/blogCountChart.vue';
import blogTypeChart from './components/blogTypeChart.vue';
import blogTypeRadar from './components/blogTypeRadar.vue';
</script>
<style lang="less">
.statistic-main {
  height: 100%;
}
.statistic-container {
  width: 100%;
  height: 100%;
  overflow-x: auto;
  display: flex;
  justify-content: center;
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
    min-width: 330px;
  }
  .statistic-right {
    // margin: 20px 3% 20px 15px;
    margin: 10px 1% 10px 15px;
    width: 20%;
    border-radius: 5px;
    min-width: 330px;
  }
  .statistic-center {
    width: calc(58% - 30px);
    box-shadow: none;
    background-color: transparent;
    margin-top: 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    .el-row {
      // margin-bottom: 15px;
      min-height: 200px;
      margin-top: 10px;
      min-width: 600px;
      height: 100%;
      border-radius: 5px;
    }
    .el-row:nth-child(1) {
      margin-top: 0px;
    }
  }
  .el-timeline-item__tail {
    display: block !important;
  }
}
</style>
