package com.pw.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.common.controller.BaseController;
import com.pw.common.controller.convertController;
import com.pw.common.token.UserLoginToken;
import com.pw.common.utils.Result;
import com.pw.common.utils.SnowFlake;
import com.pw.domain.Game;
import com.pw.service.GameService;
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
@Api(tags = "游戏")
@RequestMapping("/pw/game")
public class GameController extends BaseController implements convertController {

    @Autowired
    private GameService gameService;

    @GetMapping("/list")
    @ApiOperation(value = "查询游戏列表", notes = "", httpMethod = "GET")
    public Result list(Game game) {
        Page page =setPage(game);
        page.addOrder(OrderItem.desc("create_time"));
        IPage<Game> result = gameService.page(page, convertWrap(game));
        return resultIPage(result);
    }

    @GetMapping("/count")
    @ApiOperation(value = "查询游戏数", notes = "", httpMethod = "GET")
    public Result count(Game game) {
        return resultData(gameService.count());
    }

    @GetMapping("/getGameById")
    @ApiOperation(value = "根据游戏id查询游戏", notes = "", httpMethod = "GET")
    public Result selectWallpaperById(String gameId) {
        return resultData(gameService.getById(gameId));
    }

    @PostMapping("/save")
    @UserLoginToken
    @ApiOperation(value = "保存或修改游戏", notes = "", httpMethod = "POST")
    public Result save(@RequestBody Game game) {
        if (!isEmpty(game.getId())) {
            return resultExit(gameService.updateById(game));
        }
        game.setId(new SnowFlake(1,0).nextId());
        gameService.save(game);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除游戏", notes = "", httpMethod = "DELETE")
    public Result delete(@PathVariable Long id) {
        return resultExit(gameService.removeById(id));
    }

    @PostMapping("/deleteByIds")
    @ApiOperation(value = "批量删除游戏", notes = "", httpMethod = "POST")
    public Result deleteByIds(@RequestBody List<String> ids) {
        return resultExit(gameService.removeByIds(ids));
    }
}
