<!--
 * @Description: 切片列表
-->
<template>
  <div class="page-main slice-main">
    <div class="slice-header" style="width: 100%; height: 30px; display: flex">
      <svg-icon
        iconName="grinding"
        style="width: 30px; height: 30px; margin-right: 15px"
      />一些小组件
    </div>
    <div class="slice-center">
      <div class="slice-item" v-for="(item, i) in sliceList" @click="toSlice(item)">
        <c-image class="slice-item-cover" :src="item.url" />
        <span class="slice-item-name">{{ item.label }}</span>
        <span class="slice-item-divider"></span>
        <span class="slice-item-instoction no-wrap">{{ item.introduction }}</span>
        <!-- <div class="instoction-cover">
          {{ item.introduction }}
        </div> -->
      </div>
    </div>
  </div>
  <router-view-dialog ref="routerDialog" :title="title"></router-view-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import routerViewDialog from '@/components/routerViewDialog/index.vue';
const router = useRouter();
const routerDialog = ref(null as any);
const title = ref(null as any);
const sliceList = ref([
  {
    label: '魔方',
    routerName: 'rubiks',
    introduction: '普通3x3动态魔方',
    url: 'http://111.229.144.36:8008/魔方.png'
  },
  {
    label: '图片旋转魔方',
    routerName: 'rotatingRubik',
    introduction: '可旋转展示6面图片的魔方',
    url: 'http://111.229.144.36:8008/图片旋转魔方.png'
  },
  {
    label: '3d菜单',
    routerName: '3dMenu',
    introduction: '3d菜单',
    url: 'http://111.229.144.36:8008/3d菜单.png'
  },
  {
    label: '卡片翻转',
    routerName: 'cardFlip',
    introduction: '卡片翻转',
    url: 'http://111.229.144.36:8008/卡片翻转.png'
  },
  {
    label: '文字滚动',
    routerName: 'rollText',
    introduction: '文字滚动',
    url: 'http://111.229.144.36:8008/文字滚动.png'
  }
] as any);

function toSlice(item: any) {
  title.value = item.label;
  routerDialog.value.open();
  router.push({ name: item.routerName });
}

function close() {
  routerDialog.value.close();
}

function getAnimate(i: any) {
  if (i % 5 === 0) {
    return 'animated-0s5';
  }
  if (i % 5 === 1) {
    return 'animated';
  }
  if (i % 5 === 2) {
    return 'animated-1s5';
  }
  if (i % 5 === 3) {
    return 'animated-2s';
  }
  if (i % 5 === 4) {
    return 'animated-2s5';
  }
  return 'animated';
}
</script>
<style lang="scss" scoped>
@include theme() {
  .slice-main {
    @keyframes fade-in {
      0% {
        opacity: 0;
        transform: translateY(50px);
      }
      100% {
        opacity: 1;
        transform: translateY(0px);
      }
    }
    @include flex-column;
    background: get('background');
    border-radius: 15px;
    justify-content: start;
    padding: 30px;
    width: calc(90% - 60px) !important;
    .slice-header {
      height: 60px;
      width: calc(100% - 120px);
      text-align: left;
      font-size: 22px;
      font-weight: bold;
      padding: 12px 30px;
      margin-left: 25px;
    }
    .slice-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: start;
      align-items: center;
      width: 100%;
      padding: 10px 20px;
      .slice-item {
        animation: fade-in 0.5s linear forwards;
        margin: 10px 10px;
        cursor: pointer;
        width: calc(32% - 20px);
        aspect-ratio: 5/3.2;
        border-radius: 8px;
        overflow: hidden;
        position: relative;
        opacity: 0.9;
        box-shadow: get('box-shadow');
        background: get('background');
        position: relative;
        color: get('font-color');
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-direction: column;
        overflow: hidden;
        .slice-item-cover {
          width: 100%;
          height: calc(100%);
          object-fit: cover;
          object-position: 70%;
          background-repeat: no-repeat;
          transition: transform 0.3s ease;
        }
        .slice-item-name {
          font-weight: bold;
          position: absolute;
          color: white;
          top: 15%;
          left: 25px;
          font-size: 20px;
        }
        .slice-item-divider {
          width: 0%;
          left: 25px;
          position: absolute;
          top: 30%;
          height: 2px;
          background: white;
        }
        .slice-item-instoction {
          color: white;
          position: absolute;
          top: 35%;
          left: 25px;
          transform: translate3d(300%, 0, 0);
        }

        // .instoction-cover {
        //   position: absolute;
        //   right: -150px;
        //   width: 100%;
        //   display: flex;
        //   align-items: center;
        //   justify-content: center;
        // }
      }
      .slice-item:hover {
        transform: scale(1.02);
        @keyframes instoction-in {
          0% {
            transform: translate3d(300%, 0, 0);
          }
          100% {
            transform: translate3d(0%, 0, 0);
          }
        }
        @keyframes divider-in {
          0% {
            width: 0%;
          }
          100% {
            width: calc(100% - 50px);
          }
        }
        .slice-item-cover {
          transform: scale(1.1);
          filter: brightness(60%);
        }
        .slice-item-instoction {
          animation: instoction-in 0.3s forwards linear;
        }
        .slice-item-divider {
          animation: divider-in 0.3s forwards linear;
        }
      }
    }
  }
}
</style>
