<!--
 * @Description: 封装图片组件
-->
<template>
  <el-image
    class="c-image"
    :src="src"
    :fit="fit"
    :preview-src-list="previewSrcList"
    :preview-teleported="true"
    :initialIndex="initialIndex"
    hide-on-click-modal
  >
    <template #placeholder>
      <div class="image-slot" v-cLoading="loading" style="width: 100%; height: 100%" />
    </template>
    <template #error>
      <div class="image-error-slot">
        <svg-icon iconName="commonSvg-图片加载失败"></svg-icon>
      </div>
    </template>
  </el-image>
  <!--  <el-dialog v-model="dialogVisible" append-to=".dialog-base">-->
  <!--    <img :src="src" style="width: 100%" alt="" />-->
  <!--  </el-dialog>-->
</template>
<script lang="ts" setup>
import { ref, nextTick } from 'vue';
import { autoClearTimer } from '@/utils/timer.ts';
const loading = ref('rotate') as any;
const props = defineProps({
  src: {
    default: ''
  },
  preview: {
    default: false
  },
  fit: {
    default: 'cover'
  },
  previewSrcList: {
    default: []
  },
  initialIndex: {
    default: 0
  }
});
</script>
<style lang="scss" scoped>
.c-image {
  :deep(.el-image__inner) {
    width: 100%;
    height: 100%;
  }
}
</style>
