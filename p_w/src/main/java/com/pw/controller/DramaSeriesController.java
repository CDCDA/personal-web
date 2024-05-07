package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.DramaSeries;
import com.pw.service.DramaSeriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@Api(tags = "影视")
@RequestMapping("/pw/dramaSeries")
public class DramaSeriesController extends BaseController implements convertController {

    @Autowired
    private DramaSeriesService dramaSeriesService;

    @GetMapping("/list")
    @ApiOperation(value = "查询影视列表", notes = "", httpMethod = "GET")
    public Result list(DramaSeries dramaSeries) {
        Page page =setPage(dramaSeries);
        page.addOrder(OrderItem.desc("create_time"));
        IPage<DramaSeries> result = dramaSeriesService.page(page, convertWrap(dramaSeries));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询影视数", notes = "", httpMethod = "GET")
    public Result count(DramaSeries dramaSeries) {
        return resultData(dramaSeriesService.count());
    }

    @GetMapping("/getGameById")
    @ApiOperation(value = "根据影视id查询影视", notes = "", httpMethod = "GET")
    public Result selectDramaSeriesById(String id) {
        return resultData(dramaSeriesService.getById(id));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改影视", notes = "", httpMethod = "POST")
    public Result save(@RequestBody DramaSeries dramaSeries) {
        if (!isEmpty(dramaSeries.getId())) {
            return resultExit(dramaSeriesService.updateById(dramaSeries));
        }
        dramaSeries.setId(new SnowFlake(1,0).nextId());
        dramaSeriesService.save(dramaSeries);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除影视", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(dramaSeriesService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除影视", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(dramaSeriesService.removeByIds(ids));
    }
}
