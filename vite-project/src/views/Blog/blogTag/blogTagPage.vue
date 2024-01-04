<!--
 * @Description:  博客分类
-->
<template>
  <div class="blog-tag-page page-main c-left">
    <div class="header-tag-list">
      <div
        class="header-tag-item"
        :class="item.isActive ? 'is-active' : ''"
        v-for="item in tagList"
        @click="getList(item)"
      >
        <span class="tag-item-prefix" v-if="item.tagId">#</span>
        <span class="tag-item-content">{{ item.tagName }}</span>
        <span class="tag-item-count">{{ item.total }}</span>
      </div>
    </div>
    <div class="blog-list" v-loading="loading">
      <div class="blog-item" v-for="(item, i) in blogList" @click="toDetail(item)">
        <img class="blog-item-left-img" :src="item.coverUrl || '/img/background.jpg'" />
        <div class="blog-item-right">
          <div class="blog-title no-wrap">{{ item.blogTitle }}</div>
          <div class="tag-list">
            <div class="tag-item" v-for="tag in item.tags">
              <span class="tag-item-pretend">#</span>
              <span class="tag-item-text">{{ tag.tagName }}</span>
            </div>
          </div>
          <div class="blog-time">
            <el-icon><Clock /></el-icon>{{ item.createTime }}
          </div>
        </div>
        <span class="blog-sort-item-index">{{ i }}</span>
      </div>
    </div>
    <Pagination
      v-model:page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :total="total"
      :on-page-change="getBlogList"
      :showSizes="true"
      :pageSizeList="[10, 20, 30]"
      :on-page-size-change="getBlogList"
      class="pagi page-content"
    />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTag } from '@/api/tag';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import useUserStore from '@/store/modules/user';
import BlogUserCard from '@/views/blog/components/blogUserCard.vue';
import Pagination from '@/components/pagination/index.vue';
import { useRouter } from 'vue-router';
import BlogTagCard from '@/views/blog/components/blogTagCard.vue';
const router = useRouter();
const userStore = useUserStore();
const loading = ref(false as any);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  tagId: null
} as any);
const total = ref(100 as any);
const tagList = ref([] as any);
const blogList = ref([]) as any;
const isShowView = ref([] as any);

async function getTagList() {
  const { code, msg, data } = (await listTag({})) as any;
  if (code === 200 && data) {
    tagList.value = data.list;
    let total = 0;
    tagList.value.forEach((e: any) => {
      total += e.total;
    });
    tagList.value.unshift({
      tagName: '全部',
      isActive: false,
      total
    });
  }
}

function getList(item: any) {
  if (item) {
    tagList.value.forEach((tag: any) => {
      tag.isActive = false;
    });
    item.isActive = true;
  }
  queryParams.value.pageNum = 1;
  queryParams.value.tagId = item.tagId;
  getBlogList();
}

async function getBlogList() {
  loading.value = true;
  const { code, msg, data } = (await listBlog(queryParams.value)) as any;
  if (code === 200) {
    blogList.value = data.list;
    blogList.value.forEach((e: any) => {
      e.tags.length > 5 ? (e.tags.length = 5) : '';
    });
    total.value = data.total;
    loading.value = false;
  } else {
    ElMessage.error('博客数据获取失败', msg);
  }
}

// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

onMounted(() => {
  queryParams.value.tagId = router.currentRoute.value.query.tagId;
  getTagList();
  getBlogList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  @keyframes fade-out {
    0% {
      opacity: 0;
    }
    100% {
      opacity: 1;
    }
  }
  .blog-tag-page.page-main {
    padding: 20px;
    width: calc(86% - 40px) !important;
    border-radius: 15px;
    background: get('background');
    box-shadow: get('box-shadow');
    position: relative;
    animation: fade-out 0.5s forwards linear;
    transition: all 0.5s ease-in-out;
    .pagi {
      position: absolute;
      bottom: 10px;
      left: 0px;
      padding: 0px 20px;
      width: calc(100% - 40px);
    }
    .page-right {
      width: 300px;
      margin-left: 20px;
    }
    .header {
      font-weight: bold;
    }
    .header-tag-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      width: 100%;
      margin-bottom: 10px;
      .header-tag-item {
        @include flex;
        padding: 8px 15px;
        margin: 5px 10px;
        border-radius: 10px;
        color: #363636;
        font-size: 18px;
        background: white;
        cursor: pointer;
        border: 1px solid #d1d1d1;
        .tag-item-prefix {
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
      .header-tag-item:hover,
      .header-tag-item.is-active {
        background: get('bk');
        color: white;
        box-shadow: get('box-shadow');
        border: none;
        .tag-item-count {
          color: get('font-color');
        }
      }
    }

    .blog-list {
      @include flex;
      flex-wrap: wrap;
      justify-content: start;
      margin-bottom: 55px; // min-height: calc(100% - 120px);
      .blog-item {
        cursor: pointer;
        width: calc(50% - 20px);
        position: relative;
        height: auto;
        margin-bottom: 20px;
        margin: 10px;
        background: white;
        border-radius: 10px;
        transition: all 0.3s linear;
        display: flex;
        justify-content: center;
        align-items: center;
        justify-content: space-between;
        box-shadow: get('box-shadow');
        .blog-item-left-img {
          object-fit: cover;
          background-position: left 28%;
          width: 170px;
          height: 120px;
          border-radius: 8px;
          margin: 8px;
        }
        .blog-item-right {
          @include flex-column;
          width: calc(100% - 200px);
          padding: 0px 15px;
          align-items: start;
          text-align: left;
          .blog-title {
            font-size: 20px;
            font-weight: bold;
            width: 100%;
            margin: 5px 0px;
          }
          .tag-list {
            width: 100%;
            height: 25px;
            @include flex;
            margin: 5px 0px;
            flex-wrap: wrap;
            justify-content: start;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            margin-bottom: 10px;
            .tag-item {
              margin: 5px 10px 5px 0px;
              font-size: 16px;
              @include flex;
            }
            .tag-item-pretend {
              opacity: 0.5;
              font-weight: bold;
              margin-right: 3px;
            }
            .tag-iten-text {
              @include flex;
            }
          }
          .tag-list:hover {
            flex-wrap: wrap;
          }
          .blog-time {
            height: 28px;
            display: block;
            line-height: 28px;
            border-radius: 28px;
            opacity: 0.6;
            // border: 1px solid #d1d1d1;
            // padding: 0px 15px;
            // background: #9999992b;
            width: fit-content;
            text-align: center;
            font-size: 17px;
            @include flex;
            .el-icon {
              margin-right: 5px;
            }
          }
        }
      }
      .blog-item:hover {
        transform: scale(1.03);
      }
    }
  }
  .blog-sort-item-index {
    opacity: 0.5;
    position: absolute;
    top: 16%;
    right: 31px;
    font-style: italic;
    font-size: 68px;
  }
}
</style>
