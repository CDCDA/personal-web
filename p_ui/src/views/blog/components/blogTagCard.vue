<!--
 * @Description: 标签云卡
-->
<template>
  <div class="blog-tag-card c-card">
    <div class="card-header">
      <el-icon><PriceTag /></el-icon><span class="tag-name">标签云</span>
    </div>
    <div class="tag-list">
      <div
        class="tag-item"
        :style="{ color: generateRandomColor() }"
        v-for="item in tagList"
        @click="toBlogTag(item)"
      >
        <span>{{ item.tagName }}</span>
        <sub>{{ item.total }}</sub>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listByUserId } from '@/api/tag';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
const router = useRouter();
function toBlogTag(item: any) {
  router.push({ name: 'blogTagPage', query: { tagId: item.tagId } });
}
const tagList = ref([
  {
    tagName: '',
    count: ''
  }
] as any);
const userStore = useUserStore();
async function getList() {
  const { code, data } = (await listByUserId({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    tagList.value = data.list;
  }
}

// 获取随机颜色
function generateRandomColor() {
  var minLightness = 0; // 最小亮度值
  var maxLightness = 50; // 最大亮度值

  // 生成随机的 HSL 颜色值
  var hue = Math.floor(Math.random() * 360); // 0-359
  var saturation = Math.floor(Math.random() * 101); // 0-100
  var lightness = Math.floor(Math.random() * (maxLightness - minLightness + 1)) + minLightness; // minLightness - maxLightness
  var color = 'hsl(' + hue + ', ' + saturation + '%, ' + lightness + '%)';
  return color;
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-tag-card {
    text-align: left;
    .card-header {
      @include flex;
      width: 100%;
      justify-content: start;
      font-size: 19px;
      font-weight: bold;
      .el-icon {
        margin: 8px;
        font-size: 22px;
      }
    }
    .tag-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      .tag-item {
        cursor: pointer;
        padding: 9px 22px 6px 11px;
        border-radius: 5px;
        margin: 5px 0px;
        font-size: 17px;
        position: relative;
        sub {
          top: 2px;
          position: absolute;
          right: 11px;
          font-size: 11px;
          opacity: 0.6;
        }
      }
      .tag-item:hover {
        background: get('bk');
        color: get('re-font-color') !important;
      }
    }
  }
}
</style>
