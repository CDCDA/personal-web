/*
 * @Author: chenyd
 * @Date: 2022-12-20 11:09:51
 * @LastEditTime: 2023-01-31 17:48:13
 * @Description: 文件操作
 */

/**
 * @return {*}
 * @Description:图片上传 
 */
export const uploadFile = (params) => {
    return request({
        url: '/file/upload',
        method: 'post',
        params: params
    })
}
