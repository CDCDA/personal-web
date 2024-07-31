<template>
  <div class="mavon-editor-container page-main">
    <div class="blog-editor-header">
      <el-button @click="blogSave" class="bt-save">保存</el-button>
      <el-input class="blog-title-editor" placeholder="请输入标题" v-model="blogData.blogTitle" />
      <el-button @click="openRelease" class="bt-release">发布</el-button>
    </div>
    <MdEditor v-model="blogData.content" />
    <BlogRelease :blog-data="blogData" ref="blogRelease" @resetBlogData="resetBlogData" />
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick, onMounted, watch } from 'vue';
import { MdEditor } from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import BlogRelease from './blogRelease.vue';
import { ElNotification } from 'element-plus';
import useUserStore from '@/store/modules/user';
import { useRouter } from 'vue-router';
import { getBlogById } from '@/api/blog';
// import App from '@/App.vue';

//绑定值          modelValue:''
//主题            theme:'light' | 'dark'
//类名            class:''
//语言            language:'zh-CN','en-US'
//编辑器唯一标识   editorId:'md-editor-v3'
//是否显示行号     showCodeRowNumber:false | true
//预览内容主题     previewTheme: 'default' | 'github' | 'vuepress' | 'mk-cute' | 'smart-blue' | 'cyanosis'
//编辑器内联样式   style:''
//图表展示         noMermaid:false | true
//数学公式         noKatex :false | true
//代码块高亮样式   codeTheme:'atom'|'a11y'|'github'|'gradient'|'kimbie'|'paraiso'|'qtcreator'|'stackoverflow'
//编辑器内联样式   style:''
//图表展示         noMermaid:false | true

const router = useRouter() as any;
const userStore = useUserStore();
const loading = ref('rotate' as any);
const blogData = ref({
  tags: [],
  userId: userStore.userId,
  content: '',
  typeId: '',
  coverUrl: '',
  blogAbstract: ''
} as any);
const blogRelease = ref();

function resetBlogData() {
  blogData.value = ref({
    tags: [],
    userId: userStore.userId,
    content: '',
    typeId: '',
    coverUrl: '',
    blogAbstract: ''
  } as any);
}

//保存博客数据到本地
function blogSave() {
  window.localStorage.setItem('blogData', JSON.stringify(blogData.value));
  ElNotification.success('保存成功');
}

function openRelease() {
  if (!blogData.value.blogTitle) {
    ElNotification.error('请输入博客标题');
    return;
  }
  blogRelease.value.open();
}

async function getBlogInfo(id: string) {
  if (!id) return;
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
  let { blogId } = router.currentRoute.value.query;
  let tempBlogData = window.localStorage.getItem('blogData');
  if (blogId) {
    getBlogInfo(blogId);
  } else if (tempBlogData) {
    blogData.value = JSON.parse(tempBlogData);
  }
});
</script>
<style lang="scss">
@include theme() {
  .mavon-editor-container.page-main {
    background: transparent !important;
    backdrop-filter: none !important;
    color: get('font-color');
    display: flex;
    flex-direction: column;
    min-height: 86vh !important;
    overflow: auto;
    overflow-x: hidden;
    border-radius: 0px !important;
    .md-editor__toc-nav-title {
      color: get('font-color');
    }
    .md-editor--fullscreen {
      // background: get('background');
      box-shadow: get('box-shadow');
      backdrop-filter: blur(20px);
      border-radius: 0px !important;
    }
    #md-editor-v3 {
      border-radius: 5px;
      overflow: hidden;
    }
    .md-editor {
      height: calc(86vh - 35px);
      border-radius: 5px;
      overflow: hidden;
      box-shadow: get('box-shadow');
    }
    .cm-content.cm-lineWrapping,
    .md-editor-preview-wrapper {
      text-align: left;
    }
    .ml-1 {
      width: 100px;
    }
    .blog-title-editor {
      .el-input__inner {
        text-align: center;
        font-size: 16px;
      }
      .el-input__wrapper {
        height: 30px !important;
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
