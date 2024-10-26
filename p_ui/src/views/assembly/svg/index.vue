<!--
 * @Description: svg列表
-->
<template>
  <div class="page-main svg-main">
    <div class="svg-header">
      <svg-icon iconName="物质" style="width: 30px; height: 30px; margin-right: 15px" />svg列表
      <!-- <div><el-input v-model="searchText" :suffix-icon="Search"></el-input></div> -->
    </div>
    <div class="svg-center">
      <div class="svg-item" v-for="(item, i) in svgList" @click="getSvgCode(item)">
        <svg-icon :iconName="item.fileName" class="svg-item-icon" />
        <span class="svg-item-name">{{ item.fileName }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElNotification } from 'element-plus';
import { copyText } from '@/utils/common';
import { Search } from '@element-plus/icons-vue';
const router = useRouter();
const svgList = ref([] as any);
const searchText = ref('') as any;
function getSvgCode(item: any) {
  copyText(`<svg-icon iconName="${item.fileName}" />`);
  ElNotification.success('svg代码复制成功');
}

onMounted(() => {
  const files = (import.meta as any).glob('@/assets/svg/*.svg', { eager: true });
  Object.keys(files).forEach((name: any) => {
    const fileName = name.split('/').pop().replace('.svg', '');
    svgList.value.push({ fileName, src: files[name].default });
  });
  console.log('qqq', svgList.value);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .svg-main {
    @keyframes fade-in {
      0% {
        opacity: 0;
        transform: translateY(50px);
      }
      100% {
        opacity: 1;
        transform: translateY(0px);
      }
    }
    @include flex-column;
    background: get('background');
    border-radius: 8px;
    justify-content: start;
    .svg-header {
      height: 30px;
      width: calc(100% - 80px);
      text-align: left;
      font-size: 22px;
      font-weight: bold;
      padding: 12px 0px 5px 0px;
      margin: 25px auto 0px auto;
      display: flex;
    }
    .svg-center {
      display: flex;
      flex-wrap: wrap;
      justify-content: start;
      align-items: center;
      width: calc(100% - 60px);
      padding: 10px 0px 30px 0px;
      margin: 0px auto;
      .svg-item {
        animation: fade-in 0.5s linear forwards;
        margin: 10px 10px;
        cursor: pointer;
        width: calc(10% - 20px);
        min-width: 80px;
        border-radius: 8px;
        overflow: hidden;
        position: relative;
        opacity: 0.9;
        box-shadow: get('box-shadow');
        background: get('background');
        position: relative;
        color: get('font-color');
        display: flex;
        justify-content: start;
        align-items: center;
        flex-direction: column;
        overflow: hidden;
        .svg-item-name {
          font-size: 14px;
          text-align: center;
          width: calc(100% - 10px);
          height: 30px;
          padding: 0px 5px;
          margin-bottom: 5px;
          line-height: 30px;
          text-overflow: ellipsis;
          overflow: hidden;
          white-space: nowrap;
          color: get('font-color');
        }
        .svg-item-icon {
          width: 40px;
          height: 40px;
          margin: 15px auto 5px auto;
        }
      }
      .svg-item:hover {
        transform: scale(1.02);
        @keyframes instoction-in {
          0% {
            transform: translate3d(300%, 0, 0);
          }
          100% {
            transform: translate3d(0%, 0, 0);
          }
        }
        @keyframes divider-in {
          0% {
            width: 0%;
          }
          100% {
            width: calc(100% - 50px);
          }
        }
        .svg-item-cover {
          transform: scale(1.1);
          filter: brightness(60%);
        }
        .svg-item-instoction {
          animation: instoction-in 0.3s forwards linear;
        }
        .svg-item-divider {
          animation: divider-in 0.3s forwards linear;
        }
      }
    }
  }
}
</style>
