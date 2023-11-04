<!--
 * @Description: 相册
-->
<template>
  <div class="page-main equipment-main">
    <div class="equipment-header animated bounceInDown">
      <!-- <div class="equipment-header-img" :src="require('@/assets/images/bk-1.jpg')"></div> -->
      <video
        :src="require('@/assets/images/video_1800042575.mp4')"
        class="equipment-header-video"
        autoplay
        loop
        muted
      ></video>
      <span class="equipment-header-top">装备</span>
      <span class="equipment-header-center">科技是第一生产力</span>
      <span class="equipment-header-bottom">但是穷</span>
    </div>

    <div class="equipment-center">
      <div class="equipment-item c-right" :class="getAnimate(i)" v-for="(item, i) in equipmentList">
        <img class="equipment-item-cover" :src="item.url" />
        <span class="equipment-item-name">{{ item.equipmentName }}</span>
        <span class="equipment-item-type">{{ item.equipmentType }}</span>
        <span class="equipment-item-instoction">{{ item.introduction }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
const equipmentList = ref([
  {
    equipmentName: 'ROG幻16',
    equipmentType: 'i9-13900H 16G 1T RTX4060 2.5K 240Hz P3广色域',
    introduction: 'ROG幻16 2023 第13代英特尔酷睿i9 16英寸 星云屏 设计师轻薄高性能游戏本笔记本电脑',
    url: 'https://fc1tn.baidu.com/it/u=453693990,4223342416&fm=202&mola=new&crop=v1'
  },
  {
    equipmentName: 'ROG 游侠 RX',
    equipmentType: '光学机械键盘',
    introduction:
      '搭载 ROG RX 光学机械轴，具备环绕式 Aura Sync RGB 灯效、IP57 防水防尘、USB 2.0 端口以及铝合金上盖',
    url: 'https://static.asus.com.cn/store/album/images/RYLWNRC4F7LYQC/18155357974854.jpg'
  },
  {
    equipmentName: 'ROG 月刃',
    equipmentType: '游戏鼠标',
    introduction: '短刃相接 轻者胜  人体工学 16000DPI 轻约62g 近乎0延迟传感器 lol吃鸡神器',
    url: 'https://static.asus.com.cn/store/album/images/W4THA5LIVOWTB1/09233151101549.jpg'
  },
  {
    equipmentName: 'FreeBuds 5',
    equipmentType: '冰霜银',
    introduction: '超磁感澎湃单元，半入耳舒适降噪，超级快充，陶瓷白、冰霜',
    url: 'https://res.vmallres.com/pimages//uomcdn/CN/pms/202303/gbom/6941487277452/428_428_C47024E37E4035D0C66072F6A17E1DB2mp.png'
  }
] as any);

function getAnimate(i: any) {
  if (i % 4 === 0) {
    return 'animated-0s5';
  }
  if (i % 4 === 1) {
    return 'animated';
  }
  if (i % 4 === 2) {
    return 'animated-1s5';
  }
  if (i % 4 === 3) {
    return 'animated-2s';
  }
  return 'animated';
}
</script>
<style lang="scss">
@include theme() {
  // <div class="page-main equipment-main">
  //   <div class="equipment-header">
  //     <el-image class="equipment-header-img"></el-image>
  //   </div>
  //   <div class="equipment-center">
  //     <div class="equipment-item" v-for="item in equipmentList">
  //       <el-image class="equipment-item-cover"></el-image>
  //       <span class="equipment-item-name">{{ item.equipmentName }}</span>
  //       <span class="equipment-ite-date">{{ item.equipmentType }}</span>
  //       <span class="equipment-item-instoction">{{ item.introduction }}</span>
  //     </div>
  //   </div>
  // </div>
  .equipment-main {
    @include flex-column;
    justify-content: start;
    .equipment-header {
      height: 26rem;
      width: 100%;
      border-radius: 20px;
      box-shadow: get('box-shadow');
      position: relative;

      .equipment-header-video {
        opacity: 0.95;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        background: url('@/assets/images/cyberpunk-city.jpg');
        background-size: left 28% / cover no-repeat;
        object-fit: cover;
      }
      margin-bottom: 40px;
      // color: get('re-font-color');
      color: white;
      .equipment-header-top {
        position: absolute;
        top: 10px;
        left: 15px;
      }
      .equipment-header-center {
        position: absolute;
        top: 47px;
        left: 14px;
        font-size: 35px;
        font-weight: bold;
      }
      .equipment-header-bottom {
        position: absolute;
        bottom: 14px;
        left: 16px;
      }
    }
    .equipment-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      @media screen and (max-width: 1200px) {
        .equipment-item-content-item {
          width: calc(50% - 12px);
        }
      }

      .equipment-item {
        width: calc(25% - 20px);

        border-radius: 20px;
        margin: 10px 0px;
        // opacity: 0.9;
        box-shadow: get('box-shadow');
        background: get('background');
        position: relative;
        color: get('font-color');
        height: 340px;
        position: relative;

        @include flex-column;
        justify-content: start;
        .equipment-item-cover {
          width: 100%;
          height: 210px;
          border-radius: 20px 20px 0px 0px;
          object-fit: cover;
          object-position: center;
          background-repeat: no-repeat;
          background: white;
        }
        .equipment-item-name {
          font-weight: bold;
          font-size: 20px;
          margin: 5px;
        }
        .equipment-item-type {
          margin-bottom: 0px;
          opacity: 0.6;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
          width: calc(100% - 30px);
        }
        .equipment-item-instoction {
          overflow: hidden;
          display: block;
          width: calc(100% - 20px);
          margin: 10px;
          display: -webkit-box;
          overflow: hidden;
          text-overflow: ellipsis;
          -webkit-box-orient: vertical;
          -webkit-line-clamp: 2;
        }
      }
      .equipment-item:hover {
        transform: scale(1.03);
      }
    }
  }
}
</style>
