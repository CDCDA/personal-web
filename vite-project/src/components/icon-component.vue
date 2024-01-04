<!--
 * @Description:  svg图标
-->
<template>
  <i class="svg-icon-wrap">
    <svg :class="'svg-icon ' + ($attrs.iconClass ? $attrs.iconClass : '')" aria-hidden="true">
      <use class="svg-use theme-icon" :href="'#' + $attrs.iconName" :fill="props.color" />
    </svg>
  </i>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
const props = defineProps({
  color: null
});
</script>

<style>
.svg-icon-wrap {
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
use {
  width: 100%;
  height: 100%;
}
.svg-icon {
  vertical-align: -0.1em;
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin: auto auto;
}
</style>
