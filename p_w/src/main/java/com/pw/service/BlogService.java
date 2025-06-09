package com.pw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.domain.Blog;
import com.pw.dto.BlogPageDTO;
import com.pw.vo.*;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/18 17:39
 * @description <博客接口>
 **/
public interface BlogService extends IService<Blog> {
    List<BlogVO> list(BlogPageDTO blog);

    List<BlogTypeDataVO> listByType(Integer number);


    BlogVO getBlogById(Long blogId);

    Integer count(BlogPageDTO blog);

    List<BlogCountVO> countBlogByDateRange(String userId, String startTime,String endTime);

    List<BlogTypeCountVO> countBlogByType(String userId, String startTime,String endTime);

    List<BlogTagCountVO> countBlogByTag(String userId, String startTime, String endTime);

    String getRandomBlog();

    String getPreBlog(String blogId);

    String getNextBlog(String blogId);

}
