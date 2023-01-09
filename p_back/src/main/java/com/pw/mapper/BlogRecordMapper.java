package com.pw.mapper;

import com.pw.entity.BlogLabel;
import com.pw.entity.BlogRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRecordMapper {

    List<BlogRecord> getBlogRecord(@Param("userId") String userId,
                                   @Param("startRow") int startRow);

    int createBlogRecord(BlogRecord blogRecord);

    int deleteBlogRecord(String blogRecordId);

    int updateBlogRecord(BlogRecord blogRecord);
}
