<!--
 * @Author: chenyd
 * @Date: 2023-01-04 10:41:53
 * @LastEditTime: 2023-01-16 17:40:02
 * @Description: 博客展示页
-->
<template>
  <div class="blog-display-container page-main">
    <div class="blog-display-left">
      <blog-user-item></blog-user-item>
      <directory html="blog-display-html"></directory>
    </div>
    <div class="blog-display">
      <div class="blog-display-header">
        <h2 style="text-align: center" class="blog-title">{{ "QWEQWEEQW" }}</h2>
        <div class="blog-label">
          <div>
            <span>{{ "用户名" }}</span>
            <i class="el-icon-time">于{{ "2022-10-05" }}发布</i>
            <i class="el-icon-view">{{ "554" }}</i>
            <i class="el-icon-star-on">{{ "143" }}</i>
          </div>
          <div style="display: flex; margin-top: 5px">
            <span>分类专栏：{{ "分类" }}</span>
            <div style="margin-left: 10px">
              标签： <el-button size="mini">标签1</el-button
              ><el-button size="mini">标签2</el-button
              ><el-button size="mini">标签3</el-button>
            </div>
          </div>
        </div>
      </div>
      <div class="blog-display-html" v-html="html"></div>
      <div style="text-align: right" class="blog-display-footer">
        <el-tooltip class="item" effect="dark" content="点赞" placement="top">
          <div style="width: 20px; height: 20px; overflow: hidden"></div>
          <img src="@/assets/svg/icon-praise.svg" class="icon1" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="踩" placement="top">
          <i class="el-icon-star-on"></i>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="收藏" placement="top">
          <i class="el-icon-star-on"></i>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="评论" placement="top">
          <i class="el-icon-chat-line-square"></i>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="分享" placement="top">
          <i class="el-icon-share"></i>
        </el-tooltip>
      </div>
      <div class="blog-display-comment">
        <comment></comment>
      </div>
    </div>
    <div class="blog-display-right"></div>
    <!-- <float-botton :blogData="blogData"></float-botton> -->
  </div>
</template>

<script>
import Comment from "bright-comment";
import BlogUserItem from "./components/BlogUser";
import Directory from "@/components/Directory/directory";
import FloatBotton from "./components/BlogFloatItem.vue";
export default {
  components: { BlogUserItem, Directory, FloatBotton, Comment },
  data() {
    return {
      html: "<h3>暂无数据</h3>",
      blogData: this.$route.query.blogData,
    };
  },
  methods: {},
  created() {
    console.log("WER", this.$route.query.blogData.blogMk);
    this.html = this.$route.query.blogData.blogMk;
    this.$store.commit("setBlogData", this.$route.query.blogData);
  },
  mounted() {},
  destroyed() {
    // this.$store.commit("setBlogData", null);
  },
};
</script>
<style lang="scss">
.blog-display-container {
  overflow: auto;
  .blog-display {
    border-radius: 5px;
  }
  .blog-user-component,
  .directory {
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    border-radius: 5px;
    margin-top: 5px;
  }
  .directory {
    margin-top: 15px;
  }
  .blog-display-left {
    width: 300px;
    margin-left: 5%;
  }
  .blog-display-right {
    width: 300px;
    margin-right: 5%;
  }
  .blog-display {
    overflow-y: auto;
    width: calc(90% - 630px);
    color: #fff;
    text-align: left;
    padding: 0px 25px;
    margin: 0px 15px;
    margin-top: 5px;
    min-width: 750px;
    max-width: 1200px;
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    th,
    td {
      border: 1px solid;
    }
  }
  pre {
    background: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    padding: 10px 5px;
    font-size: 17px;
  }
  .icon1 {
    height: 20px;
    width: 20px;
    g polyline {
      stroke: #fff;
    }
  }
}
</style>