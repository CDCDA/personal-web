<!--
 * @Description: 3d地球
-->
<!--
 * @Description: 基础图
-->
<template>
  <div class="earth-3d" :id="id"></div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import * as echarts from 'echarts';
import { onMounted, watch } from 'vue';
import 'echarts-gl';
const options = ref({
  backgroundColor: 'transparent',
  globe: {
    baseTexture: '/earth3d/world.topo.bathy.200401.jpg',
    heightTexture: '/earth3d/world.topo.bathy.200401.jpg',
    displacementScale: 0.04,
    shading: 'realistic',
    environment: '',
    realisticMaterial: {
      roughness: 0.9
    },
    postEffect: {
      enable: true
    },
    light: {
      main: {
        intensity: 5,
        shadow: true
      },
      ambientCubemap: {
        texture: '/earth3d/pisa.hdr',
        diffuseIntensity: 0.2
      }
    }
  }
} as any);
const id = Math.random().toString(36).slice(-8);
function init() {
  // 基于准备好的dom，初始化echarts实例
  var myChart = echarts.init(document.getElementById(id) as any);
  // 绘制图表
  myChart.setOption(options.value);
}

onMounted(() => {
  init();
});
</script>
<style lang="scss">
.earth-3d {
  width: 100%;
  height: 100%;
}
</style>
