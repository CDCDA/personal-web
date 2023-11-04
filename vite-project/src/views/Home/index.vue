<!--
 * @Description:  主页
-->
<template>
  <div class="home-container">
    <div class="home-top">
      <span class="main-top-title">CCCC的小破站</span>
      <span class="main-top-vice-title">逛逛吧</span>
      <el-image class="CycleUpDown" :src="require('@/assets/svg/down.svg')" @click="toMainPage" />
      <!-- <el-icon  @click="toMainPage"><DArrowRight class="down-icon" /></el-icon> -->
    </div>
    <div class="home-main">
      <div class="main-header">
        <el-icon><ChromeFilled /></el-icon>
        <div class="main-header-text-list">
          <div class="main-header-text" v-for="item in headerList">{{ item.text }}</div>
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
              <div class="recommend-left-top-cover">
                <div>随便逛逛</div>
                <el-image :src="require('@/assets/svg/right.svg')"></el-image>
              </div>
            </div>
            <div class="recommend-left-bottom">
              <div class="bottom-item">
                <span>必读精选</span><img src="@/assets/svg/book.svg" />
              </div>
              <div class="bottom-item"><span>热门文章</span><img src="@/assets/svg/hot.svg" /></div>
              <div class="bottom-item">
                <span>生活随笔</span><img src="@/assets/svg/edit.svg" />
              </div>
            </div>
          </div>
          <div class="recommend-right">
            <div class="right-item" v-for="item in recommends">
              <div class="icon-rec">荐</div>
              <el-image
                class="item-image"
                :src="require('@/assets/images/fiture-room.jpg')"
              ></el-image>
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
              <div class="header-item" v-for="item in typeList">{{ item.typeName }}</div>
              <div class="header-more" style="float: right">更多</div>
            </div>
            <div class="display-list">
              <div class="list-item" v-for="item in recommends">
                <el-image
                  class="list-item-img"
                  :src="require('@/assets/images/fiture-room.jpg')"
                ></el-image>
                <div class="list-item-footer">
                  <span class="list-item-title">{{ item.blogTitle }}</span>
                  <div class="list-item-tag">
                    <span class="item-tag">#前端</span>
                    <span class="item-tag">#笔记</span>
                    <span class="item-time">{{ item.createTime }}</span>
                  </div>
                </div>
              </div>
            </div>
            <Pagination
              :page="pageData.page"
              :page-size="pageData.pageSize"
              :total="total"
              :on-page-change="getBlogList"
              :showSizes="true"
              :pageSizeList="pageData.pageSizeList"
              :on-page-size-change="getBlogList"
              class="pagi page-content"
            ></Pagination>
          </div>
          <div class="display-right">
            <BlogUserCard />
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
import { onMounted, ref } from 'vue';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import { listTypeTree } from '@/api/type';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { formateDate } from '@/utils/date.ts';
import Pagination from '@/components/pagination/index.vue';
import BlogUserCard from '@/views/blog/components/blogUserCard.vue';
import BlogTypeCard from '@/views/blog/components/blogTypeCard.vue';
import BlogTagCard from '@/views/blog/components/blogTagCard.vue';
import BlogCountCard from '@/views/blog/components/blogCountCard.vue';
import TechnologyStackCard from '../introduction/personalProfile/components/technologyStackCard.vue';
const recommends = ref([] as any);
const userStore = useUserStore();
const headerList = ref([
  {
    text: '加了个滚动技术栈',
    path: ''
  },
  {
    text: '相册已完成',
    path: ''
  },
  {
    text: '修整了一下首页',
    path: ''
  },
  {
    text: '放假放假放假',
    path: ''
  }
] as any);
const router = useRouter() as any;
const typeList = ref([] as any);
const pageData = ref({
  pageTotal: 0,
  page: 1,
  pageSize: 10,
  pageSizeList: [10, 20, 30]
} as any);
const total = ref(100 as any);
async function getBlogList() {
  const params = {
    pageNum: 1,
    pageSize: 6
  };
  const { code, msg, data } = (await listBlog(params)) as any;
  if (code === 200) {
    recommends.value = data.list;
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
  const params = {
    userId: userStore.userId
  };
  const { code, msg, data } = (await listTypeTree(params)) as any;
  if (code === 200) {
    typeList.value = data;
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

onMounted(() => {
  getBlogList();
  getTypeTree();
  let header = document.querySelector('.main-header-text-list');

  if (header) {
    setInterval(() => {
      headerRoll(header);
    }, 5000);
  }
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
@keyframes retate-img {
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
@keyframes re-retate-img {
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
    .home-top {
      width: 100%;
      min-height: 100vh;
      @include flex-column;
      position: relative;
    }
    .main-top-title {
      color: get('font-color');
      font-weight: bold;
      font-size: 40px;
    }
    .main-top-vice-title {
      margin-top: 10px;
      margin-bottom: 100px;
      color: get('font-color');
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
      height: 80px;
      color: get('font-color');
      bottom: 40px;
      cursor: pointer;
      position: absolute;
    }
    .home-main {
      width: calc(100% - 12%);
      @include flex-column;
      .main-header {
        height: 60px;
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
          height: 380px;
          .recommend-left {
            width: calc(50% - 5px);
            margin-right: 10px;
            @include flex-column;
            justify-content: space-between;
            .recommend-left-top {
              background: get('background');
              border-radius: 15px;
              width: 100%;
              height: 280px;
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
                transform: rotate(-45deg);
                position: absolute;
              }
              .recommend-left-top-cover {
                opacity: 0;
                z-index: 999;
                width: 100%;
                position: absolute;
                height: calc(100%);
                background: get('bk');
                font-size: 85px;
                font-weight: bold;
                color: get('re-font-color');
                @include flex-column;
                align-items: flex-start;
                padding-left: 50px;
                .el-image {
                  width: 120px;
                }
              }
              &:hover {
                .recommend-left-top-cover {
                  opacity: 1;

                  transition: cubic-bezier(0.71, 0.15, 0.16, 1.15) 0.6s;
                }
              }
            }
            .recommend-left-bottom {
              display: flex;
              justify-content: space-between;
              align-items: center;
              margin-top: 5px;
              width: 100%;

              .bottom-item {
                height: 90px;
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
                img {
                  opacity: 0.2;
                  position: absolute;
                  right: 0%;
                  top: 20%;
                  transition: 0.3s;
                  width: 90px;
                  text-align: center;
                  filter: blur(2px);
                  transform: rotate(15deg);
                }
              }
              .bottom-item:not(:hover) {
                animation: re-slide-in 0.3s forwards linear;
                img {
                  animation: re-retate-img 0.3s forwards linear;
                }
              }
              .bottom-item:hover {
                & {
                  width: 50%;
                }
                animation: slide-in 0.3s forwards linear;
                img {
                  animation: retate-img 0.3s forwards linear;
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
              height: 60px;
              background: get('background');
              box-shadow: get('box-shadow');
              border-radius: 15px;
              padding: 0px 20px;
              position: relative;
              .header-item {
                font-size: 18px;
                font-weight: bold;
                padding: 5px 15px;
                cursor: pointer;
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
                mask: linear-gradient(135deg, #000c 40%, #000, #000c 60%) 100% 100%/240% 240%;
                transition: 0.4s;
              }
              .list-item {
                width: calc(50% - 10px);
                height: 400px;
                margin-bottom: 20px;
                background: get('background');
                box-shadow: get('box-shadow');
                border-radius: 15px;
                @include flex-column;
                justify-content: start;
                .list-item-img {
                  border-radius: 15px 15px 0px 0px;
                  width: 100%;
                  height: calc(100% - 100px);
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
                .list-item-img:hover::before {
                  /* 元素沿X轴45横切，沿X轴左移150px */
                  transform: skewX(45deg) translateX(-650px);
                }
                .list-item-fooer {
                  height: 100px;
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
}
</style>
