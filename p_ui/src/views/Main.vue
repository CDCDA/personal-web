<!--
 * @Description: 博客首页
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-04-25 15:54:36
 * @LastEditTime: 2023-06-29 16:54:07
-->
<template>
  <el-container class="container-outside">
    <el-container class="container-inside">
      <el-header class="el-header">
        <common-header></common-header>
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <float-button></float-button>
  </el-container>
</template>
<script>
// import CommonAside from "../components/CommonAside.vue";
import CommonHeader from "../components/CommonHeader.vue";
import floatButton from "../components/floatButton.vue";
export default {
  components: {
    // CommonAside,
    CommonHeader,
    floatButton,
  },
  data() {
    return {};
  },
  mounted() {
    this.$router.push("/Home");
  },
};
</script>

<style lang="less" scoped>
.el-header {
  //background-color: transparent;
  //color: #ffffff;
  text-align: center;
  line-height: 56px;
  padding: 0 0 0 0;
  height: auto !important;
}
// .container-inside{
//   background-color: transparent;
// }

.el-aside {
  //background-color: #333;
  //color: #ffffff;
  text-align: center;
}

.el-main {
  //background-color: #e9eef3;
  //color: #333;
  text-align: center;
  padding: 10px 30px 20px 30px;
  overflow: hidden;
}
.container-outside,
.container-inside {
  height: 100%;
  background: transparent;
}
::v-deep .el-menu.el-menu--horizontal {
  border-bottom: solid 0px #e6e6e6;
}
</style>
