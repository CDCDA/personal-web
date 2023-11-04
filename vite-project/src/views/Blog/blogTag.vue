<!--
 * @Description:  博客分类
-->
<template>
  <div class="blog-tag page-main">
    <div class="page-left">
      <div class="tag-list">
        <div
          class="tag-item"
          :class="item.isActive ? 'is-active' : ''"
          v-for="item in tagList"
          @click="getBlogList(item)"
        >
          <span class="tag-item-prefix">#</span>
          <span class="tag-item-content">{{ item }}</span>
          <span class="tag-item-count">{{ '5' }}</span>
        </div>
      </div>
      <div class="blog-list">
        <div class="blog-item" v-for="item in blogList">
          <el-image
            class="blog-item-left-img"
            :src="require('@/assets/images/fiture-room.jpg')"
          ></el-image>
          <div class="blog-item-right">
            <div class="blog-title no-wrap">{{ item.blogTitle }}</div>
            <div class="tag-list">
              <div class="tag-item" v-for="tag in item.tags">
                {{ tag }}
              </div>
            </div>
            <div class="blog-time">{{ item.createTime }}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="page-right" style="width: 300px">
      <BlogUserCard></BlogUserCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listByUserId } from '@/api/tag';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import useUserStore from '@/store/modules/user';
import BlogUserCard from './components/blogUserCard.vue';
const userStore = useUserStore();
const tagList = ref([
  // {
  //   tagName: '前端',
  //   key: '1',
  //   isActive: false,
  //   children: [
  //     { tagName: 'html', key: '1-1' },
  //     { tagName: 'css', key: '1-2' },
  //     { tagName: 'javascript', key: '1-3' }
  //   ]
  // },
  // {
  //   tagName: '后端',
  //   key: '2',
  //   isActive: false,
  //   children: [
  //     { tagName: 'springboot', key: '2-1' },
  //     { tagName: 'tomcat', key: '2-2' }
  //   ]
  // }
] as any);
const blogList = ref([]) as any;
const isShowView = ref([] as any);

function clickFoucus(data: any) {
  let key = data.tagId;
  isShowView.value = [];
  isShowView.value[key] = true;
}

async function gettagTree(userId: any) {
  const { code, msg, data } = (await listByUserId({ userId })) as any;
  if (code === 200 && data) {
    tagList.value = data.list;
  }
}

async function getBlogList(item: any) {
  if (item) {
    tagList.value.forEach((tag: any) => {
      tag.isActive = false;
    });
    item.isActive = true;
  }
  const params = {
    pageNum: 1,
    pageSize: 5
  };
  const { code, msg, data } = (await listBlog(params)) as any;
  if (code === 200) {
    blogList.value = data.list;
  } else {
    ElMessage.error('博客数据获取失败', msg);
  }
}

onMounted(() => {
  gettagTree(userStore.userId);
  getBlogList(null);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-tag {
    display: flex;
    .page-left {
      width: calc(100% - 320px);
      padding: 20px;
      border-radius: 15px;
      background: get('background');
      box-shadow: get('box-shadow');
    }
    .page-right {
      width: 300px;
      margin-left: 20px;
    }
    .header {
      font-weight: bold;
    }
    .tag-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      width: 100%;
      .tag-item {
        @include flex;
        padding: 8px 25px;
        margin: 5px 10px;
        border-radius: 10px;
        color: #363636;
        font-size: 18px;
        background: white;
        // box-shadow: get('box-shadow');
        border: 1px solid #d1d1d1;
        .tag-item-prefix {
          font-size: 22px;
          opacity: 0.4;
          font-weight: bold;
        }
        .tag-item-content {
          margin-left: 4px;
          font-weight: bold;
        }
        .tag-item-count {
          background: #f7f7f9;
          padding: 0px 5px;
          border-radius: 8px;
          text-align: center;
          min-width: 21px;
          display: inline-block;
          margin-left: 6px;
        }
      }
      .tag-item:hover,
      .tag-item.is-active {
        background: get('bk');
        color: white;
        .tag-item-count {
          color: get('font-color');
        }
      }
    }

    .blog-list {
      @include flex;
      flex-wrap: wrap;
      justify-content: start;
      margin-top: 20px;
      .blog-item {
        cursor: pointer;
        width: calc(50% - 30px);
        height: auto;
        margin-bottom: 20px;
        margin: 10px;
        background: white;
        // box-shadow: get('box-shadow');
        border: 1px solid #d1d1d1;
        border-radius: 15px;
        @include flex;
        justify-content: space-between;
        .blog-item-left-img {
          width: 160px;
          height: 150px;
          border-radius: 15px 0px 0px 15px;
        }
        .blog-item-right {
          @include flex-column;
          width: calc(100% - 160px);
          padding: 0px 15px;
          text-align: left;
          .blog-title {
            font-size: 18px;
            font-weight: bold;
            width: 100%;
            margin: 5px 0px;
          }
          .tag-list {
            width: 100%;
            @include flex;
            margin: 5px 0px;
            flex-wrap: nowrap;
            justify-content: start;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;

            .tag-item {
              margin: 5px;
              flex: 0 0 auto;
              &::before {
                content: '#';
                opacity: 0.5;
              }
            }
          }
          .blog-time {
            margin: 5px 0px;
            width: 100%;
            opacity: 0.5;
          }
        }
      }
      .blog-item:hover {
        transform: scale(1.03);
      }
    }
  }
}
</style>
