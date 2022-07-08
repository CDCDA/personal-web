<template>
  <div class="commonHead">
    <div class="head-title">这是个网站</div>
    <div class="menu-container">
      <el-menu
        :default-active="this.$route.path"
        exact
        class="el-menu-demo"
        mode="horizontal"
        background-color="transparent"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <nav-menu-item
          v-for="(item, index) in menuData"
          :key="index"
          :data="item"
        />
      </el-menu>
      <i class="el-icon-s-tools"></i>
    </div>
  </div>
</template>

<script>
import NavMenuItem from "./NavMenuItem.vue";
export default {
  components: {
    NavMenuItem,
  },
  data() {
    return {
      menuData: [
        {
          label: "首页",
          icon: "el-icon-s-home",
          name: "Home",
          index: "1",
        },
        {
          label: "用户",
          icon: "el-icon-menu",
          name: "Users",
          index: "2",
        },
        {
          label: "组件",
          icon: "el-icon-s-grid",
          name: "Assembly",
          index: "3",
        },
        {
          label: "博客",
          icon: "el-icon-notebook-1",
          name: "Blog",
          index: "4",
        },
      ],
    };
  },
  methods: {
    isCollapseChange() {
      this.$store.commit("collapseChange");
    },
  },
};
</script>
<style lang="less" scoped>
.head-title {
  width: 60%;
  color: white;
}
.menu-container {
  width: 40%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.commonHead {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  background-color: transparent;
}
.el-icon-s-tools {
  font-size: 23px;
  margin-right: 20px;
  color: white;
}
.el-menu-demo {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
