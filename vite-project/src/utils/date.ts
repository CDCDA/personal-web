/*
 * @Author: chenyd
 * @Date: 2023-01-12 14:03:57
 * @LastEditTime: 2023-08-05 13:44:48
 * @Description:日期处理方法合集
 */

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 日期转 年-月-日 string
 */
export const formateDate = (type: String, date: Date) => {
  let year = date.getFullYear().toString();
  let month = (date.getMonth() + 1).toString().padStart(2, '0');
  let day = date.getDate().toString().padStart(2, '0');
  let hours = date.getHours().toString().padStart(2, '0');
  let minutes = date.getMinutes().toString().padStart(2, '0');
  let seconds = date.getSeconds().toString().padStart(2, '0');

  return `${type.includes('YY') ? year : ''}${type.includes('MM') ? '-' + month : ''}${
    type.includes('dd') ? '-' + day : ''
  }${type.includes('hh') ? '-' + hours : ''}${type.includes('mm') ? '-' + minutes : ''}${
    type.includes('ss') ? '-' + seconds : ''
  }`;
};

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取本周第一天
 */
export const getWeekStartDay = (date: Date) => {
  return new Date(
    new Date(date).getTime() -
      3600 * 1000 * 24 * (new Date(date).getDay() == 0 ? 6 : new Date(date).getDay() - 1)
  );
};

/**
 * @description: 获取本周最后一天
 * @param {*} date
 * @return {*}
 */
export const getWeekEndDay = (date: Date) => {
  return new Date(
    new Date(date).getTime() +
      3600 * 1000 * 24 * (new Date(date).getDay() == 0 ? 0 : 7 - new Date(date).getDay())
  );
};

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取月份第一天
 */
export const getMonthStartDay = (date: Date) => {
  var y = date.getFullYear(); //获取年份
  var m = (date.getMonth() + 1) as any; //获取月份
  var d = '01';
  m = m < 10 ? '0' + m : m; //月份补 0
  return [y, m, d].join('-');
};

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取月份最后一天
 */
export const getMonthEndDay = () => {
  var y = new Date().getFullYear(); //获取年份
  var m = (new Date().getMonth() + 1) as any; //获取月份
  var d = new Date(y, m, 0).getDate() as any; //获取当月最后一日
  m = m < 10 ? '0' + m : m; //月份补 0
  d = d < 10 ? '0' + d : d; //日数补 0
  return [y, m, d].join('-');
};

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取当前年第一天
 */
export const getYearStartDay = (date: Date) => {
  var tYear = date.getFullYear();
  var startTime = new Date(tYear, 0, 1); //当前年第一天
  return formateDate('YY-MM-dd-hh-mm-ss', startTime);
};

/**
 * @param {Date} date
 * @return {string}  yy-mm-dd
 * @Description: 获取当前年最后一天
 */
export const getYearEndDay = (date: Date) => {
  var tYear = date.getFullYear();
  var endTime = new Date(tYear, 11, 31); //当前年最后一天
  return formateDate('YY-MM-dd-hh-mm-ss', endTime);
};

/**
 * @param {Date} date
 * @param {int} preNum
 * @return {string}  yy-mm-dd
 * @Description: 获取前n个月时间
 */
export const getPreMonthDate = (date: Date, preNum: number) => {
  let cdate = new Date(date.valueOf());
  // 日期必须设置为1-28号内，
  // 当月日期比上月日期的最大还要大时，就会直接进入下一个月的Date
  cdate.setMonth(cdate.getMonth() - preNum, 1);
  return formateDate('YY-MM-dd-hh-mm-ss', cdate);
};

/**
 * @description: 时间差
 * @param {any} startTime
 * @param {any} endTime
 * @return {*}
 */
export const monthDayDiff = (startTime: any, endTime: any) => {
  let flag = [1, 3, 5, 7, 8, 10, 12, 4, 6, 9, 11, 2];
  let start = new Date(startTime);
  let end = new Date(endTime);
  let year = end.getFullYear() - start.getFullYear();
  let month = end.getMonth() - start.getMonth();
  let day = end.getDate() - start.getDate();
  if (month < 0) {
    year--;
    month = end.getMonth() + (12 - start.getMonth());
  }
  if (day < 0) {
    month--;
    let index = flag.findIndex(temp => {
      return temp === start.getMonth() + 1;
    });
    let monthLength;
    if (index <= 6) {
      monthLength = 31;
    } else if (index > 6 && index <= 10) {
      monthLength = 30;
    } else {
      monthLength = 28;
    }
    day = end.getDate() + (monthLength - start.getDate());
  }
  return `${year}年${month}月`;
};
