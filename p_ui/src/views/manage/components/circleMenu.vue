<!--
 * @Description: 管理页按钮栏
-->
<template>
  <div class="circle-menu">
    <div class="navigation-circle">
      <div class="navigation-circle__inner">
        <svg
          class="navigation-circle-svg navigation-circle-svg--opaque"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          x="0px"
          y="0px"
          viewbox="0 0 320 320"
          style="enable-background: new 0 0 320 320; height: 100%; width: 100%"
        >
          <circle
            cx="250"
            cy="250"
            r="248"
            fill="none"
            stroke-width="3"
            stroke="#c644fc"
            stroke-linecap="round"
            stroke-miterlimit="10"
            style="stroke-dashoffset: 0; stroke-dasharray: none"
          ></circle>
        </svg>
        <svg
          class="navigation-circle-svg navigation-circle-svg--mask"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          x="0px"
          y="0px"
          viewbox="0 0 320 320"
          style="enable-background: new 0 0 320 320; height: 100%; width: 100%"
        >
          <circle
            id="mask-circle"
            cx="250"
            cy="250"
            r="248"
            fill="none"
            stroke-width="5"
            stroke="#c644fc"
            stroke-linecap="round"
            stroke-miterlimit="10"
            style="stroke-dasharray: 1005.3088px"
          ></circle>
        </svg>
        <ul class="navigation-circle__list">
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(1)"
              @onmouseenter="calculate(1)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #1</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(2)"
              @onmouseenter="calculate(2)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="label"><img src="" /></div>
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #2</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(3)"
              @onmouseenter="calculate(3)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #3</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(4)"
              @onmouseenter="calculate(4)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #4</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(5)"
              @onmouseenter="calculate(5)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #5</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(6)"
              @onmouseenter="calculate(6)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #6</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
          <li class="navigation-circle-list-item">
            <div
              class="navigation-circle-list-item__point"
              @click="onClick(7)"
              @onmouseenter="calculate(7)"
              @onMouseLeave="onMouseLeave()"
            >
              <div class="navigation-circle-list-item__meta">
                <h3 class="navigation-circle-list-item__title">Item #7</h3>
                <h4 class="navigation-circle-list-item__subtitle">It just goes round and round</h4>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
const pointCount = 7;
const circleRadius = 250;
const startAnimDelta = 5;
const circumference = Math.PI * circleRadius * 2;
var selectedItemIndex = -1;
const circlePath = ref(null) as any;

function calculate(index: number = 0) {
  let offset = 0;
  if (index !== 0) {
    offset = (circumference / pointCount) * (pointCount - index);
  }
  circlePath.value.style.strokeDashoffset = `${offset}px`;
}

const onMouseLeave = () => {
  let index = selectedItemIndex !== -1 ? selectedItemIndex : 0;
  calculate(index);
};

function onClick(index: any) {
  //If already selected, deselect
  selectedItemIndex = selectedItemIndex === index ? -1 : index;
  calculate(index);

  //Find active item, deselect
  let activeListItem = document.querySelectorAll('.navigation-circle-list-item.active');
  if (activeListItem.length > 0) activeListItem[0].classList.remove('active');

  //Find new item by index, select
  let listItem = document.querySelectorAll(
    '.navigation-circle-list-item:nth-of-type(' + selectedItemIndex + ')'
  );
  if (listItem.length > 0) listItem[0].classList.add('active');
}

onMounted(() => {
  circlePath.value = document.getElementById('mask-circle') as any;

  // INTRO

  let buffer = 500;
  let delay = 1000 * (1 + pointCount / startAnimDelta - 1 / startAnimDelta) + buffer;

  setTimeout(() => onClick(1), delay);
});
</script>

<style lang="scss" scoped>
* {
  margin: 0;
  padding: 0;
  border: 0;
  outline: none;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

.circle-menu {
  display: flex;
  height: 100vh;
  width: 100vw;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.navigation-circle {
  display: block;
  position: relative;
  height: 500px;
  width: 500px;
  margin: auto;
}
.navigation-circle__inner {
  display: block;
  position: relative;
  height: 100%;
  width: 100%;
}
.navigation-circle__list {
  display: block;
  position: absolute;
  height: 500px;
  width: 500px;
  transform: rotate(-90deg);
  animation: 2.2s cubic-bezier(0.25, -0.25, 0.35, 1) animate-in-list forwards;
}

.navigation-circle-svg {
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  transform: rotateZ(-90deg);
}
.navigation-circle-svg--opaque {
  opacity: 0.5;
}
.navigation-circle-svg--mask circle {
  transition: all 0.5s ease;
  transition-delay: 0.5s;
  animation: 2.2s ease 1 animate-in-svg-circle-mask backwards;
}

.navigation-circle-list-item {
  display: block;
  position: absolute;
  height: 0;
  width: calc(63% + 35px);
  top: 50%;
  left: 50%;
  list-style: none;
  transform-origin: 0 0;
}
.navigation-circle-list-item__point {
  display: block;
  position: absolute;
  height: 70px;
  width: 70px;
  top: -16px;
  right: 70px;
  cursor: pointer;
  transform: scale(0);
}
.navigation-circle-list-item__point .label {
  display: block;
  position: absolute;
  height: 70px;
  width: 70px;
  top: 0;
  color: #c644fc;
  background-color: #1f1f21;
  font-size: 20px;
  font-weight: 400;
  line-height: 32px;
  text-align: center;
  border: 2px solid #c644fc;
  border-radius: 50%;
  box-shadow: inset 0px 0px 0px 0px #c644fc;
  transform: scale(0.75);
  transition: all 0.5s ease;
}
//.navigation-circle-list-item__point:after {
//  content: '';
//  display: block;
//  position: absolute;
//  height: 1px;
//  width: 0px;
//  top: 18px;
//  left: 31px;
//  background-color: #c644fc;
//  transition: all 0.5s ease;
//}
.navigation-circle-list-item__meta {
  display: block;
  position: absolute;
  overflow: hidden;
  opacity: 0;
  transform-origin: center;
  margin-left: 78px;
  min-width: 68px;
  padding: 4px;
  font-size: 1rem;
  width: 8rem;
}
.navigation-circle-list-item__title {
  display: block;
  color: #f7f7f7;
  text-align: left;
  border-bottom: 1px solid #f7f7f7;
  padding-bottom: 4px;
  margin-bottom: 6px;
}
.navigation-circle-list-item__subtitle {
  display: block;
  color: #f7f7f7;
  font-weight: 200;
  text-align: left;
}

.navigation-circle-list-item:nth-of-type(1) {
  transform: rotateZ(calc((360deg / 7) * 1));
}

.navigation-circle-list-item:nth-of-type(1) .navigation-circle-list-item__meta {
  transform: rotateZ(38.5714285714deg);
}

.navigation-circle-list-item:nth-of-type(1) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 0s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(2) {
  transform: rotateZ(calc((360deg / 7) * 2));
}

.navigation-circle-list-item:nth-of-type(2) .navigation-circle-list-item__meta {
  transform: rotateZ(-12.8571428571deg);
}

.navigation-circle-list-item:nth-of-type(2) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 0.2s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(3) {
  transform: rotateZ(calc((360deg / 7) * 3));
}

.navigation-circle-list-item:nth-of-type(3) .navigation-circle-list-item__meta {
  transform: rotateZ(-64.2857142857deg);
}

.navigation-circle-list-item:nth-of-type(3) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 0.4s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(4) {
  transform: rotateZ(calc((360deg / 7) * 4));
}

.navigation-circle-list-item:nth-of-type(4) .navigation-circle-list-item__meta {
  transform: rotateZ(-115.7142857143deg);
}

.navigation-circle-list-item:nth-of-type(4) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 0.6s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(5) {
  transform: rotateZ(calc((360deg / 7) * 5));
}

.navigation-circle-list-item:nth-of-type(5) .navigation-circle-list-item__meta {
  transform: rotateZ(-167.1428571429deg);
}

.navigation-circle-list-item:nth-of-type(5) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 0.8s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(6) {
  transform: rotateZ(calc((360deg / 7) * 6));
}

.navigation-circle-list-item:nth-of-type(6) .navigation-circle-list-item__meta {
  transform: rotateZ(-218.5714285714deg);
}

.navigation-circle-list-item:nth-of-type(6) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 1s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:nth-of-type(7) {
  transform: rotateZ(calc((360deg / 7) * 7));
}

.navigation-circle-list-item:nth-of-type(7) .navigation-circle-list-item__meta {
  transform: rotateZ(-270deg);
}

.navigation-circle-list-item:nth-of-type(7) .navigation-circle-list-item__point {
  animation: 1s cubic-bezier(0.55, -0.3, 0.6, 1.5) 1.2s 1 animate-in-list-item-point forwards;
}

.navigation-circle-list-item:hover .navigation-circle-list-item__point:before {
  transform: scale(1);
  font-size: 16px;
}

.navigation-circle-list-item:hover .navigation-circle-list-item__point:after {
  width: 32px;
  left: 34px;
}

.navigation-circle-list-item:hover .navigation-circle-list-item__meta {
  opacity: 1;
}

.navigation-circle-list-item:active .navigation-circle-list-item__point:before,
.navigation-circle-list-item.active .navigation-circle-list-item__point:before {
  transform: scale(0.85);
  color: #1f1f21;
  box-shadow: inset 0 0 0 3px #c644fc;
  border-color: transparent;
}

.navigation-circle-list-item:active .navigation-circle-list-item__point:after,
.navigation-circle-list-item.active .navigation-circle-list-item__point:after {
  width: 34px;
  left: 32px;
}

.navigation-circle-list-item:active .navigation-circle-list-item__meta,
.navigation-circle-list-item.active .navigation-circle-list-item__meta {
  opacity: 1;
}

@keyframes animate-in-list {
  0% {
    transform: rotate(-540deg);
  }
  100% {
    transform: rotate(-90deg);
  }
}
@keyframes animate-in-svg-circle-mask {
  0% {
    stroke-dashoffset: 1005;
  }
  100% {
    stroke-dashoffset: 0;
  }
}
@keyframes animate-in-list-item-point {
  0% {
    transform: scale(0);
  }
  100% {
    transform: scale(1);
  }
}
</style>
