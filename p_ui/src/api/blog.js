/*
 * @Author: chenyd
 * @Date: 2022-12-20 11:09:51
 * @LastEditTime: 2023-01-30 17:12:43
 * @Description: 
 */

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
//根据时间获取博客 params :{userId,startTime,endTime}
export const getBlogCountByTime = (params) => {
    return request({
        url: '/blog/getBlogCountByTime',
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

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 浏览数+1
 */
export const addViewCount = (params) => {
    return request({
        url: '/blog/addViewCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 点赞数+1
 */
export const addPraiseCount = (params) => {
    return request({
        url: '/blog/addPraiseCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 点赞数-1
 */
export const decreasePraiseCount = (params) => {
    return request({
        url: '/blog/decreasePraiseCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 获取点赞数
 */
export const getPraiseCount = (params) => {
    return request({
        url: '/blog/getPraiseCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 点踩数+1
 */
export const addUnPraiseCount = (params) => {
    return request({
        url: '/blog/addUnPraiseCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 点踩数-1
 */
export const decreaseUnPraiseCount = (params) => {
    return request({
        url: '/blog/decreaseUnPraiseCount',
        method: 'post',
        params: params
    })
} 

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 获取点踩数
 */
export const getUnPraiseCount = (params) => {
    return request({
        url: '/blog/getUnPraiseCount',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 是否已点赞
 */
export const isPraised = (params) => {
    return request({
        url: '/blog/isPraised',
        method: 'post',
        params: params
    })
}

/**
 * @param {*} params：{blogId}
 * @return {*}
 * @Description: 是否已点踩
 */
export const isUnPraised = (params) => {
    return request({
        url: '/blog/isUnPraised',
        method: 'post',
        params: params
    })
}