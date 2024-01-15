<!--
 * @Description:  主页
-->
<template>
  <div class="home-container">
    <div class="home-top">
      <!-- <span class="main-top-title anim-typewriter">{{ slogan }}</span> -->
      <div class="main-top-title">
        <div data-text></div>
      </div>
      <!-- <span class="main-top-vice-title">逛逛吧</span> -->
      <svg-icon iconName="down-black" class="CycleUpDown" @click="toMainPage"></svg-icon>
    </div>
    <div class="home-main">
      <div class="main-header">
        <el-icon><ChromeFilled /></el-icon>
        <div class="main-header-text-list">
          <div
            class="main-header-text"
            @click="router.push({ name: item.routerName })"
            v-for="item in headerList"
          >
            {{ item.text }}
          </div>
        </div>
        <el-icon><Right /></el-icon>
      </div>
      <div class="main-container">
        <div class="recommend">
          <div class="recommend-left">
            <div class="recommend-left-top">
              <div class="recommd-left-title">
                <span>我的技术栈</span>
              </div>
              <TechnologyStackCard
                class="recommd-left-stack"
                :isHoverShow="false"
              ></TechnologyStackCard>
              <div class="recommend-left-top-cover" @click="toRange()">
                <div>随便逛逛</div>
                <svg-icon iconName="right"></svg-icon>
              </div>
            </div>
            <div class="recommend-left-bottom">
              <div
                class="bottom-item"
                @click="router.push({ name: 'blogTypePage', query: { typeId: '1' } })"
              >
                <span>前端小记</span><svg-icon iconName="book" />
              </div>
              <div
                class="bottom-item"
                @click="router.push({ name: 'blogTypePage', query: { typeId: '2' } })"
              >
                <span>后端总结</span><svg-icon iconName="hot" />
              </div>
              <div class="bottom-item" @click="router.push({ name: 'essay' })">
                <span>生活随笔</span><svg-icon iconName="edit" />
              </div>
            </div>
          </div>
          <div class="recommend-right">
            <div class="right-item" v-for="item in tRecommends" @click="toDetail(item)">
              <div class="icon-rec">荐</div>
              <c-image class="item-image" :src="item.coverUrl"></c-image>
              <div class="item-title">
                <el-text truncated>
                  {{ item.blogTitle }}
                </el-text>
              </div>
            </div>
          </div>
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
              <div class="list-item" v-for="item in recommends" @click="toDetail(item)">
                <div class="list-item-img">
                  <el-image :src="item.coverUrl || '/img/default.jpg'" lazy>
                    <template #placeholder>
                      <div
                        class="image-slot"
                        v-cLoading="'rotate'"
                        style="width: 100%; height: 100%"
                      ></div> </template
                    ><template #error>
                      <div class="image-error-slot">
                        <svg-icon iconName="imgFailed"></svg-icon>
                      </div> </template
                  ></el-image>
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
            <BlogUserCard />
            <WeatherCard />
            <VisitorCard />
            <BlogTypeCard></BlogTypeCard>
            <BlogTagCard></BlogTagCard>
            <BlogCountCard></BlogCountCard>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import rightClickMenu from '@/components/rightClickMenu/index.vue';
import { onMounted, ref, watch } from 'vue';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import { listTotalType } from '@/api/type';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { formateDate } from '@/utils/date.ts';
import Pagination from '@/components/pagination/index.vue';
import BlogUserCard from '@/views/blog/components/blogUserCard.vue';
import BlogTypeCard from '@/views/blog/components/blogTypeCard.vue';
import BlogTagCard from '@/views/blog/components/blogTagCard.vue';
import BlogCountCard from '@/views/blog/components/blogCountCard.vue';
import TechnologyStackCard from '../introduction/personalProfile/components/technologyStackCard.vue';
import useThemeStore from '@/store/modules/theme.ts';
import VisitorCard from './components/visitorCard.vue';
import WeatherCard from './components/weatherCard.vue';
const themeStore = useThemeStore();
const recommends = ref([] as any);
const tRecommends = ref([]) as any;
const userStore = useUserStore();
const theme = ref('' as any);

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
  'CCCC的小破站',
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
    tRecommends.value = JSON.parse(JSON.stringify(data.list));
    tRecommends.value.length > 6 ? (tRecommends.value.length = 6) : '';
    total.value = data.total;
  } else {
    ElMessage.error('博客数据获取失败', msg);
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
let displacement = ref(72 as any);

function headerRoll(header: any) {
  displacement.value -= 49;
  if (displacement.value == -75) displacement.value = 72;
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

function toRange() {
  router.push({
    name: 'blogDisplay',
    query: { blogId: recommends.value[Math.ceil(Math.random() * recommends.value.length)].blogId }
  });
}

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
  getBlogList();
  getTypeTree();
  let header = document.querySelector('.main-header-text-list');
  setFontColor();
  if (header) {
    setInterval(() => {
      headerRoll(header);
    }, 5000);
  }
  setTyping();
});
</script>
<style lang="scss" scoped>
@keyframes slide-in {
  0% {
    width: 33%;
  }
  100% {
    width: 50%;
  }
}
@keyframes rotate-img {
  0% {
    opacity: 0.2;
    transform: rotate(15deg);
  }
  100% {
    opacity: 1;
    right: 5%;
    top: -5%;
    filter: blur(0px);
    transform: rotate(0deg);
  }
}
@keyframes re-slide-in {
  0% {
    width: 50%;
  }
  100% {
    width: 33%;
  }
}
@keyframes re-rotate-img {
  0% {
    opacity: 1;
    right: 5%;
    top: -5%;
    filter: blur(0px);
    transform: rotate(0deg);
  }
  100% {
    opacity: 0.2;
    transform: rotate(15deg);
  }
}
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
      width: calc(100% - 12%);
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
          transform: translate3d(0px, 72px, 0px);
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
          .recommend-left {
            width: calc(50% - 5px);
            margin-right: 10px;
            @include flex-column;
            justify-content: space-between;
            cursor: pointer;
            .recommend-left-top {
              background: get('background');
              border-radius: 15px;
              width: 100%;
              height: 36vh;
              box-shadow: get('box-shadow');
              overflow: hidden;
              position: relative;
              .recommd-left-title {
                position: absolute;
                top: 28px;
                left: 46px;
                font-size: 44px;
                font-weight: bold;
              }
              .recommd-left-stack {
                transform: rotate(-35deg);
                position: absolute;
                animation: none;
              }
              .recommend-left-top-cover {
                opacity: 0;
                z-index: 1;
                width: 100%;
                position: absolute;
                height: calc(100%);
                background: get('bk');
                font-size: 85px;
                font-weight: bold;
                color: white;
                @include flex-column;
                align-items: flex-start;
                padding-left: 50px;
                transition: cubic-bezier(0.71, 0.15, 0.16, 1.15) 0.6s;
                .svg-icon-wrap {
                  width: 120px;
                  height: 120px;
                }
              }
              &:hover {
                .recommend-left-top-cover {
                  opacity: 1;
                }
              }
            }
            .recommend-left-bottom {
              display: flex;
              justify-content: space-between;
              align-items: center;
              margin-top: 5px;
              width: 100%;
              height: calc(14vh - 10px);
              .bottom-item {
                height: 100%;
                cursor: pointer;
                box-shadow: get('box-shadow');
                width: calc(33% - 5px);
                background: get('background');
                border-radius: 15px;
                @include flex;
                position: relative;
                justify-content: start;
                span {
                  color: white;
                  font-weight: bold;
                  font-size: 20px;
                  padding-left: 25px;
                }
                .svg-icon-wrap {
                  opacity: 0.2;
                  position: absolute;
                  right: 0%;
                  top: 20%;
                  transition: 0.3s;
                  width: 90px;
                  text-align: center;
                  filter: blur(2px);
                  transform: rotate(15deg);
                  .theme-icon {
                    color: white !important;
                    fill: white !important;
                  }
                }
              }
              .bottom-item:not(:hover) {
                animation: re-slide-in 0.3s forwards linear;
                img {
                  animation: re-rotate-img 0.3s forwards linear;
                }
              }
              .bottom-item:hover {
                & {
                  width: 50%;
                }
                animation: slide-in 0.3s forwards linear;
                .svg-icon-wrap {
                  animation: rotate-img 0.3s forwards linear;
                }
                width: 25%;
              }

              .bottom-item:nth-child(1) {
                background: get('bk');
                margin-right: 10px;
              }
              .bottom-item:nth-child(2) {
                margin-right: 10px;
                background: linear-gradient(to right, #ff6655, #ffbf37);
              }
              .bottom-item:nth-child(3) {
                background: linear-gradient(to right, #18e7ae, #1eebeb);
              }
            }
          }
          .recommend-right {
            width: calc(50% - 5px);
            margin-left: 10px;
            display: flex;
            color: get('font-color');
            justify-content: space-between;
            flex-wrap: wrap;
            @keyframes in {
              0% {
                left: -35px;
              }
              100% {
                left: 0px;
              }
            }
            .icon-rec {
              position: absolute;
              z-index: 1;
              top: 0;
              color: white;
              left: -35px;
              width: 35px;
              height: 35px;
              background: linear-gradient(to right, #1488cc, #2b32b2);
              border-radius: 15px 0px;
              @include flex;
            }
            .right-item:hover {
              transform: scale(1.02);
              .icon-rec {
                animation: in 0.1s forwards linear;
              }
            }
            .right-item {
              height: calc(50% - 5px);
              width: calc(33.33% - 7px);
              background: get('background');
              box-shadow: get('box-shadow');
              position: relative;
              cursor: pointer;
              overflow: hidden;
              @include flex-column;
              border-radius: 15px;
              .item-image {
                height: calc(100% - 50px);
                width: 100%;
                border-radius: 15px 15px 0px 0px;
              }
              .item-title {
                @include flex;
                height: 50px;
                padding: 0px 10px;
                width: calc(100% - 20px);
                margin: 0 auto;

                .el-text {
                  font-size: 18px;
                  font-weight: bold;
                  color: get('font-color');
                }
              }
            }
            .right-item:nth-child(1),
            .right-item:nth-child(2),
            .right-item:nth-child(3) {
              margin-bottom: 5px;
            }
            .right-item:nth-child(4),
            .right-item:nth-child(5),
            .right-item:nth-child(6) {
              margin-top: 5px;
            }
          }
          .recommend-left,
          .recommend-right {
            height: 100%;
          }
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
                  width: calc(100% - 40px);
                  height: 100px;
                  display: flex;
                  justify-content: space-evenly;
                  flex-direction: column;
                  padding: 0px 20px;
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
                      width: 80%;
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
      color: #363636;
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
