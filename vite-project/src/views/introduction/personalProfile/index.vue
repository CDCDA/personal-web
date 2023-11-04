<!--
 * @Description: 个人信息
-->
<template>
  <div class="page-main personal-profile-main">
    <PersonalInfo class="bounceInDown animated"></PersonalInfo>
    <div class="ps-welcome c-left animated">
      <span>欢迎来到我的网站<el-image :src="require('@/assets/svg/welcome.svg')"></el-image></span>
      <span>我叫CCCC</span>
      <span>是一名全栈开发工程师</span>
    </div>
    <div class="ps-timeline c-right animated">
      <TimeLine></TimeLine>
    </div>
    <div class="ps-info">
      <div class="ps-info-left c-left animated">
        <div class="title">技术栈</div>
        <div class="text">感觉脑子不是自己的o_O</div>
        <technology-stack-card></technology-stack-card>
      </div>
      <div class="ps-info-right">
        <div class="ps-info-map c-right animated">
          <el-image class="map-img"></el-image>
          <div class="map-tip">现居 福州</div>
        </div>
        <div class="ps-info-life c-right animated">
          <div class="life-college">生于<span>1999</span></div>
          <div class="life-profession">福州大学<span>计算机科学与技术专业</span></div>
          <div class="life-education">目前<span>在职。。。</span></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import PersonalInfo from './components/personalInfo.vue';
import TechnologyStackCard from './components/technologyStackCard.vue';
import TimeLine from '@/components/timeLine/index.vue';
</script>
<style lang="scss" scoped>
@include theme() {
  .personal-profile-main {
    @include flex-column;
    justify-content: start;
    .ps-welcome {
      width: 100%;
      height: 300px;
      @include flex-column;
      justify-content: space-evenly;
      background: get('background');
      box-shadow: get('box-shadow');
      margin: 20px;
      border-radius: 20px;
      font-weight: bold;

      span:nth-child(1) {
        @include flex;
        font-size: 30px;
        background-image: linear-gradient(to right, #f0c27b, #4b1248);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        .el-image {
          width: 40px;
          height: 40px;
          margin-left: 10px;
        }
      }
      span:nth-child(2) {
        font-size: 30px;
        color: get('font-color');
      }
      span:nth-child(3) {
        font-size: 35px;
      }
    }
    .ps-timeline {
      width: 100%;
      height: 450px;
      box-shadow: get('box-shadow');
      border-radius: 20px;
      overflow: hidden;
      margin-bottom: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .ps-info {
      width: 100%;
      height: 450px;
      @include flex;
      .ps-info-left {
        width: 50%;
        height: calc(100% - 40px);
        background: get('background');
        box-shadow: get('box-shadow');
        border-radius: 20px;
        margin-right: 20px;
        overflow: hidden;
        padding: 20px 0px;
        position: relative;
        .title {
          text-align: left;
          margin-left: 33px;
        }
        .text {
          text-align: left;
          margin-left: 26px;
          font-size: 37px;
          margin-top: 2px;
          font-weight: bold;
          margin-bottom: 37px;
        }
      }
      .ps-info-right {
        width: calc(50% - 20px);
        height: 100%;
        @include flex-column;
        justify-content: start;

        .ps-info-map {
          height: calc(100% - 170px);
          width: 100%;
          background: get('background');
          box-shadow: get('box-shadow');
          border-radius: 20px;
          margin-bottom: 20px;
          position: relative;
          overflow: hidden;
          .map-img {
            height: 100%;
            width: 100%;
          }
          .map-tip {
            height: 70px;
            width: 100%;
            color: get('font-color');
            position: absolute;
            bottom: 0px;
            background: white;
            backdrop-filter: blur(20px);
            @include flex;
            justify-content: start;
            padding-left: 20px;
            font-size: 22px;
          }
        }
        @keyframes tip-up {
          0% {
            bottom: -70px;
          }
          100% {
            bottom: 0px;
          }
        }
        @keyframes tip-down {
          0% {
            bottom: 0px;
          }
          100% {
            bottom: -70px;
          }
        }
        .ps-info-map:hover {
          .map-tip {
            animation: tip-down 0.4s forwards linear;
          }
          .map-img {
            transform: scale(1.2);
            transition: 1s ease-in-out;
          }
        }
        .ps-info-map:not(:hover) {
          .map-tip {
            animation: tip-up 0.4s forwards linear;
          }
          .map-img {
            transform: scale(1);
            transition: 1s ease-in-out;
          }
        }
        .ps-info-life {
          height: 150px;
          width: 100%;
          @include flex;
          justify-content: space-evenly;
          background: get('background');
          box-shadow: get('box-shadow');
          border-radius: 20px;
          .life-college,
          .life-profession,
          .life-education {
            height: 100%;
            @include flex-column;
            span {
              font-size: 30px;
              font-weight: bold;
              margin-top: 10px;
              color: get('bk');
            }
          }
        }
      }
    }
  }
}
</style>
