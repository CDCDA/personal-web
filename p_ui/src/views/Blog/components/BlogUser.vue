<template>
  <div class="blog-user-component">
    <div class="user-image-container">
      <el-image class="user-image" :src="url"></el-image>
    </div>
    <span>{{ userName }}</span>
    <span>{{ userPosition }}</span>
    <span><i class="el-icon-map-location"></i>{{ userLocation }}</span>
    <div class="user-list">
      <div>
        <div><span>文章</span></div>
        <div class="number"><span>58</span></div>
      </div>
      <div>
        <div><span>分类</span></div>
        <div class="number"><span>10</span></div>
      </div>
      <div>
        <div><span>标签</span></div>
        <div class="number"><span>25</span></div>
      </div>
    </div>
    <el-button class="bt-concern" type="primary" size="mini">关注我</el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: require("@/assets/images/app-background2.webp"),
      userName: "Tom",
      userPosition: "Cat",
      userLocation: "Tom And Jerry",
    };
  },
};
</script>
<style lang="scss" scoped>
.user-image {
  width: 100px;
  height: 100px;
  border-radius: 50px;
  margin-top: 20px;
  color: $FontColor;
  display: block;
  margin: 0 auto;
}
span {
  color: $FontColor;
  display: block;
  height: 20px;
  margin-top: 15px;
}
.blog-user-component {
  text-align: center;
  // margin: 5px;
  border-radius: 5px;
  // background-color: $insideColor;
  // border: 1px solid #888888;
  color: #fff;
  width: 100%;
  span{
    color: #fff;
  }
}
.bt-concern {
  width: 80%;
  border-radius: 10px;
  margin-top: 25px;
  margin-bottom:30px;
}
::v-deep .el-button--mini {
  padding: 10px 0 8px 0;
}
.user-list {
  display: flex;
  text-align: center;
  justify-content: space-between;
  width: 70%;
  margin: 0 auto;
  margin-top: 15px;
}
.number {
  margin-top: 15px;
}
.user-image-container{
  padding-top: 25px;
}
</style>
