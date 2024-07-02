/*
 * @Description: 博客接口
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-07-07 16:59:41
 * @LastEditTime: 2023-11-08 16:11:58
 */
import request from '@/utils/request';
// 分页
export const listBlog = (params: any) =>
  request({
    url: '/pw/blog/list',
    method: 'get',
    params
  });

// 保存或修改
export const saveBlog = (params: any) =>
  request({
    url: '/pw/blog/save',
    method: 'post',
    data: params
  });

// 博客详情
export const getBlogById = (id: any) =>
  request({
    url: '/pw/blog/' + id,
    method: 'get'
  });

// 查询博客数
export const countBlog = (params: any) =>
  request({
    url: '/pw/blog/count',
    method: 'get',
    params
  });

// 删除
export const deleteBlog = (id: any) =>
  request({
    url: '/pw/blog/' + id,
    method: 'delete'
  });

// 批量删除
export const deleteBlogs = (ids: any) =>
  request({
    url: '/pw/blog/deleteByIds',
    method: 'post',
    data: ids
  });

// 按时间范围统计
export const countBlogByDateRange = (params: any) =>
  request({
    url: '/pw/blog/countBlogByDateRange',
    method: 'get',
    params
  });

// 按分类统计
export const countBlogByType = (params: any) =>
  request({
    url: '/pw/blog/countBlogByType',
    method: 'get',
    params
  });

// 按分类统计
export const getRandomBlog = () =>
  request({
    url: '/pw/blog/getRandomBlog',
    method: 'get'
  });
