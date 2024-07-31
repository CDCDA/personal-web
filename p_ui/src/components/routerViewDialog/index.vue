<!--
 * @Description: 路由跳转弹窗
-->
<template>
  <c-dialog
    class="router-view-dialog bounceInLeft animated"
    v-model="dialogVisible"
    :fullscreen="isFull"
    width="88%"
    top="60px"
    :title="props.title"
    :modal="false"
  >
    <KeepAlive> <router-view /></KeepAlive>
  </c-dialog>
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

    .el-dialog__body {
      height: calc(100% - 46px) !important;
      padding-top: 0px !important;
      & > :first-child {
        border-radius: 8px !important;
        height: 100% !important;
      }
    }
  }
}
</style>
