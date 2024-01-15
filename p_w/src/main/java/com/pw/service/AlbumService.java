package com.pw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.domain.Album;
import com.pw.dto.AlbumDTO;
import com.pw.vo.AlbumVO;

import java.util.List;


/***
 * @author cyd
 * @date 2023/5/18 17:39
 * @description <相册接口>
 **/

public interface AlbumService extends IService<Album> {

    List<AlbumVO> listAlbum(AlbumDTO albumDTO);

    AlbumVO getAlbumById(String albumId);

    Integer count(AlbumDTO albumDTO);
}
