package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.Essay;
import com.pw.dto.EssayDTO;
import com.pw.vo.EssayCountVO;
import com.pw.vo.EssayVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/***
 * @author cyd
 * @date 2023/11/7 21:46
 * @description <随笔>
 **/
@Repository
public interface EssayMapper extends BaseMapper<Essay> {
    List<Essay> listEssay(EssayDTO userId);

    EssayVO getEssayById(String essayId);


    Integer count(EssayDTO essayDTO);

    List<EssayCountVO> countEssayByDateRange(String userId, String startTime, String endTime);
}
