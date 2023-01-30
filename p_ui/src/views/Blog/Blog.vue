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
    <!-- <div>
      <blog-float-item></blog-float-item>
    </div> -->
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
@keyframes rotate {
  100% {
    transform: rotate(1turn);
  }
}
.blog-main {
  overflow: auto;
  .blog-latest-articles {
    // padding: 15px 10px;
  }
  .blog-latest-articles,
  .blog-user,
  .blog-type,
  .blog-news-up,
  .blog-news-down {
    background-color: rgba(29, 32, 33, 0.6);
    border-radius: 10px;
    box-shadow: 0 2px 12px 0 #000000;
    color: rgba(255, 255, 255, 0.6);
  }
  .blog-main-aside,
  .blog-right-aside {
    border-radius: 10px;
  }
  .blog-left-aside {
    margin: 30px 0 30px 12%;
    width: 17%;
    min-width: 250px;
    max-width: 350px;
  }
  .blog-main-aside {
    margin: 30px 30px 30px 30px;
    width: 40%;
    min-width: 450px;
  }
  .blog-right-aside {
    margin: 30px 12% 30px 0;
    width: 17%;
    min-width: 250px;
    max-width: 350px;
  }
  .blog-type {
    margin-top: 20px;
  }
  .blog-news-down {
    margin-top: 25px;
  }
}
</style>
