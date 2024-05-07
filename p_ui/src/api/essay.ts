/*
 * @Description: 随笔
 */
import request from '@/utils/request';

// 查询随笔数
export const countEssay = () =>
  request({
    url: '/pw/essay/count',
    method: 'get'
  });

// 查询随笔列表
export const listEssay = (params: any) =>
  request({
    url: '/pw/essay/list',
    method: 'get',
    params
  });

// 保存随笔
export const saveEssay = (params: any) =>
  request({
    url: '/pw/essay/save',
    method: 'post',
    data: params
  });

// 批量删除随笔
export const delEssay = (params: any) =>
  request({
    url: '/pw/essay/deleteByIds',
    method: 'post',
    data: params
  });

// 根据随笔id查询随笔
export const getEssayById = (id: any) =>
  request({
    url: '/pw/essay/getEssayById',
    method: 'get',
    params: id
  });
