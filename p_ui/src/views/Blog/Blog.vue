<template>
  <div class="blog-main page-main">
    <div class="blog-left-aside">
      <div class="blog-user bounceInLeft animated"><blog-user></blog-user></div>
      <div class="blog-type bounceInLeft animated"><blog-type></blog-type></div>
    </div>
    <div class="blog-main-aside bounceInUp animated">
      <div class="blog-news-up"><blog-news></blog-news></div>
      <div class="blog-news-down"><blog-news></blog-news></div>
    </div>
    <div class="blog-right-aside">
      <div class="blog-latest-articles bounceInRight animated">
        <div v-for="(blogData, index) in blogList" :key="index">
          <blog-article-item :blogData="blogData"></blog-article-item>
        </div>
      </div>
      <div class="blog-tags"></div>
    </div>
    <div>
      <blog-float-item></blog-float-item>
    </div>
  </div>
</template>

<script>
import BlogEditor from "./BlogEditor.vue";
import BlogUser from "./components/BlogUser.vue";
import BlogType from "./components/BlogType.vue";
import BlogNews from "./components/BlogNews.vue";
import BlogArticleItem from "./components/BlogArticleItem.vue";
import BlogFloatItem from "./components/BlogFloatItem.vue";
import { getBlogByUserId } from "@/api/blog";
export default {
  components: {
    BlogEditor,
    BlogUser,
    BlogType,
    BlogNews,
    BlogArticleItem,
    BlogFloatItem,
  },
  data() {
    return {
      blogList: [],
    };
  },
  methods: {
    getBlogData() {
      getBlogByUserId({ userId: this.$store.state.userId }).then((res) => {
        res.data ? (this.blogList = res.data) : "";
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
    this.getBlogData();
  },
};
</script>
<style lang="scss">
// .blog-type,
// .blog-left-aside,
// .blog-main-aside,
// .blog-right-aside {
//   box-shadow: 5px 5px 2px #888888;
// }
@keyframes rotate {
  100% {
    transform: rotate(1turn);
  }
}
.blog-main {
  // background-color: transparent;
  // width: 100%;
  // height: 100%;
  // display: flex;
  // position: relative;
  // z-index: 0;
  // margin: auto;
  // border-radius: 10px;
  // overflow-x: hidden;
  // box-shadow: 0px 0px 2px 2px #888888;
  // margin-bottom: 20px;
  // &::before {
  //   content: "";
  //   position: absolute;
  //   z-index: -2;
  //   left: -50%;
  //   top: -50%;
  //   width: 200%;
  //   height: 200%;
  //   background-color: #1a232a;
  //   background-repeat: no-repeat;
  //   background-position: 0 0;
  //   background-image: conic-gradient(
  //     transparent,
  //     rgba(168, 239, 255, 1),
  //     transparent 30%
  //   );
  //   animation: rotate 10s linear infinite;
  // }

  // &::after {
  //   content: "";
  //   position: absolute;
  //   z-index: -1;
  //   left: 6px;
  //   top: 6px;
  //   width: calc(100% - 12px);
  //   height: calc(100% - 12px);
  //   background: #000;
  //   border-radius: 5px;
  // }
}
.blog-latest-articles {
  // padding: 15px 10px;
}
.blog-latest-articles,
.blog-user,
.blog-type,
.blog-news-up,
.blog-news-down {
  // margin: 10px 15px 10px 5px;
  // box-shadow: 12px 5px 2px #888888;
  background-color: rgba(29, 32, 33, 0.6);
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 #000000;
  color: rgba(255, 255, 255, 0.6);
}
.blog-main-aside,
.blog-right-aside {
  // background-color: $BackGround;
  // height: auto;
  border-radius: 10px;
  // border: 1px solid #888888;
}
.blog-left-aside {
  margin: 30px 0 30px 12%;
  width: 17%;
}
.blog-main-aside {
  margin: 30px 30px 30px 30px;
  width: 40%;
}
.blog-right-aside {
  margin: 30px 12% 30px 0;
  width: 17%;
}
.blog-type {
  margin-top: 20px;
}
.blog-news-down {
  margin-top: 25px;
}
</style>
