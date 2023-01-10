import request from "@/utils/request"
import qs from 'qs'
//获取网站修改记录 params:{startRow,pageSize}
export const getWebRecord = (params) => {
    return request({
        url: '/web/getWebRecord',
        method: 'post',
        params: params
    })
}

//增加/更新网站修改记录 params:{recordType,recordTime,recordOperation}
export const createWebRecord = (params) => {
    return request({
        url: '/web/createWebRecord',
        method: 'post',
        params: params
    })
}

//删除网站修改记录 params:{webRecordId}
export const deleteWebRecord = (params) => {
    return request({
        url: '/web/deleteWebRecord',
        method: 'post',
        params: params
    })
}