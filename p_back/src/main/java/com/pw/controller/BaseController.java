package com.pw.controller;

import com.github.pagehelper.PageInfo;
import com.pw.entity.AjaxResult;
import com.pw.entity.HttpStatus;

import java.util.List;

public class BaseController {
    public static AjaxResult toAjax(List<?> data){
        AjaxResult resData = new AjaxResult();
        resData.setCode(String.valueOf(HttpStatus.SUCCESS));
        resData.setMsg("查询成功");
        resData.setData(data);
        resData.setTotal(data.size());
        return resData;
    }
}
