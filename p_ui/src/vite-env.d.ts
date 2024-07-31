/*
 * @Description:
 */
/*
 * @Description:声明文件
 */
declare module 'particles.vue3';
declare module '@/../lib/vform/designer.umd.js';
declare module 'vue-draggable-resizable';
declare module 'vue-grid-layout';
declare module 'js-cookie';
declare module '@/utils/techStackData.js';
declare module 'vue-mini-weather';
declare module 'vuepress-plugin-sakura';
declare module '@xunlei/vue-lazy-component';
declare module '/weatherCard/script.js';
declare module 'vue-waterfall-easy';

// declare module 'vite-plugin-require-transform';
declare module 'md-editor-v3';

declare module '*.vue' {
  import { ComponentOptions } from 'vue';
  const componentOptions: ComponentOptions;
  export default componentOptions;
}
