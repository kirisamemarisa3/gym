const { defineConfig } = require('@vue/cli-service')

// vue.config.js（放在和 package.json 同级）
module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9090', // 后端地址
        changeOrigin: true,
        pathRewrite: {
          '^/api': '' // 把 /api 去掉，转发到 http://localhost:9090/getMemberPassword
        }
      }
    }
  }
}