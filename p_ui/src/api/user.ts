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

//保存或修改用户信息
export const saveUser = (data: Object) =>
  request({
    url: '/pw/user/save',
    method: 'post',
    data
  });

//验证token
export const verifyToken = () =>
  request({
    url: '/pw/user/tokenVerify',
    method: 'get'
  });
