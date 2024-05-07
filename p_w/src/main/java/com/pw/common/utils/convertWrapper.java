package com.pw.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

import static com.pw.common.utils.ObjectAndMapUtil.objectToMap;
import static com.pw.common.utils.camelAndUnderlineUntil.camelUnder;

public class convertWrapper {
    public static <T> QueryWrapper<T> convertWrap(T entity) {
        Map<String, Object> params = objectToMap(entity);
        return mapToWrap(params);
    }

    public static <T> QueryWrapper<T> convertEqWrap(T entity) {
        Map<String, Object> params = objectToMap(entity);
        return mapToEqWrap(params);
    }

    public static <T> QueryWrapper<T> mapToWrap(Map<String, Object> map) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        map.forEach((key, value) -> {
            if (value != null && !key.equals("pageNum") && !key.equals("pageSize") && !value.equals(""))
                wrapper.like(camelUnder(key), value);
        });
        wrapper.orderByDesc("create_time");
        return wrapper;
    }

    public static <T> QueryWrapper<T> mapToEqWrap(Map<String, Object> map) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        map.forEach((key, value) -> {
            if (value != null && !key.equals("pageNum") && !key.equals("pageSize") && !value.equals("")) {
                wrapper.eq(camelUnder(key), value);
            }

        });
        return wrapper;
    }

}
