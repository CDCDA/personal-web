/*
 * @Description: 设备
 */
import request from '@/utils/request';

// 查询设备列表
export const listEquip = (params: any) =>
  request({
    url: '/pw/equipment/list',
    method: 'get',
    params
  });

// 保存设备
export const saveEquip = (params: any) =>
  request({
    url: '/pw/equipment/save',
    method: 'post',
    data: params
  });

// 批量删除设备
export const delEquip = (params: any) =>
  request({
    url: '/pw/equipment/deleteByIds',
    method: 'post',
    data: params
  });

// 根据设备id查询设备
export const getEquipById = (id: any) =>
  request({
    url: '/pw/equipment/getEquipById',
    method: 'get',
    params: id
  });
