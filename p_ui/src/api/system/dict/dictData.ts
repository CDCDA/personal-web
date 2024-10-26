/*
 * @Description:
 */
import request from '@/utils/request';

// 根据dictType获取字典数据
export const getDicts = (dictType: any) =>
  request({
    url: '/pw/dictData/getDictDataByDictType',
    method: 'post',
    data: dictType
  });

// 查询字典数据数
export const countDictData = () =>
  request({
    url: '/pw/dictData/count',
    method: 'get'
  });

// 查询字典数据列表
export const listDictData = (params: any) =>
  request({
    url: '/pw/dictData/list',
    method: 'get',
    params
  });

// 根据id查询字典数据
export const getDictDataById = (params: any) =>
  request({
    url: '/pw/dictData/getDictDataById',
    method: 'get',
    params
  });

// 保存或修改字典数据
export const saveDictData = (params: any) =>
  request({
    url: '/pw/dictData/save',
    method: 'post',
    data: params
  });

// 批量删除字典数据
export const delDictData = (params: any) =>
  request({
    url: '/pw/dictData/deleteByIds',
    method: 'post',
    data: params
  });
