<!--
 * @Description: 加载组件
-->
<template>
  <div
    class="loading-container c-loading"
    :class="[`loading-${state.loading.type}`, `${isClose ? 'close' : ''}`]"
    :style="{ display: `${isShow ? 'flex' : 'none'}` }"
  >
    <div class="gear-container-left" v-if="state.loading.type == 'gear'"></div>
    <div class="gear-container-right" v-if="state.loading.type == 'gear'"></div>
    <div class="container" v-if="state.loading.type == 'gear'">
      <div class="gearbox">
        <div class="overlay"></div>
        <div class="gear one">
          <div class="gear-inner">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
          </div>
        </div>
        <div class="gear two">
          <div class="gear-inner">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
          </div>
        </div>
        <div class="gear three">
          <div class="gear-inner">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
          </div>
        </div>
        <div class="gear four large">
          <div class="gear-inner">
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
            <div class="bar"></div>
          </div>
        </div>
      </div>
      <h1>Loading...</h1>
    </div>
    <div class="loader" v-if="state.loading.type == 'rotate'">
      <div class="inner one"></div>
      <div class="inner two"></div>
      <div class="inner three"></div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { reactive, ref, onMounted, watch } from 'vue';
const isClose = ref(false as boolean);
const FONT_SIZE = {
  samll: {
    icon: '16px',
    p: '12px'
  },
  default: {
    icon: '20px',
    p: '16px'
  },
  large: {
    icon: '24px',
    p: '20px'
  }
};

const isShow = ref(true as any);

const state = reactive({
  loading: {
    type: 'gear'
  }
} as any);
function updateInfo(options: any) {
  state.loading = {
    type: options.type
  };
}

watch(
  () => state.loading.type,
  val => {
    if (val == null || val == '' || val == false) {
      isShow.value = false;
    }
  },
  {
    deep: true
  }
);

defineExpose({ updateInfo });

onMounted(() => {
  if (state.loading.type == 'gear') {
    let container = (document as any).querySelector('.loading-gear') as any;
    if (container) {
      setTimeout(() => {
        isClose.value = true;
      }, 1000);
      setTimeout(() => {
        container.style.display = 'none';
      }, 3000);
    }
  }
});
</script>
<style lang="scss" scoped>
.loading-container {
  transition: all ease-in-out 5s linear;
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  width: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  color: #335dfd;
  :deep(.anticon-loading) {
    font-size: 20px;
  }
  p {
    margin-top: 10px;
    font-size: 16px;
  }
}
* {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

@-webkit-keyframes clockwise {
  0% {
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@-moz-keyframes clockwise {
  0% {
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -moz-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    -webkit-transform: rotate(360deg);
    transform: rotate(360deg);
  }
}
@-webkit-keyframes counter-clockwise {
  0% {
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -moz-transform: rotate(-360deg);
    -ms-transform: rotate(-360deg);
    -webkit-transform: rotate(-360deg);
    transform: rotate(-360deg);
  }
}
@-moz-keyframes counter-clockwise {
  0% {
    -moz-transform: rotate(0deg);
    -ms-transform: rotate(0deg);
    -webkit-transform: rotate(0deg);
    transform: rotate(0deg);
  }
  100% {
    -moz-transform: rotate(-360deg);
    -ms-transform: rotate(-360deg);
    -webkit-transform: rotate(-360deg);
    transform: rotate(-360deg);
  }
}
.container {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-left: -100px;
  height: 150px;
  width: 200px;
  margin-top: -75px;
}

.gearbox {
  background: #111;
  height: 150px;
  width: 200px;
  position: relative;
  border: none;
  overflow: hidden;
  -moz-border-radius: 6px;
  -webkit-border-radius: 6px;
  border-radius: 6px;
  -moz-box-shadow: 0px 0px 0px 1px rgba(255, 255, 255, 0.1);
  -webkit-box-shadow: 0px 0px 0px 1px rgba(255, 255, 255, 0.1);
  box-shadow: 0px 0px 0px 1px rgba(255, 255, 255, 0.1);
}
.gearbox .overlay {
  -moz-border-radius: 6px;
  -webkit-border-radius: 6px;
  border-radius: 6px;
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 10;
  -moz-box-shadow: inset 0px 0px 20px black;
  -webkit-box-shadow: inset 0px 0px 20px black;
  box-shadow: inset 0px 0px 20px black;
  -moz-transition: background 0.2s;
  -o-transition: background 0.2s;
  -webkit-transition: background 0.2s;
  transition: background 0.2s;
}
.gearbox.turn .overlay {
  background: transparent;
}

.gear {
  position: absolute;
  height: 60px;
  width: 60px;
  -moz-box-shadow: 0px -1px 0px 0px #888888, 0px 1px 0px 0px black;
  -webkit-box-shadow: 0px -1px 0px 0px #888888, 0px 1px 0px 0px black;
  box-shadow: 0px -1px 0px 0px #888888, 0px 1px 0px 0px black;
  -moz-border-radius: 30px;
  -webkit-border-radius: 30px;
  border-radius: 30px;
}
.gear.large {
  height: 120px;
  width: 120px;
  -moz-border-radius: 60px;
  -webkit-border-radius: 60px;
  border-radius: 60px;
}
.gear.large:after {
  height: 96px;
  width: 96px;
  -moz-border-radius: 48px;
  -webkit-border-radius: 48px;
  border-radius: 48px;
  margin-left: -48px;
  margin-top: -48px;
}
.gear.one {
  top: 12px;
  left: 10px;
}
.gear.two {
  top: 61px;
  left: 60px;
}
.gear.three {
  top: 110px;
  left: 10px;
}
.gear.four {
  top: 13px;
  left: 128px;
}
.gear:after {
  content: '';
  position: absolute;
  height: 36px;
  width: 36px;
  -moz-border-radius: 36px;
  -webkit-border-radius: 36px;
  border-radius: 36px;
  background: #111;
  top: 50%;
  left: 50%;
  margin-left: -18px;
  margin-top: -18px;
  z-index: 3;
  -moz-box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.1), inset 0px 0px 10px rgba(0, 0, 0, 0.1),
    inset 0px 2px 0px 0px #090909, inset 0px -1px 0px 0px #888888;
  -webkit-box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.1), inset 0px 0px 10px rgba(0, 0, 0, 0.1),
    inset 0px 2px 0px 0px #090909, inset 0px -1px 0px 0px #888888;
  box-shadow: 0px 0px 10px rgba(255, 255, 255, 0.1), inset 0px 0px 10px rgba(0, 0, 0, 0.1),
    inset 0px 2px 0px 0px #090909, inset 0px -1px 0px 0px #888888;
}

.gear-inner {
  position: relative;
  height: 100%;
  width: 100%;
  background: #555;
  -webkit-animation-iteration-count: infinite;
  -moz-animation-iteration-count: infinite;
  -moz-border-radius: 30px;
  -webkit-border-radius: 30px;
  border-radius: 30px;
  border: 1px solid rgba(255, 255, 255, 0.1);
}
.large .gear-inner {
  -moz-border-radius: 60px;
  -webkit-border-radius: 60px;
  border-radius: 60px;
}
.gear.one .gear-inner {
  -webkit-animation: counter-clockwise 3s infinite linear;
  -moz-animation: counter-clockwise 3s infinite linear;
}
.gear.two .gear-inner {
  -webkit-animation: clockwise 3s infinite linear;
  -moz-animation: clockwise 3s infinite linear;
}
.gear.three .gear-inner {
  -webkit-animation: counter-clockwise 3s infinite linear;
  -moz-animation: counter-clockwise 3s infinite linear;
}
.gear.four .gear-inner {
  -webkit-animation: counter-clockwise 6s infinite linear;
  -moz-animation: counter-clockwise 6s infinite linear;
}
.gear-inner .bar {
  background: #555;
  height: 16px;
  width: 76px;
  position: absolute;
  left: 50%;
  margin-left: -38px;
  top: 50%;
  margin-top: -8px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  border-left: 1px solid rgba(255, 255, 255, 0.1);
  border-right: 1px solid rgba(255, 255, 255, 0.1);
}
.large .gear-inner .bar {
  margin-left: -68px;
  width: 136px;
}
.gear-inner .bar:nth-child(2) {
  -moz-transform: rotate(60deg);
  -ms-transform: rotate(60deg);
  -webkit-transform: rotate(60deg);
  transform: rotate(60deg);
}
.gear-inner .bar:nth-child(3) {
  -moz-transform: rotate(120deg);
  -ms-transform: rotate(120deg);
  -webkit-transform: rotate(120deg);
  transform: rotate(120deg);
}
.gear-inner .bar:nth-child(4) {
  -moz-transform: rotate(90deg);
  -ms-transform: rotate(90deg);
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}
.gear-inner .bar:nth-child(5) {
  -moz-transform: rotate(30deg);
  -ms-transform: rotate(30deg);
  -webkit-transform: rotate(30deg);
  transform: rotate(30deg);
}
.gear-inner .bar:nth-child(6) {
  -moz-transform: rotate(150deg);
  -ms-transform: rotate(150deg);
  -webkit-transform: rotate(150deg);
  transform: rotate(150deg);
}

h1 {
  font-family: 'Helvetica';
  text-align: center;
  text-transform: uppercase;
  color: #888;
  font-size: 19px;
  padding-top: 10px;
  text-shadow: 1px 1px 0px #111;
}
</style>
<style scoped lang="scss">
html {
  height: 100%;
}

body {
  background-image: radial-gradient(circle farthest-corner at center, #3c4b57 0%, #1c262b 100%);
}

.loader {
  position: absolute;

  margin: auto;
  max-height: 200px;
  height: 100%;
  aspect-ratio: 1/1;
  max-width: 200px;
  border-radius: 50%;
  perspective: 800px;
}

.inner {
  position: absolute;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.loading-container {
  display: flex;
}

@keyframes toLeft {
  0% {
    left: 0px;
  }
  100% {
    left: -100%;
  }
}

@keyframes toRight {
  0% {
    right: 0px;
  }
  100% {
    right: -100%;
  }
}

@keyframes gearHidden {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  100% {
    transform: scale(0.8) translateY(20px);
    opacity: 0;
  }
}

.gear-container-left {
  width: 50%;
  height: 100%;
  background: #555;
  top: 0px;
  left: 0px;
  position: absolute;
}
.gear-container-right {
  width: 50%;
  height: 100%;
  top: 0px;
  right: 0px;
  background: #555;
  position: absolute;
}
.close.loading-container {
  background: transparent;
  .gear-container-left {
    animation: toLeft 2s linear forwards;
  }
  .gear-container-right {
    animation: toRight 2s linear forwards;
  }
  .container {
    animation: gearHidden 1s linear forwards;
  }
}
</style>
