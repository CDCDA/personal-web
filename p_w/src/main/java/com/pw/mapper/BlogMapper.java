package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.Blog;
import com.pw.dto.BlogPageDTO;
import com.pw.vo.BlogCountVO;
import com.pw.vo.BlogTypeCountVO;
import com.pw.vo.BlogVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//代表持久层
public interface BlogMapper extends BaseMapper<Blog> {
    List<BlogVO> list(BlogPageDTO blog);

    BlogVO getBlogById(Long blogId);

    Integer count(BlogPageDTO blog);

    List<BlogCountVO> countBlogByDateRange(String userId, String startTime, String endTime);

    List<BlogTypeCountVO> countBlogByType(String userId, String startTime, String endTime);

    String getRandomBlog();

    String getPreBlog(String blogId);

    String getNextBlog(String blogId);
}
