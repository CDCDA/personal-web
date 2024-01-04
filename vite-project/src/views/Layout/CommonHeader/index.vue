<template>
  <div class="common-header" :class="isHideen ? 'is-hidden' : 'is-show'">
    <!-- <div class="page-name" v-if="pageChange">
      <span class="page-name-text">{{ pageName }}</span>
    </div> -->
    <div class="header-name">
      <common-link></common-link>
      <a class="title" href="/home" title="返回主页"><span>CCCC的小破站</span></a>
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
      <svg-icon iconName="train" class="header-icon train" @click="searchClick" />
      <svg-icon iconName="search" class="header-icon search" @click="searchClick" />
      <svg-icon
        iconName="fullScreen"
        class="header-icon full-screen"
        @click="fullScreen"
        v-if="!isFullScreen"
      />
      <svg-icon
        iconName="exitFullScreen"
        class="header-icon exit-full-screen"
        @click="exitFullScreen"
        v-if="isFullScreen"
      />
      <svg-icon
        iconName="console"
        class="header-icon console"
        @click="router.push({ name: 'manage' })"
      />
      <svg-icon iconName="logout" class="header-icon logout" @click="logout" />
      <span @click="returnTop" class="progress" :class="progress == '100' ? 'is-progress-full' : ''"
        >{{ progress == '100' ? '返回顶部' : progress }}
        <el-icon @click="returnTop"><Top /></el-icon>
      </span>
      <!-- <div class="dropdown">
        <el-avatar :src="require('/img/cat-star.jpg')"></el-avatar>
        <div class="dropdown-content">
          <div class="dropdown-menu">
            <div class="menuItem" @click="clickMenu(item)" v-for="item in settings">
              {{ item.label }}
            </div>
          </div>
        </div>
      </div> -->
    </div>
  </div>
  <SideSetting :isHideen="false"></SideSetting>
  <searchDialog v-if="searchVisible" @close="close"></searchDialog>
</template>

<script setup lang="ts">
import { ElMessageBox } from 'element-plus';
import { reactive, ref, onMounted, watch } from 'vue';
import { useRouter, onBeforeRouteUpdate } from 'vue-router';
import useUserStore from '@/store/modules/user';
import SideSetting from '@/views/layout/sideSetting/index.vue';
const userStore = useUserStore();
import commonLink from './components/commonLink.vue';
import searchDialog from './components/searchDialog.vue';
const router = useRouter();
const articleElement = ref(null as any);
const progress = ref('0' as any);
const isFullScreen = ref(false as any);
const themeDialog = ref() as any;
const showSearch = ref(false);
const isHideen = ref(false);
const searchVisible = ref(false as any);
const settings = ref([
  {
    label: '个人中心',
    icon: 'User',
    name: 'manage',
    index: '5-1'
  },
  {
    label: '换肤',
    icon: 'Connection',
    name: 'skinPeeler',
    index: '5-2'
  },
  {
    label: '设置',
    icon: 'Setting',
    name: 'settings',
    index: '5-3'
  },
  {
    label: '注销',
    icon: 'SwitchButton',
    name: 'logOut',
    index: '5-3'
  }
] as any);
const headerSearchSelect = ref() as any;
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
]);

let headerBarMenu = document.querySelector('.header-bar-menu') as any;
let headerBarTitle = document.querySelector('.header-bar-title-text') as any;

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
    setTimeout(() => {
      pageChange.value = true;
    }, 200);
    setTimeout(() => {
      let el = document.querySelector('.page-name') as any;
      if (el) el.classList.toggle('is-change');
    }, 1000);
  },
  {
    immediate: true
  }
);

// watch(
//   () => router,
//   val => {
//     console.log('路由', val), { deep: true };
//   }
// );
const isWaveShow = ref(false as any);

onBeforeRouteUpdate(to => {
  if (to.path === '/blogDisplay') isWaveShow.value = true;
  (document as any).querySelector('.el-main').scrollTo({ top: 0 });
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

/**
 * @description: 回到顶部
 * @return {*}
 */
function returnTop() {
  articleElement.value.scrollTo({ top: '0', behavior: 'smooth' });
}

/**
 * @description: 全屏
 * @return {*}
 */
function fullScreen() {
  var elem = document.documentElement as any; // 获取整个文档的元素
  if (elem.requestFullscreen) {
    elem.requestFullscreen();
  } else if (elem.mozRequestFullScreen) {
    // Firefox
    elem.mozRequestFullScreen();
  } else if (elem.webkitRequestFullscreen) {
    // Chrome, Safari and Opera
    elem.webkitRequestFullscreen();
  } else if (elem.msRequestFullscreen) {
    // Internet Explorer
    elem.msRequestFullscreen();
  }
  isFullScreen.value = true;
}

function exitFullScreen() {
  var elem = document.documentElement as any; // 获取整个文档的元素
  if (elem.exitFullscreen) {
    elem.exitFullscreen();
  } else if (elem.mozCancelFullScreen) {
    /* Firefox */
    elem.mozCancelFullScreen();
  } else if (elem.webkitExitFullscreen) {
    /* Chrome, Safari and Opera */
    elem.webkitExitFullscreen();
  } else if (elem.msExitFullscreen) {
    /* IE/Edge */
    elem.msExitFullscreen();
  }
  isFullScreen.value = false;
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
      window.localStorage.setItem('token', '');
      router.push({ name: 'login' });
    })
    .catch(() => {});
}

function clickMenu(item: any) {
  pageName.value = item.label;
  if (item.name === 'logOut') {
  } else router.push({ name: item.name });
}

function toHome() {
  router.push({ name: 'home' });
}

onMounted(() => {
  headerBarMenu = document.querySelector('.header-bar-menu') as any;
  headerBarTitle = document.querySelector('.header-bar-title-text') as any;
  setTimeout(() => {
    // 监听滚动事件并更新样式
    window.addEventListener('scroll', scrollEvent, true);
  }, 1000);
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

  .common-header {
    z-index: 999;
    position: absolute;
    // overflow: hidden;
    transition: all 0.3s ease-in-out;
    top: 0px;
    width: 100%;
    color: get('font-color');
    display: flex;
    justify-content: space-around;
    box-shadow: 0 0px 8px 0 #000000;
    font-weight: bold;
    height: 56px;
    margin-bottom: 10px;
    background: get('background');
    backdrop-filter: blur(20px);
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
      .header-icon::before {
        content: ' ';
        transition: all 0.3s linear;
        background: get('font-color');
        height: 100%;
        width: 100%;
        border-radius: 10px;
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
      width: 30%;
      font-weight: bold;
      font-size: 25px;
      display: flex;
      align-items: center;
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
        &:before {
          content: ' ';
          background: url(/src/assets/svg/main-white.svg) no-repeat;
          background-size: cover;
          height: 32px;
          width: 32px;
          display: inline-block;
          position: absolute;
          z-index: 1;
          left: 76px;
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
          left: -3px;
          top: 11px;
          padding: 0px 10px;
          box-shadow: get('box-shadow');
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
      width: 40%;
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
          background: linear-gradient(to right, #358bff, #15c6ff);
          box-shadow: get('box-shadow');
          height: 35px;
          width: 80px;
          display: inline-block;
          position: absolute;
          z-index: -1;
          left: 0px;
          top: 11px;
          border-radius: 20px;
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
          background: get('background-no-tp');
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
        //   border-radius: 20px;
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
      width: 30%;
      display: flex;
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
      .el-icon {
        visibility: hidden;
        position: absolute;
        height: 30px;
        width: 30px;
        border-radius: 15px;
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
      width: 80px;
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
    backdrop-filter: blur(0px);
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
