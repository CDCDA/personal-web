/*
 * @Description: 博客分类
 */
import request from '@/utils/request';

// 分页
export const listType = (params: any) =>
  request({
    url: '/pw/blogType/list',
    method: 'get',
    params
  });

// 博客分类统计
export const countBlogType = (params: any) =>
  request({
    url: '/pw/blogType/count',
    method: 'get',
    params
  });

// 删除博客分类
export const delBlogType = (ids: string[]) =>
  request({
    url: '/pw/blogType/deleteByIds',
    method: 'post',
    data: ids
  });

// 保存或修改博客分类
export const saveBlogType = (data: any) =>
  request({
    url: '/pw/blogType/save',
    method: 'post',
    data
  });

// 博客分类列表（带统计）
export const listTotalType = (params: any) =>
  request({
    url: '/pw/blogType/totalList',
    method: 'get',
    params
  });
