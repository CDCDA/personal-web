
package com.pw.service;
        import com.pw.entity.BlogRecord;
        import org.springframework.stereotype.Service;

        import java.util.List;
@Service
public interface BlogRecordService {
    List<BlogRecord> getBlogRecord(String userId, int startRow);

    int createBlogRecord(BlogRecord blogRecord);

    int deleteBlogRecord(String blogRecordId);

    int updateBlogRecord(BlogRecord blogRecord);
}
