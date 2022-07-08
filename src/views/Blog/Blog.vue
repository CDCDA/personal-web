<template>
  <div class="blog-main">
    <div class="blog-left-aside">
      <div class="blog-user"><blog-user></blog-user></div>
      <div class="blog-type"><blog-type></blog-type></div>
    </div>
    <div class="blog-main-aside">
      <div class="bolg-news-up"><blog-news></blog-news></div>
      <div class="bolg-news-down"><blog-news></blog-news></div>
    </div>
    <div class="blog-right-aside">
      <div class="bolg-latest-articles">
        <template v-for="i in 5">
          <blog-article-item :key="i"></blog-article-item>
        </template>
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
    return {};
  },
};
</script>
<style>
.blog-main {
  background-color: #0F1224;
  width: 80%;
  height: 100%;
  display: flex;
  margin: 0 auto;
}
.blog-left-aside,
.blog-main-aside,
.blog-right-aside {
  background-color: #0F1224;
  height: auto;
  border-radius: 10px;
}
.blog-left-aside {
  margin: 30px 0 30px 150px;
  width: 20%;
}
.blog-main-aside {
  margin: 30px 30px 30px 30px;
  width: 40%;
}
.blog-right-aside {
  margin: 30px 150px 30px 0;
  width: 20%;
}
.blog-type {
  margin-top: 20px;
}
.bolg-news-down {
  margin-top: 25px;
}
</style>
