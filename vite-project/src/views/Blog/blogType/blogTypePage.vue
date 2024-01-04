<!--
 * @Description:  博客分类具体列表
-->
<template>
  <div class="blog-type-page page-main c-left">
    <div class="type-list">
      <div
        class="type-item"
        :class="item.isActive ? 'is-active' : ''"
        v-for="item in typeList"
        @click="getList(item)"
      >
        <span class="type-item-prefix" v-if="item.typeId">#</span>
        <span class="type-item-content">{{ item.typeName }}</span>
        <span class="type-item-count">{{ item.total }}</span>
      </div>
    </div>
    <div class="c-divider" style="margin: 30px 9px"></div>
    <div class="blog-list" v-cLoading="loading">
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
            <el-icon><Calendar /></el-icon>{{ formateDate(new Date(item.createTime), 'YY-mm-dd') }}
          </div>
        </div>
        <span class="blog-sort-item-index">{{ i + 1 }}</span>
        <div class="blog-cursor-cover">
          <span class="no-wrap" style="-webkit-line-clamp: 3">{{
            item.blogAbstract || '暂时没有摘要 ╮(￣▽￣)╭'
          }}</span>
        </div>
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
import { listTotalType } from '@/api/type';
import { listBlog } from '@/api/blog';
import { ElMessage } from 'element-plus';
import useUserStore from '@/store/modules/user';
import Pagination from '@/components/pagination/index.vue';
import { formateDate } from '@/utils/date.ts';
import { useRouter } from 'vue-router';
const router = useRouter();
const userStore = useUserStore();
const loading = ref('rotate' as any);
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  typeId: null
} as any);
const total = ref(100 as any);
const typeList = ref([] as any);
const blogList = ref([]) as any;

async function getTypeList() {
  const { code, data } = (await listTotalType({})) as any;
  if (code === 200 && data) {
    typeList.value = data.list;
    let total = 0;
    typeList.value.forEach((e: any) => {
      total += e.total;
    });
    typeList.value.unshift({
      typeName: '全部',
      isActive: false,
      total
    });
    queryParams.value.typeId = router.currentRoute.value.query.typeId;
    typeList.value.forEach((e: any) => {
      if (e.typeId === queryParams.value.typeId) e.isActive = true;
    });
  }
}

function getList(item: any) {
  if (item) {
    typeList.value.forEach((type: any) => {
      type.isActive = false;
    });
    item.isActive = true;
  }
  queryParams.value.pageNum = 1;
  queryParams.value.typeId = item.typeId;
  getBlogList();
}

async function getBlogList() {
  loading.value = 'rotate';
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
    loading.value = false;
  }
}

// 博客详情
function toDetail(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}

onMounted(() => {
  getTypeList();
  queryParams.value.typeId = router.currentRoute.value.query.typeId;
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
  .blog-type-page.page-main {
    padding: 40px;
    width: calc(86% - 80px) !important;
    border-radius: 15px;
    background: get('background');
    box-shadow: get('box-shadow');
    position: relative;
    transition: all 0.5s ease-in-out;
    .pagi {
      position: absolute;
      bottom: 10px;
      left: 0px;
      padding: 0px 40px;
      width: calc(100% - 80px);
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
      margin-bottom: 10px;
      .type-item {
        @include flex;
        cursor: pointer;
        padding: 8px 15px;
        margin: 5px 10px;
        border-radius: 10px;
        color: #363636;
        font-size: 18px;
        transition: all 0.3s ease-in-out;
        border: 2px solid get('border-color');
        .type-item-prefix {
          opacity: 0.8;
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
      .type-item:active {
        transform: translateY(5px);
      }
      .type-item:hover,
      .type-item.is-active {
        background: get('border-color');
        color: white;

        // box-shadow: get('box-shadow');

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
        width: calc(50% - 20px);
        position: relative;
        height: auto;
        margin-bottom: 20px;
        margin: 7px;
        background: white;
        border-radius: 10px;
        transition: all 0.15s linear;
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
        justify-content: space-between;
        // box-shadow: get('box-shadow');
        border: 3px solid get('border-color');
        .blog-cursor-cover {
          position: absolute;
          background: get('border-color');
          width: calc(85% - 20px);
          height: calc(100% - 20px);
          padding: 10px 20px 10px 15%;
          left: 100%;
          top: 0px;
          color: white;
          display: flex !important;
          align-items: center;
          justify-content: center;
          transition: all 0.4s ease-in-out;
        }
        .blog-item-left-img {
          object-fit: cover;
          background-position: left 28%;
          transition: all 0.4s ease-in-out;
          width: 140px;
          height: 100px;
          border-radius: 8px;
          margin: 8px;
        }
        .blog-item-right {
          @include flex-column;
          width: calc(100% - 140px);
          padding: 0px 85px 0px 15px;
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
            .tag-item-text {
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
            font-weight: bold;
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
        .blog-item-left-img {
          width: 0px;
        }
        .blog-sort-item-index {
          right: calc(100% - 125px);
          z-index: 199;
          opacity: 1;
          color: white;
          justify-content: start;
        }
        // transform: scale(1.02);
        box-shadow: 0px 0px 7px 0px get('border-color');
        .blog-cursor-cover {
          left: 0px;
        }
      }
    }
  }
  .blog-sort-item-index {
    opacity: 0.5;
    position: absolute;
    top: 13%;
    right: 30px;
    font-style: italic;
    font-size: 68px;
    transition: all 0.4s ease-in-out;
    display: flex;
    justify-content: end;
    align-items: center;
    width: 100px;
  }
}
</style>
