<!--
 * @Description: 技术栈卡片 
-->
<template>
  <div class="technology-stack-card">
    <div class="stack-rowup">
      <div class="stack-item-col" v-for="item in techStackData">
        <el-image class="stack-item" :src="item.upStack.url"></el-image>
        <el-image class="stack-item" :src="item.downStack.url"></el-image>
      </div>
    </div>
    <div class="stack-tag-list" v-if="props.isHoverShow">
      <div class="stack-tag" v-for="item in techStackList">
        <el-image :src="item.url"></el-image><span class="tag-text">{{ item.label }}</span>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import { techStackData, techStackList } from '@/utils/techStackData.js';

const props = defineProps({
  isHoverShow: {
    type: Boolean,
    default: true
  }
});
</script>
<style lang="scss" scoped>
@include theme() {
  @keyframes row-up {
    0% {
      transform: translateX(0%);
    }

    100% {
      transform: translateX(-20%);
    }
  }
  .technology-stack-card {
    height: 300px;
    position: relative;
    .stack-rowup {
      @include flex;
      flex-wrap: nowrap;
      animation: row-up 10s linear infinite;
      .stack-item-col {
        margin: 0px 5px;
        @include flex-column;
        .stack-item {
          width: 110px;
          height: 110px;
          padding: 5px;
          box-shadow: get('box-shadow');
          border-radius: 30px;
          margin: 10px 5px;
          background: white;
        }
        .stack-item:nth-child(2) {
          transform: translate(-80px);
        }
      }
    }
    .stack-tag-list {
      position: absolute;
      bottom: -25px;
      left: -1000px;
      @include flex;
      flex-wrap: wrap;
      justify-content: start;
      width: 100%;
      height: calc(100% + 40px);
      z-index: 1;
      background: get('bk');
      .stack-tag {
        border: 1px solid #d1d1d1;
        border-radius: 20px;
        padding: 5px 20px;
        margin: 10px;
        background: white;
        @include flex;
        .el-image {
          width: 30px;
          height: 30px;
          margin-right: 10px;
        }
        .tag-text {
        }
      }
    }
  }
  @keyframes stack-tag-in {
    0% {
    }
    100% {
      left: 0px;
    }
  }
  @keyframes stack-tag-out {
    0% {
      left: 0px;
    }
    100% {
      left: -1000px;
    }
  }
  .technology-stack-card:hover {
    .stack-tag-list {
      animation: stack-tag-in 0.3s forwards linear;
    }
  }
  .technology-stack-card:not(:hover) {
    .stack-tag-list {
      animation: stack-tag-out 0.3s forwards linear;
    }
  }
}
</style>
