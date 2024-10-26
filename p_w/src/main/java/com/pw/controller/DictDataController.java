package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import com.pw.domain.DictData;
import com.pw.service.impl.DictDataServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;

@RestController
@Api(tags = "字典数据")
@RequestMapping("/pw/dictData")
public class DictDataController extends BaseController implements convertController {

    @Autowired
    private DictDataServiceImpl dictDataService;

    @GetMapping("/list")
    @ApiOperation(value = "查询字典数据列表", notes = "", httpMethod = "GET")
    public Result list(DictData dictData) {
        return resultList(dictDataService.list(convertWrap(dictData)), (int) dictDataService.count(convertWrap(dictData)));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询字典数据数", notes = "", httpMethod = "GET")
    public Result count(DictData dictData) {
        return resultData((int) dictDataService.count(convertWrap(dictData)));
    }

    @GetMapping("/getDictDataById")
    @ApiOperation(value = "根据字典id查询字典数据", notes = "", httpMethod = "GET")
    public Result getDictDataById(String id) {
        return resultData(dictDataService.getById(id));
    }

    @GetMapping("/getDictDataByDictType")
    @ApiOperation(value = "根据字典类型查询字典数据", notes = "", httpMethod = "GET")
    public Result getDictDataByDictType(String dictType) {
        LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictData::getDictType,dictType).orderByAsc(DictData::getDictSort);
        return resultData(dictDataService.list(wrapper));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改字典数据", notes = "", httpMethod = "POST")
    public Result save(@RequestBody DictData dictData) {
        dictDataService.saveOrUpdate(dictData);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典数据", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(dictDataService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除字典数据", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(dictDataService.removeByIds(ids));
    }
}
