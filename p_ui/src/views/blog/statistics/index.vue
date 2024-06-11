<!--
 * @Description: 数据统计 
-->
<template>
  <div class="page-main statistic-main">
    <div class="total">
      <div class="total-col">
        <el-statistic title="文章数" :value="blogNumValue" />
      </div>
      <div class="total-col">
        <el-statistic title="相册数" :value="albumNumValue" />
      </div>
      <div class="total-col">
        <el-statistic title="分类数" :value="typeNumValue" />
      </div>
      <div class="total-col">
        <el-statistic title="随笔数" :value="essayNumValue" />
      </div>
      <div class="total-col">
        <el-statistic title="网站更新数" :value="updateLogNumValue" />
      </div>
    </div>
    <div class="chart">
      <h3>博客统计图</h3>
      <blogCountChart style="width: 100%; height: 100%" />
    </div>
    <div class="chart">
      <h3>博客统计图</h3>
      <blogTypeRadar style="width: 100%; height: 100%" />
    </div>
    <div class="chart" style="height: 300px">
      <h3>博客统计图</h3>
      <blogTypeChart style="width: 100%; height: 100%" />
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import blogCountChart from './components/blogCountChart.vue';
import blogTypeRadar from './components/blogTypeRadar.vue';
import blogTypeChart from './components/blogTypeChart.vue';
import { countBlog } from '@/api/blog.ts';
import { countAlbum } from '@/api/album.ts';
import { countEssay } from '@/api/essay.ts';
import { countMusic } from '@/api/music.ts';
import { countType } from '@/api/type.ts';
import { countUpdateLog } from '@/api/updateLog.ts';
import { useTransition } from '@vueuse/core';
const blogNum = ref(0 as any);
const blogNumValue = useTransition(blogNum);
const albumNum = ref(0 as any);
const albumNumValue = useTransition(albumNum);
const essayNum = ref(0 as any);
const essayNumValue = useTransition(essayNum);
const musicNum = ref(0 as any);
const musicNumValue = useTransition(musicNum);
const typeNum = ref(0 as any);
const typeNumValue = useTransition(typeNum);
const updateLogNum = ref(0 as any);
const updateLogNumValue = useTransition(updateLogNum);

async function blogCount() {
  const { code, data } = (await countBlog({})) as any;
  if (code == 200) {
    blogNum.value = data;
  }
}

async function albumCount() {
  const { code, data } = (await countAlbum()) as any;
  if (code == 200) {
    albumNum.value = data;
  }
}

async function essayCount() {
  const { code, data } = (await countEssay()) as any;
  if (code == 200) {
    essayNum.value = data;
  }
}

async function musicCount() {
  const { code, data } = (await countMusic()) as any;
  if (code == 200) {
    musicNum.value = data;
  }
}

async function typeCount() {
  const { code, data } = (await countType()) as any;
  if (code == 200) {
    typeNum.value = data;
  }
}

async function updateLogCount() {
  const { code, data } = (await countUpdateLog()) as any;
  if (code == 200) {
    updateLogNum.value = data;
  }
}

function init() {
  blogCount();
  albumCount();
  essayCount();
  musicCount();
  typeCount();
  updateLogCount();
}

onMounted(() => {
  init();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .statistic-main {
    background: get('background');
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    border-radius: 15px;
  }
  .total {
    border: 2px solid get('bk');
  }
}
.total {
  width: calc(50%);
  height: 150px;
  margin-top: 80px;
  border-radius: 15px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 0px 5%;
  .total-col {
    :deep(.el-statistic__head) {
      font-weight: bold;
      font-size: 20px;
      margin-bottom: 30px;
    }
    :deep(.el-statistic__content) {
      font-weight: bold;
      font-size: 18px;
    }
  }
}
.chart {
  height: 200px;
  width: 70%;
  margin: 30px;
}
</style>
