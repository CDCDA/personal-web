<!--
 * @Description:  主页
-->
<template>
  <div class="home-container">
    <div class="home-top">
      <div class="main-top-title">
        <div data-text></div>
      </div>
      <svg-icon iconName="down-black" class="CycleUpDown" @click="toMainPage"></svg-icon>
    </div>
    <div class="home-main">
      <div class="main-header">
        <el-icon><ChromeFilled /></el-icon>
        <div class="main-header-text-list">
          <rollText :list="headerList" />
        </div>
        <el-icon><Right /></el-icon>
      </div>
      <div class="main-container">
        <div class="recommend">
          <RecommendLeft />
          <RecommendRight />
        </div>
        <div class="display-page">
          <div class="display-left">
            <div class="display-header">
              <div class="type-list">
                <div
                  class="type-item"
                  :class="item.isActive ? 'is-active' : ''"
                  v-for="item in typeList"
                  @click="setQuery(item)"
                >
                  <!-- <span class="type-item-prefix" v-if="item.typeId">#</span> -->
                  <span class="type-item-content">{{ item.typeName }}</span>
                  <!-- <span class="type-item-count">{{ item.total }}</span> -->
                </div>
              </div>
            </div>
            <div class="display-list">
              <div @click="toDetail(item)" class="list-item" v-for="item in recommends">
                <div class="list-item-img">
                  <el-image :src="item.coverUrl" lazy>
                    <template #placeholder>
                      <div
                        class="image-slot"
                        v-cLoading="'rotate'"
                        style="width: 100%; height: 100%"
                      />
                    </template>
                    <template #error>
                      <div class="image-error-slot">
                        <svg-icon iconName="imgFailed"></svg-icon>
                      </div>
                    </template>
                  </el-image>
                </div>

                <div class="list-item-footer">
                  <span class="list-item-title">{{ item.blogTitle }}</span>
                  <div class="list-item-tag">
                    <div class="item-tag-list">
                      <template v-for="(tag, i) in item.tags">
                        <span class="item-tag" v-if="i <= 4">
                          <span class="item-tag-pretend">#</span>
                          <span class="item-tag-text">{{ tag.tagName }}</span>
                        </span>
                      </template>
                    </div>
                    <span class="item-time">{{ formateToDay(item.createTime) }}</span>
                  </div>
                </div>
              </div>
            </div>
            <Pagination
              v-model:page="queryParams.pageNum"
              v-model:page-size="queryParams.pageSize"
              :total="total"
              :on-page-change="getBlogList"
              :showSizes="true"
              :pageSizeList="[10, 20, 30]"
              :on-page-size-change="getBlogList"
              class="pagi page-content"
            />
          </div>
          <div class="display-right">
            <visible-lazy :component="BlogUserCard" />
            <visible-lazy :component="WeatherCard" />
            <visible-lazy :component="VisitorCard" />
            <visible-lazy :component="BlogTypeCard" />
            <visible-lazy :component="BlogTagCard" />
            <visible-lazy :component="BlogCountCard" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted, defineAsyncComponent, ref, watch } from 'vue';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import { listTotalType } from '@/api/type';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { listUpdateLog } from '@/api/updateLog.ts';
import Pagination from '@/components/pagination/index.vue';
const BlogUserCard = defineAsyncComponent(() => import('@/views/blog/components/blogUserCard.vue'));
const WeatherCard = defineAsyncComponent(() => import('./components/weatherCard.vue'));
const BlogTypeCard = defineAsyncComponent(() => import('@/views/blog/components/blogTypeCard.vue'));
const BlogTagCard = defineAsyncComponent(() => import('@/views/blog/components/blogTagCard.vue'));
const BlogCountCard = defineAsyncComponent(
  () => import('@/views/blog/components/blogCountCard.vue')
);
const VisitorCard = defineAsyncComponent(() => import('./components/visitorCard.vue'));
// const RecommendLeft = defineAsyncComponent(() => import('./components/recommendLeft.vue'));
// const RecommendRight = defineAsyncComponent(() => import('./components/recommendRight.vue'));
import RecommendRight from './components/recommendRight.vue';
import RecommendLeft from './components/recommendLeft.vue';
import useThemeStore from '@/store/modules/theme.ts';

import rollText from '@/components/rollText/index.vue';
const themeStore = useThemeStore();
const recommends = ref([] as any);

const userStore = useUserStore();
const theme = ref('' as any);
const loading = ref('rotate' as any);

watch(
  () => themeStore.theme,
  newValue => {
    theme.value = newValue;
  },
  {
    deep: true,
    immediate: true
  }
);

const slogans = ref([
  '记录',
  '来逛逛吧',
  '生活不只有眼前的苟且',
  '还有远方的苟且',
  '几年很快的',
  '风一吹就没了'
] as any);

const slogan = ref('' as String);

const headerList = ref([
  {
    text: '加了个滚动技术栈',
    routerName: 'main'
  },
  {
    text: '相册已完成',
    routerName: 'album'
  },
  {
    text: '修整了一下首页',
    routerName: 'main'
  },
  {
    text: '放假放假放假',
    routerName: 'essay'
  }
] as any);
const router = useRouter() as any;
const typeList = ref([] as any);

const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  userId: null
} as any);

const total = ref(100 as any);
async function getBlogList() {
  const { code, msg, data } = (await listBlog(queryParams.value)) as any;
  if (code === 200) {
    recommends.value = data.list;
    total.value = data.total;
  } else {
    ElMessage.error('博客数据获取失败', msg);
  }
}

async function getHeaderList() {
  const { code, data } = (await listUpdateLog({ operation: '', pageNum: 1, pageSize: 999 })) as any;
  if (code == 200) {
    headerList.value = data.list;
    headerList.value.forEach((x: any) => {
      x.text = x.operation;
    });
  }
}

/**
 * @description: 滚动一个页面长度
 * @return {*}
 */
function toMainPage() {
  let el = document.querySelector('.el-main') as any;
  el.scrollTo({ top: window.innerHeight - 80, behavior: 'smooth' });
}

/**
 * @description: 获取分类树
 * @return {*}
 */

async function getTypeTree() {
  const { code, msg, data } = (await listTotalType(queryParams.value)) as any;
  if (code === 200) {
    typeList.value = data.list;
    let total = 0;
    typeList.value.forEach((e: any) => {
      total += e.total;
    });
    typeList.value.unshift({
      typeName: '全部',
      isActive: false,
      total
    });
    queryParams.value.typeId = router.currentRoute.value.query.typeId;
    typeList.value.forEach((e: any) => {
      if (e.typeId === queryParams.value.typeId) e.isActive = true;
    });
  } else {
    ElMessage.error('分类数据获取失败', msg);
  }
}
let displacement = ref(0 as any);

function headerRoll(header: any) {
  displacement.value -= 49;
  if (displacement.value == -75) displacement.value = 0;
  header.style.transform = `translate3d(0px, ${displacement.value}px, 0px)`;
}

function formateToDay(date: any) {
  return date?.substring(0, 10);
}

function isActive(item: any) {
  typeList.value.forEach((e: any) => {
    e.isActive = false;
  });
  item.isActive = true;
  queryParams.value.userId = item.typeId;
  getBlogList();
}

// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

/**
 * @description: 设置首页字体颜色
 * @return {*}
 */
function setFontColor() {
  let options = JSON.parse(localStorage.getItem('aspectOptions') as any) as any;
  if (!options) return;
  const { mhFontColor } = options;
  let header = document.querySelector('.common-header') as any;
  let homeTop = document.querySelector('.home-top') as any;
  let CycleUpDown = document.querySelector('.CycleUpDown') as any;
  if (header) {
    header.style.color = mhFontColor;
    let icons = header.querySelectorAll('.theme-icon');
    Object.keys(icons).forEach((e: any) => {
      icons[e].style.fill = mhFontColor;
    });
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

function setTyping() {
  let wrapper = null as any;
  const sleep = (ms: any) => new Promise(resolve => setTimeout(resolve, ms));
  async function writingAll(container: any) {
    wrapper = document.querySelector('[' + container + ']');
    // const stringsContainer = document.getElementsByClassName(stringTarget);

    while (wrapper) {
      for (let i = 0; i < slogans.value.length; i++) {
        const string = slogans.value[i];
        await write(string);
        await sleep(2000);
        await erase();
        await sleep(1000);
      }
    }
  }

  async function write(text: any) {
    let index = 0;
    while (index < text.length) {
      const timeout = 100;
      await sleep(timeout);
      index++;
      wrapper.innerHTML = text.substring(0, index);
    }
  }

  async function erase() {
    while (wrapper.textContent.length) {
      const timeout = 100;
      await sleep(timeout);
      wrapper.textContent = wrapper.textContent.substring(0, wrapper.textContent.length - 2);
    }
  }
  writingAll('data-text');
}

const routes = router.getRoutes().filter((e: any) => {
  return e.meta.isHidden !== true && e.meta.remark;
}) as any;

function setQuery(item: any) {
  if (item) {
    typeList.value.forEach((type: any) => {
      type.isActive = false;
    });
    item.isActive = true;
  }
  queryParams.value.pageNum = 1;
  queryParams.value.typeId = item.typeId;
  getBlogList();
}

onMounted(() => {
  getHeaderList();

  getBlogList();
  getTypeTree();
  setFontColor();
  setTyping();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .home-container {
    @include flex-column;
    color: get('font-color');
    .home-top {
      width: 100%;
      min-height: 100vh;
      @include flex-column;
      position: relative;
    }
    .main-top-title {
      font-weight: bold;
      font-size: 40px;
    }
    .main-top-vice-title {
      margin-top: 10px;
      margin-bottom: 100px;
      font-size: 25px;
    }
    .main-top-back {
      height: 100vh;
      width: 100%;
      @include flex-column;
    }
    .down-icon {
      transform: rotate(90deg);
    }
    .CycleUpDown {
      font-size: 40px;
      height: 60px;
      color: get('font-color');
      bottom: 40px;
      cursor: pointer;
      position: absolute;
    }
    .home-main {
      width: calc(90vw);
      max-width: 1500px;
      @include flex-column;
      .main-header {
        height: 7vh;
        width: calc(100% - 60px);
        color: get('font-color');
        border-radius: 15px;
        background: get('background');
        box-shadow: get('box-shadow');
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 30px;
        font-size: 22px;
        font-weight: bold;
        overflow: hidden;
        .main-header-text-list {
          @include flex-column;
          height: 100%;
          transition-duration: 300ms;
          transform: translate3d(0px, 0px, 0px);
          .main-header-text {
            height: 30px;
            margin: 10px;
            cursor: pointer;
          }
        }
        .el-icon {
          font-size: 25px;
          color: get('foont-color');
          cursor: pointer;
        }
      }
      .main-container {
        width: 100%;
        margin-top: 20px;
        color: get('font-color');
        .recommend {
          @include flex;
          width: 100%;
          height: 50vh;
        }
        .display-page {
          width: 100%;
          display: flex;
          margin-top: 20px;
          .display-left {
            width: calc(100% - 300px);
            margin-right: 20px;
            .display-header {
              @include flex;
              justify-content: start;
              height: 8vh;
              background: get('background');
              box-shadow: get('box-shadow');
              border-radius: 15px;
              padding: 0px 20px;
              position: relative;
              .header-item {
                font-size: 18px;
                font-weight: bold;
                padding: 5px 15px 7px 15px;
                cursor: pointer;
                margin-right: 10px;
              }
              .header-more {
                position: absolute;
                right: 10px;
                cursor: pointer;
                font-size: 18px;
                font-weight: bold;
                padding: 5px 15px;
              }
              .header-item:hover,
              .header-item.is-active {
                background: linear-gradient(to right, #56ccf2, #2f80ed);
                // box-shadow: get('box-shadow');
                color: white;
                border-radius: 15px;
              }
            }
            .display-list {
              margin-top: 20px;
              // height: 400px;
              border-radius: 15px;
              display: flex;
              justify-content: space-between;
              flex-wrap: wrap;
              .list-item {
                cursor: pointer;
              }
              .list-item:hover {
                --i: -1;
                mask-position: 0 0;
                transform: perspective(1800px) rotate3d(1, -1, 0, calc(var(--i, 1) * 8deg));
                // mask: linear-gradient(135deg, #000c 40%, #000, #000c 60%) 100% 100%/240% 240%;
                transition: 0.4s;
              }
              .list-item {
                width: calc(50% - 10px);
                aspect-ratio: 3/2;
                margin-bottom: 20px;
                background: get('background');
                box-shadow: get('box-shadow');
                border-radius: 15px;
                @include flex-column;
                justify-content: start;
                .list-item-img {
                  border-radius: 15px 15px 0px 0px;
                  width: 100%;
                  // height: calc(100% - 100px);
                  aspect-ratio: 7/4;
                  margin: 5px;
                  width: calc(100% - 10px);
                  border-radius: 10px;
                  .el-image {
                    width: 100%;
                    height: 100%;
                  }
                }
                .list-item-img {
                  position: relative;
                  /* 盒子阴影 */
                  box-shadow: 0px 5px 45px rgba(0, 0, 0, 0.1);
                  /* 背景模糊 */
                  backdrop-filter: blur(2px);
                  /* 加个动画过渡，动画才不会太过生硬 */
                  transition: all 0.3s;
                  overflow: hidden;
                }
                .list-item-img::before {
                  content: '';
                  position: absolute;
                  top: 0px;
                  left: 0px;
                  width: 500px;
                  height: 100%;
                  background-color: #fff;
                  opacity: 0.5;
                  /* 元素沿X轴45横切，沿X轴右移150px */
                  transform: skewX(45deg) translateX(850px);
                  /* 动画过渡 */
                  transition: all 0.3s;
                }
                .list-item-img:hover {
                  &::before {
                    z-index: 1;
                    /* 元素沿X轴45横切，沿X轴左移150px */
                    transform: skewX(45deg) translateX(-650px);
                  }
                  .el-image {
                    aspect-ratio: 5/3;
                    transform: scale(1.1);
                    transition: all 0.2s;
                  }
                }
                .list-item-img:not(:hover) {
                  &::before {
                    /* 元素沿X轴45横切，沿X轴左移150px */
                    transform: skewX(0deg) translateX(0px);
                  }
                  .el-image {
                    transform: scale(1);
                    transition: all 0.2s;
                  }
                }
                .list-item-footer {
                  width: calc(100% - 30px);
                  height: 100px;
                  display: flex;
                  justify-content: space-evenly;
                  flex-direction: column;
                  padding: 0px 15px;
                  align-items: start;
                  .list-item-title {
                    font-size: 20px;
                    font-weight: bold;
                  }
                  .list-item-tag {
                    width: 100%;
                    @include flex;
                    justify-content: space-between;
                    .item-tag-list {
                      @include flex;
                      justify-content: start;
                      overflow: hidden;
                      height: 30px;
                      width: calc(100% - 105px);
                      text-align: left;
                      display: -webkit-box;
                      overflow: hidden;
                      text-overflow: ellipsis;
                      -webkit-line-clamp: 1;
                      -webkit-box-orient: vertical;
                      .item-tag {
                        margin-right: 15px;
                        font-size: 20px;
                        display: inline-block;
                      }
                      .item-tag-pretend {
                        opacity: 0.5;
                        font-weight: bold;
                      }
                    }
                    .item-time {
                      width: 105px;
                    }
                  }
                }
              }
            }
          }
          .display-right {
            width: 300px;
            // background: get('background');
            // box-shadow: get('box-shadow');
            // border-radius: 15px;
          }
        }
      }
    }
  }
  .type-list {
    @include flex;
    justify-content: start;
    flex-wrap: wrap;
    width: 100%;
    .type-item {
      display: flex;
      justify-content: center;
      align-items: center;
      cursor: pointer;
      padding: 6px 12px;
      margin: 3px;
      border-radius: 10px;
      color: get('font-color');
      font-size: 18px;
      transition: all 0.3s ease-in-out;
      // border: 2px solid get('border-color');
      .type-item-prefix {
        opacity: 0.8;
        font-weight: bold;
      }
      .type-item-content {
        margin-left: 4px;
        font-weight: bold;
      }
      .type-item-count {
        background: #f7f7f9;
        padding: 0px 5px;
        border-radius: 8px;
        text-align: center;
        min-width: 21px;
        display: inline-block;
        margin-left: 6px;
      }
    }
    .type-item:active {
      transform: translateY(5px);
    }
    .type-item:hover,
    .type-item.is-active {
      background: get('border-color');
      color: white;

      // box-shadow: get('box-shadow');

      .type-item-count {
        color: get('font-color');
      }
    }
  }
}
</style>
<style>
.main-top-title .item {
  visibility: hidden;
  display: none;
}
.main-top-title h2 {
  font-size: 50px;
  margin: 0;
  height: 50px;
  color: white;
  text-align: left;
}
.main-top-title > div {
  min-height: 20px;
  height: 50px;
  text-transform: uppercase;
  letter-spacing: 1px;
  display: flex;
  align-items: flex-end;
  font-weight: bold;
}
.main-top-title > div:after {
  content: '';
  width: 2.5rem;
  height: 4px;
  display: block;
  background: white;
  color: orange;
  margin-bottom: 4px;
  margin-left: 2px;
  animation-duration: 350ms;
  animation-name: fade;
  animation-direction: alternate;
  animation-iteration-count: infinite;
}

@keyframes fade {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
</style>
