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
      <div class="icon-settings"><i class="el-icon-s-tools"></i></div>
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
.icon-settings {
  height: 56px;
  width: auto;
  margin-right: 10px;
  margin-left: 5px;
  border: 1px solid #fff;
  padding: 0px 5px;
  border-radius: 3px;
  background: black;
  cursor: pointer;
  i {
    z-index: 999;
    margin: auto;
  }
  position: relative;
}
.head-title {
  width: 60%;
  color: white;
  position: relative;
  margin: 10px 5px 10px 5px;
  border: 1px solid #fff;
  cursor: pointer;
  border-radius: 3px;
  background: black;
}
.head-title,
.icon-settings {
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
  transition: all 0.5s; // 表示从 hover 进入普通状态时，0.5s 内完成变换
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
