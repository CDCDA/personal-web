<template>
  <div style="position: relative">
    <div class="button-box" v-dialogDrag>
      <!-- <div class="bt-edit-blog" @click="openBox">
        <i class="el-icon-edit-outline"></i>
        <span>{{ text }}</span>
      </div> -->
    </div>
  </div>
</template>

<script lang="ts">
// export default {
//   props: ['caseID', 'blogData'],
//   data() {
//     return {
//       text: '编辑博客',
//       isOpen: false,
//       isMove: false
//     };
//   },
//   methods: {
//     openBox() {
//       this.$router.push({
//         path: '/BlogEditor',
//         query: { blogData: this.blogData }
//       });
//     },
//     mousedowm(e) {
//       // 鼠标按下时的鼠标所在的X，Y坐标
//       this.mouseDownX = e.pageX;
//       this.mouseDownY = e.pageY;
//       // 初始位置的X，Y 坐标
//       // this.initX = obj.offsetLeft;
//       // this.initY = obj.offsetTop;
//       console.log('e', e);
//       // 表示鼠标已按下
//       this.flag = true;
//     },
//     mousemove(e) {
//       if (this.flag) {
//         console.log('e :', e);
//       }
//     }
//   },
//   directives: {
//     drag(el) {
//       let oDiv = el; // 当前元素
//       // let self = this // 上下文
//       // 禁止选择网页上的文字
//       //   document.onselectstart = function () {
//       //     return false;
//       //   };
//       oDiv.onmousedown = function (e) {
//         // 鼠标按下，计算当前元素距离可视区的距离
//         let disX = e.clientX - oDiv.offsetLeft;
//         let disY = e.clientY - oDiv.offsetTop;
//         document.onmousemove = function (e) {
//           // 通过事件委托，计算移动的距离
//           let l = e.clientX - disX;
//           let t = e.clientY - disY;
//           // 移动当前元素
//           oDiv.style.left = l + 'px';
//           oDiv.style.top = t + 'px';
//         };
//         document.onmouseup = function (e) {
//           document.onmousemove = null;
//           document.onmouseup = null;
//         };
//         // return false不加的话可能导致黏连，就是拖到一个地方时div粘在鼠标上不下来，相当于onmouseup失效
//         return false;
//       };
//     }
//   }
// };
</script>

<style lang="scss" scoped>
@include theme() {
  .button-box {
    width: 100px;
    position: fixed;
    bottom: 80px;
    right: 50px;
    padding-left: 10px;
    padding-right: 10px;
    cursor: pointer;
    opacity: 0.7;
    z-index: 888;
  }
  .bt-edit-blog {
    width: 100px;
    height: 30px;
    line-height: 30px;
    border-radius: 5px;
    background-size: cover;
    color: get('font-color');
    margin-bottom: 20px;
    background: rgba(80, 81, 82, 0.6);
    box-shadow: get('box-shadow');
  }
  .button-box:hover {
    color: get('font-color');
    opacity: 1;
  }
}
</style>
