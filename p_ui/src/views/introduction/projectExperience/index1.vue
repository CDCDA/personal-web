<!--
 * @Description: 项目经历
-->
<template>
  <div class="page-main project-main">
    <div class="project-main-left">
      <div class="title">项目经历</div>
      <div class="intro">记录工作以来做过的一些项目</div>
      <div class="hint">点击右侧项目图片查看详细信息</div>
      <div class="link flex-start">
        <el-button class="btn-purple">个人信息</el-button
        ><el-button class="btn-white">联系方式</el-button>
      </div>
      <div class="tips">
        <p class="tips-line">
          <el-icon><Warning /></el-icon>关联技术栈
        </p>
        <p class="tips-line">
          <el-icon><Warning /></el-icon>总体功能
        </p>
        <p class="tips-line">
          <el-icon><Warning /></el-icon>负责模块
        </p>
        <p class="tips-line">
          <el-icon><Warning /></el-icon>由于是公司项目，不会介绍得太详细
        </p>
      </div>
    </div>
    <div class="project-main-right">
      <swiper :itemList="projectList" @itemClick="itemClick" />
    </div>
    <projectInfo ref="projectInfoEl"></projectInfo>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import swiper from '@/components/swiper/index.vue';
import projectInfo from './components/projectInfo.vue';
const projectInfoEl = ref(null) as any;
import projectList from './projectData';
function itemClick(item: any) {
  projectInfoEl.value.open();
}

onMounted(() => {});
</script>
<style lang="scss" scoped>
.project-main.page-main {
  display: flex;
  align-items: center;
  border-radius: 0 !important;
  position: fixed !important;
  width: 100vw !important;
  height: 100vh !important;
  margin: 0 !important;
  max-width: none !important;
  background: linear-gradient(75deg, #1e81c0, #2b20a0 39%, #131951) !important;
  .project-main-left {
    height: 100%;
    width: 35%;
    padding-left: 15%;
    margin-top: 6rem;
    color: white;
    text-align: left;
    .title {
      margin-top: 12vh;
      font-size: 2rem;
      font-weight: bold;
      margin-bottom: 2rem;
    }
    .intro {
      margin-bottom: 5rem;
    }
    .tips {
      .tips-line {
        display: flex;
        align-items: center;
        justify-content: start;
        font-size: 0.9rem;
        .el-icon {
          margin-right: 5px;
        }
      }
    }
    .hint {
      font-size: 1.5rem;
      margin-bottom: 2rem;
    }
    .link {
      margin: 6rem 0;
    }
  }
  .project-main-right {
    height: 100%;
    width: 40%;
    padding-right: 10%;
    margin-top: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
