<!--
 * @Description: 首页布局
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-26 16:56:13
 * @LastEditTime: 2023-07-07 13:35:04
-->
<template>
  <el-container class="container-outside">
    <el-container class="container-inside">
      <el-header class="el-header">
        <common-header></common-header>
      </el-header>
      <el-main>
        <keep-alive><router-view></router-view></keep-alive>
      </el-main>
    </el-container>
    <float-button></float-button>
  </el-container>
</template>
<script setup lang="ts">
// import CommonAside from '@/views/Layout/CommonHeader/CommonHeader.vue'
import { useRouter } from 'vue-router';
import CommonHeader from '@/views/Layout/CommonHeader/CommonHeader.vue';
import { onMounted } from 'vue';
import floatButton from '@/components/floatButton.vue';

const router = useRouter();

onMounted(() => {
  router.push({ name: 'Home' });
});
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
  padding: 00px 30px 20px 30px;
  overflow: hidden;
}
.container-outside,
.container-inside {
  height: 100%;
  background: transparent;
}
:v-deep(.el-menu.el-menu--horizontal) {
  border-bottom: solid 0px #e6e6e6;
}
</style>
