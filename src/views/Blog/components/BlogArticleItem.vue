<template>
  <div class="blog-article-item">
    <el-image :src="url" class="article-image"></el-image>
    <div class="article-item-right">
      <span class="article-date">{{ date }}</span>
      <span class="article-title">{{ title }}</span>
      <span class="article-type">{{ type }}</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: require("@/assets/images/app-background1.webp"),
      date: new Date(),
      title: "垂直水平居中方法汇总",
      type: "前端",
    };
  },
  methods: {},
};
</script>
<style lang="scss" scoped>
.blog-article-item {
  text-align: left;
  margin: 5px;
  border-radius: 5px;
  background-color: $insideColor;
  color: $FontColor;
  display: flex;
  justify-content: space-between;
}
.article-image {
  width: 50%;
  height: auto;
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
.article-item-right{
  margin-left: 5px;
}
</style>
