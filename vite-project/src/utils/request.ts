/*
 * @Description:
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-06-27 09:30:37
 * @LastEditTime: 2023-07-08 11:19:31
 */
import type { AxiosInstance, InternalAxiosRequestConfig, AxiosResponse } from 'axios';
import axios from 'axios';

// import useUserStore from '@/store/modules/user';

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
    // (config as any).headers.Authorization = `Bearer ${useUserStore().token}`;
    return config;
  },

  error => {
    Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => response.data,
  error => {
    Promise.reject(error);
    return error.response;
  }
);

export default service;
