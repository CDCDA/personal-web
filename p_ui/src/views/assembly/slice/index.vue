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
    url: 'http://111.229.144.36:8008/bk-1.jpg',
    path: '/slice'
  },
  {
    label: '图片旋转魔方',
    routerName: 'rotatingRubik',
    introduction: '可旋转展示6面图片的魔方',
    url: 'http://111.229.144.36:8008/rotate rubik.jpg'
  },
  {
    label: '闪光边框按钮',
    routerName: 'lightButton',
    introduction: '炫彩',
    url: 'http://111.229.144.36:8008/rotate rubik.jpg'
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
