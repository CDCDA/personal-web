package com.pw.controller;

import cn.hutool.core.util.ObjectUtil;
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

    @GetMapping("/getDictDataByDictType/{dictType}")
    @ApiOperation(value = "根据字典类型查询字典数据", notes = "", httpMethod = "GET")
    public Result getDictDataByDictType(@PathVariable String dictType) {
        LambdaQueryWrapper<DictData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DictData::getDictType, dictType).eq(DictData::getStatus, "1").orderByAsc(DictData::getDictSort);
        return resultData(dictDataService.list(wrapper));
    }

    @PostMapping("/pageDictDataByDictType")
    @ApiOperation(value = "根据字典类型查询字典数据分页", notes = "", httpMethod = "GET")
    public Result getDictDataByDictType(@RequestBody DictData dictData) {
        dictData.setOrderBy("dict_sort");
        dictData.setAsc("asc");
        return resultList(dictDataService.list(convertWrap(dictData)), (int) dictDataService.count(convertWrap(dictData)));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改字典数据", notes = "", httpMethod = "POST")
    public Result save(@RequestBody DictData dictData) {
        if (ObjectUtil.isEmpty(dictData.getId())) {
            DictData existDict = dictDataService.list(new LambdaQueryWrapper<DictData>()
                            .eq(DictData::getDictValue, dictData.getDictValue())
                            .eq(DictData::getDictType, dictData.getDictType()))
                    .stream().findFirst().orElse(null);
            if (ObjectUtil.isNotEmpty(existDict)) {
                return Result.error("字典类型已存在");
            }
        }
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
