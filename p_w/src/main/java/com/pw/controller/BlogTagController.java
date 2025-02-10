package com.pw.controller;

import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.BlogTag;
import com.pw.mapper.BlogTagRelationMapper;
import com.pw.service.BlogService;
import com.pw.service.BlogTagRelationSerivce;
import com.pw.service.BlogTagService;
import com.pw.vo.BlogTagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/***
 * @author cyd
 * @date 2023/6/14 17:20
 * @description <>
 **/
@RestController
@Api(tags = "博客标签")
@RequestMapping("/pw/blogTag")
public class BlogTagController extends BaseController implements convertController {
    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTagRelationSerivce blogTagRelationSerivce;
    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询标签列表", notes = "", httpMethod = "GET")
    public Result list(BlogTag blogTag) {
        List<BlogTagVO> result = blogTagRelationSerivce.listBlogTag(blogTag);
        return resultList(result, blogTagRelationMapper.countBlogTag(blogTag));
    }

//    @GetMapping("/pageList")
//    @ApiOperation(value = "查询标签列表", notes = "", httpMethod = "GET")
//    public Result pageList(@RequestBody BlogTag blogTag) {
//        List<BlogTagVO> result = blogTagRelationSerivce.li(userId);
//        return resultList(result, blogTagRelationMapper.countBlogTag(userId));
//    }
@GetMapping("/listTagAndBlog")
@ApiOperation(value = "查询标签列表并列出指定数量的博客", notes = "", httpMethod = "GET")
public Result lisTagAndBlog() {
    return resultList(blogTagService.listTypeAndBlog());
}


    @GetMapping("/selectById")
    @ApiOperation(value = "根据id查询标签", notes = "", httpMethod = "GET")
    public Result selectById(String id) {
        BlogTag blogTag = blogTagService.getById(id);
        return resultData(blogTag);
    }

    @GetMapping("/countByUserId/{id}")
    @ApiOperation(value = "根据用户id查询标签数", notes = "", httpMethod = "GET")
    public Result countByUserId(@PathVariable String id) {
        return resultData(blogTagRelationSerivce.countByUserId(id));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改标签", notes = "", httpMethod = "POST")
    public String save(@RequestBody BlogTag blogTag) {
        if (ObjectUtils.isNotEmpty(blogTag.getTagId())) {
            blogTagService.updateById(blogTag);
            return blogTag.getTagId().toString();
        } else {
            String tagId = blogTagRelationSerivce.isTagExit(blogTag.getTagName());
            if (isEmpty(tagId)) {
                blogTag.setTagId(String.valueOf(new SnowFlake(1, 0).nextId()));
                blogTagService.save(blogTag);
                return blogTag.getTagId().toString();
            } else
                return tagId;
        }
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除标签", notes = "", httpMethod = "POST")
    public Result delete(String id) {
        return resultExit(blogTagService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除标签", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(blogTagService.removeByIds(ids));
    }
}
