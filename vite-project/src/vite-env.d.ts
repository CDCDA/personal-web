/*
 * @Description:声明文件
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-26 16:41:56
 * @LastEditTime: 2023-08-09 14:30:09
 */
// <reference types="vite/client" />;
declare module 'particles.vue3';
declare module '@kangc/v-md-editor';
declare module '@kangc/v-md-editor/lib/theme/github.js';
declare module 'codemirror';
declare module '@kangc/v-md-editor/lib/codemirror-editor';
declare module '@kangc/v-md-editor/lib/preview';
declare module '@/../lib/vform/designer.umd.js';
declare module 'vue-draggable-resizable';
declare module 'codemirror/lib/codemirror.js';

declare module '*.vue' {
  import { ComponentOptions } from 'vue';
  const componentOptions: ComponentOptions;
  export default componentOptions;
}
