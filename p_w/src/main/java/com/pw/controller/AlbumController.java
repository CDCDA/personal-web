package com.pw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.common.utils.TokenUtil;
import com.pw.domain.Album;
import com.pw.domain.ImageRelation;
import com.pw.dto.AlbumDTO;
import com.pw.service.AlbumService;
import com.pw.service.ImageRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.pw.common.utils.ResultUtil.*;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@Api(tags = "相册")
@RequestMapping("/pw/album")
public class AlbumController extends BaseController implements convertController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ImageRelationService imageRelationService;


    @GetMapping("/list")
    @ApiOperation(value = "查询相册列表", notes = "", httpMethod = "GET")
    public Result list(AlbumDTO albumDTO) {
        return resultList(albumService.listAlbum(albumDTO), albumService.count(albumDTO));
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询相册数", notes = "", httpMethod = "GET")
    public Result count(AlbumDTO albumDTO) {
        return resultData(albumService.count(albumDTO));
    }

    @GetMapping("/getAlbumById")
    @ApiOperation(value = "根据相册id查询相册", notes = "", httpMethod = "GET")
    public Result selectAlbumById(String albumId) {
        return resultData(albumService.getAlbumById(albumId));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存或修改相册", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Album album) {
        List<String> urls = new ArrayList<>();
        album.setUserId(TokenUtil.getTokenUserId());
        List<ImageRelation> imageRelationList = album.getImages();
        if (ObjectUtils.isNotEmpty(album.getId())) {
            albumService.updateById(album);
            QueryWrapper<ImageRelation> wrapper = new QueryWrapper<>();
            wrapper.eq("album_id", album.getId());
            imageRelationService.remove(wrapper);
            if (imageRelationList.size() > 0) {
                for (ImageRelation imageRelation : imageRelationList) {
                    if (!isEmpty(imageRelation.getUrl())) {
                        urls.add(imageRelation.getUrl());
                    }
                }
                imageRelationService.insertAlbumImageRelations(urls, album.getId());
            }
            return resultExit(albumService.updateById(album));
        }
        album.setId(new SnowFlake(1, 0).nextId());
        albumService.save(album);
        if (imageRelationList.size() > 0) {
            for (ImageRelation imageRelation : imageRelationList) {
                if (!isEmpty(imageRelation.getUrl())) {
                    urls.add(imageRelation.getUrl());
                }
            }
            imageRelationService.insertAlbumImageRelations(urls, album.getId());
        }
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除相册", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(albumService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除相册", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(albumService.removeByIds(ids));
    }
}
