<template>
  <!-- 一级菜单 -->
  <div class="container">
    <el-menu-item
      class="el-menu-first"
      v-if="!data.child"
      :index="data.index"
      @click="clickMenu(data)"
    >
      <i :class="data.icon"></i>
      <span class="first-menu-name" slot="title">{{ data.label }}</span>
    </el-menu-item>
    <!-- 二级菜单 -->
    <el-submenu
      @click="clickMenu(data)"
      class="el-submenu-second"
      :index="data.index"
      v-else
    >
      <template slot="title">
        <i :class="data.icon"></i>
        <span>{{ data.label }}</span>
      </template>
      <!-- 多级菜单 -->
      <nav-menu-item v-for="(child, i) in data.child" :data="child" :key="i" />
    </el-submenu>
  </div>
</template>
<script>
export default {
  name: "NavMenuItem",
  //接收父组件传递的数据，进行渲染
  props: ["data"],
  methods: {
    clickMenu(data) {
      this.$router.push({
        name: data.name,
      });
    },
  },
};
</script>
<style>
:root {
  --borderColor: #888888;
}
</style>
<style lang="less" scoped>
.el-menu-item,
.el-submenu {
}

.container {
  display: inline;
  position: relative;
  margin: 10px 5px 10px 5px;
  border: 1px solid #fff;
  cursor: pointer;
  width: 25%;
  border-radius: 3px;
  background: black;
  &::before,
  &::after {
    content: "";
    position: absolute;
    width: 20px;
    height: 20px;
    transition: 0.3s ease-in-out;
  }

  &::before {
    top: -5px;
    left: -5px;
    border-top: 1px solid #fff;
    border-left: 1px solid #fff;
    border-radius: 3px;
  }

  &::after {
    right: -5px;
    bottom: -5px;
    border-bottom: 1px solid #fff;
    border-right: 1px solid #fff;
    border-radius: 3px;
  }

  &:hover::before,
  &:hover::after {
    width: calc(100% + 9px);
    height: calc(100% + 9px);
  }
  li:hover {
    background: transparent !important;
    z-index: 1;
  }
}
</style>

