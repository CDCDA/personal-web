package com.pw.service.impl;

import com.pw.entity.Blog;
import com.pw.mapper.BlogMapper;
import com.pw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    //一定会有数据访问层对象
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> getBlogByUserId(int userId, int starRow,int pageSize) {
        return blogMapper.getBlogByUserId(userId, starRow,pageSize);
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
    public int updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int deleteBlogById(int blogId) {
        return blogMapper.deleteBlogById(blogId);
    }

    @Override
    public List<Blog> getBlogByTime(String userId,  String stratTime, String endTime) {
        return blogMapper.getBlogByTime(userId,stratTime,endTime);
    }

    @Override
    public int addViewCount(int blogId) {
        return blogMapper.addViewCount(blogId);
    }

    @Override
    public int addPraiseCount(int blogId, int userId) {
        return blogMapper.addPraiseCount(blogId,userId);
    }

    @Override
    public int addUnPraiseCount(int blogId, int userId) {
        return blogMapper.addUnPraiseCount(blogId,userId);
    }

    @Override
    public int decreasePraiseCount(int blogId, int userId) {
        return blogMapper.decreasePraiseCount(blogId,userId);
    }

    @Override
    public int decreaseUnPraiseCount(int blogId, int userId) {
        return blogMapper.decreaseUnPraiseCount(blogId,userId);
    }

    @Override
    public int getPraiseCount(int blogId) {
        return blogMapper.getPraiseCount(blogId);
    }

    @Override
    public int getUnPraiseCount(int blogId) {
        return blogMapper.getUnPraiseCount(blogId);
    }

    @Override
    public int isPraised(int blogId, int userId) {
        return blogMapper.isPraised(blogId,userId);
    }

    @Override
    public int isUnPraised(int blogId, int userId) {
        return blogMapper.isUnPraised(blogId,userId);
    }

}
