<!--
 * @Author: chenyd 1205489124@qq.com
 * @Date: 2023-01-24 19:29:51
 * @LastEditors: chenyd 1205489124@qq.com
 * @LastEditTime: 2023-01-25 11:41:48
 * @FilePath: \p_ui\src\views\Home\Home.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <div class="home-main page-main"><random-slogan></random-slogan></div>
</template>

<script>
import RandomSlogan from "@/components/RandomSlogan.vue";

export default {
  components: {
    RandomSlogan,
  },
  data() {
    return {};
  },
  mounted() {
    console.log("QQQQ", this.$store.state.userId);
  },
};
</script>
<style lang="less">
.bt-login {
  margin-top: 60px;
  width: 100px;
  height: 40px;
  border-radius: 10px;
  box-shadow: 1ch;
}
.random-slogan {
  margin-bottom: 100px;
  color: #fff;
}
.container {
  display: grid;
}
.home-main {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
