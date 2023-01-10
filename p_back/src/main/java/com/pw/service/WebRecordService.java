
package com.pw.service;

import com.pw.entity.Blog;
import com.pw.entity.User;
import com.pw.entity.WebRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface WebRecordService {
    List<WebRecord> getWebRecord(int startRow,int pageSize);

    int createWebRecord(WebRecord webRecord);

    int deleteWebRecord(String webRecordId);

    int updateWebRecord(WebRecord webRecord);
}
