package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.Album;
import com.pw.domain.ImageRelation;
import com.pw.domain.Music;
import com.pw.domain.User;
import com.pw.dto.AlbumDTO;
import com.pw.service.AlbumService;
import com.pw.service.ImageRelationService;
import com.pw.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static com.pw.common.utils.convertWrapper.convertWrap;
import static com.pw.common.utils.pageUtil.setPage;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@Api(tags = "音乐")
@RequestMapping("/pw/music")
public class MusicController extends BaseController implements convertController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private ImageRelationService imageRelationService;


    @GetMapping("/list")
    @ApiOperation(value = "查询音乐列表", notes = "", httpMethod = "GET")
    public Result list(Music music) {
        return resultList(musicService.list(), (int) musicService.count());
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询音乐数", notes = "", httpMethod = "GET")
    public Result count(Music music) {
        return resultData(musicService.count());
    }

    @GetMapping("/getAlbumById")
    @ApiOperation(value = "根据音乐id查询音乐", notes = "", httpMethod = "GET")
    public Result selectMusicById(String musicId) {
        return resultData(musicService.getById(musicId));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改音乐", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Music music) {
        if (!isEmpty(music.getId())) {
            return resultExit(musicService.updateById(music));
        }
        music.setId(new SnowFlake(1,0).nextId());
        musicService.save(music);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除音乐", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(musicService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除音乐", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(musicService.removeByIds(ids));
    }
}
