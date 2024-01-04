<!--
 * @Description: 管理页
-->
<template>
  <div class="manage page-main">
    <sideBar class="manage-left"></sideBar>
    <div class="manage-right">
      <KeepAlive>
        <RouterView></RouterView>
      </KeepAlive>
    </div>
  </div>
</template>
<script setup lang="ts">
import sideBar from './components/sideBar.vue';
</script>
<style lang="scss" scoped>
@include theme() {
  .manage {
    height: calc(98vh - 60px);
    min-height: calc(98vh - 60px) !important;
    background: get('background');
    box-shadow: get('box-shadow');
    position: relative;
    overflow: hidden;
    border-radius: 10px;
  }
}
.manage {
  display: flex;
  justify-content: space-between;
  .manage-right {
    width: calc(100% - 240px);
  }
}
</style>
