package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.Blog;
import com.pw.dto.BlogPageDTO;
import com.pw.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//代表持久层
public interface BlogMapper extends BaseMapper<Blog> {
    List<BlogVO> list(BlogPageDTO blog);

    List<BlogTypeDataVO> listByType(Integer number);

    BlogVO getBlogById(Long blogId);

    Integer count(BlogPageDTO blog);

    List<BlogCountVO> countBlogByDateRange(String userId, String startTime, String endTime);

    List<BlogTypeCountVO> countBlogByType(String userId, String startTime, String endTime);

    List<BlogTagCountVO> countBlogByTag(String userId, String startTime, String endTime);

    String getRandomBlog();

    String getPreBlog(String blogId);

    String getNextBlog(String blogId);
}
