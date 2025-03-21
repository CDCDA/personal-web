/*
 * @Description:壁纸
 */
import request from '@/utils/request';

// 查询壁纸列表
export const listWallpaper = (params: any) =>
  request({
    url: '/pw/wallpaper/list',
    method: 'get',
    params
  });

export const getRandomWallpaper = () =>
  request({
    url: '/pw/wallpaper/getRandomWallpaper',
    method: 'get'
  });

// 根据id查询壁纸
export const getWallpaperById = (params: any) =>
  request({
    url: '/pw/Wallpaper/getWallpaperById',
    method: 'get',
    params
  });

// 保存或修改壁纸
export const saveWallpaper = (params: any) =>
  request({
    url: '/pw/wallpaper/save',
    method: 'post',
    data: params
  });

// 批量删除壁纸
export const delWallpaper = (params: any) =>
  request({
    url: '/pw/wallpaper/deleteByIds',
    method: 'post',
    data: params
  });
