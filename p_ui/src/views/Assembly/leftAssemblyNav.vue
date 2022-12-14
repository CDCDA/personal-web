<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    router
  >
    <el-menu-item v-for="(item, i) in data" :key="i" @click="clickMenu(item)">
      <span class="first-menu-name" slot="title" :index="item.path">{{ item.label }}</span>
    </el-menu-item>
  </el-menu>
</template>
<script>
export default {
  data() {
    return {
      data: [
        {
          AssemblyID: "1",
          path:"AntvG6",
          label: "antvg6流程图",
        },
        {
          AssemblyID: "2",
          path:"Cascader",
          label: "层级选择器",
        },
        {
          AssemblyID: "3",
          path:"MonacoEditor",
          label: "monaco编辑器",
        },
        {
          AssemblyID: "4",
          path:"SelectTree",
          label: "下拉树选择框",
        },
        {
          AssemblyID: "5",
          path:"DatePicker",
          label: "日期选择器",
        },
        {
          AssemblyID: "100",
          path:"TestComponennts",
          label: "测试",
        },
        
      ],
    };
  },
  methods: {
    handleOpen() {},
    handleClose() {},
    clickMenu(item){
      this.$router.push({
        name: item.path,
      });
    }
  },
};
</script>
<style lang="less" scoped>
.el-menu-vertical-demo{
  border-radius: 10px;
}
</style>