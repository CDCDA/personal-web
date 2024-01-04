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
      <svg-icon iconName="movie-black" /> 电影<span
        >没什么电影看啊，去电影院也就看个氛围，实际还是不咋样</span
      >
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
    <div class="divider">
      <svg-icon iconName="teleplay-black" />电视剧<span>电视剧就更惨烈了（叹气）</span>
    </div>
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
    <div class="divider">
      <svg-icon iconName="animation-black" />动漫<span>动漫也不咋感兴趣，还是看点无厘头扯淡的</span>
    </div>
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
const singlePlayerGames = ref([] as any);

const movies = ref([
  {
    name: '流浪地球2',
    coverUrl:
      'https://t10.baidu.com/it/u=3491272544,191407053&fm=30&app=106&f=JPEG?w=571&h=800&s=7FE7B544CA3286DE0A6870800300A0D8',
    intro: '中国科幻里程碑！'
  },
  {
    name: '阿甘正传',
    coverUrl:
      'https://img0.baidu.com/it/u=27386080,1383610749&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500',
    intro: '有种说不出的感觉'
  }
]);

const teleplays = ref([
  {
    name: '琅琊榜',
    coverUrl:
      'https://img2.baidu.com/it/u=2458365701,2208642697&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800',
    intro: '没看，凑个数'
  },
  {
    name: '仙剑奇侠传三',
    coverUrl:
      'https://5b0988e595225.cdn.sohucs.com/images/20200407/c7c221eb903d49be9c27f4b321581095.jpeg',
    intro: '觉得一般，但对比一下又好像还行'
  },
  {
    name: '去有风的地方',
    coverUrl:
      'https://n.sinaimg.cn/sinakd20122/120/w1440h1080/20220518/63b9-c943c70442e642fc246db9142447b823.jpg',
    intro: '退休模拟器，可惜贫穷的我没法共情'
  }
]);

const animations = ref([
  {
    name: '瑞克和莫蒂系列',
    coverUrl:
      'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F05732394-f07b-4db4-9c31-0c01762ec232%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1702807244&t=d08fc23a5532a2eb0c8a38d66ca15dcc',
    intro: '爆炸脑洞，逆天剧情，给你一点小小的三观震撼'
  },
  {
    name: '猫和老鼠',
    coverUrl:
      'https://t10.baidu.com/it/u=384139979,181772917&fm=30&app=106&f=JPEG?w=552&h=435&s=3DEDE7064B30FAC65017436F0300A06A',
    intro: '万万没想到我会在大学的时候把猫和老鼠重看一遍'
  },
  {
    name: '银魂',
    coverUrl:
      'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F94ded97b-85e4-4ada-92aa-3d6e497a5cf6%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1702807285&t=96dab81b9f2dbe96a3b99ac3e357ecb8',
    intro: '一入银魂深似海，从此节操是路人'
  },
  {
    name: '碧蓝之海',
    coverUrl:
      'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F456520f7-e925-4eaf-8541-6505a7bcc99d%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1702807365&t=9991857f75edf518f8449f1bdc50f1d6',
    intro: '是运动番（确信）,什么时候出第二季'
  }
]);

const animateTimeList = ['0s5', '0s7', '1s', '1s2', '1s5', '1s7', '2s', '2s2', '2s5', '2s7', '3s'];

function getAnimateTime() {
  return `animated-${animateTimeList[Math.floor(Math.random() * 9) + 1]}`;
}

onMounted(() => {});
</script>

<style lang="scss" scoped>
@include theme() {
  .drama-main {
    @include flex-column;
  }
  .drama-carousel {
    margin-top: 10px;
    width: 100%;
    height: 45vh !important;
    ::v-deep .el-carousel__container {
      height: 100%;
    }
    .drama-carousel-item {
      height: calc(100%);
      box-shadow: get('box-shadow');
      // opacity: 0.9;
      border-radius: 15px;
    }
    .carousel-item-img {
      width: 100%;
      height: 100%;
      z-index: 1;
      object-fit: cover;
      background-position: center;
      border-radius: 15px;
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
      font-family: 'Courier New', Courier, monospace;
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
