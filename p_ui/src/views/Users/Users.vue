<!--
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-02-01 16:04:07
 * @Description: 
-->
<template>
  <div class="page-main user-main">
    <el-row class="user-container">
      <el-col :span="6" class="user-left"> </el-col>
      <el-col :span="18" class="user-right"> </el-col>
    </el-row>
    <!-- <el-drawer
      title="我是标题"
      :visible.sync="drawer"
      :direction="direction"
      :before-close="handleClose"
    >
      <span>我来啦!</span>
    </el-drawer> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      drawer: false,
    };
  },
};
</script>
<style lang="less">
.user-container {
  width: 100%;
  height: 100%;
  .el-row,.el-col{
    border: 1px solid white;
  }
  .user-left,.user-right{
    height: 100%;
  }
}
</style>
