/*
 * @Description:声明文件
 */
declare module 'particles.vue3';
declare module '@kangc/v-md-editor';
declare module '@kangc/v-md-editor/lib/theme/github.js';
declare module 'codemirror';
declare module '@kangc/v-md-editor/lib/codemirror-editor';
declare module '@kangc/v-md-editor/lib/preview';
declare module '@/../lib/vform/designer.umd.js';
declare module 'vue-draggable-resizable';
declare module 'codemirror/lib/codemirror.js';
declare module 'vue-grid-layout';
declare module 'js-cookie';
declare module '@/utils/techStackData.js';
declare module 'require';
declare module '@kangc/v-md-editor/lib/plugins/line-number/index';

declare module '*.vue' {
  import { ComponentOptions } from 'vue';
  const componentOptions: ComponentOptions;
  export default componentOptions;
}
