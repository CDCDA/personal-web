/*
 * @Description:接口
 */
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';
import axios from 'axios';
import { useRouter } from 'vue-router';
import useUserStore from '@/store/modules/user';
import { ElMessageBox, ElMessage } from 'element-plus';
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
  (config: InternalAxiosRequestConfig) => {
    config.headers.Authorization = `${user.token}`;
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
    ElMessage.error(error.response);
    return error.response;
  }
);

export default service;
