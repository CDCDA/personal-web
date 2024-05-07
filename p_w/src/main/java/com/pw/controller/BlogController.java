package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.common.utils.TokenUtil;
import com.pw.domain.Blog;
import com.pw.domain.BlogTag;
import com.pw.domain.BlogTagRealation;
import com.pw.dto.BlogPageDTO;
import com.pw.service.BlogService;
import com.pw.service.BlogTagRelationSerivce;
import com.pw.service.BlogTagService;
import com.pw.vo.BlogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pw.common.utils.ResultUtil.*;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@Api(tags = "博客")
@RequestMapping("/pw/blog")
public class BlogController extends BaseController implements convertController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTagService blogTagService;

    @Autowired
    private BlogTagController blogTagController;

    @Autowired
    private BlogTagRelationSerivce blogTagRelationSerivce;

    @GetMapping("/list")
    @ApiOperation(value = "查询博客列表", notes = "", httpMethod = "GET")
    public Result list(BlogPageDTO blog) {
        blog.setUserId(TokenUtil.getTokenUserId());
        return resultList(blogService.list(blog), blogService.count(blog));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询博客列表计数", notes = "", httpMethod = "GET")
    public Result count(BlogPageDTO blog) {
        blog.setUserId(TokenUtil.getTokenUserId());
        return Result.ok().data(blogService.count(blog));
    }

    @GetMapping("/{blogId}")
    @ApiOperation(value = "根据id查询博客", notes = "", httpMethod = "GET")
    public Result selectBlogById(@PathVariable Long blogId) {
        BlogVO blog = blogService.getBlogById(blogId);
        return resultData(blog);
    }

    @UserLoginToken
    @PostMapping("/save")
    @ApiOperation(value = "保存或修改博客", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Blog blog) {
        if (ObjectUtils.isNotEmpty(blog.getBlogId())) {
            blogService.updateById(blog);
            if (blog.getTags().size() > 0) {
//                List<String> tagIds = new ArrayList<>();
//                for (BlogTag blogTag : blog.getTags()) {
//                    if (!isEmpty(blogTag.getTagId()))
//                        tagIds.add(blogTagController.save(blogTag));
//                }
                QueryWrapper<BlogTagRealation> wrapper = new QueryWrapper<>();
                wrapper.eq("blog_id", blog.getBlogId());
                blogTagRelationSerivce.remove(wrapper);
                List<String> ids = new ArrayList<>();
                for (BlogTag blogTag : blog.getTags()) {
                    if (!isEmpty(blogTag.getTagId()))
                        ids.add(blogTag.getTagId().toString());
                    else {
                        String id = blogTagController.save(blogTag);
                        ids.add(id);
                    }
                }
                blogTagRelationSerivce.insertTags(ids, blog.getBlogId());
                return Result.ok().data(blog.getBlogId().toString());
            }
        } else {
            blog.setBlogId(new SnowFlake(1, 0).nextId());
            blog.setUserId(TokenUtil.getTokenUserId());
            blogService.save(blog);
            if (blog.getTags().size() > 0) {
                List<String> tagIds = new ArrayList<>();
                for (BlogTag blogTag : blog.getTags()) {
                    if (!isEmpty(blogTag.getTagId())) {
                        String a = blogTag.getTagId().toString();
                        tagIds.add(a);
                    } else
                        tagIds.add(blogTagController.save(blogTag));

                }
                blogTagRelationSerivce.insertTags(tagIds, blog.getBlogId());
                return Result.ok().data(blog.getBlogId().toString());
            }
        }
        return Result.ok().data(blog.getBlogId().toString());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除博客", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable String id) {
        return resultExit(blogService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除博客", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(blogService.removeByIds(ids));
    }

    @GetMapping("/countBlogByDateRange")
    @ApiOperation(value = "按时间范围查询博客列表计数", notes = "", httpMethod = "GET")
    public Result countBlogByDateRange(String userId, String startTime, String endTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isEmpty((userId))) {
            userId = String.valueOf(TokenUtil.getTokenUserId());
        }
        return resultData(blogService.countBlogByDateRange(userId, startTime, endTime));
    }

    @GetMapping("/countBlogByType")
    @ApiOperation(value = "按分类查询博客列表计数", notes = "", httpMethod = "GET")
    public Result countBlogByType(String userId, String startTime, String endTime) {
        return resultData(blogService.countBlogByType(userId, startTime, endTime));
    }

    @PostMapping("/uploadImg")
    @ApiOperation(value = "上传文件并保存", notes = "测试上传")
    public Result uploadImg(@RequestParam MultipartFile file) throws IOException {
        //获取文件名(包含后缀)
        String fileName = file.getOriginalFilename();
        //获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名
        fileName = UUID.randomUUID() + suffix;
        //保存图片的路径
        String dirPath = "/www/wwwroot/files";
        File dirFile = new File(dirPath);
        //判断文件如果不存在则创建
        if (!dirFile.exists()) {
            //创建文件夹
            dirFile.mkdirs();
        }
        //得到文件的完整路径
        String filePath = dirPath + "/" + fileName;
        //保存文件
        file.transferTo(new File(filePath));
        //把图片路径返回给前端
        return resultData("http://111.229.144.36:8008/" + fileName);
    }
}
