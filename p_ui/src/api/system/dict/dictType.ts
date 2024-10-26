/*
 * @Description:
 */
import request from '@/utils/request';

// 查询字典类型数
export const countDictType = () =>
  request({
    url: '/pw/dictType/count',
    method: 'get'
  });

// 查询字典类型列表
export const listDictType = (params: any) =>
  request({
    url: '/pw/dictType/list',
    method: 'get',
    params
  });

// 根据id查询字典类型
export const getDictTypeById = (params: any) =>
  request({
    url: '/pw/dictType/getDictTypeById',
    method: 'get',
    params
  });

// 保存或修改字典类型
export const saveDictType = (params: any) =>
  request({
    url: '/pw/dictType/save',
    method: 'post',
    data: params
  });

// 批量删除字典类型
export const delDictType = (params: any) =>
  request({
    url: '/pw/dictType/deleteByIds',
    method: 'post',
    data: params
  });
