<template>
  <div id="app-theme" data-theme="theme-dark">
    <!-- :style="background" -->
    <!-- <video
      src="./assets/images/background.jpg"
      class="particles"
      autoplay
      loop
      muted
    ></video> -->
    <!-- <div class="particles" uid></div> -->
    <Particles class="particles" id="tsparticles" :options="options" />
    <router-view />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();

const options = {
  background: {
    color: {
      // value: "#000", //粒子颜色
    }
  },
  fpsLimit: 60,
  interactivity: {
    events: {
      onClick: {
        enable: true,
        mode: 'repulse' //可用的click模式有: "push", "remove", "repulse", "bubble"。
      },
      onHover: {
        enable: true,
        mode: 'grab' //可用的hover模式有: "grab", "repulse", "bubble"。
      },
      resize: true
    },
    modes: {
      bubble: {
        distance: 400,
        duration: 2,
        opacity: 0.8,
        size: 40
      },
      push: {
        quantity: 4
      },
      repulse: {
        distance: 200,
        duration: 0.4
      }
    }
  },
  particles: {
    color: {
      value: '#ffffff'
    },
    links: {
      color: '#ffffff', //'#dedede'。线条颜色。
      distance: 150, //线条长度
      enable: true, //是否有线条
      opacity: 0.5, //线条透明度。
      width: 1 //线条宽度。
    },
    collisions: {
      enable: false
    },
    move: {
      direction: 'none',
      enable: true,
      outMode: 'bounce',
      random: false,
      speed: 2, //粒子运动速度。
      straight: false
    },
    number: {
      density: {
        enable: true,
        area: 800
      },
      value: 60 //粒子数量。
    },
    opacity: {
      value: 0.5 //粒子透明度。
    },
    shape: {
      type: 'circle' //可用的粒子外观类型有："circle","edge","triangle", "polygon","star"
    },
    size: {
      random: true,
      value: 5
    }
  },
  detectRetina: true
};

onMounted(() => {
  let themeKey = window.localStorage.getItem('themeKey');
  let backUrl = window.localStorage.getItem('backUrl');
  if (!themeKey) window.localStorage.setItem('themeKey', 'theme-dark');
  if (!backUrl)
    window.localStorage.setItem(
      'backUrl',
      'left/cover fixed no-repeat url(../../../assets/images/bk-3.jpg)'
    );
  let back = document.getElementById('tsparticles') as any;
  (document.getElementById('app-theme') as any).setAttribute('data-theme', themeKey);
  back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
  if (window.localStorage.getItem('token')) {
    router.push({ path: '/home' });
  } else router.push({ path: '/login' });
});
</script>

<style lang="scss">
#app-theme,
#app {
  @include full();
  background: transparent;
}
body,
html {
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow: hidden;
  // font-family: FangDaKai;
}
.particles {
  position: fixed;
  top: 0;
  left: 0;
  @include full();
  z-index: -1;
  object-fit: cover;
  background: left / cover fixed no-repeat url('@/assets/images/bk-3.jpg');
}
</style>
