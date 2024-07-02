<!--
 * @Description: 首页布局 
-->
<template>
  <el-container class="container-outside vacuumization">
    <!-- <sakura style="position: absolute" :new-options="options" /> -->
    <el-container class="container-inside">
      <el-header class="el-header">
        <common-header></common-header>
      </el-header>
      <el-main id="main">
        <keep-alive><router-view></router-view></keep-alive>
        <common-footer></common-footer>
      </el-main>
    </el-container>
    <!-- <float-button></float-button> -->
  </el-container>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import CommonHeader from '@/views/layout/commonHeader/index.vue';
import CommonFooter from '@/views/layout/commonFooter/index.vue';
import { onMounted } from 'vue';
import { verifyToken } from '@/api/user.ts';
const userStore = useUserStore();
const options = ref({
  staticx: false,
  stop: null,
  num: 15,
  show: true,
  zIndex: -1
} as any);
const router = useRouter();
onMounted(() => {
  if (window.localStorage.getItem('token') && userStore.userId != '2') verifyToken({});
  router.push({ name: 'home' });
  // router.push({ name: 'ganttChart' });
});
</script>

<style lang="scss" scoped>
.el-header {
  text-align: center;
  line-height: 56px;
  padding: 0 0 0 0;
  height: auto !important;
}

.el-aside {
  text-align: center;
}

.el-main {
  text-align: center;
  padding: 0px;
  overflow: auto;
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
