/*
 * @Description:打包配置
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-26 16:41:56
 * @LastEditTime: 2023-10-24 13:49:39
 */
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import commonjs from '@rollup/plugin-commonjs';
import requireTransform from 'vite-plugin-require-transform';
import path from 'path';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';
const resolve = dir => path.resolve(__dirname, dir);
// https://vitejs.dev/config/
export default defineConfig({
  // root: resolve('./'), //  入口index.html，注意入口js应该与index.html 同一目录下（只能写到目录，不能写到具体文件）
  // base: './',
  // publicDir: resolve('static'), //静态资源文件夹
  plugins: [
    commonjs() as any,
    vue(),
    requireTransform({
      fileRegex: /.js$|.vue$/
    })

    // AutoImport({
    //   resolvers: [ElementPlusResolver()]
    // }),
    // Components({
    //   resolvers: [ElementPlusResolver()]
    // })
  ],
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: '@import "./src/assets/theme/theme.scss";' // 加载全局样式，使用scss特性
      }
    }
  },
  resolve: {
    alias: {
      // 这里就是需要配置resolve里的别名
      '@': path.join(__dirname, './src') // path记得引入
      // 'vue': 'vue/dist/vue.esm-bundler.js' // 定义vue的别名，如果使用其他的插件，可能会用到别名
    }
  },
  optimizeDeps: {
    include: ['@/../lib/vform/designer.umd.js'] //此处路径必须跟main.js中import路径完全一致！
  },
  build: {
    /* 其他build生产打包配置省略 */
    //...
    commonjsOptions: {
      include: /node_modules|lib/ //这里记得把lib目录加进来，否则生产打包会报错！！
    },
    terserOptions: {
      compress: {
        drop_console: true,
        drop_debugger: true
      }
    }
  },
  server: {
    port: 8086,
    host: '0.0.0.0',
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    proxy: {
      // https://cn.vitejs.dev/config/#server-proxy
      '/dev-api': {
        target: 'http://localhost:8080',
        // target: 'http://111.229.144.36:5008',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/dev-api/, '')
      },
      '/img': {
        // target: 'http://localhost:8080',
        target: 'http://111.229.144.36:8008',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/img/, '')
      }
    }
  }
});
