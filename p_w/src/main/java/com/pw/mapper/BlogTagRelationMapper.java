package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.BlogTag;
import com.pw.domain.BlogTagRealation;
import com.pw.domain.User;
import com.pw.vo.BlogTagVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//代表持久层
public interface BlogTagRelationMapper extends BaseMapper<BlogTagRealation> {
    Integer insertTags(List<String> tags, Long blogId);

    Integer countByIds(List<String> ids, String blogId);

    List<BlogTagVO> listBlogTag(BlogTag blogTag);

    String isTagExit(String tagName);

    Integer countBlogTag(BlogTag blogTag);
}