<!--
 * @Description:  
-->
<!--
 * @Description: 首页前置页面
-->
<template>
  <div class="page-main preView-main">
    <baseChart class="text-chert" :options="options"></baseChart>
    <el-button class="in-bt" icon="Right" @click="toMain"></el-button>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import baseChart from '@/components/charts/baseChart.vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const options = ref({
  graphic: {
    elements: [
      {
        type: 'text',
        left: 'center',
        top: 'center',
        style: {
          text: 'welcome',
          fontSize: 100,
          fontWeight: 'bold',
          lineDash: [0, 100],
          lineDashOffset: 0,
          fill: 'transparent',
          stroke: 'white',
          lineWidth: 1
        },
        keyframeAnimation: {
          duration: 3000,
          loop: false,
          keyframes: [
            {
              percent: 0.7,
              style: {
                fill: 'transparent',
                lineDashOffset: 200,
                lineDash: [200, 0]
              }
            },
            {
              // Stop for a while.
              percent: 0.8,
              style: {
                fill: 'transparent'
              }
            },
            {
              percent: 1,
              style: {
                fill: 'white'
              }
            }
          ]
        }
      }
    ]
  }
} as any);

function toMain() {
  router.push({ name: 'home' });
}

onMounted(() => {});
</script>

<style lang="scss">
.preView-main {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100%;
  .base-chart.text-chert {
    bottom: 15%;
  }
  .in-bt {
    width: 150px;
  }
  .in-bt {
    opacity: 0; /** 初始状态为不可见 */
    animation: 4s fade-in forwards linear; /** 动画属性 */
  }
  @keyframes fade-in {
    0% {
      opacity: 0; /** 开始时不可见 */
    }
    100% {
      opacity: 1; /** 结束时完全可见 */
    }
  }
}
</style>
