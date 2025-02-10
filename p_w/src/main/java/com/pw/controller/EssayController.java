package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.common.utils.TokenUtil;
import com.pw.domain.Essay;
import com.pw.domain.ImageRelation;
import com.pw.dto.EssayDTO;
import com.pw.service.EssayService;
import com.pw.service.ImageRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.TokenUtil.getTokenUserId;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@Api(tags = "随笔")
@RequestMapping("/pw/essay")
public class EssayController extends BaseController implements convertController {

    @Autowired
    private EssayService essayService;

    @Autowired
    private ImageRelationService imageRelationService;


    @GetMapping("/list")
    @ApiOperation(value = "查询随笔列表", notes = "", httpMethod = "GET")
    public Result list(EssayDTO essay) {
        essay.setUserId(TokenUtil.getTokenUserId());
        return resultList(essayService.listEssay(essay), essayService.count(essay));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询随笔数", notes = "", httpMethod = "GET")
    public Result count(EssayDTO essayDTO) {
        return resultData(essayService.count(essayDTO));
    }


    @GetMapping("/countEssayByDateRange")
    @ApiOperation(value = "按时间范围查询随笔列表计数", notes = "", httpMethod = "GET")
    public Result countEsssayByDateRange(String startTime, String endTime) throws ParseException {
        String userId = String.valueOf(TokenUtil.getTokenUserId());
        return resultData(essayService.countEssayByDateRange(userId, startTime, endTime));
    }

    @GetMapping("/getEssayById")
    @ApiOperation(value = "根据随笔id查询随笔", notes = "", httpMethod = "GET")
    public Result selectEssayById(String essayId) {
        return resultData(essayService.getEssayById(essayId));
    }

    @UserLoginToken
    @PostMapping("/save")
    @ApiOperation(value = "保存或修改随笔", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Essay essay) {
        List<String> urls = new ArrayList<>();
        essay.setUserId(getTokenUserId());
        List<ImageRelation> imageRelationList = essay.getImageRelations();
        if (!isEmpty(essay.getId())) {
            essayService.updateById(essay);
            QueryWrapper<ImageRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("essay_id", essay.getId());
            imageRelationService.remove(wrapper);
            if (imageRelationList.size() > 0) {
                for (ImageRelation imageRelation : imageRelationList) {
                    if (!isEmpty(imageRelation.getUrl())) {
                        urls.add(imageRelation.getUrl());
                    }
                }
                imageRelationService.insertEssayImageRelations(urls, essay.getId());
            }
            return resultExit(essayService.updateById(essay));
        }
        essay.setId(new SnowFlake(1, 0).nextId());
        essayService.save(essay);
        if (imageRelationList.size() > 0) {
            for (ImageRelation imageRelation : imageRelationList) {
                if (!isEmpty(imageRelation.getUrl())) {
                    urls.add(imageRelation.getUrl());
                }
            }
            imageRelationService.insertEssayImageRelations(urls, essay.getId());
        }
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除随笔", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(essayService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除随笔", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(essayService.removeByIds(ids));
    }
}
