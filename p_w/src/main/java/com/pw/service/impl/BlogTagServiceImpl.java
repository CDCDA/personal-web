package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.BlogTag;
import com.pw.domain.BlogType;
import com.pw.domain.User;
import com.pw.mapper.BlogTagMapper;
import com.pw.mapper.BlogTagRelationMapper;
import com.pw.mapper.BlogTypeMapper;
import com.pw.mapper.UserMapper;
import com.pw.service.BlogTagService;
import com.pw.service.UserService;
import com.pw.vo.BlogTagVO;
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
public class BlogTagServiceImpl  extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

}
