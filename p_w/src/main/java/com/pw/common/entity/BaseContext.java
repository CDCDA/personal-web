package com.pw.common.entity;

import com.pw.domain.User;

/***
 * @author cyd
 * @date 2023/6/5 18:00
 * @description <>
 **/
public class BaseContext {


    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param userId
     */
    public static void setLoginUser(String userId) {
        threadLocal.set(userId);
    }

    /**
     * 获取值
     *
     * @return
     */
    public static String getLoginUser() {
        return threadLocal.get();
    }

}
