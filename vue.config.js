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
  // devServer: {
  //   host: '10.1.88.22:8080/',
  //   //https: false,
  //   open: true,
  // }
})