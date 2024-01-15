package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.BlogType;
import com.pw.domain.Wallpaper;
import com.pw.mapper.BlogTypeMapper;
import com.pw.mapper.WallpaperMapper;
import com.pw.service.BlogTypeService;
import com.pw.service.WallpaperService;
import com.pw.vo.BlogTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * @author cyd
 * @date 2023/5/18 17:52
 * @description <>
 **/
@Service
public class WallpaperServiceImpl extends ServiceImpl<WallpaperMapper, Wallpaper> implements WallpaperService {
}
