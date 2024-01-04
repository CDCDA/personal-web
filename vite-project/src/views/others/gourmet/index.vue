<!--
 * @Description: 美食
-->
<template>
  <div class="page-main gourmet-main">
    <div class="gourmet-top animated bounceInDown"><BubbleCollision /></div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import BubbleCollision from './components/bubbleCollision.vue';
import RotatingBorder from '@/components/rotatingBorder.vue';
onMounted(() => {});
</script>

<style lang="scss" scoped>
@include theme() {
  .gourmet-top {
    width: 100%;
    height: 45vh;
    border-radius: 15px;
    overflow: hidden;
  }
}
</style>
<style></style>
