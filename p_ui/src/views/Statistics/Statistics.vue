<template>
  <div class="page-main statistic-main">
    <el-row class="statistic-container">
      <el-col :span="6" class="statistic-left bounceInLeft animated">
        <blog-timeline></blog-timeline>
      </el-col>
      <el-col :span="12" class="statistic-center">
        <el-row class="bounceInDown animated"><blog-chat></blog-chat></el-row>
        <el-row> <blog-chat></blog-chat></el-row>
        <el-row class="bounceInUp animated"><blog-chat></blog-chat> </el-row>
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
import blogChat from "./components/blogChat";
import blogTypeChat from "./components/blogTypeChat";

export default {
  components: {
    blogTimeline,
    updateTimeLine,
    blogChat,
    blogTypeChat,
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
  }
  .statistic-right {
    // margin: 20px 3% 20px 15px;
    margin: 10px 1% 10px 15px;
    width: 20%;
  }
  .statistic-center {
    width: calc(58% - 30px);
    box-shadow: none;
    .el-row {
      margin-bottom: 15px;
      min-height: 240px;
      margin-top: 10px;
      min-width: 600px;
      height: calc(33% - 7px);
    }
  }
}
</style>
