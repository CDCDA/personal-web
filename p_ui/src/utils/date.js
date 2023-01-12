/*
 * @Author: chenyd
 * @Date: 2023-01-12 14:03:57
 * @LastEditTime: 2023-01-12 15:15:53
 * @Description:日期处理方法合集 
 */

/**
 * @param {Date} date    
 * @return {string}  yy-mm-dd
 * @Description: 日期转string
 */
const dateTimeToString = (date) => {
    var y = date.getFullYear();
    var M = date.getMonth() + 1;
    var d = date.getDate();
    var H = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m) + ":" + (s < 10 ? ('0' + s) : s);
}

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取本周第一天
 */
const getWeekStartDay = (date) => {
    return new Date(new Date(date).getTime() -
        (3600 * 1000 * 24 * (new Date(date).getDay() == 0 ? 6 : new Date(date).getDay() - 1)))
}

const getWeekEndDay = (date) => {
    return new Date(new Date(date).getTime() +
        (3600 * 1000 * 24 * (new Date(date).getDay() == 0 ? 0 : 7 - new Date(date).getDay(date))))
}

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取月份第一天
 */
const getMonthStartDay = (date) => {
    var y = date.getFullYear(); //获取年份
    var m = date.getMonth() + 1; //获取月份
    var d = '01'
    m = m < 10 ? '0' + m : m; //月份补 0
    return [y, m, d].join("-")
}

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取月份最后一天
 */
const getMonthEndDay = (date) => {
    var y = new Date().getFullYear(); //获取年份
    var m = new Date().getMonth() + 1; //获取月份
    var d = new Date(y, m, 0).getDate(); //获取当月最后一日
    m = m < 10 ? '0' + m : m; //月份补 0
    d = d < 10 ? '0' + d : d; //日数补 0

    return [y, m, d].join("-")
}

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取当前年第一天
 */
const getYearStartDay = (date) => {
    var tYear = date.getFullYear()
    var startTime = new Date(tYear, 0, 1) //当前年第一天
    return dateTimeToString(startTime)
}

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取当前年最后一天
 */
const getYearEndDay = (date) => {
    var tYear = date.getFullYear()
    var endTime = new Date(tYear, 11, 31) //当前年最后一天
    return this.dateTimeToString(endTime)
}

/**
 * @param {Date} date
 * * @param {int} preNum
 * @return {string}  yy-mm-dd
 * @Description: 获取前n个月时间
 */
const getPreMonthDate = (date, preNum) => {
    let cdate = new Date(date.valueOf())
    // 日期必须设置为1-28号内， 
    // 当月日期比上月日期的最大还要大时，就会直接进入下一个月的Date
    cdate.setMonth(cdate.getMonth() - preNum, 1)
    return dateTimeToString(cdate)
}

export default { dateTimeToString, getMonthStartDay, getMonthEndDay, getYearStartDay, getYearEndDay, getWeekStartDay, getWeekEndDay, getPreMonthDate }