<template>
  <div class="home-main page-main">
    <!-- <el-button @click="printData()">数据</el-button> -->
    <DraggableContainer :referenceLineColor="'white'">
      <Vue3DraggableResizable
        v-for="item in draggableList"
        :initW="item.initW"
        :initH="item.initH"
        v-model:x="item.x"
        v-model:y="item.y"
        v-model:w="item.width"
        v-model:h="item.height"
        v-model:active="item.active"
        :draggable="!item.isLock"
        :resizable="!item.isLock"
        :parent="true"
        :class="item.type"
        @resize-end="resizeEndHandle(item)"
      >
        <component
          :is="item.type"
          :style="{ width: item.width, height: item.height }"
          v-if="!item.hidden"
        ></component>
        <div class="show-tools" @click="showTools(item)">
          <el-icon v-if="!item.toolsShow"><DArrowRight></DArrowRight></el-icon>
          <el-icon v-else><DArrowLeft></DArrowLeft></el-icon>
        </div>
        <div class="vdr-tools" v-if="item.toolsShow">
          <div class="vdr-tools-left">
            <el-tooltip :content="item.isLock ? '解锁' : '锁定'" placement="top">
              <el-icon v-if="item.isLock" @click="unLock(item)"><Lock></Lock></el-icon>
              <el-icon v-else @click="lock(item)"><Unlock></Unlock></el-icon>
            </el-tooltip>
            <el-tooltip content="恢复初始位置" placement="top">
              <el-icon @click="refresh(item)"><RefreshRight></RefreshRight></el-icon>
            </el-tooltip>
          </div>
          <div class="vdr-tools-right">
            <el-divider direction="vertical" />
            <el-tooltip content="上移1px" placement="top">
              <el-icon @click="move(item, 'top')"><CaretTop></CaretTop></el-icon>
            </el-tooltip>
            <el-tooltip content="下移1px" placement="top">
              <el-icon @click="move(item, 'bottom')"><CaretBottom></CaretBottom></el-icon>
            </el-tooltip>
            <el-tooltip content="左移1px" placement="top">
              <el-icon @click="move(item, 'left')"><CaretLeft></CaretLeft></el-icon>
            </el-tooltip>
            <el-tooltip content="右移1px" placement="top">
              <el-icon @click="move(item, 'right')"><CaretRight></CaretRight></el-icon>
            </el-tooltip>
          </div>
        </div>
      </Vue3DraggableResizable>
    </DraggableContainer>
  </div>
</template>
<script setup lang="ts">
import { ref, reactive } from 'vue';
import weather from './components/weather.vue';
import BlogTags from '../Blog/components/BlogTags.vue';
import BlogType from '../Blog/components/BlogType.vue';
import BlogUser from '../Blog/components/BlogUser.vue';
// import randomSlogan from '@/components/RandomSlogan.vue';
import earth3d from './components/earth3d.vue';
import { DraggableContainer } from 'vue3-draggable-resizable';
import Vue3DraggableResizable from 'vue3-draggable-resizable';
//default styles
import 'vue3-draggable-resizable/dist/Vue3DraggableResizable.css';
import { inputNumberEmits } from 'element-plus';
const draggableList = reactive([
  {
    name: '天气预报',
    id: 1,
    isLock: false,
    type: weather,
    active: false,
    width: 225,
    height: 270,
    minWidth: 225,
    minHeight: 270,
    x: 0,
    y: 0,
    initW: 225,
    initH: 270,
    initX: 0,
    initY: 0,
    toolsShow: false
  },
  {
    name: '标签云',
    id: 2,
    active: false,
    isLock: false,
    type: BlogTags,
    width: 225,
    height: 270,
    minWidth: 225,
    minHeight: 270,
    x: 236,
    y: 0,
    initW: 225,
    initH: 270,
    initX: 236,
    initY: 0,
    toolsShow: false
  },
  {
    name: '分类',
    id: 3,
    active: false,
    isLock: false,
    type: BlogType,
    width: 225,
    height: 270,
    minWidth: 225,
    minHeight: 270,
    x: 236,
    y: 283,
    initW: 225,
    initH: 270,
    initX: 236,
    initY: 283,
    toolsShow: false
  },
  {
    name: '用户信息',
    id: 4,
    type: BlogUser,
    active: false,
    isLock: false,
    width: 225,
    height: 270,
    minWidth: 225,
    minHeight: 270,
    x: 0,
    y: 283,
    initW: 225,
    initH: 270,
    initX: 0,
    initY: 283,
    toolsShow: false
  },
  {
    name: '3d地球',
    id: 5,
    type: earth3d,
    active: false,
    isLock: false,
    reload: true,
    width: 400,
    height: 400,
    minWidth: 300,
    minHeight: 300,
    x: 473,
    y: 0,
    initW: 400,
    initH: 400,
    initX: 473,
    initY: 0,
    toolsShow: true
  }
] as any);

function showTools(item: any) {
  item.toolsShow = !item.toolsShow;
  draggableList.forEach((element: any) => {
    if (element.id != item.id) {
      element.toolsShow = false;
    }
  });
}

function lock(item: any) {
  item.isLock = true;
}

function unLock(item: any) {
  item.isLock = false;
}

function move(item: any, direction: String) {
  switch (direction) {
    case 'top':
      item.y = item.y - 1;
      break;
    case 'bottom':
      item.y = item.y + 1;
      break;
    case 'left':
      item.x = item.x - 1;
      break;
    case 'right':
      item.x = item.x + 1;
      break;
    default:
      break;
  }
}

function resizeEndHandle(item: any) {
  if (!item.reload) return;
  item.hidden = true;
  setTimeout(() => {
    item.hidden = false;
  }, 500);
}

function refresh(item: any) {
  item.x = item.initX;
  item.y = item.initY;
}

function printData() {
  console.log(draggableList);
}
</script>
<style lang="less">
.home-main {
  display: flex;
  height: calc(100% - 50px);
  width: calc(100% - 40px);
  margin-top: 5px;
  justify-content: space-between;
  flex-direction: column;
  align-content: space-around;
  padding: 20px;
  box-shadow: 0 2px 12px 0 #000000;
  .vdr-container {
    display: flex;
    justify-content: center;
    border: none;
    // position: relative;
    height: 100%;
    width: 100%;
    box-shadow: 0 2px 12px 0 #000000;
  }

  .vdr-handle-tl {
    background:
      /*上左*/ linear-gradient(to top, #ffd04b, #ffd04b) left top no-repeat,
      /*左上*/ linear-gradient(to right, #ffd04b, #ffd04b) left top no-repeat;
  }

  .vdr-handle-tr {
    background:
      /*右上*/ linear-gradient(to left, #ffd04b, #ffd04b) right top no-repeat,
      /*上右*/ linear-gradient(to bottom, #ffd04b, #ffd04b) right top no-repeat;
  }

  .vdr-handle-bl {
    background:
      /*左下*/ linear-gradient(to left, #ffd04b, #ffd04b) left bottom no-repeat,
      /*下左*/ linear-gradient(to bottom, #ffd04b, #ffd04b) left bottom no-repeat;
  }

  .vdr-handle-br {
    background:
      /*右下*/ linear-gradient(to top, #ffd04b, #ffd04b) right bottom no-repeat,
      /*下右*/ linear-gradient(to left, #ffd04b, #ffd04b) right bottom no-repeat;
  }

  .handle {
    width: 12px;
    height: 12px;
    border: none;
    background-size: 2px 12px, 12px 2px, 2px 12px, 12px 2px;
  }
  .vdr-handle-tm {
    width: 12px;
    height: 5px;
    background: transparent;
    border-top: 2px solid #ffd04b;
  }

  .vdr-handle-bm {
    width: 12px;
    height: 5px;
    background: transparent;
    border-bottom: 2px solid #ffd04b;
  }
  .vdr-handle-ml {
    width: 5px;
    height: 12px;
    background: transparent;
    border-left: 2px solid #ffd04b;
  }
  .vdr-handle-mr {
    width: 5px;
    height: 12px;
    background: transparent;
    border-right: 2px solid #ffd04b;
  }
  .vdr-tools {
    z-index: 999;
    height: 25px;
    width: calc(100% - 20px);
    margin-top: 5px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0px 10px;
    backdrop-filter: blur(20px);
    box-shadow: 0 2px 12px 0 #000000;
    position: absolute;
    bottom: -31px;
    .el-icon {
      color: white;
      margin: 0px 3px 2px 3px;
    }
    .vdr-tools-left,
    .vdr-tools-right {
      display: flex;
      align-items: center;
      justify-content: flex-start;
    }
  }
  .show-tools {
    position: absolute;
    bottom: 0px;
    backdrop-filter: none;
    .el-icon {
      visibility: hidden;
      margin: 0px 10px;
      transform: rotate(90deg);
      color: white;
    }
  }
  .show-tools:hover {
    backdrop-filter: blur(20px);
    .el-icon {
      visibility: visible;
      margin: 5px 10px;
      transform: rotate(90deg);
      color: white;
    }
  }
}
</style>
