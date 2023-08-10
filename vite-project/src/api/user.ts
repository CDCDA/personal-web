import request from '@/utils/request';

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
