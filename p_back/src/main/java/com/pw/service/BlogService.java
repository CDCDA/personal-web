package com.pw.service;

import com.pw.entity.Blog;
import com.pw.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {
    List<Blog> getBlogByUserId(int userId, int starRow);

    int getBlogRowCount(int userId);

    Blog getBlogByBlogId(int blogId);

    int createBlog(Blog blog);

    int deleteBlogById(int blogId);
}
