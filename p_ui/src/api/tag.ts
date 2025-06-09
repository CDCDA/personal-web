/*
 * @Description:标签标签
 */
import request from '@/utils/request';

export const countTag = () =>
  request({
    url: '/pw/blogTag/countTag',
    method: 'get'
  });

export const listByUserId = (params: any) =>
  request({
    url: '/pw/blogTag/list',
    method: 'get',
    params
  });

// 分页
export const listTag = (params: any) =>
  request({
    url: '/pw/blogTag/list',
    method: 'get',
    params
  });

// 删除标签分类
export const delTag = (ids: string[]) =>
  request({
    url: '/pw/blogTag/deleteByIds',
    method: 'post',
    data: ids
  });

// 保存或修改标签分类
export const saveTag = (data: any) =>
  request({
    url: '/pw/blogTag/save',
    method: 'post',
    data
  });
