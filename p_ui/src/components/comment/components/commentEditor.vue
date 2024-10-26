<!--
 * @Description: 评论发送模块
-->
<template>
  <div class="comment-editor">
    <el-form class="comment-editor-header" :inline="true">
      <c-image class="comment-editor-avatar" :src="commentData.avatar"></c-image>
      <el-form-item label="昵称">
        <el-input v-model="commentData.nickName"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="commentData.email"></el-input>
      </el-form-item>
      <el-form-item label="网址">
        <el-input v-model="commentData.webUrl"></el-input>
      </el-form-item>
    </el-form>
    <editor v-model="commentData.comment" />
    <div class="comment-editor-footer">
      <el-button class="sent" @click="submit">发送</el-button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import editor from '@/components/editor/index.vue';
import { getOSAndBrowser } from '@/utils/common.ts';
import { saveComment } from '@/api/comment.ts';
import { ElNotification } from 'element-plus';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const emit = defineEmits(['getList', 'closeEditor']);
const props = defineProps(['relevanceId', 'type', 'parentId']);
const oSAndBrowser = getOSAndBrowser();
const commentData = ref({
  comment: '', //评论内容
  type: props.type, //评论分类
  os: oSAndBrowser.os, //操作系统
  browser: oSAndBrowser.browser, //浏览器
  nickName: '', //昵称
  email: '', //邮箱
  webUrl: '', //博客网站地址
  avatar: '', //头像
  relevanceId: props.relevanceId, //关联id
  userId: userStore.userId,
  parentId: props.parentId
});

async function submit() {
  commentData.value.relevanceId = props.relevanceId;
  commentData.value.type = props.type;
  commentData.value.parentId = props.parentId;
  const { code, data } = (await saveComment(commentData.value)) as any;
  if (code == 200) {
    ElNotification.success('评论发布成功');
    commentData.value.comment = '';
    emit('getList');
    emit('closeEditor');
  }
}
onMounted(() => {
  commentData.value.avatar = userStore.avatar;
  commentData.value.nickName = userStore.nickName;
  commentData.value.email = userStore.email;
});
</script>
<style lang="scss" scoped>
@include theme() {
  .comment-editor {
    height: 268px;
    background: get('background');
    border-radius: 8px;
    padding: 20px;
    .sent {
      background: rgb(131, 123, 199) !important;
      color: white !important;
      border: none !important;
    }
    :deep(.w-e-scroll) {
      * {
        text-align: left;
      }
    }
    .comment-editor-avatar {
      width: 40px;
      height: 40px;
      border-radius: 20px;
      margin-right: 10px;
      margin-left: 4px;
      display: flex;
      align-items: center;
    }
    .comment-editor-header {
      display: flex;
      justify-content: start;
      margin-bottom: 3px;
      padding-bottom: 10px;
      align-items: center;
      :deep(.el-form-item) {
        margin-right: 0px;
        margin-left: 10px;
        margin-bottom: 0px !important;
        width: calc(33.33% - 18px);
      }
    }
    .c-editor-wrap {
      height: 180px;
    }
    .comment-editor-footer {
      margin-top: 12px;
      display: flex;
      justify-content: end;
    }
  }
}
</style>
