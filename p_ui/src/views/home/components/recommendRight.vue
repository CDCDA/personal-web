<template>
  <div class="recommend-right">
    <div class="front" v-if="frontShow">
      <c-image class="front-img" :src="recommends[1]?.coverUrl" @click="toDetail(recommends[1])" />
      <span class="reBlog">推荐文章</span>
      <span class="reBlogTitle">{{ recommends[1]?.blogTitle }}</span>
      <span class="more-re" @click="showMore()"
        ><el-icon><Top /></el-icon>更多推荐</span
      >
    </div>
    <div class="back" v-else>
      <div class="right-item" v-for="item in recommends" @click="toDetail(item)">
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
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { listBlog } from '@/api/blog';
const frontShow = ref(true);
const router = useRouter() as any;
const recommends = ref([]) as any;
// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

function showMore() {
  frontShow.value = false;
}

async function getRecommendBlogList() {
  let form = {
    pageNum: 1,
    pageSize: 6,
    userId: null,
    isRecommend: '1'
  };
  const { code, data } = (await listBlog(form)) as any;
  if (code === 200) {
    recommends.value = data.list;
  }
}

onMounted(() => {
  getRecommendBlogList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .recommend-right {
    height: 100%;
    width: calc(50% - 5px);
    margin-left: 10px;
    color: get('font-color');

    border-radius: 12px;
    overflow: hidden;
    .front,
    .back {
      height: 100%;
      width: 100%;
      position: relative;
    }
    .front {
      cursor: pointer;
    }
    .back {
      display: flex;
      justify-content: space-between;
      flex-wrap: wrap;
    }
    .front-img {
      height: 100%;
      width: 100%;
      box-shadow: get('box-shadow');
    }
    .reBlog,
    .reBlogTitle,
    .more-re {
      color: white;
      position: absolute;
    }
    .reBlog {
      left: 40px;
      bottom: 80px;
      font-size: 14px;
      opacity: 0.8;
    }
    .reBlogTitle {
      left: 40px;
      bottom: 30px;
      font-size: 25px;
      font-weight: bold;
    }
    .more-re {
      right: 30px;
      bottom: 40px;
      color: white;
      background: gray;
      padding: 5px 10px;
      border-radius: 20px;
      opacity: 0.9;
      display: flex;
      justify-content: space-between;
      align-items: center;
      :deep(.el-icon) {
        font-size: 20px;
        margin-right: 5px;
        cursor: pointer;
      }
    }
    .more-re::after {
      content: '';
      background: transparent;
      backdrop-filter: blur(20px);
    }
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
}
</style>
