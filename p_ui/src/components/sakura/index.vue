<template>
  <div class="Sakura">
    <canvas id="canvas_sakura" ref="canvas_sakura" :style="{ zIndex: zIndex }"></canvas>
  </div>
</template>

<script>
class Sakura {
  constructor(x, y, s, r, fn, that) {
    this.x = x;
    this.y = y;
    this.s = s;
    this.r = r;
    this.fn = fn;
    this.that = that;
  }
  draw(cxt) {
    const img = new Image();
    img.src = new URL('../../assets/images/sakura.png', import.meta.url).href; //樱花效果图相对路径
    cxt.save();
    var xc = (40 * this.s) / 4;
    cxt.translate(this.x, this.y);
    cxt.rotate(this.r);
    cxt.drawImage(img, 0, 0, 40 * this.s, 40 * this.s);
    cxt.restore();
  }
  update() {
    this.x = this.fn.x(this.x, this.y);
    this.y = this.fn.y(this.y, this.y);
    this.r = this.fn.r(this.r);
    if (this.x > window.innerWidth || this.x < 0 || this.y > window.innerHeight || this.y < 0) {
      this.r = this.that.getRandom('fnr');
      if (Math.random() > 0.4) {
        this.x = this.that.getRandom('x');
        this.y = 0;
        this.s = this.that.getRandom('s');
        this.r = this.that.getRandom('r');
      } else {
        this.x = window.innerWidth;
        this.y = this.that.getRandom('y');
        this.s = this.that.getRandom('s');
        this.r = this.that.getRandom('r');
      }
    }
  }
}
class SakuraList {
  constructor() {
    this.list = [];
  }
  push(sakura) {
    this.list.push(sakura);
  }
  update() {
    for (var i = 0, len = this.list.length; i < len; i++) {
      this.list[i].update();
    }
  }
  draw(cxt) {
    for (var i = 0, len = this.list.length; i < len; i++) {
      this.list[i].draw(cxt);
    }
  }
  get(i) {
    return this.list[i];
  }
  size() {
    return this.list.length;
  }
}
export default {
  name: 'Sakura',
  props: ['newOptions'],
  data() {
    return {
      options: {
        staticx: false,
        stop: null,
        num: 10,
        show: true,
        zIndex: 999
      }
    };
  },
  mounted() {
    if (this.newOptions) {
      Object.assign(this.options, this.newOptions);
    }
    this.$nextTick(() => {
      if (this.options.show) {
        this.startSakura();
      }
    });
  },
  methods: {
    getRandom(option) {
      var ret, random;
      switch (option) {
        case 'x':
          ret = Math.random() * window.innerWidth;
          break;
        case 'y':
          ret = Math.random() * window.innerHeight;
          break;
        case 's':
          ret = Math.random();
          break;
        case 'r':
          ret = Math.random() * 6;
          break;
        case 'fnx':
          random = -0.5 + Math.random() * 1;
          ret = function (x, y) {
            return x + 0.5 * random - 1.7;
          };
          break;
        case 'fny':
          random = 1.5 + Math.random() * 0.7;
          ret = function (x, y) {
            return y + random;
          };
          break;
        case 'fnr':
          random = Math.random() * 0.03;
          ret = function (r) {
            return r + random;
          };
          break;
      }
      return ret;
    },
    startSakura() {
      let that = this;
      requestAnimationFrame =
        window.requestAnimationFrame ||
        window.mozRequestAnimationFrame ||
        window.webkitRequestAnimationFrame ||
        window.msRequestAnimationFrame ||
        window.oRequestAnimationFrame;
      var canvas = document.getElementById('canvas_sakura');
      this.options.staticx = true;
      this.$refs.canvas_sakura.width = window.innerWidth;
      this.$refs.canvas_sakura.height = window.innerHeight;
      var cxt = this.$refs.canvas_sakura.getContext('2d');
      var sakuraList = new SakuraList();
      for (var i = 0; i < that.options.num; i++) {
        let sakura, randomX, randomY, randomS, randomR, randomFnx, randomFny, randomFnR;
        randomX = this.getRandom('x');
        randomY = this.getRandom('y');
        randomR = this.getRandom('r');
        randomS = this.getRandom('s');
        randomFnx = this.getRandom('fnx');
        randomFny = this.getRandom('fny');
        randomFnR = this.getRandom('fnr');
        sakura = new Sakura(
          randomX,
          randomY,
          randomS,
          randomR,
          {
            x: randomFnx,
            y: randomFny,
            r: randomFnR
          },
          this
        );
        sakura.draw(cxt);
        sakuraList.push(sakura);
      }
      this.options.stop = requestAnimationFrame(function fn() {
        cxt.clearRect(0, 0, canvas.width, canvas.height);
        sakuraList.update();
        sakuraList.draw(cxt);
        that.options.stop = requestAnimationFrame(fn);
      });
    },
    stopp() {
      if (this.options.staticx) {
        var child = document.getElementById('canvas_sakura');
        child.parentNode.removeChild(child);
        window.cancelAnimationFrame(this.options.stop);
        this.options.staticx = false;
      } else {
        this.startSakura();
      }
    }
  }
};
</script>

<style scoped>
#canvas_sakura {
  pointer-events: none;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
}
</style>
