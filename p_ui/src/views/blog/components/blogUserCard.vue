<!--
 * @Description: 作者信息卡
-->
<template>
  <div class="blog-user-card c-card">
    <div class="filter-bg">
      <div class="user-header">熬夜成仙吧</div>
      <div class="user-avatar">
        <el-avatar :src="userInfo.avatar"></el-avatar>
        <div class="introduction">我是一名全栈开发工程师</div>
      </div>
      <div class="user-footer">
        <div class="footer-leftSide">
          <div class="user-name">{{ userInfo.nickName }}</div>
          <el-tooltip :content="userInfo.introduction">
            <div class="user-motto no-wrap">{{ userInfo.introduction }}</div>
          </el-tooltip>
        </div>
        <div class="footer-rightSide">
          <svg-icon iconName="weixin" class="footer-icon weixin" @click="toQrCode"></svg-icon>
          <svg-icon iconName="github" class="footer-icon github"></svg-icon>
        </div>
      </div>
    </div>
  </div>

  <el-dialog class="weixin-qrcode" v-model="dialogVisible" width="300" :modal="false">
    <c-image :src="weixinQrcodeUrl"></c-image>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import useUserStore from '@/store/modules/user';
import { getUserById } from '@/api/user';

const userStore = useUserStore();
const userInfo = ref({} as any);

const weixinQrcodeUrl = new URL(
  '@/assets/images/3ca475b86ed6c381f709391a8edf54af.jpg',
  import.meta.url
).href;

const dialogVisible = ref(false);

// 获取用户信息
async function getUserInfo(userId: any) {
  const { code, msg, data } = (await getUserById(userId)) as any;
  if (code === 200 && data) {
    Object.assign(userInfo.value, data);
  }
}

function toQrCode() {
  dialogVisible.value = true;
}

onMounted(() => {
  getUserInfo(userStore.userId);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .filter-bg {
    width: calc(100% - 40px);
    height: 100%;
    background: transparent;
    backdrop-filter: blur(5px);
    padding: 20px;
    @include flex-column;
  }
  .blog-user-card {
    transition: cubic-bezier(0.075, 0.82, 0.165, 1) 0.4s;
    overflow: hidden;
    padding: 0px !important;
    width: 300px !important;
    background: url('http://111.229.144.36:8008/Ruins.jpg') center 28% / cover no-repeat !important;

    color: get('re-font-color') !important;
    .user-header {
      height: 24px;
      width: fit-content;
      padding: 2px 20px;
      border-radius: 15px;
      font-size: 15px;
      margin: 25px 0px;
      color: get('re-font-color');
      cursor: pointer;
      @include flex;
      background: rgba(255, 255, 255, 0.2);
    }
    .user-header:hover {
      transform: scale(1.05);
      color: get('font-color');
      background: white;
      transition: ease-in-out 0.3s;
    }
    .introduction {
      position: absolute;
      opacity: 0;
      width: 150px;
      height: 150px;
      top: 0px;
      left: 0px;
      @include flex;
    }
    .user-avatar {
      height: 150px;
      margin: 10px 0px 0px 0px;
      width: 150px;
      position: relative;
      .el-avatar {
        width: 130px;
        height: 130px;
        position: absolute;
        bottom: 15px;
        left: calc(50% - 65px);
        animation: huxi_light 4s infinite;
        border: solid 5px get('re-font-color');
        transition: cubic-bezier(0.075, 0.82, 0.165, 1) 0.4s;
      }
    }
    .user-footer {
      height: 80px;
      width: 100%;
      display: flex;
      justify-content: space-around;
      align-items: center;

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
      }
    }
  }
  .blog-user-card:hover {
    .el-avatar {
      height: 0px;
      width: 0px;
      left: calc(50%);
      bottom: 15px;

      animation: none;
      border: none;
    }
    .introduction {
      opacity: 1;
      transition: cubic-bezier(0.075, 0.82, 0.165, 1) 0.4s;
    }
  }
}
</style>
