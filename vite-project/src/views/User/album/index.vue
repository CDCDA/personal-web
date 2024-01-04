<!--
 * @Description: 相册
-->
<template>
  <div class="page-main album-main">
    <div class="album-header animated bounceInDown">
      <!-- <div class="album-header-img" :src="require('/img/bk-1.jpg')"></div> -->
      <video
        :src="'/img/video_1783824280.mp4'"
        class="album-header-video"
        autoplay
        loop
        muted
      ></video>
      <span class="album-header-top">相册集</span>
      <span class="album-header-center">唯有记忆，才是最完美的影像。</span>
      <span class="album-header-bottom">历历在目</span>
    </div>
    <div class="album-center">
      <div class="album-item c-left" @click="routeTo(item)" v-for="(item, i) in albumList">
        <el-image class="album-item-cover" :src="item.coverUrl" fit="cover">
          <template #placeholder>
            <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%"></div>
          </template>
        </el-image>
        <span class="album-item-name">{{ item.name }}</span>
        <span class="album-item-date">{{
          formateDate(new Date(item.createTime), 'YY-mm-dd')
        }}</span>
        <span class="album-item-instoction">{{ item.intro }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { listAlbum } from '@/api/album.ts';
import useUserStore from '@/store/modules/user';
import { formateDate } from '@/utils/date.ts';
const router = useRouter();
const userStore = useUserStore();
const albumList = ref([] as any);

function getAnimate(i: any) {
  if (i % 4 === 0) {
    return 'animated-0s5';
  }
  if (i % 4 === 1) {
    return 'animated';
  }
  if (i % 4 === 2) {
    return 'animated-1s5';
  }
  if (i % 4 === 3) {
    return 'animated-2s';
  }
  return 'animated';
}

async function getAlbumList() {
  const { code, msg, data } = (await listAlbum({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    albumList.value = data.list;
  }
}

function routeTo(item: any) {
  router.push({ name: 'albumPage', query: { albumId: item.id } });
}

onMounted(() => {
  getAlbumList();
});
</script>
<style lang="scss">
@include theme() {
  // <div class="page-main album-main">
  //   <div class="album-header">
  //     <el-image class="album-header-img"></el-image>
  //   </div>
  //   <div class="album-center">
  //     <div class="album-item" v-for="item in albumList">
  //       <el-image class="album-item-cover"></el-image>
  //       <span class="album-item-name">{{ item.albumName }}</span>
  //       <span class="album-ite-date">{{ item.createTime }}</span>
  //       <span class="album-item-instoction">{{ item.introduction }}</span>
  //     </div>
  //   </div>
  // </div>
  .album-main {
    @include flex-column;
    justify-content: start;
    .album-header {
      height: 45vh;
      width: 100%;
      border-radius: 20px;
      box-shadow: get('box-shadow');
      position: relative;
      // .album-header-img {
      //   opacity: 0.8;
      //   height: 100%;
      //   width: 100%;
      //   border-radius: 20px;
      //   background: url('/img/cyberpunk-city.jpg');
      //   background-size: left 28% / cover no-repeat;
      // }
      .album-header-video {
        opacity: 0.95;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        object-fit: cover;
      }
      margin-bottom: 40px;
      // color: get('re-font-color');
      color: white;
      .album-header-top {
        position: absolute;
        top: 10px;
        left: 15px;
      }
      .album-header-center {
        position: absolute;
        top: 47px;
        left: 14px;
        font-size: 35px;
        font-weight: bold;
      }
      .album-header-bottom {
        position: absolute;
        bottom: 14px;
        left: 16px;
      }
    }
    .album-center {
      display: flex;
      position: relative;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      .album-item {
        width: calc(25% - 20px);
        height: 450px;
        border-radius: 20px;
        margin: 10px 0px;
        opacity: 0.9;
        cursor: pointer;
        box-shadow: get('box-shadow');
        position: relative;
        // color: get('re-font-color');
        color: white;
        .album-item-cover {
          width: 100%;
          height: 100%;
          border-radius: 20px;
          object-fit: cover;
          object-position: 70%;
          background-repeat: no-repeat;
        }
        .album-item-name {
          position: absolute;
          left: 19px;
          top: 10px;
          opacity: 0.8;
          font-size: 25px;
        }
        .album-item-date {
          position: absolute;
          bottom: 14px;
          right: 37px;
          opacity: 0.7;
        }
        .album-item-instoction {
          position: absolute;
          font-weight: bold;
          font-size: 52px;
          left: 19px;
          top: 45px;
          opacity: 0.9;
        }
      }
    }
    // .album-center:not(:hover) {
    // filter: blur(0px);
    .album-item:not(:hover) {
      filter: blur(8px);
    }
    .album-item:hover {
      filter: blur(0px);
      transform: scale(1.05) translateZ(0);
      transition: all 200ms linear;
    }
    .album-center:not(:hover) {
      .album-item {
        filter: blur(0px);
      }
    }
    // }
  }
}
</style>
