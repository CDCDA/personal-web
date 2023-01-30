<!--
 * @Author: chenyd
 * @Date: 2023-01-04 10:41:53
 * @LastEditTime: 2023-01-25 15:20:46
 * @Description: 博客展示页
-->
<template>
  <div class="blog-display-main page-main">
    <div class="blog-display-left">
      <blog-user-item></blog-user-item>
      <directory html="blog-display"></directory>
    </div>
    <div class="blog-display">
      <div class="blog-display-header">
        <h2 style="text-align: center" class="blog-title">{{ blogData.blogTitle }}</h2>
        <div class="blog-label">
          <div class="blog-info">
            <span>{{ blogInfo.userName }}</span>
            <i class="el-icon-time">{{ blogData.blogCreateTime }}发布</i>
            <i class="el-icon-view">{{ "554" }}</i>
            <i class="el-icon-star-on">{{ "143" }}</i>
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
      <div class="blog-display-html" v-html="html"></div>
      <el-divider></el-divider>
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
      <el-divider></el-divider>
      <div class="blog-display-comment">
        <comment></comment>
      </div>
    </div>
    
    <div class="blog-display-right">
      <div v-for="(blogData, index) in blogList" :key="index">
        <blog-article-item :blogData="blogData"></blog-article-item>
      </div>
    </div>
    <!-- <float-botton :blogData="blogData"></float-botton> -->
  </div>
</template>

<script>
import Comment from "bright-comment";
import BlogUserItem from "./components/BlogUser";
import Directory from "@/components/Directory/directory";
import FloatBotton from "./components/BlogFloatItem.vue";
import BlogArticleItem from "./components/BlogArticleItem.vue";
import { getBlogByUserId } from "@/api/blog";
import { getUserById } from "@/api/login";
export default {
  components: {
    BlogUserItem,
    Directory,
    FloatBotton,
    Comment,
    BlogArticleItem,
  },
  data() {
    return {
      html: "<h3>暂无数据</h3>",
      blogData: this.$route.query.blogData,
      blogList: [],
      blogInfo: [],
    };
  },
  methods: {
    getBlogData() {
      getBlogByUserId({ userId: this.$store.state.userId }).then((res) => {
        res.data ? (this.blogList = res.data) : "";
        this.blogList.length = 7;
      });
    },
    toBlogEdit() {
      this.$router.push({
        path: "/BlogEditor",
        query: { blogData: this.blogData },
      });
    },
  },
  mounted() {
    // console.log("QQQQ", this.$store.state.blogData);
    this.html = this.$route.query.blogData.blogMk;
    this.$store.commit("setBlogData", this.$route.query.blogData);
    this.getBlogData();
    getUserById({ userId: this.blogData.userId }).then((res) => {
      this.blogInfo = res.data;
    });
  },
  destroyed() {
    // this.$store.commit("setBlogData", null);
  },
  destroyed() {
    this.$store.commit("setBlogData", null);
  },
};
</script>
<style lang="scss">
.blog-display-main.page-main {
  overflow: auto;
  // display: block;
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
    min-width: 260px;
    // margin-left: 5%;
  }
  .blog-display-right {
    min-width: 260px;
    // margin-left: 5%;
  }
  .blog-display {
    overflow-y: auto;
    width: calc(90% - 630px);
    color: #fff;
    text-align: left;
    padding: 0px 25px;
    margin: 0px 15px;
    margin-top: 5px;
    min-width: 660px;
    /* max-width: 1200px; */
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
    text-align: left;
    padding: 0px 100px;
    th,
    td {
      border: 1px solid;
    }
    .blog-info {
      span,
      i {
        margin-right: 15px;
      }
      
    }
    .blog-label{
      .el-button{
        padding: 3px 13px;
        font-size: 15px;
      }
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
.page-main {
  overflow: auto;
}
</style>