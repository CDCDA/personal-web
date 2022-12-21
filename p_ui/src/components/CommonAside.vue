<template>
  <div class="commonAside">
    <h3>导航</h3>
    <el-menu
      :default-active="this.$route.path"
      exact
      class="el-menu-vertical-demo"
      text-color="#ffffff"
      background-color="#333"
      active-text-color="#ffd04b"
      :collapse="isCollapse"
      popper-append-to-body="true"
    >
      <!--将子菜单抽取出来，做为一个子组件-->
      <nav-menu-item
        v-for="(item, index) in menuData"
        :key="index"
        :datas="item"
      />
    </el-menu>
  </div>
</template>
<style lang="less" scoped>
::v-deep .el-menu-vertical-demo {
  font-size: 18px;
  height: 100%;
  border: none;
  .el-menu-item i {
    color: #fff;
  }
}
</style>
<script>
import NavMenuItem from "./NavMenuItem.vue";
export default {
  components: {
    NavMenuItem,
  },
  data() {
    return {
      //伪造的数据，通过判断child属性有没有子菜单
      menuData: [
        {
          label: "首页",
          icon: "el-icon-s-home",
          name: "home",
        },
        {
          label: "用户",
          icon: "el-icon-menu",
          name: "user",
          // child: [
          //   {
          //     label: "菜单2-1",
          //     url: "/flowerUp",
          //     icon: "",
          //     name: "demo-2-1",
          //   },
          //   {
          //     label: "菜单2-2",
          //     url: "/flowerDown",
          //     icon: "",
          //     name: "demo-2-2",
          //   },
          // ],
        },
        {
          label: "菜单3",
          url: "/user",
          icon: "el-icon-setting",
          name: "demo-3",
        },
        {
          label: "菜单4",
          url: "/chart",
          icon: "el-icon-pie-chart",
          name: "demo-4",
        },
      ],
    };
  },
  props: [],
  watch: {},
  computed: {
    isCollapse() {
      return this.$store.state.isMenuCollapse;
    },
  },
  methods: {},
};
</script>