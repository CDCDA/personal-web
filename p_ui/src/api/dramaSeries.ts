/*
 * @Description: 影视
 */
import request from '@/utils/request';

// 查询影视列表
export const listDrama = (params: any) =>
  request({
    url: '/pw/dramaSeries/list',
    method: 'get',
    params
  });

// 保存影视
export const saveDrama = (params: any) =>
  request({
    url: '/pw/dramaSeries/save',
    method: 'post',
    data: params
  });

// 批量删除影视
export const delDrama = (params: any) =>
  request({
    url: '/pw/dramaSeries/deleteByIds',
    method: 'post',
    data: params
  });

// 根据影视id查询影视
export const getDramaById = (id: any) =>
  request({
    url: '/pw/dramaSeries/getDramaById',
    method: 'get',
    params: id
  });
