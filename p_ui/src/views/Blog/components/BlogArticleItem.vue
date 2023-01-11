<template>
  <div class="blog-article-item" @click="viewBlog(blogData)">
    <!-- <el-image :src="url" class="article-image"></el-image> -->
    <div class="article-item-right">
      <span class="article-title">{{ blogData.blogTitle }}</span>
      <span class="article-type">{{ blogData.blogType }}</span>
      <span class="article-date">{{ blogData.blogUpdateTime }}</span>
    </div>
  </div>
</template>

<script>
export default {
  props: ["blogData"],
  data() {
    return {
      url: require("@/assets/images/app-background1.webp"),
      date: new Date(),
      title: "垂直水平居中方法汇总",
      type: "前端",
    };
  },
  methods: {
    viewBlog(val) {
      this.$store.commit("setBlogData", this.blogData);
      this.$router.push({
        path: "/BlogDisplay",
        query: { blogData: val },
      });
    },
  },
};
</script>
<style lang="scss" scoped>
.blog-article-item {
  text-align: left;
  padding: 10px;
  border-radius: 5px;
  background-color: transparent;
  color: #fff;
  display: flex;
  justify-content: space-between;
  /* border: 1px solid; */
  margin: 5px 0px;
  background-color: rgba(29, 32, 33, 0.6);
}
.blog-article-item:hover {
  background-color: rgba(29, 32, 33, 1);
  box-shadow: 0 0px 12px 2px #000000;
  width: calc(100% - 16px);
  height: calc(100% + 10px);
  margin-left: -2px;
}
.article-image {
  width: 40%;
  height: auto;
  margin-right: 10px;
}
.article-date {
  font-size: 11px;
  display: block;
}
.article-title {
  font-size: 12px;
  display: block;
}
.article-type {
  font-size: 12px;
  display: block;
}
.article-item-right {
  margin-left: 5px;
  width: calc(100% - 10px);
  span {
    text-overflow: ellipsis;
    white-space: nowrap;
    width: 100%;
    overflow: hidden;
    text-align: left;
    height: 33%;
  }
  .article-date {
    color: #a29b9b;
    font-size: 12px;
  }
  .article-title {
    font-size: 15px;
    margin-bottom: 5px;
  }
  .article-type {
    color: #a29b9b;
    font-size: 12px;
  }
}
</style>
