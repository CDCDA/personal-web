/*
 * @Description:
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-07-11 19:26:54
 * @LastEditTime: 2023-07-11 19:27:47
 */
import request from '@/utils/request';
// 分页
export const countByUserId = (id: any) =>
  request({
    url: '/pw/blogTag/countByUserId/' + id,
    method: 'get'
  });

export const listByUserId = (params: any) =>
  request({
    url: '/pw/blogTag/list',
    method: 'get',
    params
  });
