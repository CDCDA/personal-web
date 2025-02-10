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
      <el-tooltip content="菜单" placement="top">
        <svg-icon iconName="commonSvg-菜单" @click="openMenu" />
      </el-tooltip>
      <el-tooltip content="刷新" placement="top">
        <svg-icon iconName="commonSvg-刷新" @click="emit('refresh')" />
      </el-tooltip>
      <!--      <el-tooltip content="全屏" placement="top">-->
      <!--        <svg-icon iconName="commonSvg-全屏" @click="fullScreen" />-->
      <!--      </el-tooltip>-->
      <el-tooltip content="搜索栏" placement="top">
        <svg-icon iconName="commonSvg-搜索" @click="showSearch" />
      </el-tooltip>
    </div>
  </el-row>
  <c-dialog
    class="circle-menu-dialog"
    type="filter"
    :isFull="true"
    v-model="menuShow"
    style="height: 70%"
  >
    <circleMenu style="z-index: 9999; width: 100%; height: 60vh" />
  </c-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import circleMenu from './circleMenu.vue';
import { useRouter } from 'vue-router';
const props = defineProps({
  selection: {
    default: []
  }
});
const menuShow = ref(false);
const emit = defineEmits(['handleAdd', 'handleEdit', 'handleView', 'handleDel', 'refresh']);

function openMenu() {
  menuShow.value = true;
}

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
  // useTableResize();
}
onMounted(() => {});
</script>
<style>
.circle-menu-dialog {
  .el-dialog__body {
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>
<style lang="scss" scoped>
@include theme() {
  .manage-tools {
    position: absolute;
    right: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    .svg-icon {
      font-size: 1.2rem;
      cursor: pointer;
      margin-left: 8px;
      position: relative;
      transition: all 0.2s ease;
    }
    .svg-icon:active {
      transform: translateY(2px);
    }
    .svg-icon:hover {
      &::before {
        content: '';
        width: 28px;
        height: 28px;
        background: get('border-color');
        color: get('re-font-color');
        border-radius: 4px;
        position: absolute;
        z-index: -1;
      }
    }
  }
}
</style>
