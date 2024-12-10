<!--
 * @Description: 相册
-->
<template>
  <div class="page-main album-main">
    <div class="album-header animated bounceInDown">
      <video
        :src="'/video/video_1783824280.mp4'"
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
        <span class="album-item-count">{{ item.images.length }}</span>
        <span class="album-item-date">{{ formatDate(new Date(item.createTime), 'YY-mm-dd') }}</span>
        <span class="album-item-instoction">{{ item.intro }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="album">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { listAlbum } from '@/api/album.ts';
import useUserStore from '@/store/modules/user';
import { formatDate } from '@/utils/date.ts';
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
  const { code, data } = (await listAlbum({ userId: userStore.userId })) as any;
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
  .album-header {
    height: 40vh;
    width: calc(100%);
    // margin: 40px 60px;
    border-radius: 12px;
    box-shadow: get('box-shadow');
    .album-header-video {
      opacity: 0.95;
      height: 100%;
      width: 100%;
      border-radius: 12px;
      object-fit: cover;
    }
    margin-bottom: 1rem;
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
  .album-main {
    @include flex-column;
    justify-content: start;
    background: transparent !important;
    backdrop-filter: none !important;
    // max-width: 1100px !important;
    .album-center {
      display: flex;
      position: relative;
      flex-wrap: wrap;
      justify-content: start;
      align-items: center;
      width: calc(100% + 20px);
      .album-item {
        width: calc(25% - 20px);
        aspect-ratio: 5/7;
        border-radius: 12px;
        margin: 10px;
        transition: all 0.6s ease;
        opacity: 0.9;
        cursor: pointer;
        box-shadow: get('box-shadow');
        position: relative;
        color: white;
        .album-item-cover {
          width: 100%;
          height: 100%;
          border-radius: 12px;
          object-fit: cover;
          object-position: 70%;
          background-repeat: no-repeat;
        }
        .album-item-name {
          position: absolute;
          left: 24px;
          top: 20px;
          font-size: 30px;
          font-weight: bold;
        }
        .album-item-count {
          bottom: 20px;
          position: absolute;
          left: 25px;
          /* top: 10px; */
          font-size: 30px;
        }
        .album-item-date {
          position: absolute;
          bottom: 27px;
          right: 25px;
          opacity: 0.8;
        }
        .album-item-instoction {
          position: absolute;
          font-weight: bold;
          font-size: 1rem;
          left: 25px;
          top: 77px;
          opacity: 0.9;
        }
      }
    }
    // .album-center:not(:hover) {
    // filter: blur(0px);

    .album-item:hover {
      filter: blur(0px);
      transform: scale(1.03);
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
