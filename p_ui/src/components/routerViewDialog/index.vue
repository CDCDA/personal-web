<!--
 * @Description: 路由跳转弹窗
-->
<template>
  <el-dialog
    class="router-view-dialog bounceInLeft animated"
    v-model="dialogVisible"
    :fullscreen="true"
    width="88%"
    top="60px"
    :modal="false"
  >
    <template #header="{ close, titleId, titleClass }">
      <div class="c-dialog-header">
        <div class="c-dialog-title">{{ props.title }}</div>
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
