<!--
 * @Description: 博客首页 -- 作者信息
-->
<template>
  <div class="blog-user-component">
    <el-row class="blog-user-top">
      <el-col :span="8">
        <el-avatar size="large" :src="url" />
      </el-col>
      <el-col :span="16">
        <div>{{ userInfo.nickName }}</div>
        <div style="margin-top: 5px; font-size: 13px">
          入站时间：{{ formateDate('YY-MM', new Date(userInfo.createTime)) }}
        </div>
      </el-col>
    </el-row>
    <el-row class="blog-user-count">
      <el-col :span="8">
        <div>文章</div>
        <div class="number">{{ userInfo.blogCount || 0 }}</div>
      </el-col>
      <el-col :span="8">
        <div>分类</div>
        <div class="number">{{ userInfo.typeCount || 0 }}</div>
      </el-col>
      <el-col :span="8"
        ><div>标签</div>
        <div class="number">{{ userInfo.tagCount || 0 }}</div></el-col
      >
    </el-row>
    <el-row class="blog-user-bt">
      <el-button size="small" round>私信</el-button>
      <el-button size="small" round>关注</el-button>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import useUserStore from '@/store/modules/user';
import { getUserById } from '@/api/user';
import { countByUserId } from '@/api/tag';
import { countBlogById } from '@/api/blog';
import { countBlogType } from '@/api/type';
import { formateDate } from '@/utils/date';

const userStore = useUserStore();
const url = ref(require('@/assets/images/cat-star.jpg'));
const userName = ref('Tom');
const userPosition = ref('Cat');
const userLocation = ref('Tom And Jerry');
const arriveAge = ref('1');
const userInfo = ref({} as any);

async function getUserInfo(userId: any) {
  const { code, msg, data } = (await getUserById(userId)) as any;
  if (code === 200 && data) {
    Object.assign(userInfo.value, data);
  }
}

async function getTagCount(userId: any) {
  const { code, msg, data } = (await countByUserId(userId)) as any;
  if (code === 200 && data) {
    userInfo.value.tagCount = data;
  }
}

async function getBlogCount(userId: any) {
  const { code, msg, data } = (await countBlogById({ userId })) as any;
  if (code === 200 && data) {
    userInfo.value.blogCount = data;
  }
}

async function getTypeCount(userId: any) {
  const { code, msg, data } = (await countBlogType({ userId })) as any;
  if (code === 200 && data) {
    userInfo.value.typeCount = data;
  }
}

onMounted(() => {
  getUserInfo(userStore.userId);
  getTagCount(userStore.userId);
  getTypeCount(userStore.userId);
  getBlogCount(userStore.userId);
});
</script>
<style lang="scss">
.blog-user-component {
  padding: 0px 0px 25px 0px;
  .user-image {
    width: 60px;
    height: 60px;
    border-radius: 30px;
    color: #fff;
    margin: 0 auto;
    margin-left: 10px;
  }
  .el-row {
    padding: 20px 20px 0px 20px;
  }
  .el-col {
    color: #fff;
  }
  .number {
    margin-top: 10px;
  }
  .blog-user-bt {
    display: flex;
    justify-content: space-between;
    .el-button--small.is-round {
      max-width: 120px;
      border-radius: 8px;
      width: 80px;
      min-width: 80px;
      background: transparent;
      border-color: #9d9d9d;
      color: #fff;
      i {
        font-size: 20px;
      }
    }
  }
  .blog-user-top {
    display: flex;
    align-items: center;
  }
}
</style>
