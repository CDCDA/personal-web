<!-- @contextmenu.prevent.native="openMenu($event)" -->
<template>
  <div id="app-theme" data-theme="theme-dark" v-cLoading="loading">
    <video id="tsparticles" autoplay loop muted v-if="backType == 'video'"></video>
    <div
      id="tsparticles"
      class="particles"
      v-if="backType != 'video' && !themeStore.aspectOptions?.isParticles"
    ></div>
    <Particles
      class="particles"
      id="tsparticles"
      :options="options"
      v-if="backType != 'video' && themeStore.aspectOptions?.isParticles"
    />
    <KeepAlive> <router-view /></KeepAlive>
    <rightClickMenu :visible="visible" :left="left" :top="top" />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import rightClickMenu from '@/components/rightClickMenu/index.vue';
import { useRouter } from 'vue-router';
import useThemeStore from '@/store/modules/theme.ts';

var themeStore = useThemeStore();
const loading = ref('gear' as any);
const router = useRouter();
const visible = ref(false);
const top = ref(0);
const left = ref(0);
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
const realOptions = ref(JSON.parse(JSON.stringify(options)) as any);
const backType = ref('img' as any);

watch(
  () => visible.value,
  newValue => {
    if (newValue) {
      //菜单显示的时候
      // document.body.addEventListener，document.body.removeEventListener它们都接受3个参数
      // ("事件名" , "事件处理函数" , "布尔值");
      // 在body上添加事件处理程序
      document.body.addEventListener('click', closeMenu);
    } else {
      //菜单隐藏的时候
      // 移除body上添加的事件处理程序
      document.body.removeEventListener('click', closeMenu);
    }
  }
);
watch(
  () => themeStore.backType,
  newValue => {
    backType.value = newValue;
  },
  {
    deep: true,
    immediate: true
  }
);

watch(
  () => themeStore.aspectOptions.isParticles,
  val => {
    if (val) {
      realOptions.value = JSON.parse(JSON.stringify(options));
    } else {
      realOptions.value = null;
    }
    init();
  },
  {
    deep: true
  }
);

//右击
function openMenu(e: any) {
  var x = e.pageX; //这个应该是相对于整个浏览器页面的x坐标，左上角为坐标原点（0,0）
  var y = e.pageY; //这个应该是相对于整个浏览器页面的y坐标，左上角为坐标原点（0,0）
  top.value = y;
  left.value = x;
  visible.value = true; //显示菜单
}
//关闭菜单
function closeMenu() {
  visible.value = false; //关闭菜单
}

function init() {
  setTimeout(() => {
    // loading.value = false;
    //   (document.getElementById('app-theme') as any).style.animation =
    //     'blur-to-clear 2.5s cubic-bezier(0.6, 0.25, 0.25, 1) 0s 1 backwards,scale 2.2s cubic-bezier(0.6, 0.1, 0.25, 1) 0.5s 1 backwards';
  }, 1000);
  let themeKey = window.localStorage.getItem('themeKey');
  let backUrl = window.localStorage.getItem('backUrl');
  let options = window.localStorage.getItem('aspectOptions') as any;
  setTimeout(() => {
    let back = document.getElementById('tsparticles') as any;
    (document.getElementById('app-theme') as any).setAttribute('data-theme', themeKey);

    if (backType.value == 'img')
      back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
    if (backType.value == 'color') back.style.background = backUrl;
    if (backType.value == 'video') back.src = backUrl;
    // back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
    themeStore.theme = window.localStorage.getItem('themeKey') as any;
    themeStore.backType = window.localStorage.getItem('backType') as any;

    if (window.localStorage.getItem('token')) {
      router.push({ path: '/home' });
    } else router.push({ path: '/login' });
  }, 0);
  if (options) {
    options = JSON.parse(options);
    let appTheme = document.querySelector('#app-theme') as any;
    appTheme.style.color = options.fontColor;
    appTheme.style.fontFamily = options.fontFamily;
    let header = document.querySelector('.common-header') as any;
    let homeTop = document.querySelector('.home-top') as any;
    let CycleUpDown = document.querySelector('.CycleUpDown') as any;
    if (header) {
      // header.style.color = options.mhFontColor;
      // let icons = header.querySelectorAll('.theme-icon');
      // Object.keys(icons).forEach((e: any) => {
      //   icons[e].style.fill = options.mhFontColor;
      // });
    }
    if (homeTop) homeTop.style.color = options.mhFontColor;
    if (CycleUpDown) {
      let themeIcon = CycleUpDown.querySelector('.theme-icon') as any;
      if (themeIcon) {
        themeIcon.style.fill = options.mhFontColor;
      }
    }
  }
  backType.value = window.localStorage.getItem('backType');
  if (!themeKey) {
    window.localStorage.setItem('themeKey', 'theme-light');
  }
  if (!backUrl) window.localStorage.setItem('backUrl', 'white');
  if (!backType.value) {
    window.localStorage.setItem('backType', 'img');
  }
}

onMounted(() => {
  init();
});
</script>

<style lang="scss">
#app-theme,
#app {
  @include full();
  // font-family: DaoLiTi;
  background: url('/img/失落方舟.jpg');
}
body,
html {
  height: 100%;
  margin: 0px;
  padding: 0px;
  overflow: hidden;
  // font-family: FangDaKai;
}
body {
  --dy: 1;
  --dx: 1;
  --dz: 0;
}
@keyframes blur-to-clear {
  0% {
    -webkit-filter: blur(50px);
    transform: scale(1.2);
    filter: blur(50px);
  }
  100% {
    -webkit-filter: blur(0);
    transform: scale(1);
    filter: blur(0);
  }
}
#tsparticles {
  height: 100%;
  width: 100%;
  object-fit: cover;
  position: absolute;
  z-index: 0;
  background: left / cover fixed no-repeat url('/img/bk-3.jpg');
}
.particles {
  position: fixed;
  top: 0;
  left: 0;
  @include full();
  z-index: -1;
}
</style>
