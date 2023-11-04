<!--
 * @Description: 文章列表
-->
<template>
  <div class="blog-news">
    <div class="blog-news-item" v-for="news in newsList">
      <el-image class="news-image" :src="news.url"></el-image>
      <div class="identification-container">
        <i class="el-icon-date"></i>
        <span style="font-size: 12px; color: darkgrey">{{ news.date }}</span>
        <span style="font-size: 15px">{{ news.type }}</span>
      </div>
      <div class="news-title">
        <span style="font-size: 16px; font-weight: bold">垂直水平居中方法汇总</span>
      </div>
      <!-- <div class="news-introduction">
        <span>垂直水平居中方法汇总</span>
      </div> -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { formateDate } from '@/utils/date.ts';
import { ref } from 'vue';
const newsList = ref([
  {
    // url: require('@/assets/images/app-background1.webp'),
    date: formateDate('YY-MM-dd hh:mm', new Date()),
    type: '前端',
    url: '/img/2cf465cf-6114-4319-ac33-67bbec0983e2.jpeg'
  },
  {
    // url: require('@/assets/images/app-background1.webp'),
    date: formateDate('YY-MM-dd hh:mm', new Date()),
    type: '前端',
    url: '/img/2cf465cf-6114-4319-ac33-67bbec0983e2.jpeg'
  }
] as any);
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-news-item {
    margin-bottom: 10px;
    text-align: left;
    border-radius: 5px;
    background: get('background');
    color: get('font-color');
    padding-bottom: 7px;
    // border-radius: 10px;
    box-shadow: get('box-shadow');
    color: get('font-color');
  }
}

.blog-news {
  .news-title {
    margin: 5px 0 0 10px;
    font-size: 15px;
  }
  .el-image {
    height: 260px;
  }
}

.news-introduction {
  margin: 5px;
  font-size: 13px;
}
.news-image {
  border-radius: 5px;
}
</style>
