<!--
 * @Description: 
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-07-03 16:13:25
 * @LastEditTime: 2023-08-04 10:49:42
-->
<template>
  <div class="blog-tags">
    <div class="blog-tags-title">标签云</div>
    <template v-for="tag in tagList">
      <el-tag type="info" class="blog-tag">{{ tag }}</el-tag>
    </template>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listByUserId } from '@/api/tag';
import useUserStore from '@/store/modules/user';

const tagList = ref([] as any);
const userStore = useUserStore();
async function getList() {
  const { code, data } = (await listByUserId({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    tagList.value = data.list;
  }
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped>
.blog-tags {
  width: 100%;
  height: 100%;
  border-radius: 5px;
  background-color: transparent;
  color: #fff;
  padding-bottom: 20px;
  .blog-tags-title {
    padding: 10px 0px;
  }
  .blog-tag {
    margin: 5px 10px;
    background: transparent;
    padding: 0px 15px;
    color: white;
  }
}
</style>
