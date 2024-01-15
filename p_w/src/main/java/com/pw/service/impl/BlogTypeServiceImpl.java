package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.Blog;
import com.pw.domain.BlogType;
import com.pw.mapper.BlogMapper;
import com.pw.mapper.BlogTypeMapper;
import com.pw.service.BlogService;
import com.pw.service.BlogTypeService;
import com.pw.vo.BlogTagVO;
import com.pw.vo.BlogTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/18 17:52
 * @description <>
 **/
@Service
public class BlogTypeServiceImpl  extends ServiceImpl<BlogTypeMapper, BlogType> implements BlogTypeService {

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    @Transactional
    public List<BlogTypeVO> listBlogType(String userId){
        return  blogTypeMapper.listBlogType(userId);
    }
}
