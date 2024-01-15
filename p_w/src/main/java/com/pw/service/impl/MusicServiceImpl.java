package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.Music;
import com.pw.domain.User;
import com.pw.mapper.MusicMapper;
import com.pw.mapper.UserMapper;
import com.pw.service.MusicService;
import com.pw.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements MusicService {

}
