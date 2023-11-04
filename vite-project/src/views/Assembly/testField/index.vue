<!--
 * @Description: 切片列表
-->
<template>
  <div class="page-main slice-main">
    <div class="slice-header">
      <el-icon><Orange /></el-icon>{{ '比较复杂的小东西' }}<el-icon><Right /></el-icon>
    </div>
    <div class="slice-center">
      <div class="slice-item" v-for="item in sliceList" @click="toSlice(item)">
        <img class="slice-item-cover" :src="item.url" />
        <span class="slice-item-name">{{ item.label }}</span>
        <span class="slice-item-instoction">{{ item.introduction }}</span>
        <div class="instoction-cover">
          简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介简介
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
    label: '低代码',
    routerName: 'vForm',
    introduction: '低代码表单平台',
    url: require('@/assets/images/bk-1.jpg')
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
      justify-content: start;
      align-items: center;
      width: 100%;
      .slice-item {
        cursor: pointer;
        width: 250px;
        height: 210px;
        border-radius: 20px;
        position: relative;
        margin: 0px;
        opacity: 0.9;
        box-shadow: get('box-shadow');
        background: get('background');
        position: relative;
        color: get('font-color');
        margin-right: 20px;
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
