package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.Album;
import com.pw.domain.Blog;
import com.pw.domain.Essay;
import com.pw.dto.AlbumDTO;
import com.pw.dto.EssayDTO;
import com.pw.vo.AlbumVO;
import com.pw.vo.EssayVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * @author cyd
 * @date 2023/11/7 21:49
 * @description <相册>
 **/
@Repository
public interface AlbumMapper extends BaseMapper<Album> {

    List<AlbumVO> listAlbum(AlbumDTO albumDTO);

    AlbumVO getAlbumById(String albumId);


    Integer count(AlbumDTO albumDTO);
}
