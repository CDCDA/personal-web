/*
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-07-03 16:57:30
 * @Description: 
 */
import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import store from './store'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
//粒子特效
import VueParticles from "vue-particles";
Vue.use(VueParticles)
//import '@/assets/theme/nightTheme'/*引入公共样式*/
Vue.use(mavonEditor)
Vue.use(ElementUI)
Vue.config.productionTip = false
//日期转字符串 date.js中也有
Vue.prototype.dateTimeToString = function (date) {
  var y = date.getFullYear();
  var M = date.getMonth() + 1;
  var d = date.getDate();
  var H = date.getHours();
  var m = date.getMinutes();
  var s = date.getSeconds();
  return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m) + ":" + (s < 10 ? ('0' + s) : s);
}
//拖拽事件
Vue.directive("drag", {
  inserted: function (el) {
    let oDiv = el; //当前元素
    oDiv.style.cssText += ';cursor:move;'
    // let menu = document.querySelector(".el-dropdown-menu");
    //禁止选择网页上的文字
    document.onselectstart = function () {
      return false;
    };
    oDiv.onmousedown = function (e) {
      //鼠标按下，计算当前元素距离可视区的距离
      let disX = e.clientX - oDiv.offsetLeft;
      let disY = e.clientY - oDiv.offsetTop;
      document.onmousemove = function (e) {
        //通过事件委托，计算移动的距离
        let l = e.clientX - disX;
        let t = e.clientY - disY;

        //重置style,避免初始定位影响下拉选项的位置
        oDiv.removeAttribute("style")
        //移动当前元素
        oDiv.style.left = l + "px";
        oDiv.style.top = t + "px";
      };
      document.onmouseup = function (e) {
        document.onmousemove = null;
        document.onmouseup = null;
      };
      //return false不加的话可能导致黏连，就是拖到一个地方时div粘在鼠标上不下来，相当于onmouseup失效
      return false;
    };
  }
});

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
