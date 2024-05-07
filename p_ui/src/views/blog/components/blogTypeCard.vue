<!--
 * @Description:  分类卡
-->
<template>
  <div class="blog-type-card c-card">
    <div class="card-header">
      <el-icon><FolderOpened /></el-icon><span class="type-nmae">分类</span>
    </div>
    <div class="type-list">
      <div class="type-item" v-for="item in typeList">
        <span class="type-item-prepend">#</span>{{ item.typeName
        }}<span class="type-item-suffix">{{ item.total }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTotalType } from '@/api/type';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const typeList = ref([] as any);

async function getTypeTree() {
  const { code, msg, data } = (await listTotalType({})) as any;
  if (code === 200 && data) {
    typeList.value = data.list;
  }
}

onMounted(() => {
  getTypeTree();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-type-card {
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
    .type-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      .type-item {
        padding: 8px 15px;
        border-radius: 8px;
        color: get('font-color');
        border: 1px solid #b9b8b8;
        margin: 8px;
        font-size: 18px;
        cursor: pointer;
      }
      .type-item-prepend {
        margin-right: 6px;
        opacity: 0.8;
      }
      .type-item-suffix {
        margin-left: 6px;
        opacity: 0.8;
        background: #d1d1d1;
        border-radius: 5px;
        padding: 0px 7px;
      }
      .type-item:hover {
        background: get('bk');
        color: get('re-font-color');
      }
    }
  }
}
</style>
