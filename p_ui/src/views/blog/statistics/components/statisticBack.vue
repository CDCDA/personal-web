<!--
 * @Description: 博客统计折线图
-->
<template>
  <div class="statistics-wrap">
    <canvas id="statistic-canvas"></canvas>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
const orbitRadius = ref(0) as any;
const radius = ref(0) as any;
const orbitX = ref(0) as any;
const orbitY = ref(0) as any;
const timePassed = ref(0) as any;
const speed = ref(0) as any;
const alpha = ref(0) as any;
onMounted(() => {
  let canvas = document.getElementById('statistic-canvas') as any;
  setTimeout(() => {
    let ctx = canvas.getContext('2d');
    let w = (canvas.width = window.innerWidth * 0.8);
    let h = (canvas.height = window.innerHeight * 0.8);
    let hue = 217;
    let stars = [] as any;
    let count = 0;
    let maxStars = 1400;
    var canvas2 = document.createElement('canvas') as any,
      ctx2 = canvas2.getContext('2d') as any;
    canvas2.width = 100;
    canvas2.height = 100;
    var half = canvas2.width / 2,
      gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);
    gradient2.addColorStop(0.025, '#fff');
    gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');
    gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');
    gradient2.addColorStop(1, 'transparent');

    ctx2.fillStyle = gradient2;
    ctx2.beginPath();
    ctx2.arc(half, half, half, 0, Math.PI * 2);
    ctx2.fill();

    // End cache

    function random(min: any, max?: any) {
      if (arguments.length < 2) {
        max = min;
        min = 0;
      }

      if (min > max) {
        var hold = max;
        max = min;
        min = hold;
      }

      return Math.floor(Math.random() * (max - min + 1)) + min;
    }

    var Star = function () {
      orbitRadius.value = random(w / 2 - 50);
      radius.value = random(100, orbitRadius.value) / 10;
      orbitX.value = w / 2;
      orbitY.value = h / 2;
      timePassed.value = random(0, maxStars);
      speed.value = random(orbitRadius.value) / 100000;
      alpha.value = random(2, 10) / 10;

      count++;
      // stars[count] = this;
    };

    Star.prototype.draw = function () {
      var x = Math.sin(timePassed.value + 1) * orbitRadius.value + orbitX.value,
        y = (Math.cos(timePassed.value) * orbitRadius.value) / 2 + orbitY.value,
        twinkle = random(10);

      if (twinkle === 1 && alpha.value > 0) {
        alpha.value -= 0.05;
      } else if (twinkle === 2 && alpha.value < 1) {
        alpha.value += 0.05;
      }

      ctx.globalAlpha = alpha.value;
      ctx.drawImage(
        canvas2,
        x - radius.value / 2,
        y - radius.value / 2,
        radius.value,
        radius.value
      );
      timePassed.value += speed.value;
    };

    for (let i = 0; i < maxStars; i++) {
      Star();
    }

    function animation() {
      ctx.globalCompositeOperation = 'source-over';
      ctx.globalAlpha = 0.8;
      ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 1)';
      ctx.fillRect(0, 0, w, h);

      ctx.globalCompositeOperation = 'lighter';
      for (var i = 1, l = stars.length; i < l; i++) {
        stars[i].draw();
      }

      window.requestAnimationFrame(animation);
    }

    animation();
  }, 0);
});
</script>
<style lang="scss">
.statistics-wrap {
  width: 100%;
  height: 100%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #050c19;
}
#statistic-canvas {
  width: 60%;
  height: 60%;
}
</style>
