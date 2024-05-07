package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.entity.BaseContext;
import com.pw.common.utils.Result;
import com.pw.common.utils.TokenUtil;
import com.pw.common.utils.emptyJugeUtil;
import com.pw.domain.BlogType;
import com.pw.mapper.BlogTypeMapper;
import com.pw.service.BlogService;
import com.pw.service.BlogTagService;
import com.pw.service.BlogTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;

@RestController
@Api(tags = "博客分类")
@RequestMapping("/pw/blogType")
public class BlogTypeController extends BaseController implements convertController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @GetMapping("/list")
    @ApiOperation(value = "查询分类列表", notes = "", httpMethod = "GET")
    public Result list(BlogType blogType) {
        IPage<BlogType> result = blogTypeService.page(setPage(blogType), convertWrap(blogType));
        return resultIPage(result);
    }

    @GetMapping("/totalList")
    @ApiOperation(value = "查询分类列表(带统计)", notes = "", httpMethod = "GET")
    public Result totaLlist(BlogType blogType) {
        return resultList(blogTypeService.listBlogType(blogType.getUserId()));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询分类数", notes = "", httpMethod = "GET")
    public Result count(String userId) {
        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
        if (StringUtils.isEmpty((userId))) {
            userId = String.valueOf(TokenUtil.getTokenUserId());
        }
        wrapper.eq("user_id", userId);
        return resultData(blogTypeService.count(wrapper));
    }

//    @GetMapping("/typeTree")
//    @ApiOperation(value = "查询分类树", notes = "", httpMethod = "GET")
//    public Result typeTree(String userId) {
//        QueryWrapper<BlogType> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", userId);
//        List<BlogType> blogTypeList = blogTypeService.list(wrapper);
//        return resultData(new ArraysToTreeUtil<BlogType>()
//                .code(BlogType::getTypeId)
//                .parent(BlogType::getParentId)
//                .children(menu -> {
//                    if (menu.getChildren() == null) {
//                        menu.setChildren(new ArrayList<>());
//                    }
//                    return menu.getChildren();
//                })
//                .tree(blogTypeList));
//    }

    @GetMapping("/getById")
    @ApiOperation(value = "根据分类id查询分类", notes = "", httpMethod = "GET")
    public Result selectBlogById(Long typeId) {
        BlogType blogType = blogTypeService.getById(typeId);
        return resultData(blogType);
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改博客分类", notes = "", httpMethod = "POST")
    public Result save(@RequestBody BlogType blogType) {

        if (!emptyJugeUtil.isEmpty(blogType.getTypeId())) {
            blogType.setUpdateTime(new Date());
            blogType.setUpdateBy(BaseContext.getLoginUser());
            return resultExit(blogTypeService.updateById(blogType));
        }

        return resultExit(blogTypeService.save(blogType));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除博客分类", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(blogTypeService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除博客", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(blogTypeService.removeByIds(ids));
    }
}
