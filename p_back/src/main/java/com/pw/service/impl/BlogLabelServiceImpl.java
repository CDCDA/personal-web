package com.pw.service.impl;

import com.pw.entity.BlogLabel;
import com.pw.mapper.BlogLabelMapper;
import com.pw.service.BlogLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogLabelServiceImpl implements BlogLabelService {
    //一定会有数据访问层对象
    @Autowired
    BlogLabelMapper blogLabelMapper;

    @Override
    public List<BlogLabel> getBlogLabel(String blogId)  {
        return blogLabelMapper.getBlogLabel(blogId);
    }

    @Override
    public int getLabelCountByName(String name)  {
        return blogLabelMapper.getLabelCountByName(name);
    }
    @Override
    public int createBlogLabel(List<BlogLabel> labelList) {
        return blogLabelMapper.createBlogLabel(labelList);
    }

    @Override
    public int updateBlogLabel(List<BlogLabel> labelList) {
        return blogLabelMapper.updateBlogLabel(labelList);
    }

    @Override
    public int deleteBlogLabelById(String blogId) {
        return blogLabelMapper.deleteBlogLabelById(blogId);
    }

}
