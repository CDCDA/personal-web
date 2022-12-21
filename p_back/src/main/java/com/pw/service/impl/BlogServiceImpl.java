package com.pw.service.impl;

import com.pw.entity.Blog;
import com.pw.entity.User;
import com.pw.mapper.BlogMapper;
import com.pw.mapper.UserMapper;
import com.pw.service.BlogService;
import com.pw.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    //一定会有数据访问层对象
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogByUserId(int userId, int starRow) {
        return blogMapper.getBlogByUserId(userId, starRow);
    }

    @Override
    public int getBlogRowCount(int userId) {
        return blogMapper.getBlogRowCount(userId);
    }

    @Override
    public Blog getBlogByBlogId(int blogId) {
        return blogMapper.getBlogByBlogId(blogId);
    }

    @Override
    public int createBlog(Blog blog) {
        return blogMapper.createBlog(blog);
    }

    @Override
    public int deleteBlogById(int blogId) {
        return blogMapper.deleteBlogById(blogId);
    }

}
