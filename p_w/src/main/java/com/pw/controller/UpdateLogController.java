package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.domain.BlogType;
import com.pw.domain.UpdateLog;
import com.pw.service.UpdateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

/***
 * @author cyd
 * @date 2023/6/14 17:20
 * @description <>
 **/
@RestController
@Api(tags = "更新日志")
@RequestMapping("/pw/updateLog")
public class UpdateLogController extends BaseController implements convertController {
    @Autowired
    private UpdateLogService updateLogService;

    @GetMapping("/count")
    @ApiOperation(value = "查询更新日志数量", notes = "", httpMethod = "GET")
    public Result count(UpdateLog updateLog) {
        QueryWrapper<UpdateLog> wrapper = new QueryWrapper<>();
        return Result.ok().data(updateLogService.count(wrapper));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询更新日志列表", notes = "", httpMethod = "GET")
    public Result list(UpdateLog updateLog) {
        QueryWrapper<UpdateLog> wrapper = convertWrap(updateLog);
        IPage<BlogType> result = updateLogService.page(setPage(updateLog), wrapper);
        return resultIPage(result);
    }

    @GetMapping("/countUpdateLogByDateRange")
    @ApiOperation(value = "按时间范围查询更新日志计数", notes = "", httpMethod = "GET")
    public Result countUpdateLogByDateRange(String startTime, String endTime) {
        return resultData(updateLogService.countUpdateLogByDateRange(startTime, endTime));
    }


    @GetMapping("/selectById")
    @ApiOperation(value = "根据id查询更新日志", notes = "", httpMethod = "GET")
    public Result selectById(String id) {
        return resultData(updateLogService.getById(id));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改更新日志", notes = "", httpMethod = "POST")
    public Result save(@RequestBody UpdateLog updateLog) {
        if (!isEmpty(updateLog.getId())) {
           return resultExit(updateLogService.updateById(updateLog));
        }
        else{
            return resultExit(updateLogService.save(updateLog));
        }
    }

    @PostMapping("/delete")
    @UserLoginToken
    @ApiOperation(value = "删除更新日志", notes = "", httpMethod = "POST")
    public Result delete(String id) {
        return resultExit(updateLogService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @UserLoginToken
    @ApiOperation(value = "批量删除更新日志", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(updateLogService.removeByIds(ids));
    }
}
