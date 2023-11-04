<!--
 * @Description: 作者信息卡
-->
<template>
  <div class="blog-user-component c-card">
    <div class="user-header">熬夜成仙吧</div>
    <div class="user-avatar">
      <el-avatar :src="require('@/assets/images/cat-star.jpg')"></el-avatar>
      <div class="introduction">我是一名前端开发工程师</div>
    </div>
    <div class="user-footer">
      <div class="footer-leftSide">
        <div class="user-name">CCCC</div>
        <div class="user-motto">随便写句话</div>
      </div>
      <div class="footer-rightSide">
        <div class="footer-icon weixin"></div>
        <div class="footer-icon github"></div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import useUserStore from '@/store/modules/user';
import { getUserById } from '@/api/user';
import { countByUserId } from '@/api/tag';
import { countBlogById } from '@/api/blog';
import { countBlogType } from '@/api/type';

const userStore = useUserStore();
const userInfo = ref({} as any);

// 获取用户信息
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
<style lang="scss" scoped>
@include theme() {
  .blog-user-component {
    @include flex-column;
    .user-header {
      height: 24px;
      width: auto;
      padding: 2px 20px;
      border-radius: 15px;
      font-size: 15px;
      @include flex;
      margin: 25px 0px;
      color: white;
      cursor: pointer;
      background: get('bk');
    }
    .user-header:hover {
      transform: scale(1.02);
    }
    .user-avatar {
      height: 155px;
      margin: 10px 0px 0px 0px;
      width: 100%;
      .el-avatar {
        width: 130px;
        height: 130px;
      }
    }
    .user-footer {
      height: 80px;
      width: 100%;
      display: flex;
      justify-content: space-around;
      align-items: center;
      color: get('font-color');
      margin: 20px 0px;
      .footer-leftSide {
        @include flex-column;
        align-items: start;
        .user-name {
          font-weight: bold;
          font-size: 22px;
          margin: 2px 0px;
        }
        .user-motto {
          font-size: 16px;
          float: left;
          margin: 0px;
          margin: 2px 0px;
        }
      }
      .footer-rightSide {
        @include flex;
        margin-right: -10px;
        .footer-icon {
          width: 40px;
          height: 40px;
          margin: 10px;
          cursor: pointer;
        }
        .footer-icon:hover {
          filter: invert(1);
        }
        .weixin {
          background: url('@/assets/svg/weixin.svg') no-repeat;
          background-size: cover;
          filter: hue-rotate(90deg);
        }
        .github {
          background: url('@/assets/svg/github.svg') no-repeat;
          background-size: cover;
          filter: hue-rotate(90deg);
        }
      }
    }
  }
  .blog-user-component:hover {
    .el-avatar {
      display: none;
    }
    .introduction {
      height: 155px;
      width: 100%;
      display: block;
    }
  }
}
</style>
