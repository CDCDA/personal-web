package com.pw.mapper;

import com.pw.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {
    List<Blog> getBlogByUserId(
            @Param("userId") int userId,
            @Param("startRow") int startRow);

    int getBlogRowCount(
            @Param("userId") int userId);

    Blog getBlogByBlogId(
            @Param("blogId") int blogId);

    int createBlog(Blog blog);

    int deleteBlogById(
            @Param("blogId") int blogId);


    int updateBlog(Blog blog);
}
