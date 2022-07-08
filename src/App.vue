<template>
  <div id="app" >
    <!-- :style="background" -->
    <!-- <video
      src="./assets/images/video_appBackground.mp4"
      class="bjimg"
      autoplay
      loop
      muted
    >
      
    </video> -->

    <router-view> </router-view>
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

<style>
#app {
  height: 100%;
}
body,
html {
  height: 100%;
  margin: 0px;
  padding: 0px;
  background-color: #0F1224;
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
  z-index: -10;
  object-fit: cover;
  mix-blend-mode: screen;
}
.bt-login {
  background-color: transparent;
}
</style>
