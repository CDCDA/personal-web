<template>
  <div id="app-theme" data-theme="theme-dark">
    <sakura
      style="position: absolute"
      :new-options="sakuraOptions"
      v-if="themeStore.aspectOptions.isSakura"
    />
    <video id="tsparticles" autoplay loop muted v-if="backType == 'video'" />
    <div id="tsparticles" class="particles" v-else />
    <Particles
      v-if="themeStore.aspectOptions && themeStore.aspectOptions.isParticles"
      id="particles"
      :options="options"
    />
    <KeepAlive> <router-view /></KeepAlive>
    <rightClickMenu :visible="visible" :left="left" :top="top" />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import sakura from '@/components/sakura/index.vue';
import rightClickMenu from '@/components/rightClickMenu/index.vue';
import { useRouter } from 'vue-router';
import useThemeStore from '@/store/modules/theme.ts';
import useUserStore from './store/modules/user';
const sakuraOptions = ref({
  staticx: false,
  stop: null,
  num: 15,
  show: true,
  zIndex: -1
} as any);
const userStore = useUserStore();
var themeStore = useThemeStore();
const loading = ref('gear' as any);
const router = useRouter();
const visible = ref(false);
const top = ref(0);
const left = ref(0);
const options = ref({
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
} as any);
const realOptions = ref(JSON.parse(JSON.stringify(options.value)) as any);
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
    let themeKey = window.localStorage.getItem('themeKey') as any;
    let backUrl = window.localStorage.getItem('backUrl') as any;
    let options = window.localStorage.getItem('aspectOptions') as any;
    backType.value = window.localStorage.getItem('backType') as any;

    //设置默认主题
    if (!themeKey) {
      themeKey = 'theme-light';
      window.localStorage.setItem('themeKey', themeKey);
    }
    //设置默认壁纸类型
    if (!backType.value) {
      backType.value = 'img';
      window.localStorage.setItem('backType', backType.value);
    }
    //设置默认壁纸
    if (!backUrl) {
      backUrl = '../src/assets/images/123123123.jpg';
      window.localStorage.setItem('backUrl', backUrl);
    }
    (document.getElementById('app-theme') as any).setAttribute('data-theme', themeKey);
    setTimeout(() => {
      let back = document.getElementById('tsparticles') as any;
      if (backType.value == 'img') {
        back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
      }
      if (backType.value == 'color') back.style.background = backUrl;
      if (backType.value == 'video') back.src = backUrl;
    }, 0);
    //设置壁纸

    //记录主题数据
    themeStore.theme = themeKey;
    themeStore.backType = backType.value;
    themeStore.aspectOptions = JSON.parse(options);
    //查看是否有token
    if (window.localStorage.getItem('token')) {
      userStore.userId = window.localStorage.getItem('userId') as any;
      userStore.userName = window.localStorage.getItem('userName') as any;
      userStore.token = window.localStorage.getItem('token') as any;
      userStore.permission = JSON.parse(window.localStorage.getItem('permission') as any);
      router.push({ path: '/home' });
    } else router.push({ path: '/login' });
    if (options) {
      options = JSON.parse(options);
      let appTheme = document.querySelector('#app-theme') as any;
      appTheme.style.color = options.fontColor;
      if (!options.fontFamily) {
        options.fontFamily = 'Microsoft YaHei';
        window.localStorage.setItem('aspectOptions', JSON.stringify(options));
        appTheme.style.fontFamily = options.fontFamily;
      } else {
        appTheme.style.fontFamily = options.fontFamily;
      }

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
  }, 0);
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
}

#app-theme {
  animation: blur-to-clear 2s forwards ease-in-out;
}
body,
html {
  height: 100%;
  width: 100% !important;
  margin: 0px;
  padding: 0px;
  overflow: hidden;
}
body {
  --dy: 1;
  --dx: 1;
  --dz: 0;
}
@keyframes blur-to-clear {
  0% {
    -webkit-filter: blur(40px);
    transform: scale(1.4);
    filter: blur(40px);
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
  background: transparent;
}
#tsparticles::after {
  content: '';
  background: linear-gradient(
    90deg,
    rgba(247, 149, 51, 0.1),
    rgba(243, 112, 85, 0.1) 15%,
    rgba(239, 78, 123, 0.1) 30%,
    rgba(161, 102, 171, 0.1) 44%,
    rgba(80, 115, 184, 0.1) 58%,
    rgba(16, 152, 173, 0.1) 72%,
    rgba(7, 179, 155, 0.1) 86%,
    rgba(109, 186, 130, 0.1)
  ) !important;
}
.particles {
  position: fixed;
  top: 0;
  left: 0;
  @include full();
  z-index: -1;
}
#app {
  @include getValue() {
    @import '@/assets/styles/element-ui.scss';
    @import '@/assets/styles/common.scss';
  }
}
</style>
