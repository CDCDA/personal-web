<!--
 * @Description: 博客统计折线图
-->
<template>
  <baseChart ref="blogTagBar" :options="options" class="blog-tag-bar"></baseChart>
</template>
<script setup lang="ts">
import { reactive, onMounted, watch, ref } from 'vue';
import baseChart from '@/components/charts/baseChart.vue';
import { countBlogByTag } from '@/api/blog';
import useUserStore from '@/store/modules/user';
const blogTagBar = ref(null) as any;
const userStore = useUserStore();
const options = reactive({
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '0%',
    top: '5%',
    containLabel: true
  },
  xAxis: [
    {
      type: 'category',
      name: '',
      data: [],
      axisTick: {
        alignWithLabel: true
      },
      axisLabel: {
        interval: 0,
        rotate: 45,
        textStyle: {
          color: 'white' // 设置X轴标签文字颜色为蓝色
        }
      }
    }
  ],
  yAxis: [
    {
      type: 'value',
      name: '单位:个',
      axisLabel: {
        textStyle: {
          // fontSize: '0.7rem',
          color: 'white' // 设置X轴标签文字颜色为蓝色
        }
      }
    }
  ],
  series: [
    {
      type: 'bar',
      barWidth: '20px',
      data: []
    }
  ]
}) as any;

function init(list: Array<Object>) {
  options.series[0].data = [];
  options.xAxis[0].data = [];
  list.forEach((item: any) => {
    options.xAxis[0].data.push(item.tagName);
    options.series[0].data.push(item.total);
  });
}

async function getList() {
  const { code, msg, data } = (await countBlogByTag({
    userId: userStore.userId.toString()
  })) as any;
  if (code === 200 && data) {
    init(data);
  }
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss"></style>
