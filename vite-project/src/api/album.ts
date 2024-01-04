/*
 * @Description:博客标签
 */
import request from '@/utils/request';

export const countByUserId = (id: any) =>
  request({
    url: '/pw/blogTag/countByUserId/' + id,
    method: 'get'
  });

// 查询相册列表
export const listAlbum = (params: any) =>
  request({
    url: '/pw/album/list',
    method: 'get',
    params
  });

// 根据id查询相册
export const getAlbumById = (params: any) =>
  request({
    url: '/pw/album/getAlbumById',
    method: 'get',
    params
  });

// 保存或修改相册
export const saveAlbum = (params: any) =>
  request({
    url: '/pw/album/save',
    method: 'post',
    data: params
  });

// 批量删除相册
export const delAlbum = (params: any) =>
  request({
    url: '/pw/album/deleteByIds',
    method: 'post',
    data: params
  });
