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
    :modal="false"
  >
    <template #header="{ close, titleId, titleClass }">
      <div class="header">
        {{ props.title }}
        <el-icon class="full-screen" @click="handleFull()" style="margin-right: 18px"
          ><FullScreen
        /></el-icon>
      </div>
    </template>
    <KeepAlive> <router-view /></KeepAlive>
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
    height: 85%;
    :deep(.el-dialog__body) {
      padding: 0px !important;
      width: 100% !important;
      height: 100% !important;
    }
    .el-dialog__header {
      display: block !important;
    }
    .header {
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      text-align: center;
      font-size: 20px;
      font-weight: bold;
    }
    .full-screen {
      font-size: 17px;
      float: right;
      top: 7px;
      right: 32px;
      padding: 7px;
      position: absolute;
    }
    .full-screen:hover {
      color: get('re-font-color');
      background: get('bk');
      border-radius: 20px;
    }
    .el-dialog__headerbtn {
      top: 3px !important;
    }
  }
}

.is-fullscreen.router-view-dialog {
  height: 100%;
  border-radius: 0px !important;
}
</style>
