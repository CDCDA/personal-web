package com.pw.service.impl;

import com.pw.entity.WebRecord;
import com.pw.mapper.WebRecordMapper;
import com.pw.service.WebRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WebRecordServiceImpl implements WebRecordService {
    @Autowired
    WebRecordMapper webRecordMapper;

    @Override
    public List<WebRecord> getWebRecord(int startRow,int pageSize) {
        return webRecordMapper.getWebRecord(startRow,pageSize);
    }

    @Override
    public int createWebRecord(WebRecord webRecord) {
        return webRecordMapper.createWebRecord(webRecord);
    }

    @Override
    public int deleteWebRecord(String webRecordId) {
        return webRecordMapper.deleteWebRecord(webRecordId);
    }

    @Override
    public int updateWebRecord(WebRecord webRecord) {
        return webRecordMapper.updateWebRecord(webRecord);
    }
}
