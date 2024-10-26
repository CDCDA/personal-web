<template>
  <div class="common-header" :class="isHideen ? 'is-hidden' : 'is-show'">
    <div class="header-name">
      <common-link></common-link>
      <a class="title" href="/home" title="返回主页"><span>记录</span></a>
      <!-- <v-mini-weather class="weather">
        <template #default="{ weather, icon }">
          <v-mini-weather-icon :icon="icon"></v-mini-weather-icon>
          <span
            >{{ weather.cityname }}/{{ weather.weather }}/{{ weather.temp
            }}{{ setWeatherData(weather) }}</span
          >
        </template>
      </v-mini-weather> -->
    </div>
    <div class="header-bar">
      <div class="header-bar-menu">
        <div class="header-bar-item" v-for="item in menuData">
          <div class="item-label">
            {{ item.label }}
          </div>
          <div class="item-content" v-if="item.children">
            <div class="item-menu">
              <div class="item" @click="clickMenu(e)" v-for="e in item.children">
                <el-icon v-if="e.icon" class="menu-icon">
                  <component :is="e.icon" />
                </el-icon>
                <svg-icon
                  v-else
                  class="menu-icon"
                  :iconName="e.svgIcon"
                  style="height: 22px; width: 22px"
                ></svg-icon>
                {{ e.label }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="header-bar-title">
        <div class="header-bar-title-text">{{ pageName }}</div>
      </div>
    </div>

    <div class="header-right">
      <el-tooltip content="随机文章" placement="top">
        <svg-icon iconName="列车1" class="header-icon train" @click="toRandom" />
      </el-tooltip>
      <el-tooltip content="搜索" placement="top">
        <svg-icon iconName="搜索" class="header-icon search" @click="searchClick" />
      </el-tooltip>
      <el-tooltip content="控制台" placement="top">
        <svg-icon
          iconName="控制台"
          class="header-icon console"
          v-permission="'show'"
          @click="router.push({ name: 'manage' })"
        />
      </el-tooltip>
      <el-tooltip content="登出" placement="top">
        <svg-icon iconName="登出" class="header-icon logout" @click="logout" />
      </el-tooltip>
      <span @click="returnTop" class="progress" :class="progress == '100' ? 'is-progress-full' : ''"
        >{{ progress }}
        <el-icon @click="returnTop"><Top /></el-icon>
      </span>
    </div>
  </div>

  <searchDialog v-if="searchVisible" @close="close"></searchDialog>
</template>

<script setup lang="ts">
import { ElMessageBox } from 'element-plus';
import { ref, onMounted, watch } from 'vue';
import { getRandomBlog } from '@/api/blog';
import { vMiniWeather, vMiniWeatherIcon } from 'vue3-mini-weather';
import { autoClearTimer } from '@/utils/timer';
import { useRouter, onBeforeRouteUpdate } from 'vue-router';
import useUserStore from '@/store/modules/user';
import useThemeStore from '@/store/modules/theme.ts';
const themeStore = useThemeStore();
const userStore = useUserStore();
import commonLink from './components/commonLink.vue';
import searchDialog from './components/searchDialog.vue';
const router = useRouter() as any;
const articleElement = ref(null as any);
const progress = ref('0' as any);
const isHideen = ref(false);
const searchVisible = ref(false as any);
const weatherData = ref({});
const pageName = ref('首页');
const pageChange = ref(true);
const menuData = ref([
  {
    label: '博客',
    icon: '',
    name: '',
    children: [
      { label: '分类', icon: 'FolderOpened', name: 'blogType' },
      { label: '标签', icon: 'PriceTag', name: 'blogTag' },
      { label: '统计', icon: 'Histogram', name: 'statistics' }
    ]
  },
  {
    label: '简介',
    icon: '',
    name: '',
    children: [
      { label: '个人信息', icon: 'User', name: 'personalProfile' }
      // { label: '技术栈', icon: 'Promotion', name: 'technologyStack' },
      // { label: '项目经历', icon: 'Postcard', name: 'projectExperience' }
    ]
  },
  {
    label: '我的',
    icon: '',
    name: '',
    children: [
      { label: '随笔', icon: 'Notebook', name: 'essay' },
      { label: '相册', icon: 'Camera', name: 'album' },
      { label: '装备', icon: 'Suitcase', name: 'equipment' },
      { label: '音乐', icon: 'Headset', name: 'music' }
    ]
  },
  {
    label: '组件',
    icon: '',
    name: '',
    children: [
      { label: '切片', icon: 'Orange', name: 'slice' },
      { label: '试验田', icon: 'OfficeBuilding', name: 'testField' }
    ]
  },
  {
    label: '其他',
    icon: '',
    name: '',
    children: [
      { label: '健身', icon: '', svgIcon: 'fitness', name: 'fitness' },
      { label: '游戏', icon: '', svgIcon: 'game', name: 'game' },
      { label: '影视', icon: 'VideoCamera', name: 'drama' },
      { label: '美食', icon: 'KnifeFork', name: 'gourmet' }
    ]
  },
  {
    label: '关于',
    icon: '',
    name: '',
    children: [
      { label: '关于本站', icon: '', svgIcon: 'regardTo', name: 'website' },
      { label: '更新日志', icon: '', svgIcon: 'log', name: 'updateLog' }
    ]
  }
] as any);

const menuHeader = [
  {
    label: '博客',
    value: 'blog',
    children: []
  },
  {
    label: '简介',
    children: [],
    value: 'intro'
  },
  {
    label: '我的',
    children: [],
    value: 'user'
  },
  {
    label: '组件',
    children: [],
    value: 'assembly'
  },
  {
    label: '其他',
    children: [],
    value: 'other'
  },
  {
    label: '关于',
    children: [],
    value: 'associate'
  }
];

let headerBarMenu = document.querySelector('.header-bar-menu') as any;
let headerBarTitle = document.querySelector('.header-bar-title-text') as any;

function setWeatherData(weather: any) {
  weatherData.value = weather;
  (localStorage as any).setItem('weather', JSON.stringify(weather));
}

watch(
  () => progress.value,
  (newVal, oldVal) => {
    let difference = newVal - oldVal;
    if (headerBarMenu && headerBarTitle)
      if (difference > 0) {
        headerBarMenu.style.transform = 'translateY(-100px)';
        headerBarTitle.style.transform = 'translateY(0px)';
      } else {
        headerBarMenu.style.transform = 'translateY(0px)';
        headerBarTitle.style.transform = 'translateY(100px)';
      }
    if (newVal === '0') {
      isHideen.value = true;
      // if (headerBar) headerBar.style.transform = 'translateY(50%)';
    } else {
      isHideen.value = false;
    }
  },
  { deep: true, immediate: true }
);

watch(
  () => pageName.value,
  val => {
    val;
    pageChange.value = false;
    autoClearTimer(() => {
      pageChange.value = true;
    }, 200);
    autoClearTimer(() => {
      let el = document.querySelector('.page-name') as any;
      if (el) el.classList.toggle('is-change');
    }, 1000);
  },
  {
    immediate: true
  }
);

const isWaveShow = ref(false as any);

onBeforeRouteUpdate(to => {
  if (to.path === '/blogDisplay') isWaveShow.value = true;
  (document as any).querySelector('.el-main')?.scrollTo({ top: 0 });
});

function scrollEvent() {
  articleElement.value = document.querySelector('.el-main');
  progress.value =
    Math.round(
      (articleElement.value.scrollTop /
        (articleElement.value.scrollHeight - articleElement.value.clientHeight)) *
        100
    ) + '';
}

function close() {
  searchVisible.value = false;
}

function searchClick() {
  searchVisible.value = true;
}

async function toRandom() {
  const { data, code } = (await getRandomBlog()) as any;

  if (code == 200) {
    router.push({
      name: 'refresh'
    });
    autoClearTimer(() => {
      router.push({
        name: 'blogDisplay',
        query: { blogId: data }
      });
    }, 0);
  }
}

/**
 * @description: 回到顶部
 * @return {*}
 */
function returnTop() {
  articleElement.value?.scrollTo({ top: '0', behavior: 'smooth' });
}

function logout() {
  ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      userStore.userId = '';
      userStore.userName = '';
      userStore.token = '';
      userStore.permission = [];
      window.localStorage.setItem('userData', '');
      router.push({ name: 'login' });
    })
    .catch(() => {});
}

function clickMenu(item: any) {
  pageName.value = item.label;
  if (item.name === 'logOut') {
  } else router.push({ name: item.name });
}

router.options.routes.forEach((route: any) => {
  if (!route.meta) return;

  const { icon, svgIcon, remark, parent } = route.meta;
  if (icon || svgIcon) {
    let parentMenu = menuHeader.find((x: any) => x.value == parent) as any;
    parentMenu?.children.push({ label: remark, icon, svgIcon, name: route.name });
  }
});
menuData.value = menuHeader;

// 设置首页和顶栏颜色
function setHeaderFontColor() {
  if (themeStore.options) {
    const { mhFontColor } = themeStore.options;
    let header = document.querySelector('.common-header') as any;
    if (header) {
      header.style.color = mhFontColor;
      let icons = header.querySelectorAll('.theme-icon');
      Object.keys(icons).forEach((e: any) => {
        icons[e].style.fill = mhFontColor;
      });
    }
  }
}

onMounted(() => {
  headerBarMenu = document.querySelector('.header-bar-menu') as any;
  headerBarTitle = document.querySelector('.header-bar-title-text') as any;
  autoClearTimer(() => {
    // 监听滚动事件并更新样式
    window.addEventListener('scroll', scrollEvent, true);
    setHeaderFontColor();
  }, 200);
});
</script>
<style lang="scss" scoped>
.side-setting {
  opacity: 1;
}
.is-hidden.side-setting {
  opacity: 0;
}
@include theme() {
  @keyframes label-in {
    0% {
      left: -138px;
    }
    20% {
      left: -6px;
    }
    60% {
      left: -6px;
    }
    100% {
      left: -117px;
    }
  }

  @keyframes blur-to-clear {
    0% {
      -webkit-filter: blur(50px);
      transform: scale(1.5);
      filter: blur(50px);
    }
    50% {
      -webkit-filter: blur(30px);
      transform: scale(1.2);
      filter: blur(30px);
    }
    100% {
      -webkit-filter: blur(0);
      transform: scale(1);
      filter: blur(0);
    }
  }

  .common-header {
    z-index: 100;
    position: absolute;
    // animation: re-slide-in 1.5s forwards linear;
    top: 0px;
    width: calc(100vw - 80px);
    padding: 0px 40px;
    color: get('font-color');
    display: flex;
    justify-content: space-between;
    font-weight: bold;
    height: 56px;
    margin-bottom: 10px;
    background: get('back-tr');
    align-items: center;
    .page-name {
      width: 140px;
      height: 57px;
      display: flex;
      z-index: 999;
      justify-content: center;
      align-items: center;
      color: #363636;
      font-size: 60px;
      text-align: center;
      transform: perspective(0.5em) rotateX(5deg);
      top: 57px;
      left: -53px;
      border-radius: 4px;
      position: absolute;
      background: get('background');
      animation: label-in 2s forwards linear;
      .page-name-text {
        animation: scroll 0.5s forwards linear;
        transform: perspective(0.5em) rotateX(-9deg);
        content: attr(data-before);
        font-size: 24px;
        top: 3px;
        position: absolute;
        color: get('font-color');
      }
    }
    @keyframes name-in {
      0% {
        left: -117px;
      }
      100% {
        left: -6px;
      }
    }
    @keyframes name-out {
      0% {
        left: -6px;
      }
      100% {
        left: -117px;
      }
    }
    .page-name.is-change:hover {
      animation: name-in 0.5s forwards linear;
    }
    .page-name.is-change:not(:hover) {
      animation: name-out 0.5s forwards linear;
    }
    .page-name.is-change {
      @keyframes scroll {
        0% {
          transform: translateX(-100%);
        }
        70% {
          transform: translateX(0%);
        }
        80% {
          transform: translateX(15%);
        }
        100% {
          transform: translateX(0%);
        }
      }
      .page-name-text {
        animation: scroll 0.5s forwards linear;
      }
    }
    :deep(.el-input__wrapper) {
      border: none !important;
      height: 25px;
    }
    :deep(.el-input-group__append) {
      height: 27px;
      background: transparent;
      padding: 0px 14px;
      .el-buttton {
        box-shadow: none !important;
      }
    }
    :deep(.el-button) {
      box-shadow: none !important;
    }
    .header-name,
    .header-bar,
    .header-right {
      color: inherit;
      display: flex;
      justify-content: center;
      .header-icon {
        width: 24px;
        height: 24px;
        margin: 10px;
        @include flex;
        cursor: pointer;
        position: relative;
      }
      .console {
        width: 22px;
        height: 22px;
      }
      .header-icon::before {
        content: ' ';
        transition: all 0.3s linear;
        background: get('font-color');
        height: 26px;
        width: 26px;
        border-radius: 25px;
        position: absolute;
        z-index: -1;
        padding: 4px;
        opacity: 0;
      }
      .header-icon:hover {
        &::before {
          opacity: 1;
        }
      }
    }
    .header-name {
      width: 320px;
      font-weight: bold;
      font-size: 25px;
      display: flex;
      justify-content: start;
      align-items: center;
      height: 100%;
      .header-name-icon {
        margin-right: 10px;
        cursor: pointer;
        color: inherit;
        padding: 6px;
      }
      .header-name-icon:hover {
        margin-right: 10px;
        cursor: pointer;
        background: get('bk');
        color: white;
        border-radius: 25px;
      }
      .title {
        cursor: pointer;
        text-decoration: none;
        color: inherit;
        position: relative;
        height: 100%;
        &:before {
          content: ' ';
          background: url(../../../assets/svg/主页.svg) no-repeat;
          background-size: cover;
          height: 32px;
          width: 32px;
          display: inline-block;
          position: absolute;
          z-index: 1;
          left: calc(50% - 16px);
          top: 12px;
          opacity: 0;
        }
        &:after {
          content: ' ';
          opacity: 0;
          width: 100%;
          height: 35px;
          background: get('bk');
          border-radius: 10px;
          display: inline-block;
          position: absolute;
          left: -20%;
          top: 11px;
          padding: 0px 10px;
          box-shadow: get('box-shadow');
        }
      }
      .weather {
        font-size: 20px;
        height: 100%;
        font-size: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-left: 14px;
        .v-mini-weather-icon {
          display: flex;
          align-items: center;
          height: 42px;
        }
      }
      .title:hover {
        &:before {
          opacity: 1;
        }
        &:after {
          opacity: 1;
        }
      }

      .title:hover {
      }
    }
    .header-bar {
      @include flex-column;
      height: 100%;
      width: calc(100% - 492px);
      font-size: 20px;
    }
    .header-bar-menu {
      width: 100%;
      transform: translateY(0px);
      transition: all 0.2s linear;
      height: 100%;
      @include flex;
      .header-bar-item {
        padding: 0px 20px;
        font-size: 20px;
        cursor: pointer;
        position: relative;
        @include flex;
      }
      .header-bar-item:hover {
        .item-label {
          color: white;
        }

        &::before {
          content: ' ';
          background: get('border-color');
          box-shadow: get('box-shadow');
          height: 35px;
          width: 80px;
          display: inline-block;
          position: absolute;
          z-index: -1;
          left: 0px;
          top: 11px;
          border-radius: 25px;
        }
      }
      .item-label {
        display: inline-block;
        position: relative;
      }
      .item-content {
        position: absolute;
        visibility: hidden;

        opacity: 0;
        transition: all 0.3s ease-in-out;
        // right: -137px;
        top: 50px;
        z-index: 1;
        .item-menu {
          margin-top: 4px;
          padding: 10px 8px 15px;
          background: get('background');
          color: get('font-color');
          box-shadow: 0 0 8px;
          border-radius: 30px;
          height: 60px;
          padding: 0px 10px;
          @include flex;
          .item {
            @include flex;
            .menu-icon {
              margin: 0px 10px 0px 0px;
            }
            white-space: nowrap;
            text-align: center;
            height: 35px;
            line-height: 35px;
            cursor: pointer;
            padding: 5px 20px;
            &:hover {
              // box-shadow: get('box-shadow');
              background: get('bk');
              border-radius: 30px;
              color: white;
              .menu-icon {
                .svg-use {
                  fill: white;
                }
              }
            }
          }
        }
      }
      .header-bar-item:hover .item-content {
        visibility: visible;
        opacity: 1;
      }
      .header-bar-item:not(:hover) .item-content {
        visibility: hidden;
        opacity: 0;
      }
    }
    .header-bar-title {
      width: 150px;
      position: absolute;
      height: 100%;
      overflow: hidden;
      z-index: -1;
      cursor: pointer;
      div {
        transform: translateY(100px);
        transition: all 0.2s linear;
        position: relative;
        // cursor: pointer;
        // &:after {
        //   content: '回到顶部';
        //   opacity: 1;
        //   z-index: 11;
        //   transition: all 0.2s linear;
        //   width: 100px;
        //   height: 35px;
        //   background: get('bk');
        //   border-radius: 12px;
        //   display: inline-block;
        //   position: absolute;
        //   left: 0px;
        //   top: 10px;
        //   padding: 0px 10px;
        // }
      }
      // div:hover {
      //   &:after {
      //     opacity: 1;
      //   }
      // }
    }
    .header-right {
      width: 236px;
      display: flex;
      justify-content: end;
      align-items: center;
      .el-icon {
        margin: 0px 8px;
        font-size: 22px;
        font-weight: bold;
        cursor: pointer;
      }
      .el-avatar {
        margin: 5px;
        height: 45px;
        width: 45px;
        cursor: pointer;
      }
    }
    .progress {
      margin: 0px 10px;
      border-radius: 30px;
      font-size: 15px;
      height: 30px;
      width: 30px;
      display: flex;
      align-items: center;
      justify-content: center;
      background: get('font-color');
      color: get('re-font-color');
      position: relative;
      transition: all 0.3s ease-in-out;
      .el-icon {
        visibility: hidden;
        position: absolute;
        height: 30px;
        width: 30px;
        border-radius: 30px;
        line-height: 30px;

        background: get('font-color');
        font-weight: bold;
        cursor: pointer;
        svg {
          margin-left: -1px;
        }
        // @include flex;
      }
    }
    .progress:hover {
      .el-icon {
        visibility: visible;
      }
    }

    .is-progress-full {
      width: 50px;
      border-radius: 15px;
      cursor: pointer;
    }
  }
  .header-search {
    position: relative;
    :deep(.el-select) {
      position: relative;
      left: -150px;
      width: 150px;
      // visibility: hidden;
      border: none;
    }
  }
  .dropdown {
    margin: 0 5px;
    height: 55px;
    position: relative;
    .dropdown-title {
      display: inline-block;
      position: relative;
      height: 60px;
      line-height: 60px;
      font-size: 20px;
      color: #fff;
      background-color: #000;
      padding: 0 24px;
      border-radius: 4px;
      cursor: pointer;
      // transition: all 1s ease-in-out;
    }

    .dropdown-content {
      position: absolute;
      visibility: hidden;
      background: get('background');
      opacity: 0;
      transition: all 0.6s ease-in-out;
      right: -25px;
      top: 50px;
      z-index: 999;

      .dropdown-menu {
        margin-top: 4px; // 与title制造距离
        padding: 10px 8px 15px; // 给下面多留一点距离，抵消视觉差
        box-shadow: 0 0 8px;
        backdrop-filter: blur(20px);
        border-radius: 5px;
        padding: 0px;
        .menuItem {
          white-space: nowrap;
          text-align: center;
          height: 35px;

          line-height: 35px;
          cursor: pointer;
          border-radius: 4px;
          padding: 0px 20px;
          &:hover {
            box-shadow: get('box-shadow');
            border-radius: 5px;
          }
        }
      }
    }

    &:hover .dropdown-content {
      visibility: visible;
      opacity: 1;
    }
  }

  .is-hidden {
    box-shadow: none;
    background: transparent;
  }
  .is-show {
    .header-icon::before {
      background: get('re-font-color') !important;
    }
  }

  .setting {
    position: fixed;
    box-shadow: 0 0 8px;
    backdrop-filter: blur(20px);
    border-radius: 5px;
    // right: 93px;
    padding: 0px;
    .setting-item {
      list-style: none;
      text-align: center;
      height: 35px;
      line-height: 35px;
      padding: 0px 20px;
    }
    .setting-item:hover {
      box-shadow: get('box-shadow');
      border-radius: 5px;
    }
  }
}
</style>
