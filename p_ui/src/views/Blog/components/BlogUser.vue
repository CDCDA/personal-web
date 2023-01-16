<!--
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-01-16 14:25:14
 * @Description: 
-->
<template>
  <div class="blog-user-component">
    <el-row class="blog-user-top">
      <el-col :span="8">
        <el-image class="user-image" :src="url"></el-image>
      </el-col>
      <el-col :span="16">
        <div>{{ userName }}</div>
        <div style="margin-top: 5px; font-size: 13px">
          入站时间：{{ arriveAge }}年
        </div>
      </el-col>
    </el-row>
    <el-row class="blog-user-count">
      <el-col :span="8">
        <div>文章</div>
        <div class="number">58</div>
      </el-col>
      <el-col :span="8">
        <div>分类</div>
        <div class="number">58</div>
      </el-col>
      <el-col :span="8"
        ><div>标签</div>
        <div class="number">58</div></el-col
      >
    </el-row>
    <el-row class="blog-user-bt">
      <el-button size="small" round>私信</el-button>
      <el-button size="small" round>关注</el-button>
    </el-row>
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
      arriveAge: "1",
    };
  },
};
</script>
<style lang="scss">
.blog-user-component {
  padding: 0px 0px 25px 0px;
  .user-image {
    width: 60px;
    height: 60px;
    border-radius: 30px;
    color: #fff;
    margin: 0 auto;
    margin-left: 10px;
  }
  .el-row {
    padding: 20px 20px 0px 20px;
  }
  .el-col {
    color: #fff;
  }
  .number {
    margin-top: 10px;
  }
  .blog-user-bt {
    display: flex;
    justify-content: space-between;
    .el-button--small.is-round {
      max-width: 120px;
      border-radius: 8px;
      width: 100px;
      min-width: 80px;
      background: transparent;
      border-color: #9d9d9d;
      color: #fff;
      i {
        font-size: 20px;
      }
    }
  }
  .blog-user-top {
    display: flex;
    align-items: center;
  }
}
</style>
