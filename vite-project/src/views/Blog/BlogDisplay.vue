<!--
 * @Description: 博客展示页
-->
<template>
  <wave></wave>
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
        <directory html="blog-display-main"></directory>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import wave from '@/views/layout/wave/index.vue';
import directory from '@/components/directory/directory.vue';
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
    margin: 30px 7% 0px 7% !important;
    min-height: 0px;
    .blog-display-left {
      width: calc(100% - 320px);
      border-radius: 15px;
      background: get('background');
      box-shadow: get('box-shadow');
      min-height: 300px;
    }
    .blog-display-right {
      width: 300px;
      margin-left: 20px;
      min-height: 300px;
      .directory {
        border-radius: 15px;
        background: get('background');
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
    // .blog-display-left {
    //   overflow: visible;
    //   height: fit-content;
    //   width: auto;
    //   color: get('font-color');
    //   text-align: left;
    //   padding: 0px 25px;
    //   margin: 0px 15px;
    //   margin-top: 5px;
    //   min-width: 700px;
    //   max-width: 800px;
    //   border-radius: 5px;
    //   background: get('background');
    //   box-shadow: get('box-shadow');
    //   text-align: left;
    //   padding: 0px 30px;
    //   .blog-type {
    //     width: 225px;
    //   }
    //   th,
    //   td {
    //     border: 1px solid;
    //   }
    //   .v-md-pre-wrapper {
    //     background: transparent;
    //     box-shadow: get('box-shadow');
    //     .v-md-hljs-language {
    //       padding: 10px;
    //     }
    //     code {
    //       color: get('font-color');
    //     }
    //   }
    //   .blog-info {
    //     span,
    //     i {
    //       margin-right: 15px;
    //     }
    //   }
    //   .blog-label {
    //     .el-button {
    //       padding: 3px 13px;
    //       font-size: 15px;
    //     }
    //   }
    //   .blog-display-footer {
    //     display: flex;
    //     align-items: center;
    //     justify-content: end;
    //     & > div {
    //       display: flex;
    //       align-items: center;
    //     }
    //     img {
    //       height: 25px;
    //       width: 25px;
    //       margin: 0px 5px;
    //     }
    //     .icon-unPraise {
    //       transform: rotate(180deg);
    //     }
    //     span {
    //       margin-right: 15px;
    //     }
    //   }
    // }
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
    padding: 16px 50px 32px;
    text-align: left;
    h2 {
      border-bottom: 1px solid get('font-color');
    }
    .line-numbers-mode:after {
      top: 41px;
      margin-top: 48px;
      height: calc(100% - 60px) !important;
    }
    .line-numbers-wrapper {
      top: 0px;
      margin-top: 35px;
    }
    .v-md-pre-wrapper {
      border-radius: 15px;
      font-size: 17px;
      .v-md-hljs-java {
        padding: 50px 30px 15px 75px;
        box-shadow: none;
        box-shadow: 0 1px 2px 0 #000000;
        border-radius: 15px;
        position: relative;
        z-index: 1;
      }
      .v-md-hljs-java:before {
        content: 'code';
        width: calc(100% - 60px);
        left: 30px;
        height: 40px;
        position: absolute;
        top: 0px;
        text-align: center;
        border-bottom: 1px solid;
        line-height: 40px;
        font-size: 22px;
        font-weight: bold;
      }
    }
  }
}
</style>
<style lang="scss">
.page-main {
  overflow: auto;
}
</style>
