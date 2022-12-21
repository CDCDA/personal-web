<template>
  <div class="blog-news-component">
    <el-image class="news-image" :src="url"></el-image>
    <div class="identification-container">
      <i class="el-icon-date"></i>
      <span slot="date">{{ date }}</span>
      <span slot="type">{{ type }}</span>
    </div>
    <div class="news-title"><span>垂直水平居中方法汇总</span></div>
    <div class="news-introduction">
      <span>垂直水平居中方法汇总</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: require("@/assets/images/app-background1.webp"),
      date: new Date(),
      type: "前端",
    };
  },
  methods: {},
};
</script>
<style lang="scss" scoped>
.blog-news-component {
  text-align: left;
  border-radius: 5px;
  background-color: transparent;
  color: #fff;
  padding-bottom: 7px;
}
.identification-container {
  font-size: 11px;
  margin: 5px 0 0 10px;
}
.news-title {
  margin: 5px 0 0 10px;
  font-size: 15px;
}
.news-introduction {
  margin: 5px;
  font-size: 13px;
}
.news-image {
  border-radius: 5px;
}
</style>
