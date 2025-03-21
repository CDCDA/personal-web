<!--
 * @Description: 管理页侧边栏
-->
<template>
  <div class="sidebar" :class="isShowMenu ? 'is-show' : ''">
    <ul class="sidebar-main">
      <li
        class="sidebar-main-item"
        :class="activeMenu.name == item.name ? 'is-active' : ''"
        v-for="item in menuList"
        @click="menuClick(item)"
      >
        {{ item.meta.remark }}
        <svg-icon class="sidebar-item-icon" iconName="commonSvg-右-双层"></svg-icon>
      </li>
    </ul>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const menuList = ref([] as any);
const isShowMenu = ref(false as any);
const activeMenu = ref({} as any);

function menuClick(item: any) {
  router.push({ name: item.name });
  activeMenu.value = item;
}

onMounted(() => {
  menuList.value = (router.options.routes as any).find((x: any) => x.name == 'manage').children;
  if (menuList.value.length > 0) {
    // activeMenu.value = menuList.value[0];
    // router.push({ name: activeMenu.value.name });
  }
});
</script>
<style lang="scss" scoped>
@include theme() {
  .sidebar {
    // background: get('bk');
  }
  .cover-top,
  .cover-center,
  .cover-bottom {
    background: get('back');
  }
  .menu-count-wrap {
    background: get('back');
    box-shadow: get('box-shadow');
    .menu-count {
      span {
        background: get('border-color');
        box-shadow: get('box-shadow');
      }
    }
  }
  .sidebar-main-item {
    color: get('font-color');
  }
  .sidebar-main-item:hover,
  .sidebar-main-item.is-active {
    background: get('border-color');
    transform: scale(1.04);
    box-shadow: 0 1px 2px 0 #757171;
    border-radius: 8px;
    color: get('re-font-color');
    .sidebar-item-icon {
      opacity: 1;
      right: 20px;
    }
  }
}

@keyframes toLeftTop {
  0% {
    left: -156%;
  }
  50% {
    left: -156%;
  }
  100% {
    left: -56%;
  }
}
@keyframes toRightBottom {
  0% {
    left: 129%;
  }
  50% {
    left: 129%;
  }
  100% {
    left: 30%;
  }
}

@keyframes expansion {
  0% {
    width: 0%;
    left: 50%;
  }
  50% {
    width: 0%;
    left: 50%;
  }
  100% {
    width: 100%;
    left: 0%;
  }
}

@keyframes titleOut {
  0% {
    opacity: 1;
    transform: translateY(0px);
  }
  70% {
    opacity: 0.6;
    transform: translateY(15px);
  }
  100% {
    opacity: 0;
  }
}
@keyframes sidebarIn {
  0% {
    opacity: 0;
    transform: translateY(10px);
  }
  70% {
    opacity: 0;
    transform: translateY(10px);
  }
  100% {
    opacity: 1;
    transform: translateY(0px);
  }
}
.sidebar {
  width: 240px;
  height: 100%;
  overflow: hidden;
  position: relative;
  padding: 0 !important;
}
.sidebar-main {
  width: 100%;
  height: 100%;
  z-index: 1;
  position: relative;
  margin: 0;
  padding: 0;
  overflow-y: auto;
  overflow-x: hidden;
  li {
    height: 50px;
    // border: 1px solid;
    list-style: none;
  }
}
.cover-top {
  position: absolute;
  left: -160%;
  top: -23%;
  height: 150%;
  width: 100%;
  transform: rotate(23deg);
  transition: all 0.5s linear;
}
.cover-bottom {
  position: absolute;
  left: 160%;
  top: 0%;
  height: 150%;
  width: 100%;
  transform: rotate(23deg);
  transition: all 0.5s linear;
}
.cover-center {
  position: absolute;
  left: 50%;
  top: -26%;
  height: 150%;
  width: 0;
  transform: rotate(23deg);
  transition: all 0.5s linear;
}
.sidebar.is-show {
  .cover-top {
    // animation: toLeftTop 1s linear forwards;
  }
  .cover-bottom {
    // animation: toRightBottom 1s linear forwards;
  }
  .cover-center {
    // animation: expansion 1s linear forwards;
  }
  .menu-count-wrap {
    // animation: titleOut 0.5s linear forwards;
  }
  .sidebar-main {
    z-index: 1;
    // animation: sidebarIn 1.5s linear forwards;
  }
}
.sidebar-main-item {
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s linear;
  position: relative;
  .sidebar-item-icon {
    position: absolute;
    right: 0;
    opacity: 0;
    top: calc(50% - 10px);
    width: 20px;
    height: 20px;
    transition: all 0.3s linear;
  }
}
.sidebar-main-item:active {
  transform: translateY(2px) !important;
}

.menu-count-wrap {
  width: 40px;
  height: 100px;
  background: blue;
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  left: calc(50% - 20px);
  top: calc(50% - 40px);
  cursor: pointer;
  .menu-count-title {
    width: 100%;
    height: 100%;
    font-size: 22px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    position: absolute;
    .svg-icon {
      width: 25px;
      height: 25px;
    }
  }
  .menu-count {
    position: relative;
    width: 100%;
    height: 100%;
    span {
      position: absolute;
      right: -12px;
      top: -12px;
      border-radius: 50%;
      width: 24px;
      height: 24px;
      display: flex;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>
