<!--
 * @Description:  博客分类
-->
<template>
  <div class="blog-type page-main">
    <div class="page-left animated bounceInLeft">
      <div class="type-list">
        <div
          class="type-item"
          :class="item.isActive ? 'is-active' : ''"
          v-for="item in typeList"
          @click="getList(item)"
        >
          <span class="type-item-prefix">#</span>
          <span class="type-item-content">{{ item.typeName }}</span>
          <span class="type-item-count">{{ '5' }}</span>
        </div>
      </div>
      <div class="blog-list">
        <div class="blog-item" v-for="item in blogList" @click="toDetail(item)">
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
      <Pagination
        :page="pageData.page"
        :page-size="pageData.pageSize"
        :total="total"
        :on-page-change="getBlogList"
        :showSizes="true"
        :pageSizeList="pageData.pageSizeList"
        :on-page-size-change="getBlogList"
        class="pagi page-content"
      ></Pagination>
    </div>
    <div class="page-right animated bounceInRight" style="width: 300px">
      <BlogUserCard></BlogUserCard>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTypeTree } from '@/api/type';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import useUserStore from '@/store/modules/user';
import BlogUserCard from './components/blogUserCard.vue';
import Pagination from '@/components/pagination/index.vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const userStore = useUserStore();
const pageData = ref({
  pageTotal: 0,
  page: 1,
  pageSize: 10,
  pageSizeList: [10, 20, 30]
} as any);
const total = ref(100 as any);
const typeList = ref([
  {
    typeName: '前端',
    key: '1',
    isActive: false,
    children: [
      { typeName: 'html', key: '1-1' },
      { typeName: 'css', key: '1-2' },
      { typeName: 'javascript', key: '1-3' }
    ]
  },
  {
    typeName: '后端',
    key: '2',
    isActive: false,
    children: [
      { typeName: 'springboot', key: '2-1' },
      { typeName: 'tomcat', key: '2-2' }
    ]
  }
]);
const blogList = ref([]) as any;
const isShowView = ref([] as any);

function clickFoucus(data: any) {
  let key = data.typeId;
  isShowView.value = [];
  isShowView.value[key] = true;
}

async function getTypeTree(userId: any) {
  const { code, msg, data } = (await listTypeTree({ userId })) as any;
  if (code === 200 && data) {
    // typeList.value = data;
  }
}

function getList(item: any) {
  if (item) {
    typeList.value.forEach((type: any) => {
      type.isActive = false;
    });
    item.isActive = true;
  }
  getBlogList();
}

async function getBlogList() {
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

// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

onMounted(() => {
  getTypeTree(userStore.userId);
  getBlogList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-type {
    display: flex;
    .pagi {
      position: absolute;
      bottom: 10px;
      left: 0px;
    }
    .page-left {
      width: calc(100% - 320px);
      padding: 20px;
      border-radius: 15px;
      background: get('background');
      box-shadow: get('box-shadow');
      position: relative;
    }
    .page-right {
      width: 300px;
      margin-left: 20px;
    }
    .header {
      font-weight: bold;
    }
    .type-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      width: 100%;
      .type-item {
        @include flex;
        padding: 8px 25px;
        margin: 5px 10px;
        border-radius: 10px;
        color: #363636;
        font-size: 18px;
        background: white;
        // box-shadow: get('box-shadow');
        border: 1px solid #d1d1d1;
        .type-item-prefix {
          font-size: 22px;
          opacity: 0.4;
          font-weight: bold;
        }
        .type-item-content {
          margin-left: 4px;
          font-weight: bold;
        }
        .type-item-count {
          background: #f7f7f9;
          padding: 0px 5px;
          border-radius: 8px;
          text-align: center;
          min-width: 21px;
          display: inline-block;
          margin-left: 6px;
        }
      }
      .type-item:hover,
      .type-item.is-active {
        background: get('bk');
        color: white;
        .type-item-count {
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
