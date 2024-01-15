package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.Wallpaper;
import com.pw.service.WallpaperService;
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
@Api(tags = "壁纸")
@RequestMapping("/pw/wallpaper")
public class WallpaperController extends BaseController implements convertController {

    @Autowired
    private WallpaperService wallpaperService;

    @GetMapping("/list")
    @ApiOperation(value = "查询壁纸列表", notes = "", httpMethod = "GET")
    public Result list(Wallpaper wallpaper) {
        IPage<Wallpaper> result = wallpaperService.page(setPage(wallpaper), convertWrap(wallpaper));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询壁纸数", notes = "", httpMethod = "GET")
    public Result count(Wallpaper Wallpaper) {
        return resultData(wallpaperService.count());
    }

    @GetMapping("/getWallpaperById")
    @ApiOperation(value = "根据壁纸id查询壁纸", notes = "", httpMethod = "GET")
    public Result selectWallpaperById(String wallpaperId) {
        return resultData(wallpaperService.getById(wallpaperId));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改壁纸", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Wallpaper wallpaper) {

        if (!isEmpty(wallpaper.getId())) {
            return resultExit(wallpaperService.updateById(wallpaper));
        }
        wallpaper.setId(new SnowFlake(1,0).nextId());
        wallpaperService.save(wallpaper);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除壁纸", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(wallpaperService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除壁纸", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(wallpaperService.removeByIds(ids));
    }
}
