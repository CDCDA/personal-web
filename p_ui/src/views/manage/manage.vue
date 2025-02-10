<!--
 * @Description: 视差管理页
-->
<template>
  <div class="manage-main">
    <side-bar></side-bar>
    <div class="manage-wrap"></div>
  </div>
</template>
<script setup lang="ts">
import { onMounted } from 'vue';
import SideBar from '@/views/manage/components/sideBar.vue';
import useThemeStore from '@/store/modules/theme.ts';
const themeStore = useThemeStore();
themeStore.isShow = false;
themeStore.isFooterShow = false;
onMounted(() => {});
</script>

<style lang="scss" scoped>
.manage-main {
  width: 100vw;
  height: 100vh;
  background: white;
}
</style>
