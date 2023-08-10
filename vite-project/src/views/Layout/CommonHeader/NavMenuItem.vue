<!--
 * @Description: 子菜单
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-27 16:51:55
 * @LastEditTime: 2023-07-24 13:44:38
-->
<template>
  <el-menu-item
    v-if="!item.children"
    :index="item.index"
    @click="clickMenu(item)"
    class="nav-menu-item"
  >
    <el-icon>
      <component :is="item.icon" />
    </el-icon>
    <span class="first-menu-name" slot="title">{{ item.label }}</span>
  </el-menu-item>
  <el-sub-menu :index="item.index" :class="[item.className]" v-else>
    <template #title>
      <el-icon>
        <component :is="item.icon" />
      </el-icon>
      <span>{{ item.label }}</span>
    </template>
    <nav-menu-item v-for="(child, i) in item.children" :item="child" :key="i" />
  </el-sub-menu>
</template>
<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

const itemData = reactive({});
const props = defineProps({
  item: null
});
const router = useRouter();

function clickMenu(item: any) {
  router.push({ name: item.name });
}
</script>
<style>
:root {
  --borderColor: #888888;
}
</style>
<style lang="less" scoped>
.nav-menu-item {
  // display: inline;
  // position: relative;
  // margin: 10px 5px 10px 5px;
  // border: 1px solid #fff;
  // cursor: pointer;
  // width: auto;
  // border-radius: 3px;
  // background-color: rgb(11 12 12 / 70%);

  li:hover {
    background: transparent !important;
    z-index: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.setting-menu {
  width: 64px;

  :v-deep(.el-icon.el-sub-menu__icon-arrow) {
    display: none;
  }
}
</style>
