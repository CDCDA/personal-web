package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.Game;
import com.pw.domain.Wallpaper;
import com.pw.mapper.GameMapper;
import com.pw.mapper.WallpaperMapper;
import com.pw.service.GameService;
import com.pw.service.WallpaperService;
import org.springframework.stereotype.Service;

/***
 * @author cyd
 * @date 2023/5/18 17:52
 * @description <>
 **/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {
}
