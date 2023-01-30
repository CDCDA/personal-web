package com.pw.controller;

import com.pw.entity.*;
import com.pw.service.BlogLabelService;
import com.pw.service.BlogRecordService;
import com.pw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public AjaxResult getBlogByUserId(int userId, Integer startRow, Integer pageSize) {
        //根据页码计算起始行
        int start_row = 0;
        int page_size = 10;
        if (pageSize != null) {
            page_size = pageSize;
        }
        if (startRow != null && startRow != 0) {
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
    public AjaxResult getBlogByBlogId(int blogId) {
        Blog blog = blogService.getBlogByBlogId(blogId);
        List<BlogLabel> labelList = blogLabelService.getBlogLabel(String.valueOf(blogId));
        blog.setBlogLabel(labelList.toString());
        return toAjax(blog);
    }

    @RequestMapping("/deleteBlogById")
    public int deleteBlogById(int userId) {
        return blogService.deleteBlogById(userId);
    }

    @RequestMapping("/createBlog")
    public int createUser(Blog blog) {
//        String blogId = System.currentTimeMillis() + "";
//        blog.setBlogId(blogId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        java.util.Date date = new Date();//获得当前时间
        String dateTime = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中
        if(blog.getBlogId() != null){
            blogService.updateBlog(blog);
            int result = blogLabelService.deleteBlogLabelById(blog.getBlogId());
            System.out.println("博客"+result);
            insertBlogLabel(blog);
            BlogRecord blogRecord = new BlogRecord(blog.getBlogId(),blog.getBlogTitle(),"更新",null,dateTime,blog.getUserId()+"",null);
            blogRecordService.createBlogRecord(blogRecord);
            return 1;
        }
        else{
            blog.setBlogId((Math.random()*100000000)+"");
            blogService.createBlog(blog);
            insertBlogLabel(blog);
            BlogRecord blogRecord = new BlogRecord(blog.getBlogId(),blog.getBlogTitle(),"新增",null,dateTime,blog.getUserId()+"",null);
            blogRecordService.createBlogRecord(blogRecord);
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
    public AjaxResult getBlogRecord(String userId, Integer startRow, Integer pageSize) {
        //根据页码计算起始行
        int start_row = 0;
        int page_size = 10;
        if (pageSize != null) {
            page_size = pageSize;
        }
        if (startRow != null && startRow != 0) {
            start_row = (startRow - 1) * page_size;
        }
        List<BlogRecord> blogRecordList= blogRecordService.getBlogRecord(userId, start_row,page_size);
        return toAjax(blogRecordList);
    }
    @RequestMapping("/getBlogCountByTime")
    public AjaxResult getBlogCountByTime(String userId,String startTime, String endTime) {
        //根据页码计算起始行
        List<Blog> blogRecordList= blogService.getBlogByTime(userId,startTime,endTime);
        return toAjax(blogRecordList);
    }

    @RequestMapping("/addViewCount")
    public int addViewCount(int blogId) {
        return blogService.addViewCount(blogId);
    }
    @RequestMapping("/addPraiseCount")
    public int addPraiseCount(int blogId,int userId) {
        return blogService.addPraiseCount(blogId,userId);
    }

    @RequestMapping("/decreasePraiseCount")
    public int decreasePraiseCount(int blogId,int userId) {
        return blogService.decreasePraiseCount(blogId,userId);
    }
    @RequestMapping("/addUnPraiseCount")
    public int addUnPraiseCount(int blogId,int userId) {
        return blogService.addUnPraiseCount(blogId,userId);
    }

    @RequestMapping("/getPraiseCount")
    public AjaxResult getPraiseCount(int blogId) {
        return toAjax( blogService.getPraiseCount(blogId));
    }

    @RequestMapping("/isPraised")
    public AjaxResult isPraised(int blogId,int userId) {
        return toAjax( blogService.isPraised(blogId,userId));
    }

    @RequestMapping("/isUnPraised")
    public AjaxResult isUnPraised(int blogId,int userId) {
        return toAjax( blogService.isUnPraised(blogId,userId));
    }


//    @RequestMapping("/createBlogCollection")
//    public int createBlogCollection(int blogId) {
//        return blogService.createBlogCollection(blogId);
//    }
}
