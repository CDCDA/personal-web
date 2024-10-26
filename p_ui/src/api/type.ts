/*
 * @Description: 博客分类
 */
import request from '@/utils/request';

// 查询分类数
export const countType = () =>
  request({
    url: '/pw/blogType/count',
    method: 'get'
  });

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

// 查询分类列表并列出指定数量的博客
export const listTypeAndBlog = () =>
  request({
    url: '/pw/blogType/listTypeAndBlog',
    method: 'get'
  });
