package com.pw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.domain.Essay;
import com.pw.dto.EssayDTO;
import com.pw.vo.EssayCountVO;
import com.pw.vo.EssayVO;

import java.util.List;


/***
 * @author cyd
 * @date 2023/5/18 17:39
 * @description <用户接口>UserService
 **/

public interface EssayService extends IService<Essay> {

    List<Essay> listEssay(EssayDTO essayDTO);

    EssayVO getEssayById(String essayId);

    Integer count(EssayDTO essayDTO);

    List<EssayCountVO> countEssayByDateRange(String userId, String startTime, String endTime);

}
