<!--
 * @Description:  主页
-->
<template>
  <div class="home-container">
    <div class="home-top">
      <div class="main-top-title">
        <div data-text></div>
      </div>
      <svg-icon iconName="下" class="CycleUpDown" @click="toMainPage" />
    </div>
    <div class="home-main page-main">
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
            <BlogUserCard />
            <WeatherCard />
            <VisitorCard />
            <!-- <BlogTypeCard />
            <BlogTagCard /> -->
            <BlogCountCard />
          </div>
          <div class="display-right">
            <div v-for="name in Object.keys(typeBlogList)">
              <div class="display-header">
                <div class="display-header-left">
                  <svg-icon iconName="分类"></svg-icon>{{ name }}
                </div>
                <div class="display-header-right" @click="toBlogType(name)">
                  <svg-icon iconName="更多"></svg-icon>MORE
                </div>
              </div>
              <div class="display-list">
                <div
                  @click="toDetail(item)"
                  class="list-item"
                  :class="`list-item-${item.blogId}`"
                  v-for="(item, i) in typeBlogList[name]"
                >
                  <div class="list-item-img">
                    <c-image :src="item.coverUrl" />
                  </div>

                  <div class="list-item-footer">
                    <el-tooltip effect="dark" :content="item.blogTitle" placement="top">
                      <span class="list-item-title no-wrap">{{ item.blogTitle }}</span>
                    </el-tooltip>

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
            </div>

            <!-- <Pagination
              v-model:page="queryParams.pageNum"
              v-model:page-size="queryParams.pageSize"
              :total="total"
              :on-page-change="getBlogList"
              :showSizes="true"
              :pageSizeList="[10, 20, 30]"
              :on-page-size-change="getBlogList"
              class="pagi page-content"
            /> -->
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
import { listUpdateLog } from '@/api/system/updateLog';
import { autoClearTimer } from '@/utils/timer';
import Pagination from '@/components/pagination/index.vue';
import BlogUserCard from '@/views/blog/components/blogUserCard.vue';
import WeatherCard from './components/weatherCard.vue';
import BlogTypeCard from '@/views/blog/components/blogTypeCard.vue';
import BlogTagCard from '@/views/blog/components/blogTagCard.vue';
import { verifyToken } from '@/api/system/user';

import BlogCountCard from '@/views/blog/components/blogCountCard.vue';
import VisitorCard from './components/visitorCard.vue';
import { useLazyAppear } from '@/utils/lazy';
// const BlogUserCard = defineAsyncComponent(() => import('@/views/blog/components/blogUserCard.vue'));
// const WeatherCard = defineAsyncComponent(() => import('./components/weatherCard.vue'));
// const BlogTypeCard = defineAsyncComponent(() => import('@/views/blog/components/blogTypeCard.vue'));
// const BlogTagCard = defineAsyncComponent(() => import('@/views/blog/components/blogTagCard.vue'));
// const BlogCountCard = defineAsyncComponent(
//   () => import('@/views/blog/components/blogCountCard.vue')
// );
// const VisitorCard = defineAsyncComponent(() => import('./components/visitorCard.vue'));
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

const headerList = ref([] as any);
const router = useRouter() as any;
const typeList = ref([] as any);
const typeBlogList = ref({} as any);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  userId: null
} as any);

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
  el?.scrollTo({ top: window.innerHeight - 15, behavior: 'smooth' });
}

/**
 * @description: 获取分类树
 * @return {*}
 */

async function getTypeBlog(type: any) {
  let params = {
    pageNum: 1,
    pageSize: 3,
    typeId: type.typeId
  };
  const { code, data } = (await listBlog(params)) as any;
  if (code === 200) {
    if (data.list.length > 0) {
      typeBlogList.value[type.typeName] = data.list;
      autoClearTimer(() => {
        data.list.forEach((item: any) => {
          useLazyAppear(document.querySelector(`.list-item-${item.blogId}`) as any);
        });
      }, 500);
    }
  }
}

async function getTypeTree() {
  const { code, data } = (await listTotalType(queryParams.value)) as any;
  if (code === 200) {
    typeList.value = data.list;
    typeList.value.forEach(async (item: any) => {
      typeBlogList.value[item.typeName] = [];
      await getTypeBlog(item);
    });
  }
}

function formateToDay(date: any) {
  return date?.substring(0, 10);
}

// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

// 打字效果
function setTyping() {
  let wrapper = null as any;
  const sleep = (ms: any) => new Promise(resolve => autoClearTimer(resolve, ms));
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

function toBlogType(name: String) {
  let type = null as any;
  typeList.value.forEach((x: any) => {
    x.typeName == name ? (type = x) : '';
  });
  router.push({ name: 'blogTypePage', query: { typeId: type.typeId } });
}

// 设置首页和顶栏颜色
function setHomeColor() {
  if (themeStore.options) {
    const { mhFontColor } = themeStore.options;
    let homeTop = document.querySelector('.home-top') as any;
    let CycleUpDown = document.querySelector('.CycleUpDown') as any;
    if (homeTop) homeTop.style.color = mhFontColor;
    if (CycleUpDown) {
      let themeIcon = CycleUpDown.querySelector('.theme-icon') as any;
      if (themeIcon) {
        themeIcon.style.fill = mhFontColor;
      }
    }
  }
}

onMounted(() => {
  verifyToken();
  getHeaderList();
  getTypeTree();
  setTyping();
  autoClearTimer(() => {
    setHomeColor();
  }, 500);
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
      max-width: 1200px;
      background: transparent !important;
      backdrop-filter: none !important;
      @include flex-column;
      .main-header {
        width: calc(100% - 60px);
        color: get('font-color');
        border-radius: 12px;
        background: get('background');
        box-shadow: get('box-shadow');
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 8px 30px;
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
          height: 40vh;
        }
        .display-page {
          width: 100%;
          display: flex;
          margin-top: 20px;
          .display-right {
            width: calc(100% - 280px);
            margin-left: 20px;
            .display-header {
              .display-header-left,
              .display-header-right {
                @include flex;
              }
              .display-header-right {
                cursor: pointer;
              }
              .svg-icon-wrap {
                width: 30px;
                height: 30px;
                margin-right: 10px;
              }
              @include flex;
              justify-content: space-between;
              background: get('background');
              box-shadow: get('box-shadow');
              border-radius: 8px;
              padding: 8px 20px;
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
                border-radius: 12px;
              }
            }
            .display-list {
              margin-top: 20px;
              // height: 400px;
              border-radius: 12px;
              display: flex;
              justify-content: space-between;
              flex-wrap: wrap;
              .list-item {
                cursor: pointer;
              }
              .list-item:hover {
                transition: all 0.4s ease;
              }
              .list-item {
                width: calc(33% - 10px);
                aspect-ratio: 3/2;
                margin-bottom: 20px;
                background: get('background');
                box-shadow: get('box-shadow');
                border-radius: 10px;

                @include flex-column;
                justify-content: start;
                .list-item-img {
                  overflow: hidden;
                  border-radius: 8px;
                  width: 100%;
                  // height: calc(100% - 100px);
                  aspect-ratio: 5/3;
                  margin: 5px;
                  width: calc(100% - 10px);
                  .c-image {
                    height: 100%;
                    width: 100%;
                    transition: all 0.6s;
                  }
                }
                .list-item-img:hover {
                  .c-image {
                    transform: scale(1.2);
                  }
                }
                // .list-item-img::before {
                //   content: '';
                //   position: absolute;
                //   top: 0px;
                //   left: 0px;
                //   width: 500px;
                //   height: 100%;
                //   background-color: #fff;
                //   opacity: 0.5;
                //   /* 元素沿X轴45横切，沿X轴右移150px */
                //   transform: skewX(45deg) translateX(850px);
                //   /* 动画过渡 */
                //   transition: all 0.3s;
                // }
                // .list-item-img:hover {
                //   &::before {
                //     z-index: 1;
                //     /* 元素沿X轴45横切，沿X轴左移150px */
                //     transform: skewX(45deg) translateX(-650px);
                //   }
                //   .el-image {
                //     aspect-ratio: 5/3;
                //     transform: scale(1.1);
                //     transition: all 0.2s;
                //   }
                // }
                // .list-item-img:not(:hover) {
                //   &::before {
                //     /* 元素沿X轴45横切，沿X轴左移150px */
                //     transform: skewX(0deg) translateX(0px);
                //   }
                //   .el-image {
                //     transform: scale(1);
                //     transition: all 0.2s;
                //   }
                // }
                .list-item-footer {
                  width: calc(100% - 20px);
                  padding: 6px 0px 10px 0px;
                  height: 50px;
                  display: flex;
                  justify-content: space-between;
                  flex-direction: column;
                  align-items: start;
                  .list-item-title {
                    font-size: 17px;
                    font-weight: bold;
                    width: 100%;
                  }
                  .list-item-tag {
                    width: 100%;
                    @include flex;
                    justify-content: space-between;
                    .item-tag-list {
                      @include flex;
                      justify-content: start;
                      overflow: hidden;
                      height: 20px;
                      width: calc(100% - 105px);
                      text-align: left;
                      display: -webkit-box;
                      overflow: hidden;
                      text-overflow: ellipsis;
                      -webkit-line-clamp: 1;
                      -webkit-box-orient: vertical;
                      .item-tag {
                        margin-right: 15px;
                        font-size: 15px;
                        display: inline-block;
                      }
                      .item-tag-pretend {
                        opacity: 0.7;
                        font-size: 15px;
                        font-weight: bold;
                      }
                    }
                    .item-time {
                      font-size: 13px;
                      width: 105px;
                      height: 20px;
                      display: flex;
                      align-items: center;
                      justify-content: end;
                    }
                  }
                }
              }
            }
          }
          .display-left {
            width: 280px;
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
