<template>
  <!-- 一级菜单 -->
  <div class="container">
    <el-menu-item class="el-menu-first" v-if="!data.child" :index="data.index" @click="clickMenu(data)">
      <i :class="data.icon"></i>
      <span class="first-menu-name" slot="title">{{data.label}}</span>
    </el-menu-item>
    <!-- 二级菜单 -->
    <el-submenu @click="clickMenu(data)" class="el-submenu-second" :index="data.index" v-else >
      <template slot="title">
        <i :class="data.icon"></i>
        <span>{{data.label }}</span>
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

<style lang="less" scoped>
.el-menu-item,
.el-submenu {
  width: 200px;
}
.container{
  display: inline;
}
</style>

