package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.Cate;
import com.pw.domain.Game;
import com.pw.service.CateService;
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
@Api(tags = "美食")
@RequestMapping("/pw/cate")
public class CateController extends BaseController implements convertController {

    @Autowired
    private CateService cateService;

    @GetMapping("/list")
    @ApiOperation(value = "查询美食列表", notes = "", httpMethod = "GET")
    public Result list(Cate cate) {
        Page page =setPage(cate);
        page.addOrder(OrderItem.desc("create_time"));
        IPage<Game> result = cateService.page(page, convertWrap(cate));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询美食数", notes = "", httpMethod = "GET")
    public Result count(Cate cate) {
        return resultData(cateService.count());
    }

    @GetMapping("/getCateById")
    @ApiOperation(value = "根据美食id查询美食", notes = "", httpMethod = "GET")
    public Result selectCateById(String cateId) {
        return resultData(cateService.getById(cateId));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改美食", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Cate cate) {
        if (!isEmpty(cate.getId())) {
            return resultExit(cateService.updateById(cate));
        }
        cate.setId(new SnowFlake(1,0).nextId());
        cateService.save(cate);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除美食", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(cateService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除美食", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(cateService.removeByIds(ids));
    }
}
