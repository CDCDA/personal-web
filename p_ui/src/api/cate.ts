/*
 * @Description: 美食
 */
import request from '@/utils/request';

// 查询美食列表
export const listCate = (params: any) =>
  request({
    url: '/pw/cate/list',
    method: 'get',
    params
  });

// 保存美食
export const saveCate = (params: any) =>
  request({
    url: '/pw/cate/save',
    method: 'post',
    data: params
  });

// 批量删除美食
export const delCate = (params: any) =>
  request({
    url: '/pw/cate/deleteByIds',
    method: 'post',
    data: params
  });

// 根据美食id查询美食
export const getCateById = (id: any) =>
  request({
    url: '/pw/cate/getCateById',
    method: 'get',
    params: id
  });
