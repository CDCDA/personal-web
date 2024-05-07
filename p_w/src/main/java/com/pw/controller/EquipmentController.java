package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.Equipment;
import com.pw.domain.Game;
import com.pw.service.EquipmentService;
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
@Api(tags = "设备")
@RequestMapping("/pw/equipment")
public class EquipmentController extends BaseController implements convertController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/list")
    @ApiOperation(value = "查询设备列表", notes = "", httpMethod = "GET")
    public Result list(Equipment equipment) {
        Page page =setPage(equipment);
        page.addOrder(OrderItem.desc("create_time"));
        IPage<Game> result = equipmentService.page(page, convertWrap(equipment));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询设备数", notes = "", httpMethod = "GET")
    public Result count(Equipment equipment) {
        return resultData(equipmentService.count());
    }

    @GetMapping("/getGameById")
    @ApiOperation(value = "根据设备id查询设备", notes = "", httpMethod = "GET")
    public Result selectWallpaperById(String equipmentId) {
        return resultData(equipmentService.getById(equipmentId));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改设备", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Equipment equipment) {
        if (!isEmpty(equipment.getId())) {
            return resultExit(equipmentService.updateById(equipment));
        }
        equipment.setId(new SnowFlake(1,0).nextId());
        equipmentService.save(equipment);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除设备", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(equipmentService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除设备", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(equipmentService.removeByIds(ids));
    }
}
