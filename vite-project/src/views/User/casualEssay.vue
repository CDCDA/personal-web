<!--
 * @Description: 随笔
-->
<template>
  <div class="page-main casualEssay-main">
    <div class="casualEssay-header animated bounceInDown">
      <!-- <div class="casualEssay-header-img"></div> -->
      <video
        :src="require('@/assets/images/video_2000212665.mp4')"
        class="casualEssay-header-video"
        autoplay
        loop
        muted
      ></video>
      <span class="casualEssay-header-top">随笔</span>
      <span class="casualEssay-header-center">随便留点照片</span>
      <span class="casualEssay-header-bottom">凑活看看</span>
    </div>
    <div class="casualEssay-center">
      <div
        class="casualEssay-item c-left"
        :class="getAnimate(i)"
        v-for="(item, i) in casualEssayList"
      >
        <span class="casualEssay-item-content">{{ item.text }}</span>
        <div class="img-list">
          <el-image
            class="img-list-item"
            :preview-src-list="[img]"
            v-for="img in item.imgList"
            :src="img.url"
          />
        </div>
        <div class="c-dotted-line"></div>
        <span class="casualEssay-item-date"
          ><el-icon><Clock /></el-icon>{{ item.createTime }}</span
        >
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const casualEssayList = ref([
  {
    text: 'AAAAA',
    createTime: '2022-10-5',
    imgList: [
      {
        url: require('@/assets/images/futuristic-sci-fi-room.jpg')
      },
      {
        url: require('@/assets/images/misty-rain.jpg')
      }
    ]
  },
  {
    text: 'AAAAA',
    createTime: '2022-10-5',
    imgList: [
      {
        url: require('@/assets/images/rain-cyberpunk-city.jpg')
      },
      {
        url: require('@/assets/images/fiture-room.jpg')
      }
    ]
  },
  {
    text: 'AAAAA',
    createTime: '2022-10-5',
    imgList: [
      // {
      //   url: require('@/assets/images/bk-1.jpg')
      // },
      // {
      //   url: require('@/assets/images/bk-1.jpg')
      // }
    ]
  },
  {
    text: 'AAAAA',
    createTime: '2022-10-5',
    imgList: [
      {
        url: require('@/assets/images/future-night-view.jpg')
      }
    ]
  }
] as any);

function getAnimate(i: any) {
  if (i % 3 === 0) {
    return 'animated';
  }
  if (i % 3 === 1) {
    return 'animated-1s5';
  }
  if (i % 3 === 2) {
    return 'animated-2s';
  }
  return 'animated';
}
</script>
<style lang="scss">
@include theme() {
  .casualEssay-main {
    @include flex-column;
    justify-content: start;
    .casualEssay-header {
      height: 26rem;
      width: 100%;
      border-radius: 20px;
      box-shadow: get('box-shadow');
      position: relative;
      // .casualEssay-header-img {
      //   opacity: 0.9;
      //   height: 100%;
      //   width: 100%;
      //   border-radius: 20px;
      //   background: url('@/assets/images/resting-hexenbiest.jpg') left 28% / cover no-repeat;
      // }
      .casualEssay-header-video {
        opacity: 0.95;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        // background: url('@/assets/images/video_2000212665.mp4');
        // background-size: left 28% / cover no-repeat;
        object-fit: cover;
      }
      margin-bottom: 40px;
      // color: get('re-font-color');
      color: white;
      .casualEssay-header-top {
        position: absolute;
        top: 10px;
        left: 15px;
      }
      .casualEssay-header-center {
        position: absolute;
        top: 47px;
        left: 14px;
        font-size: 35px;
        font-weight: bold;
      }
      .casualEssay-header-bottom {
        position: absolute;
        bottom: 14px;
        left: 16px;
      }
    }
    .casualEssay-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: flex-start;
      width: 100%;
      .casualEssay-item {
        width: calc(33% - 40px);
        border-radius: 10px;
        margin: 10px 0px;
        position: relative;
        padding: 20px 15px 10px 15px;
        text-align: left;
        box-shadow: get('box-shadow');
        background: get('background');
        color: get('font-color');
        .casualEssay-item-content {
        }
        .img-list {
          overflow-x: auto;
          width: 100%;
          @include flex;
          min-height: 40px;

          justify-content: start;
          margin: 12px 0px;
          .img-list-item {
            height: 100px;
            margin-right: 10px;
            border-radius: 10px;
          }
        }

        .casualEssay-item-date {
          height: 28px;
          display: block;
          line-height: 28px;
          border-radius: 28px;
          border: 1px solid #d1d1d1;
          padding: 0px 15px;
          background: #9999992b;
          width: fit-content;
          text-align: center;
          font-size: 17px;
          @include flex;
          .el-icon {
            margin-right: 5px;
          }
        }
      }
      .casualEssay-item:hover {
        transform: scale(1.03);
        border: 1px solid get('bk');
      }
    }
  }
}
</style>
