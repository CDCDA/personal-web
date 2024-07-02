<!--
 * @Description: 随笔
-->
<template>
  <div class="page-main essay-main">
    <div class="essay-header animated bounceInDown">
      <video
        :src="'http://111.229.144.36:8008/video_2000212665.mp4'"
        class="essay-header-video"
        autoplay
        loop
        muted
      ></video>
      <span class="essay-header-center"
        >喝一壶清茶，写几行小篆，<br />看一剪流云，梦一回江南。愿与草木，随遇而安</span
      >
      <span class="essay-header-bottom">闲情逸致</span>
      <!-- <div class="essay-header-add">新增+</div> -->
    </div>
    <!-- <div class="essay-center"> -->
    <grid-layout
      class="essay-center"
      :responsive="false"
      :layout.sync="essayList"
      :col-num="3"
      :autoSize="true"
      :row-height="1"
      :is-draggable="true"
      :is-resizable="false"
      :is-mirrored="false"
      :vertical-compact="false"
      :margin="[15, 10]"
      :use-css-transforms="true"
      ref="grid"
    >
      <grid-item
        v-for="(item, i) in essayList"
        :x="item.x"
        :y="item.y"
        :w="item.w"
        :h="item.h"
        :i="item.i"
        :key="item.i"
      >
        <div class="essay-item c-left" :class="`essay-${item.i}`">
          <div class="essay-item-user">
            <c-image class="essay-item-user-avatar" :src="userData.avatar"></c-image>
            <div class="essay-item-info">
              <span class="essay-item-user-nickName">{{ userData.nickName }}</span>
              <span class="essay-item-date">
                {{ sformatDate(new Date(item.createTime)) }}
              </span>
            </div>
          </div>
          <span class="essay-item-content">{{ item.content }}</span>
          <div class="img-list" v-if="item.images && item.images.length > 0">
            <el-image
              class="img-list-item"
              :preview-src-list="item.images"
              v-for="img in item.images"
              :preview-teleported="true"
              :src="img"
              fit="cover"
              lazy
            >
              <template #placeholder>
                <div class="image-slot" v-cLoading="imgLoading" style="width: 100%; height: 100%" />
              </template>
              <template #error>
                <div class="image-error-slot">
                  <svg-icon iconName="imgFailed" />
                </div>
              </template>
            </el-image>
          </div>
          <div class="c-dotted-line"></div>
          <div class="essay-item-tag-list">
            <span class="essay-item-tag" v-for="tag in item.tags">{{ `# ${tag.tagName}` }}</span>
          </div>
        </div>
      </grid-item>
    </grid-layout>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import { useRouter } from 'vue-router';
import { getUserById } from '@/api/user.ts';
import { listEssay } from '@/api/essay.ts';
import useUserStore from '@/store/modules/user';
import { sformatDate } from '@/utils/date.ts';
const imgLoading = ref('rotate' as any);
const router = useRouter();
const userStore = useUserStore();
const essayList = ref([] as any);
const grid = ref(null as any);
function handle() {
  imgLoading.value = false;
}

/**
 * @description: 动画执行时间
 * @param {*} i
 * @return {*}
 */
function getAnimate(i: any) {
  if (i % 3 === 0) {
    return 'animated';
  }
  if (i % 3 === 1) {
    return 'animated-1s5';
  }
  if (i % 3 === 2) {
    return 'animated-2s';
  }
  return 'animated';
}

/**
 * @description: 获取随笔
 * @return {*}
 */
async function getEssayList() {
  const { code, data } = (await listEssay({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    essayList.value = [];
    let length = data.list.length;
    let index = 0;
    for (let i = 0; i < length / 3; i++) {
      for (let j = 0; j < 3; j++) {
        if (data.list[index])
          essayList.value.push({
            x: j,
            y: i,
            w: 1,
            h: 10,
            i: data.list[index].id,
            images: data.list[index].images,
            createTime: data.list[index].createTime,
            content: data.list[index].content,
            tags: data.list[index].tags ? JSON.parse(data.list[index].tags) : []
          });
        index++;
      }
    }
    // 计算随笔初始位置
    setTimeout(() => {
      let wrapHeight = 0;
      essayList.value.forEach((e: any, i: any) => {
        let y = 0;
        let tempHeight = 0;
        for (let m = i - 3; m >= 0; m = m - 3) {
          if (m >= 0) {
            let height = (document.querySelector(`.essay-${essayList.value[m].i}`) as any)
              .offsetHeight;
            tempHeight = tempHeight + height;
            y = y + height / 10;
          }
        }
        e.y = y;
        let selfHeight = (document.querySelector(`.essay-${e.i}`) as any).offsetHeight;
        e.h = selfHeight / 10 - 1;
        tempHeight = tempHeight + selfHeight;
        if (tempHeight > wrapHeight) wrapHeight = tempHeight;
      });
      grid.value.layoutUpdate();
    }, 100);
  }
}

const userData = ref({} as any);

async function getUserData() {
  const { code, data } = (await getUserById(userStore.userId)) as any;
  if (code == 200) {
    userData.value = data;
  }
}

onMounted(() => {
  getUserData();
  getEssayList();
});
</script>
<style lang="scss">
@include theme() {
  .essay-main {
    @include flex-column;
    justify-content: start;
    overflow: initial;
    .essay-header {
      height: 45vh;
      width: 100%;
      border-radius: 20px;
      box-shadow: get('box-shadow');
      position: relative;
      .essay-header-video {
        opacity: 0.95;
        height: 100%;
        width: 100%;
        border-radius: 20px;
        object-fit: cover;
      }
      margin-bottom: 30px;
      color: white;
      .essay-header-top {
        position: absolute;
        top: 10px;
        left: 15px;
      }
      .essay-header-center {
        position: absolute;
        top: 47px;
        left: 14px;
        font-size: 35px;
        font-weight: bold;
        text-align: left;
      }
      .essay-header-bottom {
        position: absolute;
        bottom: 14px;
        left: 16px;
      }
      .essay-header-add {
        width: 90px;
        height: 35px;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 20px;
        position: absolute;
        right: 10px;
        bottom: 10px;
        cursor: pointer;
        background: #d3d3d3;
        color: black;
      }
    }
    .essay-center {
      width: calc(100% + 30px);
      min-height: 100vh;
      display: table;
      .essay-item {
        transition: all 0.2s ease-in-out;
        border-radius: 10px;
        position: relative;
        padding: 15px 15px 10px 15px;
        text-align: left;
        box-shadow: get('box-shadow');
        background: get('background-no-tp');
        color: get('font-color');
        .essay-item-user {
          display: flex;
          margin-bottom: 12px;
          .essay-item-user-avatar {
            width: 50px;
            height: 50px;
            border-radius: 8px;
          }
          .essay-item-info {
            width: calc(100% - 50px);
            padding: 3px 10px;
            @include flex-column;
            justify-content: space-between;
            .essay-item-user-nickName,
            .essay-item-date {
              text-align: left;
              width: 100%;
            }
            .essay-item-user-nickName {
              font-size: 20px;
              font-weight: 400;
              color: #6dbdc3;
            }
            .essay-item-date {
              font-size: 13px;
              color: #969394;
            }
          }
        }
        .essay-item-content {
          white-space: pre-wrap;
        }
        .img-list {
          overflow-x: auto;
          width: 100%;
          @include flex;
          min-height: 40px;
          flex-wrap: wrap;
          justify-content: start;
          margin: 12px 0px;
          .img-list-item {
            // height: 100px;
            width: calc(33.33% - 10px);
            aspect-ratio: 3/2;
            margin: 5px;
            border-radius: 5px;
          }
        }
        .essay-item-tag-list {
          display: flex;
          flex-wrap: nowrap;
          .essay-item-tag {
            margin-right: 10px;
            font-size: 14px;
            padding: 2px 5px;
            color: #999;
            background: #f2f2f2;
            border-radius: 4px;
          }
        }
      }
      .essay-item:hover {
        transform: scale(1.02);
      }
    }
  }
}
.vue-grid-item {
  // height: auto !important;
  // width: calc(33% - 10px) !important;
}
</style>
