package com.pw.controller;

import com.pw.entity.AjaxResult;
import com.pw.entity.HttpStatus;

public class BaseController {
    public static <T> AjaxResult toAjax(T data){
        AjaxResult resData = new AjaxResult();
        resData.setCode(String.valueOf(HttpStatus.SUCCESS));
        resData.setMsg("查询成功");
        resData.setData(data);
//        if(length != null) {
//            resData.setTotal(length);
//        }
        return resData;
    }

}
