<template>
  <div>首页</div>
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
  margin-top: 100px;
}
.container {
  display: grid;
}
</style>
