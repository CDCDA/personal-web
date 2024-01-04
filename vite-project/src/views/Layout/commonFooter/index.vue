<!--
 * @Description: 页脚
-->
<template>
  <div class="common-footer">
    <div class="footer-top"></div>
    <div class="footer-main">
      <div class="footer-col" v-for="item in menuData">
        <span>{{ item.label }}</span>
        <div class="footer-row" v-for="child in item.children" @click="routerTo(child)">
          {{ child.label }}
        </div>
      </div>
    </div>
    <div class="footer-bottom">
      <div>©2023 - 2023 By CCCC的小破站</div>
      <div>
        距离下一次英仙座流星雨还有{{ meteorDifference }}
        <el-image :src="require('@/assets/svg/shootingStar.svg')" />
      </div>
      <div>
        本站已运行{{ timeDifference }} <el-image :src="require('@/assets/svg/rocket.svg')" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import { secondDayDiff, monthDayDiff } from '@/utils/date';
import { menuData } from '../menuData.ts';
import { useRouter, onBeforeRouteUpdate } from 'vue-router';
const router = useRouter();
const beginDate = new Date('2023-9-1');
const time = ref('' as any);
const timeDifference = ref('' as any);
const meteorDifference = ref('' as any);
const weenkendDifference = ref('' as any);

// 计算下一次英仙座流星雨的时间
function calculateNextPerseidMeteorShower() {
  const now = new Date(); // 当前日期和时间
  const currentYear = now.getFullYear(); // 当前年份

  // 设置英仙座流星雨的典型活动期起始日期（每年的8月11日）
  const showerStartDate = new Date(currentYear, 7, 11); // 月份从0开始计数，所以8月为7

  // 如果当前日期已超过活动期起始日期，则下一次流星雨的时间为明年8月11日
  if (now > showerStartDate) {
    const nextYear = currentYear + 1;
    const nextShowerStartDate = new Date(nextYear, 7, 11);
    return nextShowerStartDate;
  }
  return showerStartDate;
}

function routerTo(item: any) {
  router.push({ name: item.name });
}

onMounted(() => {
  meteorDifference.value = monthDayDiff(new Date(), calculateNextPerseidMeteorShower());
  setInterval(() => {
    timeDifference.value = secondDayDiff(beginDate, new Date(), false);
  }, 1000);
});
</script>

<style lang="scss" scoped>
.common-footer {
  height: auto;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 0%, rgb(255 255 255 / 70%) 25%);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;

  .footer-top,
  .footer-main,
  .footer-bottom {
    width: 100%;
  }
  .footer-top {
    height: 50px;
  }
  .footer-bottom {
    @keyframes upDown {
      0% {
        transform: translateY(0);
      }
      25% {
        transform: translateY(-1px);
      }
      50% {
        transform: translateY(0);
      }
      75% {
        transform: translateY(1px);
      }
      100% {
        transform: translateY(0);
      }
    }
    margin: 30px 0px 30px 0px;
    & > div {
      margin: 8px 0px;
      @include flex;
      .el-image {
        height: 25px;
        margin-left: 8px;
        animation: upDown 2s infinite linear;
      }
    }
    & > div:nth-child(1) {
      font-size: 18px;
    }
  }
  .footer-main {
    height: auto;
    display: flex;
    align-items: flex-start;
    flex-direction: center;
    justify-content: center;
    // padding: 0px 20%;
    .footer-col {
      width: 15%;
      height: 100%;
      text-align: center;
      font-weight: bold;
      color: rgba(60, 60, 67, 0.8);
    }
    .footer-row {
      width: 100%;
      height: auto;
      margin: 15px 0px;
      font-weight: 400;
      font-size: 15px;
      cursor: pointer;
      color: #363636;
    }
  }
}
</style>
