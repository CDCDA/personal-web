<!--
 * @Description: 评论组件
-->
<template>
  <div class="comment">
    <div class="comment-title"><svg-icon iconName="评论1"></svg-icon>评论</div>
    <commentEditor :type="props.type" @getList="getList" :relevanceId="props.relevanceId" />
    <div v-if="commentList.length != 0" v-cLoading="loading">
      <div class="comment-header">
        <span class="comment-count">{{ total }}条评论</span>
        <svg-icon iconName="刷新" @click="getList"></svg-icon>
      </div>
      <!-- <div class="c-divider" style="margin: 20px 20px -10px 20px"></div> -->
      <div class="comment-list">
        <div v-for="(item, i) in commentList">
          <commentItem :commentData="item" @getList="getList" :blogData="props.blogData" />
          <div
            v-if="i != commentList.length - 1"
            class="c-divider"
            style="margin: 15px 20px 0px 20px"
          ></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import commentEditor from './components/commentEditor.vue';
import commentItem from './components/commentItem.vue';
import { ref, onMounted, watch } from 'vue';
import { listTreeComment } from '@/api/comment';
const props = defineProps(['relevanceId', 'type', 'blogData']);
const commentList = ref([]) as any;
const loading = ref(false);
const total = ref(0);
const queryParams = ref({
  type: props.type,
  relevanceId: props.relevanceId
});

async function getList() {
  loading.value = true;
  const { code, data } = (await listTreeComment(queryParams.value)) as any;
  if (code == 200) {
    commentList.value = data.list;
    total.value = data.total;
  }
  loading.value = false;
}

watch(
  () => props.relevanceId,
  (val: any) => {
    if (!val) return;
    queryParams.value.relevanceId = val;
    getList();
  },
  { deep: true }
);

onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .comment {
    border-radius: 12px;
    // background: get('background');
    // padding: 20px;
    padding-bottom: 20px;
    width: calc(100% - 0px);
  }
  .comment-title {
    padding: 0px 22px;
    font-weight: bold;
    font-size: 22px;
    display: flex;
    width: 100%;
    justify-content: start;
    align-items: center;
    .svg-icon-wrap {
      height: 25px;
      width: 25px;
      margin-right: 10px;
    }
    // margin-bottom: 10px;
  }
  .comment-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: get('background');
    padding: 15px 22px 0px 22px;
    .comment-count {
      font-size: 20px;
      font-weight: bold;
    }
    .svg-icon-wrap {
      width: 20px;
      height: 20px;
      cursor: pointer;
    }
    .svg-icon-wrap:active {
      transform: translateY(2px);
    }
  }
  .comment-list {
    .comment-item {
    }
  }
}
</style>
