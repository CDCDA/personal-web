<!--
 * @Description: 博客统计折线图
-->
<template>
  <baseChart :options="options" class="blog-count-chart"></baseChart>
</template>
<script setup lang="ts">
import { reactive, onMounted, watch, ref } from 'vue';
import baseChart from '@/components/charts/baseChart.vue';
import { countBlogByType } from '@/api/blog';
import useUserStore from '@/store/modules/user';

const userStore = useUserStore();
const options = reactive({} as any);

function init() {
  // options.radar[0].indicator = [];
  // options.radar[1].indicator = [];
  // options.series[0].data[0].value = [];
  // list.forEach((blog: any) => {
  //   options.radar[0].indicator.push({ text: blog.typeName });
  //   options.radar[1].indicator.push({ text: blog.typeName });
  //   options.series[0].data[0].value;
  // });
  // console.log(options);
}

async function getList() {
  const { code, msg, data } = (await countBlogByType({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    init();
  }
}

onMounted(() => {
  // getList();
  Object.assign(options, {
    color: ['#912BD5', '#FFE434', '#56A3F1', '#FF917C'],
    title: {
      text: 'Customized Radar Chart',
      show: false
    },
    legend: {
      textStyle: {
        color: '#ffffff'
      }
    },
    radar: [
      {
        indicator: [
          { text: '力量' },
          { text: '速度' },
          { text: '射程' },
          { text: '持续力' },
          { text: '精密度' },
          { text: '成长性' }
        ],
        center: ['25%', '50%'],
        radius: 60,
        startAngle: 90,
        splitNumber: 5,
        shape: 'circle',
        axisName: {
          formatter: '【{value}】',
          color: '#428BD4'
        },
        splitArea: {
          areaStyle: {
            color: ['#77EADF', '#26C3BE', '#64AFE9', '#428BD4'],
            shadowColor: 'rgba(0, 0, 0, 0.2)',
            shadowBlur: 10
          }
        },
        axisLine: {
          lineStyle: {
            color: 'rgba(211, 253, 250, 0.8)'
          }
        },
        splitLine: {
          lineStyle: {
            color: 'rgba(211, 253, 250, 0.8)'
          }
        }
      },
      {
        indicator: [
          { text: '力量', max: 100 },
          { text: '速度', max: 100 },
          { text: '射程', max: 100 },
          { text: '持续力', max: 100 },
          { text: '精密度', max: 100 },
          { text: '成长性', max: 100 }
        ],
        legend: {
          textStyle: {
            color: '#ffffff'
          }
        },
        center: ['75%', '50%'],
        radius: 60,
        axisName: {
          color: '#fff',
          backgroundColor: '#666',
          borderRadius: 3,
          padding: [3, 5]
        }
      }
    ],
    series: [
      {
        type: 'radar',
        emphasis: {
          lineStyle: {
            width: 4
          }
        },
        data: [
          {
            value: ['100', '100', '60', '100', '100', '100'],
            name: '白金'
          },
          {
            value: ['100', '100', '60', '100', '80', '80'],
            name: '世界',
            areaStyle: {
              color: 'rgba(255, 228, 52, 0.6)'
            }
          }
        ]
      },
      {
        type: 'radar',
        radarIndex: 1,
        data: [
          {
            value: ['100', '100', '60', '100', '100', '100'],
            name: '白金',
            symbol: 'rect',
            symbolSize: 12,
            lineStyle: {
              type: 'dashed'
            },
            label: {
              show: true,
              formatter: function (params: any) {
                return params.value;
              }
            }
          },
          {
            value: ['100', '100', '60', '100', '80', '80'],
            name: '世界',
            areaStyle: {
              // color: new echarts.graphic.RadialGradient(0.1, 0.6, 1, [
              //   {
              //     color: 'rgba(255, 145, 124, 0.1)',
              //     offset: 0
              //   },
              //   {
              //     color: 'rgba(255, 145, 124, 0.9)',
              //     offset: 1
              //   }
              // ])
            }
          }
        ]
      }
    ]
  });
});
</script>
<style lang="scss"></style>
