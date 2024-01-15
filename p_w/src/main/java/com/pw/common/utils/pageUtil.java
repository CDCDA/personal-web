package com.pw.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pw.common.entity.BaseEntity;
import com.pw.common.entity.BasePageDto;

/***
 * @author cyd
 * @date 2023/5/19 17:55
 * @description <分页处理工具类>
 **/
public class pageUtil {
    public static <T extends BaseEntity> Page setPage(T entity) {
        Integer pageNum = 1;
        Integer pageSize = 99999;
        if (entity.getPageNum() != null && !entity.getPageNum().equals("")) {
            pageNum = entity.getPageNum();
        }
        if (entity.getPageSize() != null && !entity.getPageSize().equals("")) {
            pageSize = entity.getPageSize();
        }
        return new Page(pageNum, pageSize);
    }
//    public static <T extends BasePageDto> Page setPage(T entity) {
//        Integer pageNum = 1;
//        Integer pageSize = 99999;
//        if (entity.getPageNum() != null && !entity.getPageNum().equals("")) {
//            pageNum = entity.getPageNum();
//        }
//        if (entity.getPageSize() == null && !entity.getPageSize().equals("")) {
//            pageSize = entity.getPageSize();
//        }
//        return new Page(pageNum, pageSize);
//    }
}
