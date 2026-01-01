// src/utils/request.js
import axios from 'axios'

// src/utils/request.js
const service = axios.create({
  baseURL: '/api',   // ✅ 改回这个！
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`
    }

    // 【保留】如果你的后端用 @RequestParam 接收 POST 参数
    const method = config.method?.toLowerCase()
    if (['post', 'put', 'delete'].includes(method) && config.params) {
      config.data = new URLSearchParams(config.params)
      config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
      config.params = {}
    }

    return config
  },
  error => {
    console.error('请求发送失败:', error)
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => response,
  error => {
    const { status } = error.response || {}
    if (status === 401) {
      localStorage.removeItem('token')
      window.location.href = '/login'
    } else if (status === 403) {
      alert('权限不足')
    } else if (status >= 500) {
      alert('服务器错误')
    }
    return Promise.reject(error)
  }
)

export default service