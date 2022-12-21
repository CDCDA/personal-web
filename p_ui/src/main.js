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
//日期转字符串
Vue.prototype.dateTimeToString = function (date) {
  var y = date.getFullYear();
  var M = date.getMonth() + 1;
  var d = date.getDate();
  var H = date.getHours();
  var m = date.getMinutes();
  var s = date.getSeconds();
  return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m) + ":" + (s < 10 ? ('0' + s) : s);
}

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
