package com.pw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.domain.BlogTag;
import com.pw.domain.UpdateLog;
import com.pw.mapper.BlogTagMapper;
import com.pw.mapper.UpdateLogMapper;
import com.pw.service.BlogTagService;
import com.pw.service.UpdateLogService;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/***
 * @author cyd
 * @date 2023/5/18 17:52
 * @description <>
 **/
@Service
public class UpdateLogServiceImpl extends ServiceImpl<UpdateLogMapper, UpdateLog> implements UpdateLogService {

}
