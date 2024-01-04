<!--
 * @Description: 封装图片组件
-->
<template>
  <el-image class="c-image" :src="props.src" lazy>
    <template #placeholder>
      <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%" />
    </template>
    <template #error>
      <div class="image-error-slot">
        <svg-icon iconName="imgFailed"></svg-icon>
      </div>
    </template>
  </el-image>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted } from 'vue';
const props = defineProps(['src']);
onMounted(() => {
  console.log('SSS', props.src);
});
</script>
<style lang="scss" scope>
.c-image {
  :deep(.el-image__inner) {
    width: 100%;
    height: 100%;
  }
}
</style>
