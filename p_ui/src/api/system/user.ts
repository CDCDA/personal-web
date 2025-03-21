import request from '@/utils/request';
import type { AxiosInstance } from 'axios';
import axios from 'axios';
import useUserStore from '@/store/modules/user';
// 分页
export const listUser = (params: any) =>
  request({
    url: '/pw/user/list',
    method: 'get',
    params
  });

//用户详情
export const getUserById = (id: string) =>
  request({
    url: '/pw/user/' + id,
    method: 'get'
  });

//保存或修改用户信息
export const saveUser = (data: Object) =>
  request({
    url: '/pw/user/save',
    method: 'post',
    data
  });

//获取验证码
export const getCode = (params: any) =>
  request({
    url: '/pw/user/getCode',
    method: 'get',
    params
  });

//获取验证码
export const register = (params: Object) =>
  request({
    url: '/pw/user/register',
    method: 'get',
    params
  });

//验证token
export const verifyToken = () =>
  request({
    url: '/pw/user/tokenVerify',
    method: 'get'
  });

let userData = window.localStorage.getItem('userData') as any;
if (userData) userData = JSON.parse(userData) as any;
const service: AxiosInstance = axios.create({
  baseURL: process.env.NODE_ENV === 'development' ? '/dev-api' : '/prod-api',
  timeout: 150000,
  headers: {
    Authorization: `${userData?.token}`
  }
});

//验证token（无拦截）
export const verifyTokenNoIntercept = () =>
  service({
    url: '/pw/user/tokenVerify',
    method: 'get'
  });
