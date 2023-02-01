<!--
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-02-01 15:48:18
 * @Description: 首页
-->
<template>
  <div class="home-main page-main">
    <el-carousel :interval="4000" type="card"  :autoplay="false" class="carousel">
      <el-carousel-item v-for="item in 6" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item> </el-carousel
    ><random-slogan></random-slogan>
  </div>
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
    // console.log("QQQQ", this.$store.state.userId);
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
  flex-direction: column;
  .carousel {
    min-width: 500px;
    width: 50%;
    
  }
  .el-carousel__container{
    height: 50%;
    min-height: 400px;
  }
  .el-carousel__item h3 {
    color: #fff;
    font-size: 14px;
    opacity: 0.75;
    // line-height: 200px;
    height: 100%;
    margin: 0;
    box-shadow: 0px 0px 2px 2px #888888;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: transparent;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: rgba(29, 32, 33, 0.6);
  }
}
</style>
