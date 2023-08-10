<!--
 * @Description: 
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-26 16:56:13
 * @LastEditTime: 2023-08-05 15:08:12
-->
<template>
  <div class="blog-news">
    <div class="blog-news-item" v-for="news in newsList">
      <el-image class="news-image" :src="news.url"></el-image>
      <div class="identification-container">
        <i class="el-icon-date"></i>
        <span>{{ news.date }}</span>
        <span>{{ news.type }}</span>
      </div>
      <div class="news-title"><span>垂直水平居中方法汇总</span></div>
      <!-- <div class="news-introduction">
        <span>垂直水平居中方法汇总</span>
      </div> -->
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
const newsList = ref([
  {
    // url: require('@/assets/images/app-background1.webp'),
    date: new Date(),
    type: '前端'
  },
  {
    // url: require('@/assets/images/app-background1.webp'),
    date: new Date(),
    type: '前端'
  }
] as any);
</script>
<style lang="scss" scoped>
.blog-news {
  .news-title {
    margin: 5px 0 0 10px;
    font-size: 15px;
  }

  .blog-news-item {
    margin-bottom: 10px;
    text-align: left;
    border-radius: 5px;
    background-color: transparent;
    color: #fff;
    padding-bottom: 7px;
    // border-radius: 10px;
    box-shadow: 0 2px 12px 0 #000000;
    color: rgba(255, 255, 255, 0.6);
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
