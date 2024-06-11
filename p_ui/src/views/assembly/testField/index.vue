<!--
 * @Description: 切片列表
-->
<template>
  <div class="page-main testField-main">
    <div class="testField-header" style="width: 100%; height: 30px; display: flex">
      <svg-icon
        iconName="experimentalData"
        style="width: 30px; height: 30px; margin-right: 15px"
      />大模块测试
    </div>
    <div class="testField-center">
      <div class="testField-item" v-for="(item, i) in testFieldList" @click="totestField(item)">
        <c-image class="testField-item-cover" :src="item.url" />
        <span class="testField-item-name">{{ item.label }}</span>
        <span class="testField-item-divider"></span>
        <span class="testField-item-instoction no-wrap">{{ item.introduction }}</span>
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
const testFieldList = ref([
  {
    label: '低代码',
    routerName: 'vForm',
    introduction: '低代码表单平台',
    url: 'http://111.229.144.36:8008/低代码.png'
  },
  {
    label: '自由拖拽',
    routerName: 'draggle',
    introduction: '一个自由拖拽组件的页面',
    url: 'http://111.229.144.36:8008/自由拖拽.png'
  },
  {
    label: '甘特图',
    routerName: 'ganttChart',
    introduction: '复杂甘特图',
    url: 'http://111.229.144.36:8008/甘特图.png'
  }
  // {
  //   label: 'canvas研究',
  //   routerName: 'canvas',
  //   introduction: 'canvas研究',
  //   url: 'http://111.229.144.36:8008/canvas.png'
  // }
] as any);

function totestField(item: any) {
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
