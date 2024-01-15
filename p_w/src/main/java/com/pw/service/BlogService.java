package com.pw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.domain.Blog;
import com.pw.dto.BlogPageDTO;
import com.pw.vo.BlogCountVO;
import com.pw.vo.BlogTypeCountVO;
import com.pw.vo.BlogVO;

import java.util.Date;
import java.util.List;

/***
 * @author cyd
 * @date 2023/5/18 17:39
 * @description <博客接口>
 **/
public interface BlogService extends IService<Blog> {
    List<BlogVO> list(BlogPageDTO blog);

    BlogVO getBlogById(Long blogId);

    Integer count(BlogPageDTO blog);

    List<BlogCountVO> countBlogByDateRange(String userId, String startTime,String endTime);

    List<BlogTypeCountVO> countBlogByType(String userId, String startTime,String endTime);

}
