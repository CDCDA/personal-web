<!--
 * @Description: 影视
-->
<template>
  <div class="page-main drama-main">
    <el-carousel
      trigger="click"
      type="card"
      class="drama-carousel animated bounceInDown"
      :autoplay="false"
    >
      <el-carousel-item v-for="item in coverImages" class="drama-carousel-item">
        <img class="carousel-item-img" :src="item" />
      </el-carousel-item>
    </el-carousel>
    <div class="divider animated-0s5 c-left">
      <svg-icon iconName="电影" /> 电影<span>没什么电影看啊</span>
    </div>
    <div class="drama-list">
      <div class="drama-item c-up" :class="getAnimateTime()" v-for="item in movies">
        <img :src="item.coverUrl" />
        <div class="drama-info">
          <h3>{{ item.name }}</h3>
          <span class="no-wrap">{{ item.intro }}</span>
          <div class="item-cover">{{ item.intro }}</div>
        </div>
      </div>
    </div>
    <div class="divider"><svg-icon iconName="电视播放" />电视剧<span>没什么电视剧看啊</span></div>
    <div class="drama-list">
      <div class="drama-item" v-for="item in teleplays">
        <img :src="item.coverUrl" />
        <div class="drama-info">
          <h3>{{ item.name }}</h3>
          <span class="no-wrap">{{ item.intro }}</span>
          <div class="item-cover">{{ item.intro }}</div>
        </div>
      </div>
    </div>
    <div class="divider"><svg-icon iconName="animation" />动漫<span>没什么动漫看啊</span></div>
    <div class="drama-list">
      <div class="drama-item" v-for="item in animations">
        <img :src="item.coverUrl" />
        <div class="drama-info">
          <h3>{{ item.name }}</h3>
          <span class="no-wrap">{{ item.intro }}</span>
          <div class="item-cover">{{ item.intro }}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import ParallaxWheelSeeding from './components/parallaxWheelSeeding.vue';
import { coverImages } from './dramaData.ts';
import { listDrama } from '@/api/dramaSeries.ts';

const movies = ref([] as any);

const teleplays = ref([] as any);

const animations = ref([] as any);

const animateTimeList = ['0s5', '0s7', '1s', '1s2', '1s5', '1s7', '2s', '2s2', '2s5', '2s7', '3s'];

function getAnimateTime() {
  return `animated-${animateTimeList[Math.floor(Math.random() * 9) + 1]}`;
}

async function getList() {
  const { code, msg, data } = (await listDrama({})) as any;
  if (code == 200) {
    movies.value = data.list.filter((e: any) => {
      return e.type == 0;
    });
    teleplays.value = data.list.filter((e: any) => {
      return e.type == 1;
    });
    animations.value = data.list.filter((e: any) => {
      return e.type == 2;
    });
  }
}

onMounted(() => {
  getList();
});
</script>

<style lang="scss" scoped>
@include theme() {
  .drama-main {
    @include flex-column;
    background: transparent !important;
    backdrop-filter: none !important;
  }
  .drama-carousel {
    margin-top: 10px;
    width: 100%;
    height: 45vh !important;
    :deep(.el-carousel__container) {
      height: 100%;
    }
    .drama-carousel-item {
      height: calc(100%);
      box-shadow: get('box-shadow');
      // opacity: 0.9;
      border-radius: 8px;
    }
    .carousel-item-img {
      width: 100%;
      height: 100%;
      z-index: 1;
      object-fit: cover;
      background-position: center;
      border-radius: 8px;
    }
  }
  .drama-list {
    display: flex;
    justify-content: start;
    align-items: center;
    flex-wrap: wrap;
    width: calc(100% + 20px);
  }
  .drama-item {
    box-shadow: get('box-shadow');
    background: get('background');
    position: relative;
    transition: all 0.2s linear;
    border-radius: 10px;
    width: calc(25% - 26px);
    margin: 10px;
    padding: 5px 3px;
    box-shadow: get('box-shadow');
    height: 300px;
    @include flex-column;
    justify-content: start;
    overflow: hidden;
    img {
      width: calc(100% - 6px);
      border-radius: 8px;
      aspect-ratio: 5/3;
      object-fit: cover;
      background-position: center;
    }
    span {
      width: calc(100% - 20px);
      padding: 0px 10px;
      display: -webkit-box;
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 2;
    }
  }
  .drama-info {
    @include flex-column;
    transition: transform 0.3s linear;
    bottom: -6%;
    width: 100%;
    position: absolute;
    height: 60%;
  }
  .drama-item:hover {
    transform: scale(1.04);
    .drama-info {
      transform: translateY(-20px);
      background: get('background-no-tp');
    }
    img {
      filter: brightness(60%);
      // transform: scale(1.04);
    }
    // .item-cover {
    //   opacity: 1;
    //   top: 30%;
    //   filter: brightness(100%);
    // }
  }
  .divider {
    font-size: 25px;
    font-weight: bold;
    width: calc(100% - 20px);
    text-align: left;
    margin: 15px 0px;
    background: get('background');
    box-shadow: get('box-shadow');
    padding: 10px;
    border-radius: 10px;
    @include flex;
    justify-content: start;
    .svg-icon-wrap {
      height: 30px;
      width: 30px;
      margin-right: 5px;
    }
    span {
      font-size: 18px;
      font-weight: 300;
      margin-left: 10px;
    }
  }
  .item-cover {
    width: 100%;
    height: 70%;
    position: absolute;
    top: 320px;
    transition: all 0.5s linear;
    opacity: 0;
    background: white;
    @include flex;
  }
}
</style>
