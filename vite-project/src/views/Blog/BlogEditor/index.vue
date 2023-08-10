<template>
  <div class="mavon-editor-container page-main">
    <div class="blog-editor-header">
      <el-button @click="blogSave" class="bt-save">保存</el-button>
      <el-input placeholder="请输入标题" v-model="blogData.blogTitle"></el-input>
      <el-button @click="openRelease" class="bt-release">发布</el-button>
    </div>
    <v-md-editor v-model="blogData.content"></v-md-editor>
    <BlogRelease :blog-data="blogData" ref="blogRelease"></BlogRelease>
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick, onMounted, watch } from 'vue';
import BlogRelease from './BlogRelease.vue';
import { ElMessage } from 'element-plus';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { getBlogById } from '@/api/blog';

const router = useRouter() as any;
const userStore = useUserStore();
const blogData = ref({
  tags: [],
  userId: userStore.userId,
  content: '',
  typeId: '',
  coverUrl: '',
  blogAbstract: ''
} as any);
const blogRelease = ref();
//保存
function blogSave() {}

function openRelease() {
  if (!blogData.value.blogTitle) {
    ElMessage.error('请输入博客标题');
    return;
  }
  blogRelease.value.open();
}

async function getBlogInfo(id: string) {
  const { code, msg, data } = (await getBlogById(id)) as any;
  if (code === 200 && data) {
    blogData.value = data;
  }
}

watch(
  () => router,
  val => {
    const { blogId } = val.currentRoute.value.query;
    console.log(blogId);
    if (blogId) {
      getBlogInfo(blogId);
    }
  },
  { deep: true, immediate: true }
);

onMounted(() => {
  // getBlogInfo(router.currentRoute.value.query.blogId);
});
</script>
<style lang="less">
.mavon-editor-container.page-main {
  color: #fff;
  display: flex;
  flex-direction: column;
  padding: 5px;
  height: calc(100% - 10px);
  overflow: auto;
  overflow-x: hidden;
  .v-md-editor__toc-nav-title {
    color: white;
  }
  .v-md-editor--fullscreen {
    background-color: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    backdrop-filter: blur(20px);
  }
  .v-md-editor {
    height: calc(100% - 40px);
    .v-md-pre-wrapper {
      background-color: transparent;
      box-shadow: 0 2px 12px 0 #000000;
      code {
        color: #fff;
      }
    }
    .CodeMirror-line::selection {
      background-color: yellow;
      color: black;
      text-align: left;
    }
    .CodeMirror-line {
      text-align: left;

      span {
        color: white;
      }
    }
    .github-markdown-body {
      text-align: left;
    }
    .v-md-editor__left-area-title {
      color: white;
    }
    border: 1px solid white;
    background-color: transparent;
    .v-md-editor__toolbar {
      border-bottom: 1px solid white;
      border-radius: 4px 4px 0px 0px;
      .v-md-editor__toolbar-item {
        color: white;
      }
      .v-md-editor__toolbar-item:hover {
        color: black;
      }
      .v-md-editor__toolbar-item--active {
        color: black;
      }
    }
    .v-md-editor__main {
      border-left: 1px solid white;
      .CodeMirror {
        background: transparent;
      }
      .CodeMirror-gutters {
        background: transparent;
        // border: 1px solid white;
      }
      .CodeMirror-activeline-background.CodeMirror-linebackground {
        background: transparent;
      }
    }
    .v-md-editor__left-area {
      border: none;
    }
  }
  .el-button {
    background-color: transparent;
    color: #fff;
  }
  .ml-1 {
    width: 100px;
  }
  .el-input__wrapper {
    background-color: transparent;
  }
  .el-input__inner {
    background-color: transparent;
    border: none;
    color: #fff;
    text-align: center;
  }
  .content-input-wrapper,
  .content-input,
  .auto-textarea-input,
  .v-show-content {
    background-color: transparent !important;
    text-align: left;
  }

  .blog-editor-footer {
    margin-top: 15px;
  }
  .CodeMirror-cursor {
    border-color: white;
  }
  .blog-editor-header {
    display: flex;
    margin-bottom: 5px;
    .bt-save {
      margin-right: 5px;
      border: 1px solid #fff;
    }
    .bt-release {
      margin-left: 5px;
      border: 1px solid #fff;
    }
  }
}
</style>
