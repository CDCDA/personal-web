const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  css: {
    loaderOptions: {
      sass: {
        additionalData: `@import "@/assets/theme/nightTheme.scss";`
      }
    }
  },
  devServer: {
    proxy: {
      '/api/': {
        target: 'http://localhost:8082/',
        changeOrigin: true,
        pathRewrite: {
          '^/api/': '/'
        }
      },
    }
  }
})