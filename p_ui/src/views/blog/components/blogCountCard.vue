<!--
 * @Description: 博客统计卡
-->
<template>
  <div class="blog-count-card c-card">
    <div class="card-header">
      <el-icon><Histogram /></el-icon><span class="count-nmae">统计</span>
    </div>
    <div class="count-list">
      <div class="count-item" v-for="item in countList">
        <div class="count-up">
          <span class="count-month">{{ item.month }}月</span>
          <span class="count-year">{{ item.year }}</span>
        </div>
        <div class="count-down">
          <span class="count-count">{{ item.total }}</span>
          <span class="count-unit">篇</span>
        </div>
      </div>
    </div>
    <div class="c-dotted-line"></div>
    <div class="count-total">
      <div class="blog-count">
        <span
          ><el-icon><Memo /></el-icon>文章数：</span
        ><span>{{ total }}</span>
      </div>
      <div class="station-count">
        <span
          ><el-icon><Timer /></el-icon>建站天数：</span
        ><span>{{ '118天' }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { countBlogByDateRange, countBlog } from '@/api/blog.ts';
import useUserStore from '@/store/modules/user';
import { useLazyAppear } from '@/utils/lazy';
const total = ref(0 as any);
const userStore = useUserStore();
const countList = ref([] as any);

async function getBlogCount() {
  const { code, msg, data } = (await countBlog({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    total.value = data;
  }
}

async function getBlogDateRangeCount() {
  const { code, msg, data } = (await countBlogByDateRange({
    userId: userStore.userId
  })) as any;
  if (code === 200 && data) {
    data.forEach((e: any, i: any) => {
      if (i < 4) {
        let times = e.createTime.split('-');
        let year = times[0];
        let month = times[1];
        month[0] == '0' ? (month = month.substring(1)) : '';
        countList.value.push({ year, month, total: e.total });
      }
    });
  }
}

onMounted(() => {
  getBlogCount();
  getBlogDateRangeCount();
  useLazyAppear(document.querySelector('.blog-count-card') as any);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-count-card {
    text-align: left;
    .card-header {
      @include flex;
      width: 100%;
      justify-content: start;
      font-size: 0.9rem;
      font-weight: bold;
      .el-icon {
        margin: 8px;
        font-size: 1rem;
      }
    }
    .count-list {
      @include flex;
      justify-content: space-between;
      flex-wrap: wrap;
      .count-item {
        padding: 8px 10px;
        border-radius: 8px;
        color: #363636;
        border: 1px solid get('border-color');
        margin: 8px;
        font-size: 16px;
        width: calc(50% - 40px);
        .count-up {
          margin-bottom: 10px;
          .count-month {
            margin-right: 8px;
          }
        }
        .count-down {
          .count-count {
            font-size: 22px;
            font-weight: bold;
            margin-right: 5px;
          }
        }
      }
      .count-item:hover {
        background: get('bk');
        color: get('re-font-color');
      }
    }
    .count-total {
      @include flex-column;
      .blog-count,
      .station-count {
        width: 80%;
        @include flex;
        font-size: 0.9rem;
        margin: 8px;
        justify-content: space-between;
        & > span {
          @include flex;
          .el-icon {
            margin-right: 8px;
          }
          .el-icon-svg {
            margin-top: 3px;
          }
        }
      }
    }
  }
}
</style>
