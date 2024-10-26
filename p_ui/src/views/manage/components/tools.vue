<!--
 * @Description: 管理页按钮栏
-->
<template>
  <el-row :gutter="10" class="manage-button-group" style="margin-bottom: 15px">
    <el-col :span="1.5">
      <el-button type="primary" plain icon="Plus" @click="emit('handleAdd')">新增</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="primary"
        plain
        icon="Edit"
        @click="emit('handleEdit')"
        :disabled="props.selection.length == 0"
        >修改</el-button
      >
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="warning"
        plain
        icon="View"
        @click="emit('handleView')"
        :disabled="props.selection.length == 0"
        >查看</el-button
      >
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="danger"
        plain
        icon="Delete"
        @click="emit('handleDel')"
        :disabled="props.selection.length == 0"
        >删除</el-button
      >
    </el-col>
    <div class="manage-tools">
      <el-tooltip content="刷新" placement="top">
        <svg-icon iconName="刷新" @click="emit('refresh')" />
      </el-tooltip>
      <el-tooltip content="全屏" placement="top">
        <svg-icon iconName="全屏" @click="fullScreen" />
      </el-tooltip>
      <el-tooltip content="搜索栏" placement="top">
        <svg-icon iconName="搜索" @click="showSearch" />
      </el-tooltip>
    </div>
  </el-row>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useTableResize } from '@/utils/manage';
import { useRouter } from 'vue-router';
const props = defineProps({
  selection: {
    default: []
  }
});
const emit = defineEmits(['handleAdd', 'handleEdit', 'handleView', 'handleDel', 'refresh']);

function fullScreen() {
  let pageMain = document.querySelector('.page-main') as any;
  if (!pageMain) return;
  if (pageMain.classList.contains('is-full')) {
    pageMain.classList.remove('is-full');
  } else {
    pageMain.classList.add('is-full');
  }
}
function showSearch() {
  let manageMain = document.querySelector('.manage-main');
  if (!manageMain) return;
  if (manageMain.classList.contains('is-hidden')) {
    manageMain.classList.remove('is-hidden');
  } else {
    manageMain.classList.add('is-hidden');
  }
  useTableResize();
}
onMounted(() => {});
</script>
<style lang="scss" scoped>
@include theme() {
  .manage-tools {
    position: absolute;
    right: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    .svg-icon-wrap {
      height: 22px;
      width: 22px;
      cursor: pointer;
      margin-left: 8px;
      position: relative;
      transition: all 0.2s ease;
    }
    .svg-icon-wrap:active {
      transform: translateY(2px);
    }
    .svg-icon-wrap:hover {
      &::before {
        content: '';
        width: 28px;
        height: 28px;
        background: get('border-color');
        border-radius: 4px;
        position: absolute;
        z-index: -1;
      }

      :deep(.theme-icon) {
        fill: white !important;
      }
    }
  }
}
</style>
