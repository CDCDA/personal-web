/*
 * @Description:接口
 */
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';
import axios from 'axios';
import useUserStore from '@/store/modules/user';
import { ElMessageBox, ElNotification } from 'element-plus';
var user = window.localStorage.getItem('userData') as any;
if (user) user = JSON.parse(user);
import router from '@/router/index';
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// 创建axios实例
const service: AxiosInstance = axios.create({
  baseURL: process.env.NODE_ENV === 'development' ? '/dev-api' : '/prod-api',
  timeout: 150000
});

// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    if (!user) {
      user = useUserStore();
    }
    config.headers.Authorization = `${user.token}`;
    return config;
  },

  error => {
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: any) => {
    if (response.data.code === 401) {
      ElMessageBox.alert('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        type: 'warning',
        showClose: false
      })
        .then(() => {
          router.push({ name: 'login' });
        })
        .catch(() => {});
    } else if (response.data.code === 500) {
      ElNotification.error(response.data.msg || '无法连接到服务器');
    } else if (response.data.code === 403) {
      ElNotification.error(response.data.msg);
    }
    return response.data;
  },
  error => {
    Promise.reject(error);
    ElNotification.error(error.response);
    return error.response;
  }
);

export default service;
