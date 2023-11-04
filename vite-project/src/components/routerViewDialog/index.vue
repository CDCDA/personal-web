<!--
 * @Description: 路由跳转弹窗
-->
<template>
  <el-dialog
    class="router-view-dialog bounceInLeft animated"
    v-model="dialogVisible"
    :fullscreen="isFull"
    width="88%"
    top="60px"
  >
    <template #header="{ close, titleId, titleClass }">
      <div class="c-dialog-header">
        <div class="c-dialog-title">{{ props.title }}</div>
        <el-icon class="full-screen" @click="handleFull()"><FullScreen /></el-icon>
      </div>
    </template>
    <router-view></router-view>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
const dialogVisible = ref(false);
const props = defineProps({
  title: null
});
const isFull = ref(false);
function close() {
  dialogVisible.value = false;
}
function open() {
  dialogVisible.value = true;
}

function handleFull() {
  isFull.value = !isFull.value;
}

onMounted(() => {
  // getTypeTree();
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss">
@include theme() {
  .router-view-dialog {
    .el-dialog__body {
      padding: 0px !important;
      width: 100% !important;
      height: 100% !important;
      margin-top: 55px;
    }
    .c-dialog-title {
      position: absolute;
      width: 100%;
      top: 13px;
      font-size: 20px;
      font-weight: bold;
    }
    .full-screen {
      font-size: 22px;
      float: right;
      top: 9px;
      right: 32px;
      padding: 7px;
    }
    .full-screen:hover {
      color: get('re-font-color');
      background: get('bk');
      border-radius: 20px;
    }
    height: 85%;
  }
  .is-fullscreen.router-view-dialog {
    height: 100%;
    border-radius: 0px !important;
  }
}
</style>
