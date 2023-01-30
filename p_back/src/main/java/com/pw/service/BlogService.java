package com.pw.service;

import com.pw.entity.Blog;
import com.pw.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService {
    List<Blog> getBlogByUserId(int userId, int starRow,int pageSize);

    int getBlogRowCount(int userId);

    Blog getBlogByBlogId(int blogId);

    int createBlog(Blog blog);

    int updateBlog(Blog blog);
    int deleteBlogById(int blogId);

    List<Blog> getBlogByTime(String userId, String stratTime,String endTime);

    int addViewCount(int blogId);

    int addPraiseCount(int blogId,int userId);

    int addUnPraiseCount(int blogId,int userId);

    int decreaseUnPraiseCount(int blogId,int userId);

//    int createBlogCollection(int blogId,int userId);

    int decreasePraiseCount(int blogId,int userId);

    int getPraiseCount(int blogId);
    int getUnPraiseCount(int blogId);

    int isPraised(int blogId,int userId);
    int isUnPraised(int blogId,int userId);
}
