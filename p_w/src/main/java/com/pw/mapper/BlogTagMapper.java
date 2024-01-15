package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.BlogTag;
import com.pw.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//代表持久层
public interface BlogTagMapper extends BaseMapper<BlogTag> {


}