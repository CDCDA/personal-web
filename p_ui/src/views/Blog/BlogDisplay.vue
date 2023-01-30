<!--
 * @Author: chenyd
 * @Date: 2023-01-04 10:41:53
 * @LastEditTime: 2023-01-30 17:30:54
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
        <h2 style="text-align: center" class="blog-title">
          {{ blogData && blogData.blogTitle ? blogData.blogTitle : "暂无" }}
        </h2>
        <div class="blog-label">
          <div class="blog-info">
            <span>{{ blogInfo.userName }}</span>
            <i class="el-icon-time">{{ blogData.blogCreateTime }}发布</i>
            <i class="el-icon-view">{{ " " + blogData.blogViewCount }}</i>
            <i class="el-icon-star-on">{{ " " + blogData.blogCollectCount }}</i>
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
      <div class="blog-display-footer">
        <el-tooltip class="item" effect="dark" content="点赞" placement="top">
          <img src="@/assets/svg/icon-praise.svg" @click="parise"/>
        </el-tooltip>
        <span>{{ blogData.blogPraiseCount }}</span>
        <el-tooltip class="item" effect="dark" content="踩" placement="top">
          <img
            src="@/assets/svg/icon-praise.svg"
            style="transform: rotate(180deg)"
          />
        </el-tooltip>
        <span>{{ blogData.blogUnPraiseCount }}</span>
        <el-tooltip class="item" effect="dark" content="收藏" placement="top">
          <img src="@/assets/svg/icon-collection.svg" />
        </el-tooltip>
        <span>{{ blogData.blogCollectCount }}</span>
        <el-tooltip class="item" effect="dark" content="评论" placement="top">
          <img src="@/assets/svg/icon-comment.svg" />
        </el-tooltip>
        <span>{{ blogData.blogPraiseCount }}</span>
        <el-tooltip class="item" effect="dark" content="分享" placement="top">
          <img src="@/assets/svg/icon-share-full.svg" />
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
import {
  getBlogByUserId,
  addViewCount,
  addPraiseCount,
  decreasePraiseCount,
  addUnPraiseCount,
  decreaseUnPraiseCount,
  getPraiseCount,
  getUnPraiseCount,
} from "@/api/blog";
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
      blogData: null,
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
    addPraiseCount() {
      let blogData = this.blogData;
      addPraiseCount({ userId: blogData.userId, blogId: blogData.blogId });
    },
    addUnPraiseCount() {
      let blogData = this.blogData;
      addUnPraiseCount({ userId: blogData.userId, blogId: blogData.blogId });
    },
    decreasePraiseCount() {
      let blogData = this.blogData;
      decreasePraiseCount({ userId: blogData.userId, blogId: blogData.blogId });
    },
    decreaseUnPraiseCount() {
      let blogData = this.blogData;
      decreaseUnPraiseCount({
        userId: blogData.userId,
        blogId: blogData.blogId,
      });
    },
  },
  created() {
    this.blogData = this.$route.query.blogData;
    //判断你是否刷新
    if (!this.blogData.blogId) {
      this.blogData = JSON.parse(localStorage.getItem("blogDisplayData"));
      console.log(this.blogData);
    }
    this.html = this.blogData.blogMk;
    this.$store.commit("setBlogData", this.blogData);
    this.getBlogData();
    getUserById({ userId: this.blogData.userId }).then((res) => {
      this.blogInfo = res.data;
    });
    console.log(this.blogData)
    //浏览数+1
    addViewCount(this.blogData.blogId);
    getPraiseCount({ blogId: this.blogData.blogId }).then((res) => {
      this.blogData.blogPraiseCount = res.data;
    });
    getUnPraiseCount({ blogId: this.blogData.blogId }).then((res) => {
      this.blogData.blogPraiseCount = res.data;
    });
  },
  destroyed() {
    this.$store.commit("setBlogData", null);
    localStorage.setItem("blogDisplayData", JSON.stringify(this.blogData));
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
    width: 16%;
    max-width: 350px;
  }
  .blog-display-right {
    min-width: 260px;
    // margin-left: 5%;
    width: 16%;
    max-width: 350px;
  }
  .blog-display {
    overflow-y: auto;
    width: calc(68% - 230px);
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
      img {
        height: 25px;
        width: 25px;
        margin: 0px 5px;
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
}
.page-main {
  overflow: auto;
}
</style>