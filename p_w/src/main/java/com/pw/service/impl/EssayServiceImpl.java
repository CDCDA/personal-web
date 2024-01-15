package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.Essay;
import com.pw.dto.EssayDTO;
import com.pw.mapper.EssayMapper;
import com.pw.service.EssayService;
import com.pw.vo.EssayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayServiceImpl extends ServiceImpl<EssayMapper, Essay> implements EssayService {

    @Autowired
    EssayMapper essayMapper;
    @Override
    public List<Essay> listEssay(EssayDTO essayDTO) {
        return essayMapper.listEssay(essayDTO);
    }

    @Override
    public EssayVO getEssayById(String essayId) {
        return essayMapper.getEssayById(essayId);
    }

    @Override
    public Integer count(EssayDTO essayDTO) {
        return essayMapper.count(essayDTO);
    };
}
