import request from "@/utils/request"
import qs from 'qs'
//根据用户id获取博客
export const getBlogByUserId = (params) => {
    return request({
        url: '/blog/getBlogByUserId',
        method: 'post',
        params: params
    })
}
//根据用户id获取博客数
export const getBlogRowCount = (params) => {
    return request({
        url: '/blog/getBlogRowCount',
        method: 'post',
        params: params
    })
}
//根据博客id删除博客
export const deleteBlogById = (params) => {
    return request({
        url: '/blog/deleteBlogById',
        method: 'post',
        params: params
    })
}

//根据博客id获取博客
export const getBlogByBlogId = (params) => {
    return request({
        url: '/blog/getBlogByBlogId',
        method: 'post',
        params: params
    })
}
//添加博客
export const createBlog = (params) => {
    // params.blogLabel = qs.stringify(params.blogLabel)
    return request({
        url: '/blog/createBlog',
        method: 'post',
        params: params
    })
}

//添加博客操作记录 params：{blogRecord：{
//    
//}}
export const createBlogRecord = (params) => {
    return request({
        url: '/blog/createBlogRecord',
        method: 'post',
        params: params
    })
}

//删除博客操作记录 params：{blogRecordId}
export const deleteBlogRecord = (params) => {
    return request({
        url: '/blog/deleteBlogRecord',
        method: 'post',
        params: params
    })
}

//获取博客操作记录 params：{userId,startRow,pageSize}
export const getBlogRecord = (params) => {
    return request({
        url: '/blog/getBlogRecord',
        method: 'post',
        params: params
    })
}  