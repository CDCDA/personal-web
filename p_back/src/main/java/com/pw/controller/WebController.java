package com.pw.controller;

import com.pw.entity.AjaxResult;
import com.pw.entity.WebRecord;
import com.pw.service.WebRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pw.controller.BaseController.toAjax;

@CrossOrigin //在服务器端支持跨域访问
@RestController //如果全是ajax请求
@RequestMapping("/web")
public class WebController {
    @Autowired
    WebRecordService webRecordService;
    @RequestMapping("/createWebRecord")
    private void createWebRecord(WebRecord webRecord){
        if(webRecord.getRecordId() != null)
            webRecordService.createWebRecord(webRecord);
        else
            webRecordService.updateWebRecord(webRecord);
    }
    @RequestMapping("/deleteWebRecord")
    private void deleteWebRecord(String webRecordId){
        webRecordService.deleteWebRecord(webRecordId);
    }
    @RequestMapping("/getWebRecord")
    public AjaxResult getWebRecord(Integer startRow, Integer pageSize) {
        //根据页码计算起始行
        int start_row = 0;
        int page_size = 10;
        if (pageSize != null) {
            page_size = pageSize;
        }
        if (startRow != null) {
            start_row = (startRow - 1) * page_size;
        }
        List<WebRecord> webRecordList= webRecordService.getWebRecord(start_row,page_size);
        return toAjax(webRecordList);
    }
}
