package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.ArraysToTreeUtil;
import com.pw.common.utils.Result;
import com.pw.domain.Comment;
import com.pw.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;

@RestController
@Api(tags = "评论")
@RequestMapping("/pw/comment")
public class CommentController extends BaseController implements convertController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    @ApiOperation(value = "查询评论列表", notes = "", httpMethod = "GET")
    public Result list(Comment comment) {
        IPage<Comment> result = commentService.page(setPage(comment), convertWrap(comment));
        return resultIPage(result);
    }

    @GetMapping("/listTree")
    @ApiOperation(value = "查询树形评论列表", notes = "", httpMethod = "GET")
    public Result listTree(Comment comment) {
        IPage<Comment> result = commentService.page(setPage(comment), convertWrap(comment));
        List<Comment> list = result.getRecords();
        list.forEach(x -> {
            x.setChildren(new ArrayList<>());
        });
        ArraysToTreeUtil<Comment> arraysToTreeUtil = new ArraysToTreeUtil<>();
        arraysToTreeUtil.parent(Comment::getParentId).children(Comment::getChildren).code(Comment::getId);
        result.setRecords(arraysToTreeUtil.tree(list));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询评论数", notes = "", httpMethod = "GET")
    public Result count(Comment comment) {
        return resultData((int) commentService.count(convertWrap(comment)));
    }

    @GetMapping("/getCommentById")
    @ApiOperation(value = "根据id查询评论", notes = "", httpMethod = "GET")
    public Result getCommentById(String id) {
        return resultData(commentService.getById(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改评论", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Comment comment) {
        commentService.saveOrUpdate(comment);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除评论", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(commentService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除评论", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(commentService.removeByIds(ids));
    }
}
