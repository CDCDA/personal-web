package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.Game;
import com.pw.domain.Wallpaper;
import org.springframework.stereotype.Repository;

@Repository//代表持久层
public interface GameMapper extends BaseMapper<Game> {

}