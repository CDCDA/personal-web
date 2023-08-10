<!--
 * @Description: 博客分类环图
-->
<template>
  <baseChart :options="options" class="blog-type-chart"></baseChart>
</template>
<script setup lang="ts">
import { reactive, onMounted, watch, ref } from 'vue';
import baseChart from '@/components/charts/baseChart.vue';
import { countBlogByType } from '@/api/blog';
import useUserStore from '@/store/modules/user';

const userStore = useUserStore();
const options = reactive({
  tooltip: {
    trigger: 'item',
    textStyle: {
      color: '#ffffff'
    }
  },
  legend: {
    top: '5%',
    left: 'center',
    textStyle: {
      color: '#ffffff'
    }
  },
  series: [
    {
      name: 'Access From',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderRadius: 10,
        borderColor: '#fff',
        borderWidth: 2
      },
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: 40,
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: [
        { value: 1048, name: 'Search Engine' },
        { value: 735, name: 'Direct' },
        { value: 580, name: 'Email' },
        { value: 484, name: 'Union Ads' },
        { value: 300, name: 'Video Ads' }
      ]
    }
  ]
} as any);

function init(list: Array<Object>) {
  options.series[0].data = [];
  list.forEach((blog: any) => {
    options.series[0].data.push({
      value: blog.total,
      name: blog.typeName
    });
  });
  console.log(options);
}

async function getList() {
  const { code, msg, data } = (await countBlogByType({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    init(data.list);
  }
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss"></style>
