<!--
 * @Description:  博客标签
-->
<template>
  <div class="blog-tag page-main">
    <div class="cards">
      <div
        class="card bounceInDown"
        v-for="(item, i) in tagList"
        @click="toTagPage"
        :class="getAnimateTime()"
      >
        <div class="card-face">
          <div class="card-label">
            <el-image class="card-bg" :src="item.coverUrl" fit="cover">
              <template #placeholder>
                <div
                  class="image-slot"
                  v-cLoading="'rotate'"
                  style="width: 100%; height: 100%"
                ></div>
              </template>
            </el-image>
            <div class="card-bg-cover"></div>
            <span class="card-label-text">{{ item.tagName }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { listTag } from '@/api/tag.ts';
const router = useRouter();
const userStore = useUserStore();
const tagList = ref([
  {
    tagName: '前端',
    key: '1',
    isActive: false,
    children: [
      { tagName: 'html', key: '1-1' },
      { tagName: 'css', key: '1-2' },
      { tagName: 'javascript', key: '1-3' }
    ]
  },
  {
    tagName: '后端',
    key: '2',
    isActive: false,
    children: [
      { tagName: 'springboot', key: '2-1' },
      { tagName: 'tomcat', key: '2-2' }
    ]
  }
] as any);

const imgLoading = ref('rotate' as any);

async function getTagList() {
  const { code, msg, data } = (await listTag({})) as any;
  if (code === 200 && data) {
    tagList.value = data.list;
  }
}

function toTagPage(item: any) {
  router.push({ name: 'blogTagPage', query: { tagId: item.tagId } });
}

const animateTimeList = ['0s5', '0s7', '1s', '1s2', '1s5', '1s7', '2s', '2s2', '2s5', '2s7', '3s'];

function getAnimateTime() {
  return `animated-${animateTimeList[Math.floor(Math.random() * 9) + 1]}`;
}

onMounted(() => {
  getTagList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-tag {
    @include flex;
    background: get('background');
    box-shadow: get('box-shadow');
    border-radius: 15px;
  }
  .cards {
    bottom: 0;
    display: flex;
    height: 250px;
    padding: 0 50px;
    justify-content: center;
    // position: fixed;
    left: 0;
    right: 0;
  }

  .card {
    height: 200px;
    margin: 0 -25px;
    position: relative;
    width: 150px;
    cursor: pointer;
  }
  .card:after {
    bottom: 0;
    content: '';
    left: -60px;
    position: absolute;
    right: -60px;
    top: 0px;
    z-index: 10;
  }

  .card-face {
    bottom: 0;
    content: '';
    left: 0;
    pointer-events: none;
    position: absolute;
    right: 0;
    top: 0;

    transition: 800ms cubic-bezier(0.19, 1, 0.22, 1) transform;
  }

  .card-bg {
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 1;
    border-radius: 10px;
    object-fit: cover;
    opacity: 0.9;
    background: get('font-color');
    filter: brightness(0.6);
  }
  .card-label-text {
    color: white;
    font-weight: 700;
    font-size: 21px;
    z-index: 10;
    position: absolute;
    margin-left: 15px;
    top: 10px;
    left: 10px;
  }

  .card-label {
    font-family: 'helvetica neue', helvetica, arial, sans-serif;
    font-size: 24px;
    font-weight: bold;
    letter-spacing: -0.025em;
    text-align: left;
    position: relative;
    width: 100%;
    height: 100%;
    @include flex;
  }

  .card-bg-cover {
    position: absolute;
    width: calc(100% - 10px);
    height: calc(100% - 10px);
    margin: 5px;
    border: 1px solid #a9a9a9;
    z-index: 1;
    border-radius: 8px;
  }

  .card .card-face {
    background: get('background');
    // box-shadow: get('box-shadow');
    border-radius: 10px;
    transform: translateY(15px) rotate(5deg);
  }
  .card:hover:after {
    top: -175px;
  }
  .card:hover .card-face:after {
    animation: fade 1s ease-out forwards linear;
    transition: all 1s;
  }
  .card:hover .card-face {
    // box-shadow: 0 10px 20px rgba(0, 0, 0, 0.4), inset 0 0 0 2px rgba(255, 214, 153, 0.75);
    transform: translateY(-100px) rotate(0deg) scale(1.5);
    transition-duration: 1s;

    z-index: 5;
    .card-bg {
      filter: brightness(1);
    }
  }

  @keyframes fade {
    0% {
      opacity: 0.9;
      transform: scale(1);
    }
    100% {
      opacity: 0;
      transform: scale(1.15);
    }
  }
}
.loader {
  height: 50px !important;
}
</style>
