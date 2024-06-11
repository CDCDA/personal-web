<!--
 * @Description: 天气卡
-->
<template>
  <div class="weather-card c-card">
    <v-mini-weather>
      <template #default="{ weather, icon }">
        <!--插入图标-->
        <v-mini-weather-icon :icon="icon"></v-mini-weather-icon>
        <!--DIY内容-->
        <span>{{ weather.cityname }}/{{ weather.weather }}/{{ weather.temp }}</span>
      </template>
    </v-mini-weather>
  </div>
</template>
<script setup lang="ts">
import { onMounted } from 'vue';
import { vMiniWeather, vMiniWeatherIcon } from 'vue3-mini-weather';
onMounted(() => {
  // const windowData = window as any;
  // windowData.WIDGET = {
  //   CONFIG: {
  //     layout: '2',
  //     width: 230,
  //     height: 270,
  //     background: '1',
  //     dataColor: 'FFFFFF',
  //     city: '101230103',
  //     key: '923e3789e7c74e07bef0edd9481149e2'
  //   }
  // };
  // var script = document.createElement('script');
  // script.type = 'text/javascript';
  // script.src = 'https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0';
  // document.getElementsByTagName('head')[0].appendChild(script);
});
</script>
<style lang="scss">
@include theme() {
  .weather-card {
    border-radius: 15px;
    margin-bottom: 20px;
    padding: 0px !important;
    width: 300px !important;
  }
  #he-plugin-standard {
    background: transparent !important;
    width: 100% !important;
    height: 300px !important;
    border-radius: 15px !important;
    padding: 10px 15px;
    span,
    a {
      color: get('font-color') !important;
      font-size: 16px !important;
    }
    .wv-lt-col-5 {
      display: none;
    }
    .wv-lt-location {
      font-size: 18px;
      font-weight: bold;
    }
  }
}
</style>
