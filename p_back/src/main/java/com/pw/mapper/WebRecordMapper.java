package com.pw.mapper;

import com.pw.entity.BlogLabel;
import com.pw.entity.BlogRecord;
import com.pw.entity.WebRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebRecordMapper {

    List<WebRecord> getWebRecord(
                                 @Param("startRow") int startRow,
                                 @Param("pageSize") int pageSize);

    int createWebRecord(WebRecord webRecord);

    int deleteWebRecord(String webRecordId);

    int updateWebRecord(WebRecord webRecord);
}
