<!--
 * @Description: 管理页
-->
<template>
  <div class="manage page-main">
    <sideBar class="manage-left"></sideBar>
    <div class="manage-center">
      <router-view v-slot="{ Component }">
        <keep-alive>
          <component :is="Component" />
        </keep-alive>
      </router-view>
    </div>
    <!-- <footerMenu /> -->
    <!-- <Tools class="manage-right"></Tools> -->
  </div>
</template>
<script setup lang="ts">
import sideBar from './components/sideBar.vue';

import footerMenu from './components/footerMenu.vue';
</script>
<style lang="scss" scoped>
@include theme() {
  .manage {
    height: calc(98vh - 70px);
    width: 88vw !important;
    // min-height: calc(98vh - 60px) !important;
    max-width: none !important;
    // margin-top: 65px !important;
    // background: get('background') !important;
    box-shadow: get('box-shadow');
    position: relative;
    overflow: hidden;
    border-radius: 8px !important;
  }
}
.manage {
  display: flex;
  justify-content: space-between;
  .manage-left {
    width: 250px;
    // border-right: 1px solid #ccc;
  }
  .manage-center {
    width: 100%;
  }
  .manage-right {
  }
}
</style>
