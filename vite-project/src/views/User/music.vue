<!--
 * @Description: 音乐
-->
<template>
  <div class="page-main music-main">
    <div class="music-header animated bounceInDown">
      <!-- <div class="music-header-img" :src="require('@/assets/images/bk-1.jpg')"></div> -->
      <video
        :src="require('@/assets/images/video_1783831053.mp4')"
        class="music-header-video"
        autoplay
        loop
        muted
      ></video>
      <span class="music-header-top">相册集3</span>
      <span class="music-header-center">随便留点照片</span>
      <span class="music-header-bottom">凑活看看</span>
    </div>
    <div class="music-center">
      <div class="music-item" v-for="item in musicList">
        <img class="music-item-cover" :src="item.url" />
        <span class="music-item-name">{{ item.musicName }}</span>
        <span class="music-item-date">{{ item.createTime }}</span>
        <span class="music-item-instoction">{{ item.introduction }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const musicList = ref([
  {
    musicName: 'AAAAA',
    createTime: '2022',
    introduction: 'aaaaaa',
    url: require('@/assets/images/bk-1.jpg')
  },
  {
    musicName: 'AAAAA',
    createTime: '2022',
    introduction: 'aaaaaa',
    url: require('@/assets/images/bk-1.jpg')
  },
  {
    musicName: 'AAAAA',
    createTime: '2022',
    introduction: 'aaaaaa',
    url: require('@/assets/images/bk-1.jpg')
  },
  {
    musicName: 'AAAAA',
    createTime: '2022',
    introduction: 'aaaaaa',
    url: require('@/assets/images/bk-1.jpg')
  }
] as any);
</script>
<style lang="scss">
@include theme() {
  .music-main {
    @include flex-column;
    justify-content: start;
    .music-header {
      height: 26rem;
      width: 100%;
      border-radius: 20px;
      box-shadow: get('box-shadow');
      position: relative;
      .music-header-img {
        opacity: 0.8;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        background: url('@/assets/images/cyberpunk-city.jpg');
        background-size: left 28% / cover no-repeat;
        object-fit: cover;
      }
      .music-header-video {
        opacity: 0.95;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        object-fit: cover;
      }
      margin-bottom: 40px;
      // color: get('re-font-color');
      color: white;
      .music-header-top {
        position: absolute;
        top: 10px;
        left: 15px;
      }
      .music-header-center {
        position: absolute;
        top: 47px;
        left: 14px;
        font-size: 35px;
        font-weight: bold;
      }
      .music-header-bottom {
        position: absolute;
        bottom: 14px;
        left: 16px;
      }
    }
    .music-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      .music-item {
        width: calc(25% - 20px);
        height: 450px;
        border-radius: 20px;
        margin: 10px 0px;
        opacity: 0.9;
        box-shadow: get('box-shadow');
        position: relative;
        // color: get('re-font-color');
        color: white;
        .music-item-cover {
          width: 100%;
          height: 100%;
          border-radius: 20px;
          object-fit: cover;
          object-position: 70%;
          background-repeat: no-repeat;
        }
        .music-item-name {
          position: absolute;
          left: 19px;
          top: 10px;
          opacity: 0.8;
          font-size: 25px;
        }
        .music-item-date {
          position: absolute;
          bottom: 14px;
          right: 37px;
          opacity: 0.7;
        }
        .music-item-instoction {
          position: absolute;
          font-weight: bold;
          font-size: 52px;
          left: 19px;
          top: 45px;
          opacity: 0.9;
        }
      }
      .music-item:hover {
        transform: scale(1.03);
      }
    }
  }
}
</style>
