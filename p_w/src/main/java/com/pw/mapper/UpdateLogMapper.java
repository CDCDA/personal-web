package com.pw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pw.domain.BlogTag;
import com.pw.domain.UpdateLog;
import org.springframework.stereotype.Repository;

@Repository//代表持久层
public interface UpdateLogMapper extends BaseMapper<UpdateLog> {


}