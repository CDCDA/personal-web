package com.pw.common.utils;

import cn.hutool.core.util.ObjectUtil;
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
            if (value != null && !key.equals("pageNum") && !key.equals("pageSize") && !key.equals("startTime") && !key.equals("endTime") && !key.equals("orderBy")&& !key.equals("asc") && !value.equals(""))
                wrapper.like(camelUnder(key), value);
        });
        if (ObjectUtil.isNotEmpty(map.get("startTime")) && ObjectUtil.isNotEmpty(map.get("endTime"))) {
            wrapper.between("create_time", map.get("startTime"), map.get("endTime"));
        }
        if (ObjectUtil.isEmpty(map.get("asc"))) {
            if (ObjectUtil.isEmpty(map.get("orderBy"))) {
                wrapper.orderByDesc("create_time");
            } else {
                wrapper.orderByDesc((String) map.get("orderBy"));
            }
        } else {
            if (ObjectUtil.isEmpty(map.get("orderBy"))) {
                wrapper.orderByAsc("create_time");
            } else {
                wrapper.orderByAsc((String) map.get("orderBy"));
            }
        }


        return wrapper;
    }

    public static <T> QueryWrapper<T> mapToEqWrap(Map<String, Object> map) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        map.forEach((key, value) -> {
            if (value != null && !key.equals("pageNum") && !key.equals("pageSize") && !key.equals("startTime") && !key.equals("endTime") && !key.equals("orderBy")&& !key.equals("asc") && !value.equals("")) {
                wrapper.eq(camelUnder(key), value);
            }
        });
        if (ObjectUtil.isNotEmpty(map.get("startTime")) && ObjectUtil.isNotEmpty(map.get("endTime"))) {
            wrapper.between("create_time", map.get("startTime"), map.get("endTime"));
        }
        if (ObjectUtil.isEmpty(map.get("asc"))) {
            if (ObjectUtil.isEmpty(map.get("orderBy"))) {
                wrapper.orderByDesc("create_time");
            } else {
                wrapper.orderByDesc((String) map.get("orderBy"));
            }
        } else {
            if (ObjectUtil.isEmpty(map.get("orderBy"))) {
                wrapper.orderByAsc("create_time");
            } else {
                wrapper.orderByAsc((String) map.get("orderBy"));
            }
        }
        return wrapper;
    }

}
