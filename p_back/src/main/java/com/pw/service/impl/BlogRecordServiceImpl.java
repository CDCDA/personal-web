package com.pw.service.impl;

import com.pw.entity.BlogRecord;
import com.pw.mapper.BlogRecordMapper;
import com.pw.service.BlogRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogRecordServiceImpl implements BlogRecordService {
    //一定会有数据访问层对象
    @Autowired
    BlogRecordMapper blogRecordMapper;

    @Override
    public List<BlogRecord> getBlogRecord(String userId, int startRow)  {
        return blogRecordMapper.getBlogRecord(userId,startRow);
    }

    @Override
    public int createBlogRecord(BlogRecord blogRecord){
        return blogRecordMapper.createBlogRecord(blogRecord);
    }
    @Override
    public int deleteBlogRecord(String blogRecordId) {
        return blogRecordMapper.deleteBlogRecord(blogRecordId);
    }

    @Override
    public int updateBlogRecord(BlogRecord blogRecord) {
        return blogRecordMapper.updateBlogRecord(blogRecord);
    }

}
