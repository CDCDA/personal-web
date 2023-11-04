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
          <span class="count-month">{{ item.month }}</span>
          <span class="count-year">{{ item.year }}</span>
        </div>
        <div class="count-down">
          <span class="count-count">{{ item.count }}</span>
          <span class="count-unit">篇</span>
        </div>
      </div>
    </div>
    <div class="c-dotted-line"></div>
    <div class="count-total">
      <div class="blog-count">
        <span
          ><el-icon><Memo /></el-icon>文章数：</span
        ><span>{{ '14' }}</span>
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
import { monthDayDiff } from '@/utils/date';
// import { listcountTree } from '@/api/count';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const countList = ref([
  {
    month: '11月',
    year: '2023',
    count: 1
  },
  {
    month: '10月',
    year: '2023',
    count: 4
  },
  {
    month: '9月',
    year: '2023',
    count: 2
  },
  {
    month: '8月',
    year: '2023',
    count: 4
  }
]);

async function getcountTree(userId: any) {
  userId;
  // const { code, msg, data } = (await listcountTree({ userId })) as any;
  // if (code === 200 && data) {
  //   // countList.value = data;
  // }
}

onMounted(() => {
  getcountTree(userStore.userId);
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
      font-size: 19px;
      font-weight: bold;
      .el-icon {
        margin: 8px;
        font-size: 22px;
      }
    }
    .count-list {
      @include flex;
      justify-content: space-between;
      flex-wrap: wrap;
      .count-item {
        padding: 8px 15px;
        border-radius: 8px;
        color: get('font-color');
        border: 1px solid #b9b8b8;
        margin: 8px;
        font-size: 18px;
        width: calc(50% - 50px);
        .count-up {
          margin-bottom: 10px;
          .count-month {
            margin-right: 8px;
          }
        }
        .count-down {
          .count-count {
            font-size: 24px;
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
        font-size: 18px;
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
