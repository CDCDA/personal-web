package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.Album;
import com.pw.dto.AlbumDTO;
import com.pw.mapper.AlbumMapper;
import com.pw.service.AlbumService;
import com.pw.vo.AlbumVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;
    @Override
    public List<AlbumVO> listAlbum(AlbumDTO albumDTO) {
        return albumMapper.listAlbum(albumDTO);
    }

    @Override
    public AlbumVO getAlbumById(String albumId) {
        return albumMapper.getAlbumById(albumId);
    };

    @Override
    public Integer count(AlbumDTO albumDTO) {
        return albumMapper.count(albumDTO);
    };
}
