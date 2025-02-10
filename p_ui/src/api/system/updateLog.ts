/*
 * @Description: 更新日志
 */
import request from '@/utils/request';

// 查询更新日志数
export const countUpdateLog = () =>
  request({
    url: '/pw/updateLog/count',
    method: 'get'
  });

// 列表
export const listUpdateLog = (params: any) =>
  request({
    url: '/pw/updateLog/list',
    method: 'get',
    params
  });

// 列表
export const countUpdateLogByDateRange = (params: any) =>
  request({
    url: '/pw/updateLog/countUpdateLogByDateRange',
    method: 'get',
    params
  });

// 删除更新日志
export const delUpdateLog = (data: any) =>
  request({
    url: '/pw/updateLog/deleteByIds',
    method: 'post',
    data
  });

// 保存或修改更新日志
export const saveUpdateLog = (data: any) =>
  request({
    url: '/pw/updateLog/save',
    method: 'post',
    data
  });
