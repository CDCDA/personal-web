package com.pw.mapper;

import com.pw.entity.BlogLabel;
import com.pw.entity.BlogRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRecordMapper {

    List<BlogRecord> getBlogRecord(String userId,int startRow);

    int createBlogRecord(BlogRecord blogRecord);

    int deleteBlogRecord(String blogRecordId);

    int updateBlogRecord(BlogRecord blogRecord);
}
