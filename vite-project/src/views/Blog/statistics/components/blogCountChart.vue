<!--
 * @Description: 博客统计折线图
-->
<template>
  <baseChart :options="options" class="blog-count-chart"></baseChart>
</template>
<script setup lang="ts">
import { reactive, onMounted, watch, ref } from 'vue';
import baseChart from '@/components/charts/baseChart.vue';
import { countBlogByDateRange } from '@/api/blog';
import useUserStore from '@/store/modules/user';

const userStore = useUserStore();
const options = reactive({
  title: {
    text: '数量',
    x: 'left', // 水平安放位置，默认为'left'，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
    y: 'top',
    padding: [15, 5, 5, 65],
    textStyle: {
      color: '#000000'
    }
  },
  tooltip: {},
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      data: [150, 230, 224, 218, 135, 147, 260],
      type: 'line'
    }
  ],
  grid: {
    show: false,
    top: '25%', // 一下数值可为百分比也可为具体像素值
    right: '5%',
    bottom: '15%',
    left: '10%'
  }
} as any);

function init(list: any) {
  options.xAxis.data = [];
  options.series[0].data = [];
  list.forEach((blog: any) => {
    options.xAxis.data.push(blog.createTime);
    options.series[0].data.push(blog.total);
  });
}

async function getList() {
  const { code, msg, data } = (await countBlogByDateRange({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    init(data);
  }
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss"></style>
