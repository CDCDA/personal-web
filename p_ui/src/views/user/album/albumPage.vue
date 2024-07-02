<!--
 * @Description: 相册详情
-->
<template>
  <div class="album-page page-main">
    <div class="album-img-list fade-in" v-cLoading="loading">
      <div class="album-img-item" v-for="image in albumData.images">
        <c-image
          class="album-img"
          :preview-src-list="[image.url]"
          :preview-teleported="true"
          :src="image.url"
          fit="cover"
        >
          <template #placeholder>
            <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%"></div>
          </template>
        </c-image>
        <span class="album-img-time">{{ image.createTime }}</span>
      </div>
      <template>
        <el-empty description="description" />
      </template>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { getAlbumById } from '@/api/album.ts';
import useUserStore from '@/store/modules/user';
import { ElMessage } from 'element-plus';
const router = useRouter();
const userStore = useUserStore();
const loading = ref(false as any);
const albumData = ref({} as any);

async function getAlbumData() {
  loading.value = true;
  const { code, msg, data } = (await getAlbumById({
    albumId: router.currentRoute.value.query.albumId
  })) as any;
  if (code === 200) {
    albumData.value = data;
    loading.value = false;
  }
}

onMounted(() => {
  getAlbumData();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .album-page {
    background: get('background');
    border-radius: 15px;
    padding: 30px;
    width: calc(90% - 60px) !important;
  }
}
@keyframes toUp {
  0% {
    bottom: -30px;
  }
  100% {
    bottom: 0px;
  }
}
@keyframes toDown {
  0% {
    bottom: 0px;
  }
  100% {
    bottom: -30px;
  }
}
.album-img-list {
  @include flex;
  justify-content: start;
  flex-wrap: wrap;
  // animation: fade-in 1s linear forwards;
  .album-img-item {
    width: calc(33.33% - 20px);
    aspect-ratio: 7/5;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    margin: 10px;
    cursor: pointer;
    .album-img {
      height: 100%;
      width: 100%;
      transition: all 0.3s linear;
    }
    .album-img-time {
      position: absolute;
      bottom: -30px;
      right: 0px;
      width: calc(100%);
      line-height: 1.8;
      position: absolute;
      left: 0px;
      font-size: 14px;
      background: rgba(0, 0, 0, 0.3);
      padding: 4px 8px;
      color: #fff;
      transition: all 0.3s ease-in-out;
    }
  }
  .album-img-item:hover {
    .album-img-time {
      animation: toUp linear forwards 0.3s;
    }
    .album-img {
      transform: scale(1.02);
    }
  }
  .album-img-item:not(:hover) {
    .album-img-time {
      animation: toDown ease-in-out forwards 0.3s;
    }
  }
}
@include theme() {
  .album-img-item {
    box-shadow: get('box-shadow');
  }
}
</style>
