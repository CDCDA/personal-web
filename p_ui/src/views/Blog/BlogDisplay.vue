<!--
 * @Author: chenyd
 * @Date: 2023-01-04 10:41:53
 * @LastEditTime: 2023-01-31 15:08:50
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
        <div v-for="(item, index) in footerData" :key="index">
          <el-tooltip
            class="item"
            effect="dark"
            :content="item.content"
            placement="top"
          >
            <img
              :src="item.imageUrl"
              :class="item.className"
              @click="footerClick(item)"
            />
          </el-tooltip>
          <span>{{ item.count }}</span>
        </div>
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
  isPraised,
  isUnPraised,
  getBlogByBlogId,
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
      params: {},
      footerData: [
        {
          type: "1",
          content: "点赞",
          imageUrl: require("@/assets/svg/icon-praise.svg"),
          count: 0,
          className: "icon-praise",
        },
        {
          type: "2",
          content: "踩",
          imageUrl: require("@/assets/svg/icon-praise.svg"),
          count: 0,
          className: "icon-unPraise",
        },
        {
          type: "3",
          content: "收藏",
          imageUrl: require("@/assets/svg/icon-collection.svg"),
          count: 0,
          className: "icon-collection",
        },
        {
          type: "4",
          content: "评论",
          imageUrl: require("@/assets/svg/icon-comment.svg"),
          count: 0,
          className: "icon-comment",
        },
        {
          type: "5",
          content: "分享",
          imageUrl: require("@/assets/svg/icon-share-full.svg"),
          count: "",
          className: "icon-share",
        },
      ],
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
      addPraiseCount(this.params);
    },
    addUnPraiseCount() {
      addUnPraiseCount(this.params);
    },
    decreasePraiseCount() {
      decreasePraiseCount(this.params);
    },
    decreaseUnPraiseCount() {
      decreaseUnPraiseCount(this.params);
    },
    footerClick(item) {
      let data = this.blogData;
      switch (item.type) {
        case "1":
          if (!data.isPraised) {
            this.addPraiseCount();
            item.imageUrl = require("@/assets/svg/icon-praise-full.svg");
            item.count++;
            data.blogPraiseCount++;
            data.isPraised = true;
            if (data.isUnPraised) {
              let unPraiseItem = this.footerData.find((e) => {
                return e.type == "2";
              });
              this.decreaseUnPraiseCount();
              unPraiseItem.imageUrl = require("@/assets/svg/icon-praise.svg");
              unPraiseItem.count--;
              data.blogUnPraiseCount--;
              data.isUnPraised = false;
            }
          } else {
            this.decreasePraiseCount();
            item.imageUrl = require("@/assets/svg/icon-praise.svg");
            item.count--;
            data.blogPraiseCount--;
            data.isPraised = false;
          }
          break;
        case "2":
          if (!data.isUnPraised) {
            this.addUnPraiseCount();
            item.imageUrl = require("@/assets/svg/icon-praise-full.svg");
            item.count++;
            data.blogUnPraiseCount++;
            data.isUnPraised = true;
            if (data.isPraised) {
              let praiseItem = this.footerData.find((e) => {
                return e.type == "1";
              });
              this.decreasePraiseCount();
              praiseItem.imageUrl = require("@/assets/svg/icon-praise.svg");
              praiseItem.count--;
              data.blogPraiseCount--;
              data.isPraised = false;
            }
          } else {
            this.decreaseUnPraiseCount();
            item.imageUrl = require("@/assets/svg/icon-praise.svg");
            item.count--;
            data.blogUnPraiseCount--;
            data.isUnPraised = false;
          }
          break;
        default:
          break;
      }
    },
    initFooterData() {
      let data = this.blogData;
      this.footerData.forEach((e) => {
        if (e.type == "1") {
          e.count = this.blogData.blogPraiseCount || 0;
          if (data.isPraised) {
            e.imageUrl = require("@/assets/svg/icon-praise-full.svg");
          }
        }
        if (e.type == "2") {
          e.count = this.blogData.blogUnPraiseCount || 0;
          if (data.isUnPraised) {
            e.imageUrl = require("@/assets/svg/icon-praise-full.svg");
          }
        }
        if (e.type == "3") {
          e.count = this.blogData.blogCollectionCount || 0;
        }
        if (e.type == "4") {
          e.count = this.blogData.blogCommentCount || 0;
        }
      });
    },
    async init() {
      this.blogData = this.$route.query.blogData;
      //判断你是否刷新
      if (!this.blogData.blogId) {
        await getBlogByBlogId({
          blogId: localStorage.getItem("displayBlogId"),
        }).then((res) => {
          this.blogData = res.data;
        });
      }
      (this.params.userId = this.$store.state.userId),
        (this.params.blogId = this.blogData.blogId),
        (this.html = this.blogData.blogMk);

      await this.getBlogData();
      getUserById({ userId: this.blogData.userId }).then((res) => {
        this.blogInfo = res.data;
      });
      //浏览数+1
      await addViewCount({ blogId: this.blogData.blogId });
      //查询点赞数
      await getPraiseCount({ blogId: this.blogData.blogId }).then((res) => {
        this.blogData.blogPraiseCount = res.data;
      });
      //是否已点赞
      await isPraised(this.params).then((res) => {
        res.data != 0
          ? (this.blogData.isPraised = true)
          : (this.blogData.isPraised = false);
      });
      //是否已点踩
      await isUnPraised(this.params).then((res) => {
        res.data != 0
          ? (this.blogData.isUnPraised = true)
          : (this.blogData.isUnPraised = false);
      });
      //查询点踩数
      await getUnPraiseCount({ blogId: this.blogData.blogId }).then((res) => {
        this.blogData.blogUnPraiseCount = res.data;
      });
      this.$store.commit("setBlogData", this.blogData);
      this.$nextTick(() => {
        this.initFooterData();
      });
    },
  },
  created() {
    this.init();
  },
  destroyed() {
    this.$store.commit("setBlogData", null);
    localStorage.setItem("displayBlogId", this.blogData.blogId);
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
}
.page-main {
  overflow: auto;
}
</style>