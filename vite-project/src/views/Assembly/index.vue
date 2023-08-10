<!--
 * @Description: 
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-26 16:56:13
 * @LastEditTime: 2023-08-05 14:04:30
-->
<template>
  <div class="assembly-container page-main">
    <leftAssemblyNav class="left-nav bounceInRight animated"></leftAssemblyNav>
    <div class="router-view-container bounceInLeft animated">
      <router-view class="assembly-body"></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import leftAssemblyNav from './leftAssemblyNav.vue';

// export default {
//   components: {
//     leftAssemblyNav
//   },
//   data() {
//     return {};
//   }
// };
</script>
<style lang="less" scoped>
.assembly-container {
  display: flex;
  justify-content: center;
  height: 100%;
}
:v-deep(.el-menu) {
  border-right: none;
}
.left-nav {
  width: 225px;
  height: calc(100% - 20px);
  margin: 10px 15px 10px 15px;
  border-radius: 5px;
  background-color: rgba(29, 32, 33, 0.6);
  box-shadow: 0 2px 12px 0 #000000;
}
.assembly-body {
  width: 100%;
}
.router-view-container {
  display: flex;
  justify-content: center;
  width: calc(100% - 280px);
  border-radius: 5px;
  height: calc(100% - 20px);
  margin: 10px 1% 10px 0px;
  background-color: rgba(29, 32, 33, 0.6);
  box-shadow: 0 2px 12px 0 #000000;
}
:v-deep(.el-menu-item) {
  border-radius: 5px;
}
</style>
