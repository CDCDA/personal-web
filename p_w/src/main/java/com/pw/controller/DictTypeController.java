package com.pw.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import com.pw.domain.DictType;
import com.pw.service.impl.DictTypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;

@RestController
@Api(tags = "字典类型")
@RequestMapping("/pw/dictType")
public class DictTypeController extends BaseController implements convertController {

    @Autowired
    private DictTypeServiceImpl dictTypeService;

    @GetMapping("/list")
    @ApiOperation(value = "查询字典类型列表", notes = "", httpMethod = "GET")
    public Result list(DictType dictType) {
        return resultList(dictTypeService.list(convertWrap(dictType)), (int) dictTypeService.count(convertWrap(dictType)));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询字典类型数", notes = "", httpMethod = "GET")
    public Result count(DictType dictType) {
        return resultData((int) dictTypeService.count(convertWrap(dictType)));
    }

    @GetMapping("/getDictTypeById")
    @ApiOperation(value = "根据字典类型id查询字典类型", notes = "", httpMethod = "GET")
    public Result selectAlbumById(String id) {
        return resultData(dictTypeService.getById(id));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改字典类型", notes = "", httpMethod = "POST")
    public Result save(@RequestBody DictType dictType) {
        if (ObjectUtil.isEmpty(dictType.getId())) {
            DictType existDict = dictTypeService.list(new LambdaQueryWrapper<DictType>()
                            .eq(DictType::getDictType, dictType.getDictType()))
                    .stream().findFirst().orElse(null);
            if (ObjectUtil.isNotEmpty(existDict)) {
                return Result.error("字典类型已存在");
            }
        }
        dictTypeService.saveOrUpdate(dictType);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典类型", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(dictTypeService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除字典类型", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(dictTypeService.removeByIds(ids));
    }
}
