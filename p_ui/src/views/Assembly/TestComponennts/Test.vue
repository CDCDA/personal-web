<template>
  <div class="a">
    <div class="b"></div>
  </div>
</template>
<style lang="less" scoped>
@keyframes rotate {
  100% {
    transform: rotate(1turn);
  }
}
.b {
  position: absolute;
  top: 17px;
  left: 18px;
  background: url(@/assets/images/切图R1.png) no-repeat -38px -73px;
  width: 60px;
  height: 60px;
}
.a {
  position: relative;
  z-index: 0;
  width: 400px;
  height: 300px;
  margin: 20px;
  border-radius: 10px;
  overflow: hidden;
  padding: 2rem;

  &::before {
    content: "";
    position: absolute;
    z-index: -2;
    left: -50%;
    top: -50%;
    width: 200%;
    height: 200%;
    background-color: #1a232a;
    background-repeat: no-repeat;
    background-position: 0 0;
    background-image: conic-gradient(
      transparent,
      rgba(168, 239, 255, 1),
      transparent 30%
    );
    animation: rotate 4s linear infinite;
  }

  &::after {
    content: "";
    position: absolute;
    z-index: -1;
    left: 6px;
    top: 6px;
    width: calc(100% - 12px);
    height: calc(100% - 12px);
    background: #000;
    border-radius: 5px;
  }
}
</style>