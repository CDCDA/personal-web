<!--
 * @Description: 数据统计 
-->
<template>
  <div class="page-main statistic-main">
    <div class="total">
      <div class="total-col">
        <div>文章数</div>
        <div>{{ '11' }}</div>
      </div>
      <div class="total-col">
        <div>随笔数</div>
        <div>{{ '11' }}</div>
      </div>
      <div class="total-col">
        <div>分类数</div>
        <div>{{ '11' }}</div>
      </div>
      <div class="total-col">
        <div>随笔数</div>
        <div>{{ '11' }}</div>
      </div>
      <div class="total-col">
        <div>网站更新数</div>
        <div>{{ '11' }}</div>
      </div>
    </div>
    <div class="chart">
      <h3>博客统计图</h3>
      <blogCountChart style="width: 100%; height: 100%" />
    </div>
    <div class="chart">
      <h3>博客统计图</h3>
      <blogTypeRadar style="width: 100%; height: 100%" />
    </div>
    <div class="chart" style="height: 300px">
      <h3>博客统计图</h3>
      <blogTypeChart style="width: 100%; height: 100%" />
    </div>
  </div>
</template>
<script setup lang="ts">
import blogCountChart from './components/blogCountChart.vue';
import blogTypeRadar from './components/blogTypeRadar.vue';
import blogTypeChart from './components/blogTypeChart.vue';
</script>
<style lang="scss" scoped>
@include theme() {
  .statistic-main {
    background: get('background');
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    border-radius: 15px;
  }
  .total {
    border: 2px solid get('bk');
  }
}
.total {
  width: calc(60%);
  height: 150px;
  margin-top: 80px;
  border-radius: 15px;
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 0px 5%;
  .total-col {
    div:nth-child(1) {
      font-weight: bold;
      font-size: 20px;
      margin-bottom: 30px;
    }
    div:nth-child(2) {
      font-weight: bold;
      font-size: 18px;
    }
  }
}
.chart {
  height: 200px;
  width: 80%;
  margin: 30px;
}
</style>
