/*
 * @Description:接口
 */
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';
import axios from 'axios';
import { useRouter } from 'vue-router';
import useUserStore from '@/store/modules/user';
import { ElMessageBox } from 'element-plus';
const user = useUserStore();
const router = useRouter();
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';
// 创建axios实例
const service: AxiosInstance = axios.create({
  // baseURL: process.env.NODE_ENV === 'development' ? '/dev-api' : '/prod-api',
  baseURL: 'dev-api',
  timeout: 150000
});

// 请求拦截器
service.interceptors.request.use(
  /*
   * AxiosRequestConfig 在最新的axios@1.3中已经失效
   * 改成 InternalAxiosRequestConfig, 不然ts的类型声明会报错
   */

  // 如果发现 '(config: AxiosRequestConfig) => {' 有红色波浪号，就是下面没有return config,造成的
  (config: InternalAxiosRequestConfig) => {
    config.headers.Authorization = `${user.token}`;
    // (config as any).headers.Authorization = `Bearer ${useUserStore().token}`;
    return config;
  },

  error => {
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    if ((response as any).code === 401) {
      ElMessageBox.confirm('登录状态已过期，您可以继续留在该页面，或者重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          router.push({ name: 'home' });
        })
        .catch(() => {});
    }
    return response.data;
  },
  error => {
    Promise.reject(error);
    return error.response;
  }
);

export default service;
