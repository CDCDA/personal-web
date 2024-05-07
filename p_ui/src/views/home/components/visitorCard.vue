<!--
 * @Description: 访问人卡
-->
<template>
  <div class="visitor-card c-card" id="card">
    <div class="card-header">
      <svg-icon iconName="welcome" style="width: 20px; height: 20px; margin-right: 8px" /><span
        class="tag-name"
        >welcome</span
      >
    </div>
    <div class="visitor-location">
      欢迎来自
      <span>{{ ` ${visitorInfo.location?.regionName} ${visitorInfo.location?.city} ` }}</span
      >的小伙伴
    </div>
    <div class="visitor-distance">
      当前位置距离博主 <span>{{ ` ${visitorInfo.location.distance} ` }}</span
      >公里
    </div>
    <div class="visitor-ip">
      您的ip地址为<span>{{ ` ${visitorInfo.ip} ` }}</span>
    </div>
    <div class="visitor-salutation">{{ getSalutation() }}</div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue';
import { listByUserId } from '@/api/tag';
import useUserStore from '@/store/modules/user';
import axios from 'axios';
const visitorInfo = reactive({
  location: {
    distance: ''
  },
  ip: ''
} as any);

// 获取ip
async function getIp() {
  axios
    .get('https://api.ipify.org?format=json')
    .then(response => {
      visitorInfo.ip = response.data.ip; // 输出客户端IP地址
      getLocationByIp();
    })
    .catch(error => {
      console.log(error);
    });
}

// 获取地理位置
async function getLocationByIp() {
  const ip = visitorInfo.ip; // 替换为你要查询的IP地址

  axios
    .get(`http://ip-api.com/json/${ip}?lang=zh-CN`)
    .then(response => {
      visitorInfo.location = response.data;
      visitorInfo.location.distance = getDistance(
        39.9042,
        116.4074,
        visitorInfo.location.lat,
        visitorInfo.location.lon
      );
      // 在这里处理地理位置信息
    })
    .catch(error => {
      console.error(error);
      // 处理错误
    });
}

// 计算城市距离
function getDistance(lat1: any, lon1: any, lat2: any, lon2: any) {
  const R = 6371; // 地球半径，单位为千米

  const dLat = degreeToRadians(lat2 - lat1);
  const dLon = degreeToRadians(lon2 - lon1);

  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(degreeToRadians(lat1)) *
      Math.cos(degreeToRadians(lat2)) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);

  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

  const distance = R * c; // 距离，单位为千米
  return Math.floor(distance);
}

function degreeToRadians(deg: any) {
  return deg * (Math.PI / 180);
}

//获取问候语
function getSalutation() {
  const now = new Date();
  const currentHour = now.getHours();
  if (currentHour <= 6) {
    return '现在是~夜猫子时间！';
  } else if (currentHour > 6 && currentHour <= 12) {
    return '早上好 早起的虫子被鸟吃';
  } else if (currentHour > 12 && currentHour <= 18) {
    return '下午好 饮茶先啦';
  } else {
    return '晚上好 希望别熬夜，如果熬，建议通宵';
  }
}

function range(value: any, percent: any) {
  if (percent >= 0.5) {
    const p = (percent - 0.5) / 0.5;

    return -1 * value * p;
  }

  const p = (0.5 - percent) / 0.5;

  return value * p;
}

onMounted(() => {
  getIp();
});
</script>
<style lang="scss" scoped>
@include theme() {
  .visitor-card {
    text-align: left;
    padding: 20px 20px !important;
    width: calc(100% - 40px) !important;
    font-size: 18px;
    font-family: DaoLiTi;

    .card-header {
      @include flex;
      width: 100%;
      justify-content: start;
      font-size: 19px;
      font-weight: bold;
      .el-icon {
        margin: 8px;
        font-size: 22px;
      }
    }

    .visitor-location,
    .visitor-distance,
    .visitor-ip,
    .visitor-salutation {
      margin: 10px 0px;
    }
    .visitor-location span {
      color: #49b1f5;
    }
    .visitor-distance span {
      color: #e38100;
    }
    .visitor-ip span {
      color: #49b1f5;
    }
  }
}
</style>
