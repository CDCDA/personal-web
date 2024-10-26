/*
 * @Description:评论
 */
import request from '@/utils/request';

// 查询评论数
export const countComment = () =>
  request({
    url: '/pw/comment/count',
    method: 'get'
  });

// 查询评论列表
export const listComment = (params: any) =>
  request({
    url: '/pw/comment/list',
    method: 'get',
    params
  });

// 查询树形评论列表
export const listTreeComment = (params: any) =>
  request({
    url: '/pw/comment/listTree',
    method: 'get',
    params
  });

// 根据id查询评论
export const getCommentById = (params: any) =>
  request({
    url: '/pw/comment/getCommentById',
    method: 'get',
    params
  });

// 保存或修改评论
export const saveComment = (params: any) =>
  request({
    url: '/pw/comment/save',
    method: 'post',
    data: params
  });

// 批量删除评论
export const delComment = (params: any) =>
  request({
    url: '/pw/comment/deleteByIds',
    method: 'post',
    data: params
  });
