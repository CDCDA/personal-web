package com.pw.common.utils;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/***
 * @author cyd
 * @date 2023/6/21 17:31
 * @description <数组转树形工具类>
 **/
public class ArraysToTreeUtil<T> {

    private Function<T, Object> parent;
    private Function<T, Object> code;
    private Function<T, List<T>> children;

    public ArraysToTreeUtil<T> parent(Function<T, Object> parent) {
        this.parent = parent;
        return this;
    }

    public ArraysToTreeUtil<T> code(Function<T, Object> code) {
        this.code = code;
        return this;
    }

    public ArraysToTreeUtil<T> children(Function<T, List<T>> children) {
        this.children = children;
        return this;
    }

    /**
     * 数组转树形
     * 无指定父节点，则会按照当前集合查询，查询到当前集合没有找到父节点的则为父节点
     */
    public List<T> tree(List<T> list) {
        List<T> treeList = new ArrayList<>();
        Map<Object, T> codeMap = list.stream().collect(Collectors.toMap(t -> code.apply(t).toString(), t -> t));
        list.stream().forEach(t -> {
            Object parentVal = parent.apply(t);
            if (ObjectUtil.isNotEmpty(parentVal)) {
                parentVal = parentVal.toString();
            } else {
                parentVal = "";
            }
            if (!codeMap.containsKey(parentVal)) {
                treeList.add(t);
            } else {
                T parentT = codeMap.get(parentVal);
                children.apply(parentT).add(t);
            }
        });
        return treeList;
    }

    /**
     * 数组转树形
     * 指定父节点，只查询指定父节点下的数据节点
     */
    public List<T> tree(List<T> list, Object pid) {
        List<T> treeList = new ArrayList<>();
        Map<Object, T> codeMap = list.stream().collect(Collectors.toMap(t -> code.apply(t), t -> t));
        list.stream().forEach(t -> {
            if (Objects.equals(pid, parent.apply(t))) {
                treeList.add(t);
            } else {
                T parentT = codeMap.get(parent.apply(t));
                if (Objects.nonNull(parentT)) {
                    children.apply(parentT).add(t);
                }
            }
        });
        return treeList;
    }

}
