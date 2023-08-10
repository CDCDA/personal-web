<template>
  <div class="common-header">
    <el-menu
      exact
      class="nav-bar"
      mode="horizontal"
      :ellipsis="false"
      background-color="transparent"
      text-color="#fff"
      menu-trigger="click"
      active-text-color="#ffd04b"
    >
      <nav-menu-item v-for="(item, index) in menuData" :key="index" :item="item" />
    </el-menu>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import NavMenuItem from './NavMenuItem.vue';

const menuData = ref([
  {
    label: '首页',
    icon: 'HomeFilled',
    name: 'Home',
    index: '1'
  },
  {
    label: '简介',
    icon: 'Reading',
    name: 'Profile',
    index: '2'
  },
  {
    label: '统计',
    icon: 'Histogram',
    name: 'Statistics',
    index: '3'
  },
  {
    label: '组件',
    icon: 'Grid',
    name: 'Assembly',
    index: '4'
  },
  {
    label: '博客',
    icon: 'Notebook',
    name: 'Blog',
    index: '5'
  },
  {
    label: '',
    icon: 'Tools',
    name: 'Setting',
    index: '6',
    className: 'setting-menu',
    children: [
      {
        label: '个人中心',
        icon: 'User',
        name: 'PersonalPage',
        index: '5-1'
      },
      {
        label: '换肤',
        icon: 'Connection',
        name: 'skinPeeler',
        index: '5-2'
      },
      {
        label: '设置',
        icon: 'Setting',
        name: 'Settings',
        index: '5-3'
      }
    ]
  }
]);

function isCollapseChange() {
  // this.$store.commit('collapseChange')
}
function toBlogEdit() {}
function toUser() {
  // this.$router.push('/Users')
}
</script>
<style lang="less">
.common-header {
  float: right;
}

.CommonHeader {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  background-color: transparent;
  transition: all 0.5s; // 表示从 hover 进入普通状态时，0.5s 内完成变换
}
.el-menu--horizontal > .el-menu-item.is-active,
.el-menu--horizontal > .el-sub-menu.is-active {
  border-radius: 5px;
  background: linear-gradient(to top, #ffd04b, #ffd04b) left top no-repeat,
    /*上左*/ linear-gradient(to right, #ffd04b, #ffd04b) left top no-repeat,
    /*左上*/ linear-gradient(to left, #ffd04b, #ffd04b) right top no-repeat,
    /*上右*/ linear-gradient(to bottom, #ffd04b, #ffd04b) right top no-repeat,
    /*上右*/ linear-gradient(to left, #ffd04b, #ffd04b) left bottom no-repeat,
    /*下左*/ linear-gradient(to bottom, #ffd04b, #ffd04b) left bottom no-repeat,
    /*左下*/ linear-gradient(to top, #ffd04b, #ffd04b) right bottom no-repeat,
    /*下右*/ linear-gradient(to left, #ffd04b, #ffd04b) right bottom no-repeat;
  background-size: 2px 12px, 12px 2px, 2px 12px, 12px 2px;
  height: 40px;
  border: none;
}
.el-menu {
  border-bottom: none;
  padding: 10px 0px;
  margin-right: 10px;
}
.el-menu--horizontal > .el-menu-item {
  height: 40px;
  border: none;
}
.el-menu--horizontal > .el-sub-menu.is-active .el-sub-menu__title {
  border: none;
}
</style>
