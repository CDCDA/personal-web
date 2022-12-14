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
  </el-container>
</template>
<script>
import CommonAside from "../components/CommonAside.vue";
import CommonHeader from "../components/CommonHeader.vue";

export default {
  components: {
    CommonAside,
    CommonHeader,
  },
  data() {
    return {};
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
  padding: 10px 30px 20px 40px;
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
