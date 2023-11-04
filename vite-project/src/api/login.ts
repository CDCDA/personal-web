/*
 * @Description:
 */
import request from '@/utils/request';

// 登录
export const login = (params: any) =>
  request({
    url: '/pw/user/login',
    method: 'post',
    data: params
  });
