/*
 * @Description: 游戏
 */
import request from '@/utils/request';

// 查询游戏列表
export const listGame = (params: any) =>
  request({
    url: '/pw/game/list',
    method: 'get',
    params
  });

// 保存游戏
export const saveGame = (params: any) =>
  request({
    url: '/pw/game/save',
    method: 'post',
    data: params
  });

// 批量删除游戏
export const delGame = (params: any) =>
  request({
    url: '/pw/game/deleteByIds',
    method: 'post',
    data: params
  });

// 根据游戏id查询游戏
export const getGameById = (id: any) =>
  request({
    url: '/pw/game/getGameById',
    method: 'get',
    params: id
  });
