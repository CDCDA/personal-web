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

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
