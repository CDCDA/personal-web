/*
 * @Author: chenyd 1205489124@qq.com
 * @Date: 2023-01-24 19:29:51
 * @LastEditors: chenyd 1205489124@qq.com
 * @LastEditTime: 2023-01-25 13:03:29
 * @FilePath: \p_ui\src\api\login.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from "@/utils/request"
export const login = (params) => {
    return request({
        url: '/user/login',
        method: 'post',
        params: params
    })
}

export const getUserById = (params) => {
    return request({
        url: '/user/getUserById',
        method: 'post',
        params: params
    })
}   