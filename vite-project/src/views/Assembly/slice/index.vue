<!--
 * @Description: 切片列表
-->
<template>
  <div class="page-main slice-main">
    <div class="slice-header animated bounceInLeft">
      <el-icon><Orange /></el-icon>{{ '比较简单的小东西' }}<el-icon><Right /></el-icon>
    </div>
    <div class="slice-center">
      <div
        class="slice-item bounceInUp"
        :class="getAnimate(i)"
        v-for="(item, i) in sliceList"
        @click="toSlice(item)"
      >
        <img class="slice-item-cover" :src="item.url" />
        <span class="slice-item-name">{{ item.label }}</span>
        <span class="slice-item-instoction">{{ item.introduction }}</span>
        <div class="instoction-cover">
          {{ item.introduction }}
        </div>
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
    url: require('@/assets/images/bk-1.jpg'),
    path: '/slice'
  },
  {
    label: '图片旋转魔方',
    routerName: 'rotatingRubik',
    introduction: '可旋转展示6面图片的魔方',
    url: require('@/assets/images/bk-1.jpg')
  },
  {
    label: '魔方',
    routerName: 'rubiks',
    introduction: '普通3x3动态魔方',
    url: require('@/assets/images/bk-1.jpg'),
    path: '/slice'
  },
  {
    label: '魔方',
    routerName: 'rubiks',
    introduction: '普通3x3动态魔方',
    url: require('@/assets/images/bk-1.jpg'),
    path: '/slice'
  },
  {
    label: '魔方',
    routerName: 'rubiks',
    introduction: '普通3x3动态魔方',
    url: require('@/assets/images/bk-1.jpg'),
    path: '/slice'
  },
  {
    label: '魔方',
    routerName: 'rubiks',
    introduction: '普通3x3动态魔方',
    url: require('@/assets/images/bk-1.jpg'),
    path: '/slice'
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
<style lang="scss">
@include theme() {
  .slice-main {
    @include flex-column;
    justify-content: start;
    .slice-header {
      height: 60px;
      width: calc(100% - 60px);
      color: get('font-color');
      border-radius: 15px;
      background: get('background');
      box-shadow: get('box-shadow');
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0px 30px;
      font-size: 22px;
      font-weight: bold;
      margin-bottom: 20px;
      .el-icon {
        font-size: 25px;
        color: get('foont-color');
        cursor: pointer;
      }
    }
    .slice-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      width: 100%;
      .slice-item {
        margin-bottom: 20px;
        cursor: pointer;
        width: calc(20% - 15px);
        height: 210px;
        border-radius: 20px;
        position: relative;
        opacity: 0.9;
        box-shadow: get('box-shadow');
        background: get('background');
        position: relative;
        color: get('font-color');
        margin-bottom: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-direction: column;
        overflow: hidden;
        .slice-item-cover {
          width: 100%;
          height: calc(100% - 50px);
          border-radius: 20px 20px 0px 0px;
          object-fit: cover;
          object-position: 70%;
          background-repeat: no-repeat;
          transition: transform 0.3s ease;
        }
        .slice-item-name {
          font-weight: bold;
        }
        .slice-item-instoction {
          opacity: 0.9;
        }

        .instoction-cover {
          position: absolute;
          bottom: -150px;
          width: 100%;
          height: 150px;
          // color: white;
          background: get('background-no-tp');
          display: flex;
          align-items: center;
          // background: #425aef;
        }
      }
      .slice-item:hover {
        transform: scale(1.03);
        @keyframes cover-in {
          0% {
            bottom: -150px;
          }
          100% {
            bottom: 0px;
          }
        }
        .slice-item-cover {
          transform: scale(1.5);
          filter: brightness(30%);
        }
        .instoction-cover {
          animation: cover-in 0.3s forwards linear;
        }
      }
    }
  }
}
</style>
