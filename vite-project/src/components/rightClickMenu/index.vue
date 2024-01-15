<template>
  <ul
    v-show="props.visible"
    :style="{ left: props.left + 'px', top: props.top + 'px' }"
    class="contextmenu"
  >
    <li><svg-icon iconName="randomPlayback" />随便逛逛</li>
    <li><svg-icon iconName="copy" />复制地址</li>
    <li><svg-icon iconName="refresh" />重新加载</li>
    <li><svg-icon iconName="regardTo" />关于网站</li>
  </ul>
</template>
<script setup lang="ts">
const props = defineProps({
  left: null,
  top: null,
  visible: null
});
</script>

<style scoped lang="scss">
@include theme() {
  .contextmenu {
    margin: 0;
    background: get('background');
    z-index: 3000;
    position: fixed;
    list-style-type: none;
    padding: 10px 5px;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 400;
    color: #333;
    box-shadow: get('box-shadow');
  }

  .contextmenu li {
    margin: 0;
    padding: 7px 16px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    .svg-icon-wrap {
      width: 20px;
      height: 20px;
      margin-right: 5px;
    }
  }
  .contextmenu li:hover {
    background: #eee;
  }
}
</style>
