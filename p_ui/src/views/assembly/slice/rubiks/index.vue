<!--
 * @Description: 魔方
-->
<template>
  <div class="ui">
    <div class="ui__background"></div>
    <div class="ui__game" style="height: calc(100% - 20px); bottom: 0; top: initial"></div>
    <div class="ui__texts">
      <h1 class="text text--title" style="opacity: 1">
        <span
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">T</i
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">H</i
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">E</i></span
        >
        <span
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">C</i
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">U</i
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">B</i
          ><i style="opacity: 1; transform: rotate3d(0, 1, 0, 0deg)">E</i></span
        >
      </h1>
      <div class="text text--note" style="opacity: 0.999789">双击魔方即可开始</div>
      <div class="text text--timer">0:00</div>
      <div class="text text--complete">
        <span>Complete!</span>
      </div>
      <div class="text text--best-time">
        <svg class="icon" viewBox="0 0 576 512" style="width: 1.125em; height: 1em">
          <path
            fill="currentColor"
            d="M552 64H448V24c0-13.3-10.7-24-24-24H152c-13.3 0-24 10.7-24 24v40H24C10.7 64 0 74.7 0 88v56c0 66.5 77.9 131.7 171.9 142.4C203.3 338.5 240 360 240 360v72h-48c-35.3 0-64 20.7-64 56v12c0 6.6 5.4 12 12 12h296c6.6 0 12-5.4 12-12v-12c0-35.3-28.7-56-64-56h-48v-72s36.7-21.5 68.1-73.6C498.4 275.6 576 210.3 576 144V88c0-13.3-10.7-24-24-24zM64 144v-16h64.2c1 32.6 5.8 61.2 12.8 86.2-47.5-16.4-77-49.9-77-70.2zm448 0c0 20.2-29.4 53.8-77 70.2 7-25 11.8-53.6 12.8-86.2H512v16zm-127.3 4.7l-39.6 38.6 9.4 54.6c1.7 9.8-8.7 17.2-17.4 12.6l-49-25.8-49 25.8c-8.8 4.6-19.1-2.9-17.4-12.6l9.4-54.6-39.6-38.6c-7.1-6.9-3.2-19 6.7-20.5l54.8-8 24.5-49.6c4.4-8.9 17.1-8.9 21.5 0l24.5 49.6 54.8 8c9.6 1.5 13.5 13.6 6.4 20.5z"
            class=""
          ></path>
        </svg>
        <span>Best Time!</span>
      </div>
    </div>

    <div class="ui__prefs">
      <div class="range" name="flip">
        <div class="range__label">Flip Type</div>
        <div class="range__track">
          <div class="range__track-line"></div>
          <div class="range__handle" style="left: 0%">
            <div style="background: rgb(65, 170, 200)"></div>
          </div>
        </div>
        <div class="range__list">
          <div>Swift&nbsp;</div>
          <div>Smooth</div>
          <div>Bounce</div>
        </div>
      </div>
      <div class="range" name="scramble">
        <div class="range__label">Scramble Length</div>
        <div class="range__track">
          <div class="range__track-line"></div>
          <div class="range__handle" style="left: 0%">
            <div style="background: rgb(65, 170, 200)"></div>
          </div>
        </div>
        <div class="range__list">
          <div>20</div>
          <div>25</div>
          <div>30</div>
        </div>
      </div>
      <div class="range" name="fov">
        <div class="range__label">Camera Angle</div>
        <div class="range__track">
          <div class="range__track-line"></div>
          <div class="range__handle" style="left: 18.6047%">
            <div style="background: rgb(65, 170, 200)"></div>
          </div>
        </div>
        <div class="range__list">
          <div>Ortographic</div>
          <div>Perspective</div>
        </div>
      </div>
      <div class="range" name="theme">
        <div class="range__label">Color Scheme</div>
        <div class="range__track">
          <div class="range__track-line"></div>
          <div class="range__handle" style="left: 0%">
            <div style="background: rgb(65, 170, 200)"></div>
          </div>
        </div>
        <div class="range__list">
          <div>Cube</div>
          <div>Erno</div>
          <div>Dust</div>
          <div>Camo</div>
          <div>Rain</div>
        </div>
      </div>
    </div>

    <div class="ui__stats">
      <div class="stats" name="total-solves"><i>Total solves:</i><b>-</b></div>
      <div class="stats" name="best-time"><i>Best time:</i><b>-</b></div>
      <div class="stats" name="worst-time"><i>Worst time:</i><b>-</b></div>
      <div class="stats" name="average-5"><i>Average of 5:</i><b>-</b></div>
      <div class="stats" name="average-12"><i>Average of 12:</i><b>-</b></div>
      <div class="stats" name="average-25"><i>Average of 25:</i><b>-</b></div>
    </div>

    <div class="ui__buttons">
      <button class="btn btn--bl btn--stats">
        <svg class="icon" viewBox="0 0 576 512" style="width: 1.125em; height: 1em">
          <path
            fill="currentColor"
            d="M552 64H448V24c0-13.3-10.7-24-24-24H152c-13.3 0-24 10.7-24 24v40H24C10.7 64 0 74.7 0 88v56c0 66.5 77.9 131.7 171.9 142.4C203.3 338.5 240 360 240 360v72h-48c-35.3 0-64 20.7-64 56v12c0 6.6 5.4 12 12 12h296c6.6 0 12-5.4 12-12v-12c0-35.3-28.7-56-64-56h-48v-72s36.7-21.5 68.1-73.6C498.4 275.6 576 210.3 576 144V88c0-13.3-10.7-24-24-24zM64 144v-16h64.2c1 32.6 5.8 61.2 12.8 86.2-47.5-16.4-77-49.9-77-70.2zm448 0c0 20.2-29.4 53.8-77 70.2 7-25 11.8-53.6 12.8-86.2H512v16zm-127.3 4.7l-39.6 38.6 9.4 54.6c1.7 9.8-8.7 17.2-17.4 12.6l-49-25.8-49 25.8c-8.8 4.6-19.1-2.9-17.4-12.6l9.4-54.6-39.6-38.6c-7.1-6.9-3.2-19 6.7-20.5l54.8-8 24.5-49.6c4.4-8.9 17.1-8.9 21.5 0l24.5 49.6 54.8 8c9.6 1.5 13.5 13.6 6.4 20.5z"
            class=""
          ></path>
        </svg>
      </button>
      <button
        class="btn btn--bl btn--prefs"
        style="transform: translate3d(0px, 0em, 0); opacity: 0.998656; pointer-events: all"
      >
        <svg class="icon" viewBox="0 0 512 512" style="width: 1em; height: 1em">
          <path
            fill="currentColor"
            d="M444.788 291.1l42.616 24.599c4.867 2.809 7.126 8.618 5.459 13.985-11.07 35.642-29.97 67.842-54.689 94.586a12.016 12.016 0 0 1-14.832 2.254l-42.584-24.595a191.577 191.577 0 0 1-60.759 35.13v49.182a12.01 12.01 0 0 1-9.377 11.718c-34.956 7.85-72.499 8.256-109.219.007-5.49-1.233-9.403-6.096-9.403-11.723v-49.184a191.555 191.555 0 0 1-60.759-35.13l-42.584 24.595a12.016 12.016 0 0 1-14.832-2.254c-24.718-26.744-43.619-58.944-54.689-94.586-1.667-5.366.592-11.175 5.459-13.985L67.212 291.1a193.48 193.48 0 0 1 0-70.199l-42.616-24.599c-4.867-2.809-7.126-8.618-5.459-13.985 11.07-35.642 29.97-67.842 54.689-94.586a12.016 12.016 0 0 1 14.832-2.254l42.584 24.595a191.577 191.577 0 0 1 60.759-35.13V25.759a12.01 12.01 0 0 1 9.377-11.718c34.956-7.85 72.499-8.256 109.219-.007 5.49 1.233 9.403 6.096 9.403 11.723v49.184a191.555 191.555 0 0 1 60.759 35.13l42.584-24.595a12.016 12.016 0 0 1 14.832 2.254c24.718 26.744 43.619 58.944 54.689 94.586 1.667 5.366-.592 11.175-5.459 13.985L444.788 220.9a193.485 193.485 0 0 1 0 70.2zM336 256c0-44.112-35.888-80-80-80s-80 35.888-80 80 35.888 80 80 80 80-35.888 80-80z"
            class=""
          ></path>
        </svg>
      </button>
      <button class="btn btn--bl btn--back">
        <svg class="icon" viewBox="0 0 512 512" style="width: 1em; height: 1em">
          <path
            transform="translate(512, 0) scale(-1,1)"
            fill="currentColor"
            d="M503.691 189.836L327.687 37.851C312.281 24.546 288 35.347 288 56.015v80.053C127.371 137.907 0 170.1 0 322.326c0 61.441 39.581 122.309 83.333 154.132 13.653 9.931 33.111-2.533 28.077-18.631C66.066 312.814 132.917 274.316 288 272.085V360c0 20.7 24.3 31.453 39.687 18.164l176.004-152c11.071-9.562 11.086-26.753 0-36.328z"
            class=""
          ></path>
        </svg>
      </button>
      <button
        class="btn btn--br btn--pwa"
        style="
          color: rgb(65, 170, 200);
          transform: translate3d(0px, 0em, 0);
          opacity: 0.998656;
          pointer-events: all;
        "
      ></button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { onMounted } from 'vue';
import { autoClearTimer } from '@/utils/timer';
onMounted(async () => {
  var scriptList = document.getElementsByTagName('script');
  await Object.keys(scriptList).forEach((name: any) => {
    if (scriptList[name] && scriptList[name].baseURI.includes('Rubiks')) {
      const pNode = scriptList[name].parentNode as any;
      pNode.removeChild(scriptList[name]);
    }
  });

  autoClearTimer(() => {
    const script1 = document.createElement('script');
    script1.src = '/rubiks/js/three.min.js';
    script1.type = 'text/javascript';
    document.body.appendChild(script1);
    autoClearTimer(() => {
      var link = document.createElement('link');
      link.rel = 'stylesheet';
      link.href = '/rubiks/css/style.css';
      link.type = 'text/css';
      document.body.appendChild(link);
      const script2 = document.createElement('script');
      script2.src = '/rubiks/js/index.js';
      script2.type = 'text/javascript';
      document.body.appendChild(script2);
    }, 1000);
  }, 1000);
});
</script>
<style lang="scss" scoped>
.ui__background::after,
.ui__background {
  background: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
}
.assembly-body {
  height: 100%;
}
.ui {
  width: 100%;
  height: 100%;
  position: relative;
  *,
  *:before,
  *:after {
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
    box-sizing: border-box;
    cursor: inherit;
    margin: 0;
    padding: 0;
    outline: none;
    font-size: inherit;
    font-weight: inherit;
    font-style: inherit;
    text-transform: uppercase;
  }
  *:focus {
    outline: none;
  }
}
</style>
