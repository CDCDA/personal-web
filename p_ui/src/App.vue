<!--
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-01-16 10:52:03
 * @Description: 
-->
<template>
  <div id="app">
    <!-- :style="background" -->
    <!-- <video
      src="./assets/images/background.jpg"
      class="bjimg"
      autoplay
      loop
      muted
    ></video> -->
    <vue-particles
      class="bjimg"
      color="#fff"
      :particleOpacity="0.7"
      :particlesNumber="60"
      shapeType="circle"
      :particleSize="4"
      linesColor="#fff"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="6"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
    ></vue-particles>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  components: {},
  data() {
    return {
      // 顶部导航背景图片配置
      background: {
        // 背景图片地址
        backgroundImage:
          "url(" + require("./assets/images/app-background5.jpeg") + ")",
        // 背景图片是否重复
        backgroundRepeat: "no-repeat",
        // 背景图片大小
        backgroundSize: "cover",
        // 背景颜色
        backgroundColor: "#000",
        // 背景图片位置
        backgroundPosition: "center top",
      },
    };
  },
};
</script>

<style lang="scss">
#app {
  height: 100%;
  background: transparent;
}
body,
html {
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow: hidden;
}
.bgBackground {
  width: 100%;
  height: 100%;
}
.bjimg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
  object-fit: cover;
  background: left / cover fixed no-repeat url("./assets/images/background.jpg");
  //mix-blend-mode: screen;
}
.bt-login {
  background-color: transparent;
}
</style>
