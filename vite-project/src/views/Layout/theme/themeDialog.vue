<!--
 * @Description: 换肤 
-->
<template>
  <el-dialog
    class="skin-change"
    v-model="dialogVisible"
    title="换肤"
    style="height: 75%"
    width="65%"
    :modal="false"
    @close="emit('closeThemeDialog')"
  >
    <el-tabs v-model="activeName" class="skin-tabs" v-loading.fullscreen.lock="loading">
      <el-tab-pane label="主题" name="theme">
        <div class="theme-main">
          <div
            v-for="item in themes"
            :class="['theme-item', item.active ? 'theme-item-active' : '']"
            @click="changeTheme(item)"
          >
            <el-image class="theme-image" :src="item.background" :fit="fits">
              <template #placeholder>
                <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%" />
              </template>
              <template #error>
                <div class="image-error-slot">
                  <svg-icon iconName="imgFailed" />
                </div>
              </template>
            </el-image>
            <span class="theme-label">{{ item.label }}</span>
          </div>
        </div></el-tab-pane
      >
      <el-tab-pane label="静态壁纸" name="static">
        <div class="back-main" v-loading="false">
          <div
            v-for="item in backs.filter((e:any)=> e.type !== 'video')"
            :class="['back-item', item.active ? 'back-item-active' : '']"
            @click="changeBack(item)"
          >
            <el-image
              class="back-image"
              v-if="item.type == 'img'"
              style="height: 90px"
              :src="item.background"
              :preview-teleported="true"
              :preview-src-list="[item.background]"
              :fit="fits"
            >
              <template #placeholder>
                <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%" />
              </template>
              <template #error>
                <div class="image-error-slot">
                  <svg-icon iconName="imgFailed" />
                </div>
              </template>
            </el-image>
            <div
              v-if="item.type == 'color'"
              class="back-image"
              style="height: 90px"
              :style="{ background: item.background }"
            />
            <span class="back-label">{{ item.label }}</span>
          </div>
        </div></el-tab-pane
      >
      <el-tab-pane label="动态壁纸" name="dynamic">
        <div class="back-main" v-loading="false">
          <div
            v-for="item in backs.filter((e:any)=> e.type == 'video')"
            :class="['back-item', item.active ? 'back-item-active' : '']"
            @click="changeBack(item)"
          >
            <video
              v-if="item.type == 'video'"
              class="back-image"
              style="height: 90px"
              cover
              :poster="item.url"
            >
              <source :src="item.background" type="video/mp4" />
            </video>
            <span class="back-label">{{ item.label }}</span>
          </div>
        </div></el-tab-pane
      >
      <el-tab-pane label="其他" name="other">
        <div class="other-main">
          <el-form
            :model="options"
            class="other-form"
            label-width="200"
            style="width: 400px"
            label-position="left"
          >
            <el-form-item label="粒子效果(仅静态壁纸)" prop="isParticles">
              <el-switch v-model="options.isParticles" @change="setParticles()"></el-switch>
            </el-form-item>
            <el-form-item label="首页及导航字体颜色" prop="mhFontColor">
              <el-color-picker v-model="options.mhFontColor" @change="setFontColor()" />
            </el-form-item>
            <el-form-item label="字体" prop="fontFamily">
              <el-select v-model="options.fontFamily" @change="setFontFamily()">
                <el-option
                  v-for="item in fontFamilys"
                  :value="item.value"
                  :label="item.label"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';
import useThemeStore from '@/store/modules/theme.ts';
const emit = defineEmits(['closeThemeDialog']);
const themeStore = useThemeStore();
const dialogVisible = ref(true);
const loading = ref(false as any);
const activeName = ref('theme');
var activeBack = {
  key: '4',
  label: '',
  background: '/img/bk-3.jpg',
  url: '/img/bk-3.jpg',
  active: false
} as any;
var activeTheme = {
  key: 'theme-dark',
  label: '深色',
  background: '/img/bk-3.jpg',
  active: true
} as any;
// 外观设置
const options = reactive({
  //是否开启粒子特效
  isParticles: true,
  fontColor: '',
  mhFontColor: '',
  fontFamily: ''
} as any);
//主题
const themes = reactive([
  {
    key: 'theme-dark',
    label: '深色',
    background: '/img/bk-3.jpg',
    active: true
  },
  {
    key: 'theme-light',
    label: '棕黄',
    background: '/img/失落方舟.jpg',
    active: false
  },
  {
    key: 'theme-white',
    label: '白色',
    background: '/img/失落方舟.jpg',
    active: false
  },
  {
    key: 'theme-green',
    label: '青色',
    background: '/img/失落方舟.jpg',
    active: false
  }
]);
//背景图
const backs = reactive([
  {
    label: '未来世界',
    background: '/img/bk-1.jpg',
    url: '/img/bk-1.jpg',
    type: 'img',
    active: true
  },
  {
    label: '太空飞船',
    background: '/img/bk-2.jpg',
    url: '/img/bk-2.jpg',
    type: 'img',
    active: false
  },
  {
    label: '赛博城市',
    background: '/img/cyberpunk-city.jpg',
    url: '/img/cyberpunk-city.jpg',
    type: 'img',
    active: false
  },
  {
    label: '星河瀑布',
    background: '/img/bk-3.jpg',
    url: '/img/bk-3.jpg',
    type: 'img',
    active: false
  },
  {
    label: '失落方舟',
    background: '/img/失落方舟.jpg',
    url: '/img/失落方舟.jpg',
    type: 'img',
    active: false
  },
  {
    label: '未来赛博房间',
    background: '/img/futuristic-sci-fi-room.jpg',
    url: '/img/futuristic-sci-fi-room.jpg',
    type: 'img',
    active: false
  },
  {
    label: 'lol',
    background: '/img/lol.jpg',
    url: '/img/lol.jpg',
    type: 'img',
    active: false
  },
  {
    label: '龙与少女',
    background: '/img/dragon-maiden.jpg',
    url: '/img/dragon-maiden.jpg',
    type: 'img',
    active: false
  },
  {
    label: '高达',
    background: '/img/gundam.jpg',
    url: '/img/gundam.jpg',
    type: 'img',
    active: false
  },
  {
    label: '水墨太湖',
    background: '/img/Ink-painting-taihu.jpg',
    url: '/img/Ink-painting-taihu.jpg',
    type: 'img',
    active: false
  },
  {
    label: '和服女孩',
    background: '/img/kimono-girl.jpg',
    url: '/img/kimono-girl.jpg',
    type: 'img',
    active: false
  },
  {
    label: '休憩的巫女',
    background: '/img/resting-hexenbiest.jpg',
    url: '/img/resting-hexenbiest.jpg',
    type: 'img',
    active: false
  },
  {
    label: '奥特曼',
    background: '/img/ultraman.jpg',
    url: '/img/ultraman.jpg',
    type: 'img',
    active: false
  },
  {
    label: '无边光景',
    background: '/img/boundless-scene.jpg',
    url: '/img/boundless-scene.jpg',
    type: 'img',
    active: false
  },
  {
    label: '龙座',
    background: '/img/dragon-seat.jpg',
    url: '/img/dragon-seat.jpg',
    type: 'img',
    active: false
  },
  {
    label: '枫林湖畔',
    background: '/img/fenglin-lake.jpg',
    url: '/img/fenglin-lake.jpg',
    type: 'img',
    active: false
  },
  {
    label: '鹅国小酒馆',
    background: '/img/goose-country-tavern.jpg',
    url: '/img/goose-country-tavern.jpg',
    type: 'img',
    active: false
  },
  {
    label: '度假猫',
    background: '/img/holiday-cat.jpg',
    url: '/img/holiday-cat.jpg',
    type: 'img',
    active: false
  },
  {
    label: '林间小屋',
    background: '/img/hut.jpg',
    url: '/img/hut.jpg',
    type: 'img',
    active: false
  },
  {
    label: '皮卡丘',
    background: '/img/pikachu.jpg',
    url: '/img/pikachu.jpg',
    type: 'img',
    active: false
  },
  {
    label: '雨中车站',
    background: '/img/station-in-the-rain.jpg',
    url: '/img/station-in-the-rain.jpg',
    type: 'img',
    active: false
  },
  {
    label: '默认图',
    background: '/img/default.jpg',
    url: '/img/default.jpg',
    type: 'img',
    active: false
  },
  {
    label: '失落方舟 mp4',
    background: '/img/失落方舟.mp4',
    url: '/img/失落方舟.jpg',
    type: 'video',
    active: false
  },
  {
    label: 'Kiana mp4',
    background: '/img/Kiana.mp4',
    url: '/img/Kiana.jpg',
    type: 'video',
    active: false
  },
  {
    label: '御三家',
    background: '/img/御三家.mp4',
    url: '/img/御三家.jpg',
    type: 'video',
    active: false
  },
  {
    label: '寒冰 mp4',
    background: '/img/寒冰.mp4',
    url: '/img/寒冰.jpg',
    type: 'video',
    active: false
  },
  {
    label: '凛桎鸣渊 mp4',
    background: '/img/凛桎鸣渊.mp4',
    url: '/img/凛桎鸣渊.jpg',
    type: 'video',
    active: false
  },
  {
    label: '玛奇玛 mp4',
    background: '/img/makima.mp4',
    url: '/img/makima.jpg',
    type: 'video',
    active: false
  },
  {
    label: '神里-白服 mp4',
    background: '/img/神里-白服.mp4',
    url: '/img/神里-白服.jpg',
    type: 'video',
    active: false
  },
  {
    label: '桜庭統 mp4',
    background: '/img/桜庭統.mp4',
    url: '/img/桜庭統.jpg',
    type: 'video',
    active: false
  },
  {
    label: '无奇典当 mp4',
    background: '/img/无奇典当.mp4',
    url: '/img/无奇典当.jpg',
    type: 'video',
    active: false
  },
  {
    label: '睡颜影 mp4',
    background: '/img/睡颜影.mp4',
    url: '/img/睡颜影.jpg',
    type: 'video',
    active: false
  },
  {
    label: '年 mp4',
    background: '/img/年.mp4',
    url: '/img/年.jpg',
    type: 'video',
    active: false
  },
  {
    label: '太空猫 mp4',
    background: '/img/太空猫.mp4',
    url: '/img/太空猫.jpg',
    type: 'video',
    active: false
  },
  {
    label: '夏日 mp4',
    background: '/img/夏日.mp4',
    url: '/img/夏日.jpg',
    type: 'video',
    active: false
  },
  {
    label: '晓梦浮生 mp4',
    background: '/img/晓梦浮生.mp4',
    url: '/img/晓梦浮生.jpg',
    type: 'video',
    active: false
  },
  {
    label: '夜华如昼 mp4',
    background: '/img/夜华如昼.mp4',
    url: '/img/夜华如昼.jpg',
    type: 'video',
    active: false
  },
  {
    label: '遗迹 mp4',
    background: '/img/遗迹.mp4',
    url: '/img/遗迹.jpg',
    type: 'video',
    active: false
  },
  {
    label: '月下 mp4',
    background: '/img/月下.mp4',
    url: '/img/月下.jpg',
    type: 'video',
    active: false
  },
  {
    label: '纯色-灰白',
    background: '#f7f9fe',
    type: 'color',
    url: '#f7f9fe',
    active: false
  }
]);
//字体
const fontFamilys = [
  {
    value: 'DaoLiTi',
    label: '刀隶体'
  },
  {
    value: 'FangDaKai',
    label: '东方大楷'
  },
  { value: "'Press Start 2P', cursive", label: 'Press Start 2P' },
  {
    value: 'SimSun',
    label: '宋体'
  },
  {
    value: 'SimHei',
    label: '黑体'
  },
  {
    value: 'Microsoft YaHei',
    label: '微软雅黑'
  },
  {
    value: 'LiSu',
    label: '隶书'
  },
  {
    value: 'FangSong',
    label: '仿宋'
  },
  {
    value: 'KaiTi',
    label: '楷体'
  },
  {
    value: 'YaHei',
    label: '雅黑'
  }
];

var localStorage = window.localStorage as any;

const fits = 'cover';

function close() {
  emit('closeThemeDialog');
}

function open() {
  dialogVisible.value = true;
}

/**
 * @description: 设置主题
 * @return {*}
 */
function setTheme() {
  let themeKey = activeTheme.key as any;
  localStorage.setItem('themeKey', themeKey);
  (document.getElementById('app-theme') as any).setAttribute('data-theme', themeKey);
  themeStore.theme = themeKey;
}

/**
 * @description: 设置背景
 * @return {*}
 */
function setBack() {
  let backUrl = activeBack.url as any;
  if (activeBack.type == 'video') {
    localStorage.setItem('backUrl', activeBack.background);
  } else {
    localStorage.setItem('backUrl', backUrl);
  }
  localStorage.setItem('backType', activeBack.type);
  themeStore.backType = activeBack.type;
  let back = document.getElementById('tsparticles') as any;
  switch (activeBack.type) {
    case 'img':
      back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
      break;
    case 'color':
      back.style.background = backUrl;
      break;
    case 'video':
      back.src = activeBack.background;
      break;
    default:
      break;
  }
}

/**
 * @description: 设置文字主题
 * @return {*}
 */
function setFontFamily() {
  themeStore.aspectOptions = options;
  const { fontFamily, fontColor, mhFontColor } = options;
  let appTheme = document.querySelector('#app-theme') as any;
  let weather = document.querySelector('#he-plugin-standard') as any;
  appTheme.style.fontFamily = fontFamily;
  weather.style.fontFamily = fontFamily;
  localStorage.setItem('aspectOptions', JSON.stringify(options));
}

function setParticles() {
  themeStore.aspectOptions = options;
  localStorage.setItem('aspectOptions', JSON.stringify(options));
}

/**
 * @description: 设置首页字体颜色
 * @return {*}
 */
function setFontColor() {
  themeStore.aspectOptions = options;
  const { fontFamily, fontColor, mhFontColor } = options;
  let header = document.querySelector('.common-header') as any;
  let homeTop = document.querySelector('.home-top') as any;
  let CycleUpDown = document.querySelector('.CycleUpDown') as any;
  if (header) {
    header.style.color = mhFontColor;
    // let icons = header.querySelectorAll('.theme-icon');

    // Object.keys(icons).forEach((e: any) => {
    //   icons[e].style.fill = mhFontColor;
    // });
  }
  if (homeTop) homeTop.style.color = mhFontColor;
  if (CycleUpDown) {
    let themeIcon = CycleUpDown.querySelector('.theme-icon') as any;
    if (themeIcon) {
      themeIcon.style.fill = mhFontColor;
    }
  }
  localStorage.setItem('aspectOptions', JSON.stringify(options));
}

/**
 * @description: 应用外观设置
 * @return {*}
 */
function submit() {
  const { fontFamily, fontColor, mhFontColor } = options;
  let appTheme = document.querySelector('#app-theme') as any;
  let weather = document.querySelector('#he-plugin-standard') as any;
  weather.style.fontFamily = fontFamily;
  appTheme.style.color = fontColor;
  appTheme.style.fontFamily = fontFamily;
}

/**
 * @description: 选中主题
 * @param {*} item
 * @return {*}
 */
function changeTheme(item: any) {
  themes.forEach((theme: any) => {
    theme.active = false;
  });
  item.active = true;
  activeTheme = JSON.parse(JSON.stringify(item));
  setTheme();
}

/**
 * @description: 选中背景
 * @param {*} item
 * @return {*}
 */
function changeBack(item: any) {
  backs.forEach((back: any) => {
    back.active = false;
  });
  activeBack.active = false;
  item.active = true;
  activeBack = JSON.parse(JSON.stringify(item));
  setBack();
}

// function traverseFolder() {
//   let folderPath = '/img/*.jpg';
//   // 读取文件夹列表
//   const files = (import.meta as any).globEager('/img/*.jpg');
//   console.log('files', files);
//   // 遍历文件夹列表
//   Object.keys(files).forEach((file: any) => {
//     console.log('file', files[file]);
//   });
// }

/**
 * @description: 初始化主题背景数据
 * @return {*}
 */
function init() {
  let themeKey = (window.localStorage as any).getItem('themeKey') as any;
  let backUrl = (window.localStorage as any).getItem('backUrl') as any;
  let aspectOptions = window.localStorage.getItem('aspectOptions') as any;
  if (aspectOptions) Object.assign(options, JSON.parse(aspectOptions));
  themes.forEach((e: any) => {
    if (e.key == themeKey) {
      e.active = true;
      activeTheme = JSON.parse(JSON.stringify(e));
    } else {
      e.active = false;
    }
  });
  backs.forEach((e: any) => {
    if (e.url == backUrl || e.background == backUrl) {
      e.active = true;
      activeBack = JSON.parse(JSON.stringify(e));
    } else {
      e.active = false;
    }
  });
}

onMounted(() => {
  init();
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss">
@include theme() {
  .skin-change {
    .el-dialog__body {
      overflow: hidden !important;
    }
    background: get('background') !important;
    .el-tabs__content {
      height: calc(100% - 55px);
      overflow: auto;
    }
    .el-tabs {
      height: 100%;
    }
    .preview {
      position: absolute;
      opacity: 0;
      background: rgba(0, 0, 0, 0.5);
      height: 87px;
      width: 150px;
      z-index: 1000;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 4px 4px 0px 0px;
      .preview-text {
        color: white;
      }
    }
    .preview:hover {
      opacity: 2;
      .preview-text {
        display: block;
      }
    }

    .theme-main,
    .back-main {
      display: flex;
      flex-wrap: wrap;
      .theme-item,
      .back-item {
        // height: 120px;
        aspect-ratio: 3/2;
        width: calc(20% - 16px);
        display: flex;
        transition: all 0.2s linear;
        flex-direction: column;
        margin: 8px;
        box-shadow: get('box-shadow');
        border-radius: 5px;
        .back-image,
        .theme-image {
          width: calc(100% - 10px);
          margin: 5px;
          border-radius: 5px;
          object-fit: cover;
        }
      }
      .back-item:hover,
      .theme-item:hover {
        transform: scale(1.03);
      }
      .theme-label,
      .back-label {
        height: 30px;
        line-height: 30px;
        color: get('font-color');
      }
      .theme-item-active,
      .back-item-active {
        transform: scale(1.03);
      }
    }
    .el-dialog__body {
      height: calc(100% - 80px) !important;
      overflow: auto;
    }
    .other-main {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .other-form {
      padding: 0px 100px;
    }
  }
}
</style>
