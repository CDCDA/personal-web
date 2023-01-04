package com.pw.controller;

import com.pw.entity.AjaxResult;
import com.pw.entity.Blog;
import com.pw.entity.BlogLabel;
import com.pw.entity.User;
import com.pw.service.BlogLabelService;
import com.pw.service.BlogService;
import com.pw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.pw.controller.BaseController;
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
    public static final int PAGE_SIZE = 5;

    @RequestMapping("/getBlogByUserId")
    public AjaxResult getBlogByUserId(int userId, Integer page) {
        //根据页码计算起始行
        int startRow = 0;
        if (page != null) {
            startRow = (page - 1) * PAGE_SIZE;
        }
        return toAjax(blogService.getBlogByUserId(userId, startRow));
    }

    @RequestMapping("/getBlogRowCount")
    public int getBlogRowCount(int userId) {
        return blogService.getBlogRowCount(userId);
    }

    @RequestMapping("/getBlogByBlogId")
    public Blog getBlogByBlogId(int blogId) {
        Blog blog = blogService.getBlogByBlogId(blogId);
        List<BlogLabel> labelList = blogLabelService.getBlogLabel(blogId);
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
        System.out.println(blog);
        if(blog.getBlogId() != null){
            return blogService.updateBlog(blog);
        }
        return blogService.createBlog(blog) ;
    }


}
