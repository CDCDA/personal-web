package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.BlogType;
import com.pw.domain.Wallpaper;
import com.pw.vo.BlogTypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//代表持久层
public interface WallpaperMapper extends BaseMapper<Wallpaper> {

}