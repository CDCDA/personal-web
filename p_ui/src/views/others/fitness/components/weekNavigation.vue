<!--
 * @Description: 星期导航
-->
<template>
  <nav class="navbar">
    <ul class="navbar__menu">
      <li
        class="navbar__item"
        :class="item.isActive ? 'is-active' : ''"
        v-for="(item, i) in menu"
        @click="getIndex(item, i)"
      >
        <span class="navbar__link"
          >{{ item.week }}<span>{{ item.tip }}</span></span
        >
      </li>
    </ul>
  </nav>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
const menu = ref([
  {
    week: '星期一',
    tip: '胸',
    isActive: true
  },
  {
    week: '星期二',
    tip: '腹',
    isActive: false
  },
  {
    week: '星期三',
    tip: '胸',
    isActive: false
  },
  {
    week: '星期四',
    tip: '腹',
    isActive: false
  },
  {
    week: '星期五',
    tip: '胸',
    isActive: false
  },
  {
    week: '星期六',
    tip: '腹',
    isActive: false
  },
  {
    week: '星期天',
    tip: '躺',
    isActive: false
  }
] as any);
const emit = defineEmits(['getIndex']);

function getIndex(item: any, i: any) {
  emit('getIndex', i);
  menu.value.forEach((e: any) => {
    e.isActive = false;
  });
  item.isActive = true;
}

onMounted(() => {});
</script>

<style lang="scss" scoped>
@include theme() {
  .navbar {
    position: sticky;
    background: transparent;
    box-shadow: 0 0 40px rgba(0, 0, 0, 0.03);
    height: 100%;
    * {
      padding: 0;
      margin: 0;
      list-style-image: none;
      list-style-type: none;
    }
  }
  .navbar__link {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 3.5rem;
    width: 5.5rem;
    color: get('font-color');
    font-weight: bold;
    transition: 250ms ease all;
  }
  .navbar__link span {
    position: absolute;
    left: 100%;
    transform: translate(-3rem);
    margin-left: 1rem;
    opacity: 0;
    pointer-events: none;
    color: #406ff3;
    background: #fff;
    padding: 0.75rem;
    transition: 250ms ease all;
    border-radius: 17.5px;
  }
  .navbar__link:hover {
    color: #fff;
  }
  .navbar:not(:hover) .navbar__link:focus span,
  .navbar__link:hover span {
    opacity: 1;
    transform: translate(0);
    transition: none;
  }
  .navbar__menu {
    position: relative;
  }

  .navbar__item:last-child:before {
    content: '';
    position: absolute;
    opacity: 0;
    z-index: -1;
    top: 0;
    left: 1rem;
    width: 3.5rem;
    height: 3.5rem;
    background: get('bk');
    border-radius: 17.5px;
    transition: 250ms cubic-bezier(1, 0.2, 0.1, 1.2) all;
  }
  .navbar__item.is-active {
    .navbar__link {
      color: get('re-font-color');
    }
  }
  .navbar__item.is-active:nth-child(1)::after {
    top: 0%;
  }
  .navbar__item.is-active:nth-child(2)::after {
    top: calc(15% - 3px);
  }
  .navbar__item.is-active:nth-child(3)::after {
    top: calc(30% - 6px);
  }
  .navbar__item.is-active:nth-child(4)::after {
    top: calc(45% - 9px);
  }
  .navbar__item.is-active:nth-child(5)::after {
    top: calc(60% - 12px);
  }
  .navbar__item.is-active:nth-child(6)::after {
    top: calc(75% - 15px);
  }
  .navbar__item.is-active:nth-child(7)::after {
    top: calc(90% - 18px);
  }
  .navbar__item.is-active::after {
    content: '';
    position: absolute;
    opacity: 1;
    z-index: -1;
    left: 1rem;
    top: 0;
    width: 3.5rem;
    height: 3.5rem;
    background: get('bk');
    border-radius: 17.5px;
    transition: 250ms cubic-bezier(1, 0.2, 0.1, 1.2) all;
  }

  .navbar__item:first-child:nth-last-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(1) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(1):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(1) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(1):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(1) ~ li:last-child:hover:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(2) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(2):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(2) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(2):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(2) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(2):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(2) ~ li:last-child:hover:before {
    top: 50%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(3) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(3):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(3) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(3):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(3) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(3):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(3) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 33.3333333333%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(3):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(3) ~ li:last-child:hover:before {
    top: 66.6666666667%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(4):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(4):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(4):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 25%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(4):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 50%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(4):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(4) ~ li:last-child:hover:before {
    top: 75%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(5):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(5):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(5):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 20%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(5):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 40%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(5):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 60%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(5):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(5) ~ li:last-child:hover:before {
    top: 80%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(6):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(6):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 16.6666666667%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 33.3333333333%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 50%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 66.6666666667%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(6):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(6) ~ li:last-child:hover:before {
    top: 83.3333333333%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(7):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 14.2857142857%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 28.5714285714%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 42.8571428571%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 57.1428571429%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):nth-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:nth-child(6):hover ~ li:last-child:before {
    top: 71.4285714286%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(7):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(7) ~ li:last-child:hover:before {
    top: 85.7142857143%;
    animation: gooeyEffect-7 250ms 1;
  }
  @keyframes gooeyEffect-7 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(8):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 12.5%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 25%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 37.5%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 50%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(6):hover ~ li:last-child:before {
    top: 62.5%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):nth-child(7):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:nth-child(7):hover ~ li:last-child:before {
    top: 75%;
    animation: gooeyEffect-7 250ms 1;
  }
  @keyframes gooeyEffect-7 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(8):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(8) ~ li:last-child:hover:before {
    top: 87.5%;
    animation: gooeyEffect-8 250ms 1;
  }
  @keyframes gooeyEffect-8 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(9):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 11.1111111111%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 22.2222222222%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 33.3333333333%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 44.4444444444%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(6):hover ~ li:last-child:before {
    top: 55.5555555556%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(7):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(7):hover ~ li:last-child:before {
    top: 66.6666666667%;
    animation: gooeyEffect-7 250ms 1;
  }
  @keyframes gooeyEffect-7 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):nth-child(8):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:nth-child(8):hover ~ li:last-child:before {
    top: 77.7777777778%;
    animation: gooeyEffect-8 250ms 1;
  }
  @keyframes gooeyEffect-8 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(9):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(9) ~ li:last-child:hover:before {
    top: 88.8888888889%;
    animation: gooeyEffect-9 250ms 1;
  }
  @keyframes gooeyEffect-9 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(10):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 10%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 20%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 30%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 40%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(6):hover ~ li:last-child:before {
    top: 50%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(7):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(7):hover ~ li:last-child:before {
    top: 60%;
    animation: gooeyEffect-7 250ms 1;
  }
  @keyframes gooeyEffect-7 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(8):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(8):hover ~ li:last-child:before {
    top: 70%;
    animation: gooeyEffect-8 250ms 1;
  }
  @keyframes gooeyEffect-8 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):nth-child(9):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:nth-child(9):hover ~ li:last-child:before {
    top: 80%;
    animation: gooeyEffect-9 250ms 1;
  }
  @keyframes gooeyEffect-9 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(10):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(10) ~ li:last-child:hover:before {
    top: 90%;
    animation: gooeyEffect-10 250ms 1;
  }
  @keyframes gooeyEffect-10 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:hover ~ li:last-child:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(11):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:last-child:hover:before {
    opacity: 1;
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(1):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(1):hover ~ li:last-child:before {
    top: 0%;
    animation: gooeyEffect-1 250ms 1;
  }
  @keyframes gooeyEffect-1 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(2):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(2):hover ~ li:last-child:before {
    top: 9.0909090909%;
    animation: gooeyEffect-2 250ms 1;
  }
  @keyframes gooeyEffect-2 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(3):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(3):hover ~ li:last-child:before {
    top: 18.1818181818%;
    animation: gooeyEffect-3 250ms 1;
  }
  @keyframes gooeyEffect-3 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(4):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(4):hover ~ li:last-child:before {
    top: 27.2727272727%;
    animation: gooeyEffect-4 250ms 1;
  }
  @keyframes gooeyEffect-4 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(5):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(5):hover ~ li:last-child:before {
    top: 36.3636363636%;
    animation: gooeyEffect-5 250ms 1;
  }
  @keyframes gooeyEffect-5 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(6):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(6):hover ~ li:last-child:before {
    top: 45.4545454545%;
    animation: gooeyEffect-6 250ms 1;
  }
  @keyframes gooeyEffect-6 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(7):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(7):hover ~ li:last-child:before {
    top: 54.5454545455%;
    animation: gooeyEffect-7 250ms 1;
  }
  @keyframes gooeyEffect-7 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(8):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(8):hover ~ li:last-child:before {
    top: 63.6363636364%;
    animation: gooeyEffect-8 250ms 1;
  }
  @keyframes gooeyEffect-8 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(9):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(9):hover ~ li:last-child:before {
    top: 72.7272727273%;
    animation: gooeyEffect-9 250ms 1;
  }
  @keyframes gooeyEffect-9 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):nth-child(10):hover ~ li:last-child:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:nth-child(10):hover ~ li:last-child:before {
    top: 81.8181818182%;
    animation: gooeyEffect-10 250ms 1;
  }
  @keyframes gooeyEffect-10 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
  .navbar__item:first-child:nth-last-child(11):last-child:hover:before,
  .navbar__item:first-child:nth-last-child(11) ~ li:last-child:hover:before {
    top: 90.9090909091%;
    animation: gooeyEffect-11 250ms 1;
  }
  @keyframes gooeyEffect-11 {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
}
@import url('https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap');
</style>
