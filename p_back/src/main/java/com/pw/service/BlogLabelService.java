package com.pw.service;

import com.pw.entity.Blog;
import com.pw.entity.BlogLabel;
import com.pw.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BlogLabelService {


    List<BlogLabel> getBlogLabel(int blogId);

    int getLabelCountByName(String name);

    int createBlogLabel(List<BlogLabel> labelList);
    int updateBlogLabel(List<BlogLabel> labelList);
    int deleteBlogLabelById(List<BlogLabel> labelList);
}
