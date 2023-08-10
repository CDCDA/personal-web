<!--
 * @Author: chenyd
 * @Date: 2023-01-04 10:41:53
 * @LastEditTime: 2023-07-11 21:59:33
 * @Description: 博客展示页
-->
<template>
  <div class="blog-display-main page-main">
    <div class="blog-display-left">
      <BlogUser class="blog-user"></BlogUser>
      <directory html="blog-display-main"></directory>
    </div>
    <div class="blog-display">
      <div class="blog-display-header">
        <h2 style="text-align: center" class="blog-title">
          {{ blogData && blogData.blogTitle ? blogData.blogTitle : '暂无' }}
        </h2>
        <div class="blog-label">
          <div class="blog-info">
            <span>{{ blogInfo.userName }}</span>
            <i class="el-icon-time">{{ blogData.blogCreateTime }}发布</i>
            <i class="el-icon-view">{{ ' ' + blogData.blogViewCount }}</i>
            <i class="el-icon-star-on">{{ ' ' + blogData.blogCollectCount }}</i>
          </div>
          <div style="display: flex; margin-top: 10px">
            <span>分类专栏：{{ blogData.blogType }}</span>
            <div style="margin-left: 10px">
              标签：
              <span
                v-for="(label, index) in blogData.blogLabelList"
                :key="index"
                style="margin-right: 10px"
              >
                <el-button size="mini">{{ label.name }}</el-button>
              </span>
            </div>
          </div>
          <el-divider></el-divider>
        </div>
      </div>
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
      <el-backtop target=".blog-display"></el-backtop>
    </div>

    <div class="blog-display-right">
      <blogArticle class="blog-article"></blogArticle>
      <BlogType class="blog-type"></BlogType>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import BlogUser from './components/BlogUser.vue';
import BlogType from './components/BlogType.vue';
import BlogNews from './components/BlogNews.vue';
import blogArticle from './components/BlogArticle.vue';
import directory from '@/components/Directory/directory.vue';
import { getBlogById } from '@/api/blog';

const router = useRouter();
const text = ref('asdasda');
const footerData = ref({} as any);
const blogList = ref([] as any);
const blogData = ref({} as any);
const blogInfo = ref({} as any);

function footerClick() {}

async function getBlog(id: any) {
  const { code, msg, data } = (await getBlogById(id)) as any;
  if (code === 200) {
    blogData.value = data;
  }
}

onMounted(() => {
  const { blogId } = router.currentRoute.value.query;
  console.log(blogId);
  getBlog(blogId);
});
</script>
<style lang="scss">
.v-md-editor-preview {
  color: white;
}
</style>
<style lang="scss">
.blog-display-main.page-main {
  justify-content: center;
  overflow: auto;
  display: flex;
  height: 100%;
  .blog-display-left,
  .blog-display-right {
    width: 225px;
  }
  .blog-user-component,
  .directory {
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    border-radius: 5px;
    margin-top: 5px;
    margin-bottom: 15px;
  }
  .blog-display {
    overflow: visible;
    height: fit-content;
    width: auto;
    color: #fff;
    text-align: left;
    padding: 0px 25px;
    margin: 0px 15px;
    margin-top: 5px;
    min-width: 700px;
    max-width: 800px;
    border-radius: 5px;
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    text-align: left;
    padding: 0px 30px;
    .blog-type {
      width: 225px;
    }
    th,
    td {
      border: 1px solid;
    }
    .v-md-pre-wrapper {
      background-color: transparent;
      box-shadow: 0 2px 12px 0 #000000;
      .v-md-hljs-language {
        padding: 10px;
      }
      code {
        color: #fff;
      }
    }
    .blog-info {
      span,
      i {
        margin-right: 15px;
      }
    }
    .blog-label {
      .el-button {
        padding: 3px 13px;
        font-size: 15px;
      }
    }
    .blog-display-footer {
      display: flex;
      align-items: center;
      justify-content: end;
      & > div {
        display: flex;
        align-items: center;
      }
      img {
        height: 25px;
        width: 25px;
        margin: 0px 5px;
      }
      .icon-unPraise {
        transform: rotate(180deg);
      }
      span {
        margin-right: 15px;
      }
    }
  }
  pre {
    background: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    padding: 10px 5px;
    font-size: 17px;
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
.page-main {
  overflow: auto;
}
</style>
