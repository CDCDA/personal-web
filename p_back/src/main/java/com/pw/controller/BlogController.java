package com.pw.controller;

import com.alibaba.druid.util.StringUtils;
import com.pw.entity.*;
import com.pw.service.BlogLabelService;
import com.pw.service.BlogRecordService;
import com.pw.service.BlogService;
import com.pw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pw.controller.BaseController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pw.controller.BaseController.toAjax;

@CrossOrigin //在服务器端支持跨域访问
@RestController //如果全是ajax请求
@RequestMapping("/blog")
public class BlogController {
    //一定会有业务逻辑层的对象
    @Autowired
    BlogService blogService;
    @Autowired
    BlogLabelService blogLabelService;
    @Autowired
    BlogRecordService blogRecordService;
    public static final int START_ROW = 0;
    public static final int PAGE_SIZE = 10;

    @RequestMapping("/getBlogByUserId")
    public AjaxResult getBlogByUserId(int userId, Integer startRow,Integer pageSize) {
        //根据页码计算起始行
        int start_row = 0;
        int page_size = 10;
        if (pageSize != null) {
            page_size = pageSize;
        }
        if (startRow != null) {
            start_row = (startRow - 1) * page_size;
        }

        List<Blog> blogList= blogService.getBlogByUserId(userId, start_row,page_size);
        blogList.forEach(blog->{
            System.out.println("博客"+blog);
            List<BlogLabel> blogLabels = blogLabelService.getBlogLabel(blog.getBlogId());
            blog.setBlogLabelList(blogLabels);
        });
        return toAjax(blogList);
    }

    @RequestMapping("/getBlogRowCount")
    public int getBlogRowCount(int userId) {
        return blogService.getBlogRowCount(userId);
    }

    @RequestMapping("/getBlogByBlogId")
    public Blog getBlogByBlogId(int blogId) {
        Blog blog = blogService.getBlogByBlogId(blogId);
        List<BlogLabel> labelList = blogLabelService.getBlogLabel(String.valueOf(blogId));
//        blog.setBlogLabel(labelList);
        return blog;
    }

    @RequestMapping("/deleteBlogById")
    public int deleteBlogById(int userId) {
        return blogService.deleteBlogById(userId);
    }

    @RequestMapping("/createBlog")
    public int createUser(Blog blog) {
//        String blogId = System.currentTimeMillis() + "";
//        blog.setBlogId(blogId);
        if(blog.getBlogId() != null){
            blogService.updateBlog(blog);
            int result = blogLabelService.deleteBlogLabelById(blog.getBlogId());
            System.out.println("博客"+result);
            insertBlogLabel(blog);
            return 1;
        }
        else{
            blog.setBlogId((Math.random()*100000000)+"");
            blogService.createBlog(blog);
            insertBlogLabel(blog);
            return 1;
        }

    }

    private void insertBlogLabel(Blog blog) {
        String[] updateList = blog.getBlogLabel().split(",");
        List<BlogLabel> blogLabels =  new ArrayList<>();
        for(int i = 0;i < updateList.length;i++) {
            BlogLabel blogLabel = new BlogLabel(null,updateList[i],blog.getBlogId());
            blogLabels.add(blogLabel);
        }
        blogLabelService.createBlogLabel(blogLabels);
    }

    @RequestMapping("/createBlogRecord")
    private void insertBlogRecord(BlogRecord blogRecord){
        if(blogRecord.getBlogRecordId() != null)
            blogRecordService.createBlogRecord(blogRecord);
        else
            blogRecordService.updateBlogRecord(blogRecord);
    }
    @RequestMapping("/deleteBlogRecord")
    private void deleteBlogRecord(String blogRecordId){
        blogRecordService.deleteBlogRecord(blogRecordId);
    }
    @RequestMapping("/getBlogRecord")
    public AjaxResult getBlogRecord(String userId, Integer startRow,Integer pageSize) {
        //根据页码计算起始行
        int start_row = 0;
        int page_size = 10;
        if (pageSize != null) {
            page_size = pageSize;
        }
        if (startRow != null) {
            start_row = (startRow - 1) * page_size;
        }
        List<BlogRecord> blogRecordList= blogRecordService.getBlogRecord(userId, startRow,page_size);
        return toAjax(blogRecordList);
    }

}
