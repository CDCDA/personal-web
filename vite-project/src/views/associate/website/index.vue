<!--
 * @Description:  关于本站
-->
<template>
  <div class="website page-main">
    <h2><svg-icon iconName="鸡尾酒" />关于CCCC的小破站 为什么会有这个网站？</h2>
    <p>1. 一个原因是很酷，大概像是小时候想要的秘密基地那种感觉</p>
    <p>2. 然后就是记录一些工作啊生活啊之类的东西</p>
    <p>3. 这个网站是我全程手搓的，的确很有成就感，也是一个练手学习的过程吧</p>
    <h2><svg-icon iconName="可乐" />用户协议</h2>
    <p>1. 可自由转载本站文章且无需询问作者，但完整转载请标明出处</p>
    <p>2. 本站不会有任何广告等商业行为，如发现付费转载欢迎向作者反馈或点点举报</p>
    <h2><svg-icon iconName="棒棒糖" />隐私政策</h2>
    <p>1. 本站会采集访客的ip地址，以及可能的邮箱地址</p>
    <p>2. 本人不会以任何形式共享或出卖访客的个人信息</p>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
</script>
<style lang="scss" scoped>
@include theme() {
  .website {
    color: get('font-color');
  }
}
@keyframes right-top {
  0% {
    transform: translate(0, 0, 0);
  }
  100% {
    transform: translate3d(2px, -2px, 0);
  }
}
@keyframes left-bottom {
  0% {
    transform: translate3d(2px, -2px, 0);
  }
  100% {
    transform: translate(0, 0, 0);
  }
}
.website {
  h2 {
    width: 100%;
    text-align: left;
    font-family: 'Press Start 2P', cursive;
    font-size: 32px;
    cursor: pointer;
  }
  h2:hover {
    animation: right-top 0.2s linear forwards;
  }
  h2:not(:hover) {
    animation: left-bottom 0.2s linear forwards;
  }
  p {
    width: 100%;
    font-family: 'Press Start 2P', cursive;
    text-align: left;
    font-size: 20px;
  }
  .svg-icon-wrap {
    height: 40px;
    width: 40px;
    display: inline-block;
    margin-right: 10px;
  }
}

@include theme() {
  .update-log-top {
    box-shadow: get('box-shadow');
  }
  .page-main.website {
    @include flex-column;
    justify-content: start;
    background: get('background');
    border-radius: 15px;
    padding-bottom: 50px;
    padding: 40px 14% 50px 14%;
    width: 58% !important;
    box-shadow: get('box-shadow');
  }
}
</style>
