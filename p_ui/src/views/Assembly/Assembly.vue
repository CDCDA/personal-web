<template>
  <div class="assembly-container">
    <leftAssemblyNav class="left-nav"></leftAssemblyNav>
    <div class="router-view-container">
      <router-view class="assembly-main"></router-view>
    </div>
  </div>
</template>

<script>
import leftAssemblyNav from "./leftAssemblyNav.vue";

export default {
  components: {
    leftAssemblyNav,
  },
  data() {
    return {};
  },
};
</script>
<style lang="less" scoped>
.assembly-container {
  display: flex;
  justify-content: space-between;
  border: 1px solid white;
  border-radius: 5px;
  height: 100%;
  width: 100%;
  margin: 0px auto;
  position: relative;
  z-index: 0;
  margin: auto;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0px 0px 2px 2px #888888;
  &::before {
    content: "";
    position: absolute;
    z-index: -2;
    left: -50%;
    top: -50%;
    width: 200%;
    height: 200%;
    background-color: #1a232a;
    background-repeat: no-repeat;
    background-position: 0 0;
    background-image: conic-gradient(
      transparent,
      rgba(168, 239, 255, 1),
      transparent 30%
    );
    animation: rotate 6s linear infinite;
  }
  &::after {
    content: "";
    position: absolute;
    z-index: -1;
    left: 6px;
    top: 6px;
    width: calc(100% - 12px);
    height: calc(100% - 12px);
    background: #000;
    border-radius: 5px;
  }
}
.left-nav {
  width: 20%;
  height: calc(100% - 14px);
  margin: 7px;
  border-radius: 5px;
}
.assembly-main {
  width: 100%;
}
.router-view-container {
  display: flex;
  justify-content: center;
  width: 80%;
  border-radius: 5px;
  height: calc(100% - 14px);
  margin: 7px 7px 7px -5px;
  background: #1a232a;
}
::v-deep .el-menu-item {
  border-radius: 5px;
}
</style>
