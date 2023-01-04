package com.pw.mapper;

import com.pw.entity.Blog;
import com.pw.entity.BlogLabel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogLabelMapper {

    List<BlogLabel> getBlogLabel(String blogId);

    int getLabelCountByName(String name);
    int createBlogLabel(List<BlogLabel> labelList);

    int deleteBlogLabelById(String blogId);

    int updateBlogLabel(List<BlogLabel> labelList);
}
