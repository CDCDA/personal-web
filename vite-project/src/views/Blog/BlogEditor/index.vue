<template>
  <div class="mavon-editor-container page-main">
    <div class="blog-editor-header">
      <el-button @click="blogSave" class="bt-save">保存</el-button>
      <el-input class="blog-title-editor" placeholder="请输入标题" v-model="blogData.blogTitle" />
      <el-button @click="openRelease" class="bt-release">发布</el-button>
    </div>
    <v-md-editor v-model="blogData.content" />
    <BlogRelease :blog-data="blogData" ref="blogRelease" />
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick, onMounted, watch } from 'vue';
import BlogRelease from './blogRelease.vue';
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
<style lang="scss">
@include theme() {
  .mavon-editor-container.page-main {
    color: get('font-color');
    display: flex;
    flex-direction: column;
    padding: 5px;
    min-height: 86vh !important;
    overflow: auto;
    overflow-x: hidden;
    .v-md-editor__toc-nav-title {
      color: get('font-color');
    }
    .v-md-editor--fullscreen {
      // background: get('background');
      box-shadow: get('box-shadow');
      backdrop-filter: blur(20px);
      border-radius: 0px !important;
    }
    .v-md-editor {
      height: calc(86vh - 35px);
      border-radius: 5px;
      overflow: hidden;
      box-shadow: get('box-shadow');
      .v-md-pre-wrapper {
        // background: get('background');
        box-shadow: get('box-shadow');
        code {
          color: get('font-color');
        }
      }
      .CodeMirror-line::selection {
        background: yellow;
        color: black;
        text-align: left;
      }
      .CodeMirror-line {
        text-align: left;

        span {
          color: get('font-color');
        }
      }
      .github-markdown-body {
        text-align: left;
      }
      .v-md-editor__left-area-title {
        color: get('font-color');
      }
      border: 1px solid white;
      background: get('background');
      .v-md-editor__toolbar {
        // border-bottom: 1px solid white;
        border-radius: 4px 4px 0px 0px;
        background: get('background-no-tp');
        // box-shadow: get('box-shadow');
        .v-md-editor__toolbar-item {
          color: get('font-color');
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
          background: get('background');
        }
        .CodeMirror-gutters {
          background: get('background-no-tp');
          border: 1px solid #d1d1d1;
        }
        .CodeMirror-activeline-background.CodeMirror-linebackground {
          background: get('background');
        }
      }
      .v-md-editor__left-area {
        border: none;
      }
    }

    .ml-1 {
      width: 100px;
    }
    .blog-title-editor {
      .el-input__inner {
        text-align: center;
        font-size: 16px;
      }
    }

    .content-input-wrapper,
    .content-input,
    .auto-textarea-input,
    .v-show-content {
      background: transparent !important;
      text-align: left;
    }

    .blog-editor-footer {
      margin-top: 15px;
    }
    .CodeMirror-cursor {
      border-color: get('font-color');
    }
    .blog-editor-header {
      display: flex;
      margin-bottom: 10px;
      .bt-save {
        margin-right: 10px;
        width: 100px;
        height: 35px;
        font-size: 16px;
      }
      .bt-release {
        margin-left: 10px;
        width: 100px;
        height: 35px;
        font-size: 16px;
      }
    }
  }
}
</style>
