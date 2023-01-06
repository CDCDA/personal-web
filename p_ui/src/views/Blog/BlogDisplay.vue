<template>
  <div class="blog-display-container page-main">
    <div class="blog-display" v-html="html"></div>
    <blog-float-item :blogData="blogData"></blog-float-item>
  </div>
</template>

<script>
import BlogFloatItem from "./components/BlogFloatItem.vue";

export default {
  components: { BlogFloatItem },
  data() {
    return {
      html: "<p>暂无数据</div>",
      blogData: this.$route.query.blogData,
    };
  },
  methods: {},
  mounted() {
    console.log("QQQQ", this.$route.query.blogData);
    this.html = this.$route.query.blogData.blogMk;
  },
};
</script>
<style lang="scss">
.blog-display-container {
  .blog-display {
    margin: 20px 150px;
    width: calc(100% - 300px);
    color: #fff;
    background-color: rgba(29, 32, 33, 0.6);
    box-shadow: 0 2px 12px 0 #000000;
        text-align: left;
    padding: 0px 20%;
  }
  pre {
    background: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    padding: 10px 5px;
  }
}
</style>