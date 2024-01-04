<!--
 * @Description: 博客展示页
-->
<template>
  <wave :blogData="blogData"></wave>
  <div class="blog-display-main page-main">
    <div class="blog-diaplay">
      <div class="blog-display-left">
        <div class="blog-display-html">
          <v-md-preview :text="blogData.content"></v-md-preview>
        </div>

        <el-divider></el-divider>
        <div class="blog-display-footer">
          <div v-for="(item, index) in footerData" :key="index">
            <el-tooltip class="item" effect="dark" :content="item.content" placement="top">
              <img :src="item.imageUrl" :class="item.className" @click="footerClick()" />
            </el-tooltip>
            <span>{{ item.count }}</span>
          </div>
        </div>
        <el-divider></el-divider>
        <div class="blog-display-comment">
          <!-- <comment></comment> -->
        </div>
        <!-- <el-backtop target=".blog-display-left"></el-backtop> -->
      </div>

      <div class="blog-display-right">
        <BlogUserCard></BlogUserCard>
        <directory html="blog-display-main"></directory>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import wave from './components/wave/index.vue';
import directory from '@/components/directory/directory.vue';
import { getBlogById } from '@/api/blog';
import BlogUserCard from './components/blogUserCard.vue';

const router = useRouter();
const footerData = ref({} as any);
const blogData = ref({ coverUrl: '', tags: [] } as any);
const loading = ref('gear' as any);
function footerClick() {}

async function getBlog(id: any) {
  loading.value = true;
  const { code, msg, data } = (await getBlogById(id)) as any;
  if (code === 200) {
    blogData.value = data;
  }
  loading.value = false;
}

onMounted(() => {
  const { blogId } = router.currentRoute.value.query;
  getBlog(blogId);
});
</script>
<style lang="scss">
@keyframes blur-to-clear {
  0% {
    -webkit-filter: blur(50px);
    filter: blur(50px);
  }
  100% {
    -webkit-filter: blur(0);
    filter: blur(0);
  }
}

@keyframes display-in {
  0% {
    transform: scale(1.03) translateY(50px);
  }
  100% {
    transform: scale(1) translateY(0px);
  }
}

@include theme() {
  .blog-diaplay {
    width: 100%;
    display: flex;
  }
  .v-md-editor-preview {
    color: get('font-color');
  }
  .blog-display-main {
    @include flex;
    align-items: start;
    margin: 30px 7% 0px 7% !important;
    min-height: 0px;
    animation: display-in 2s linear forwards;
    .blog-display-left {
      width: calc(100% - 320px);
      border-radius: 15px;
      background: get('background');
      // box-shadow: get('box-shadow');
      min-height: 300px;
      // border: 1px solid #d1d1d1;
    }
    .blog-display-right {
      width: 298px;
      margin-left: 20px;
      min-height: 300px;

      .directory {
        border-radius: 15px;
        background: get('background-no-tp');
        box-shadow: get('box-shadow');
        margin: 0px;
      }
    }
    .blog-user-component,
    .directory {
      // background: get('background');
      box-shadow: get('box-shadow');
      border-radius: 5px;
      margin-top: 5px;
      margin-bottom: 15px;
    }
    pre {
      background: transparent;
      box-shadow: get('box-shadow');
      // padding: 10px 5px;
    }
    .el-backtop {
      right: 19% !important;
      bottom: 80px !important;
      border: 1px solid #dcdfe6;
      background: transparent;
      i {
        color: #dcdfe6;
      }
    }
  }
  .github-markdown-body {
    border-radius: 15px;
    text-align: left;
  }
}
</style>
<style lang="scss">
.page-main {
  overflow: auto;
}
</style>
