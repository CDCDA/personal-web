/*
 * @Description:
 * @Author: cyd 1205489124@qq.com
 * @Date: 2023-07-08 14:26:59
 * @LastEditTime: 2023-07-11 19:33:59
 */
import request from '@/utils/request';
// 分页
export const listTypeTree = (params: any) =>
  request({
    url: '/pw/blogType/typeTree',
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
export const delBlogType = (id: string) =>
  request({
    url: '/pw/blogType/' + id,
    method: 'delete'
  });

// 保存或修改博客分类
export const saveBlogType = (data: any) =>
  request({
    url: '/pw/blogType/save',
    method: 'post',
    data
  });
