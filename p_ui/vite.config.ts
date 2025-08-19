import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import path from 'path';
import viteCompression from 'vite-plugin-compression';
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons';
import { visualizer } from 'rollup-plugin-visualizer';
const vendorLibs = [
  { match: ['vue', 'vue-router', 'pinia'], output: 'vue-vendor' },
  { match: ['element-plus'], output: 'element-plus-vendor' },
  { match: ['echarts'], output: 'echarts-vendor' },
  { match: ['three'], output: 'three-vendor' }
];

export default defineConfig({
  base: './',
  plugins: [
    vue(),
    createSvgIconsPlugin({
      iconDirs: [
        path.resolve(process.cwd(), 'src/assets/svg'),
        path.resolve(process.cwd(), 'src/assets/svg/pixelSvg'),
        path.resolve(process.cwd(), 'src/assets/svg/commonSvg'),
        path.resolve(process.cwd(), 'src/assets/svg/linkSvg'),
        path.resolve(process.cwd(), 'src/assets/svg/techStackSvg'),
        path.resolve(process.cwd(), 'src/assets/svg/audioSvg'),
        path.resolve(process.cwd(), 'src/assets/svg/socialSvg')
      ],
      symbolId: '[dir]-[name]'
    }),
    viteCompression({
      verbose: true,
      disable: false,
      threshold: 10240,
      algorithm: 'gzip',
      ext: '.gz'
    }),
    visualizer({
      open: true,
      gzipSize: true,
      brotliSize: true,
      filename: 'visualizer/stats.html'
    })
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
      '@': path.join(__dirname, './src') // path记得引
    }
  },
  optimizeDeps: {
    include: ['@/../lib/vform/designer.umd.js', 'swiper'] //此处路径必须跟main.js中import路径完全一致！
  },
  build: {
    commonjsOptions: {
      include: /node_modules|lib/ //这里记得把lib目录加进来，否则生产打包会报错！！
    },
    cssCodeSplit: true,
    rollupOptions: {
      input: 'index.html',
      output: {
        // 静态资源打包做处理
        chunkFileNames: 'static/js/[name]-[hash].js',
        entryFileNames: 'static/js/[name]-[hash].js',
        assetFileNames: 'static/[ext]/[name]-[hash].[ext]',
        manualChunks(id) {
          // 处理第三方库
          if (id.includes('node_modules')) {
            for (const lib of vendorLibs) {
              if (lib.match.some(item => id.includes(item))) {
                return lib.output;
              }
            }
            // 其他所有 node_modules 的包都打到这一个 vendor chunk 里
            return 'vendor';
          }
        }
      }
    },
    minify: 'terser',
    terserOptions: {
      // 清除console和debugger
      compress: {
        drop_console: true,
        drop_debugger: true
      }
    }
  },
  server: {
    port: 8088,
    host: '0.0.0.0',
    headers: {
      'Access-Control-Allow-Origin': '*'
    },
    proxy: {
      '/dev-api': {
        // target: 'http://localhost:5008',
        target: 'http://120.48.127.181:5008',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/dev-api/, '')
      },
      // 服务器图片接口
      '/img': {
        target: 'http://120.48.127.181:8008',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/img/, '')
      },
      // 地址查询接口
      '/map': {
        target: 'https://api.map.baidu.com',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/map/, '')
      },
      // 音乐搜索接口
      // '/song': {
      //   target: 'https://ml.v.api.aa1.cn',
      //   changeOrigin: true,
      //   rewrite: p => p.replace(/^\/song/, '/music')
      // },
      // 网易云音乐接口
      '/wyy': {
        target: 'http://music.163.com',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/wyy/, '')
      },
      '/chatApi': {
        target: 'https://api.siliconflow.cn',
        changeOrigin: true,
        rewrite: p => p.replace(/^\/chatApi/, '')
      }
    }
  }
});
