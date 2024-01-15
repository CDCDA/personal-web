package com.pw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.domain.BlogType;
import com.pw.domain.User;
import com.pw.dto.BlogPageDTO;
import com.pw.mapper.BlogMapper;
import com.pw.vo.BlogTagVO;
import com.pw.vo.BlogTypeVO;
import com.pw.vo.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/18 17:51
 * @description <博客分类接口>
 **/
public interface BlogTypeService  extends IService<BlogType> {
    List<BlogTypeVO> listBlogType(String userId);
}
