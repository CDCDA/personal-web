/*
 * @Description:入口文件
 */
import { createApp } from 'vue';
import App from './App.vue';
//粒子特效
import Particles from 'particles.vue3';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import image from './components/image/image.vue';
// import '@/assets/theme/public.scss';
import '@/assets/styles/animate.scss';
import '@/assets/styles/element-ui-out-app.scss';
// import '@/assets/styles/bounce.scss';
import VueGridLayout from 'vue-grid-layout';

import router from './router';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import directives from '@/directives/index';
// import VueMarkdownEditor from '@kangc/v-md-editor';
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import pinia from './store';
// highlightjs
import hljs from 'highlight.js';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';

import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
import '@kangc/v-md-editor/lib/theme/style/github.css';

import SvgIcon from './components/icon-component.vue';
import 'virtual:svg-icons-register';

/* vForm 表单构建器 */
import VForm3 from '@/../lib/vform/designer.umd.js';
import '../lib/vform/designer.style.css';

import './components/loading/index.js';

VMdPreview.use(githubTheme, {
  Hljs: hljs
});

VMdEditor.Codemirror = Codemirror;
VMdEditor.use(githubTheme, {
  Hljs: hljs
});
VMdEditor.use(createLineNumbertPlugin());
VMdPreview.use(createLineNumbertPlugin());

import loadingDirective from '@/components/loading/index.ts';

const app = createApp(App);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.component('svg-icon', SvgIcon);
app.component('c-image', image);
app.provide('$theme', 'theme-light');
app.use(VMdEditor);
app.use(directives);
app.directive('cLoading', loadingDirective);
app.use(VForm3);
app.use(pinia);
app.use(ElementPlus, { zIndex: 3000 });
app.use(Particles);
app.use(router);
app.use(VMdPreview);
app.use(VueGridLayout);
app.mount('#app');
