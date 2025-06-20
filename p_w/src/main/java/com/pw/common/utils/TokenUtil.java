package com.pw.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/***
 * @author cyd
 * @date 2023/6/9 16:36
 * @description <>
 **/
public class TokenUtil {

    public static long getTokenUserId() {
        String token = getRequest().getHeader("Authorization");// 从 http 请求头中取出 token
        if(ObjectUtil.isEmpty(token)){
            return 0L;
        }
        Long userId = Long.valueOf(JWT.decode(token).getAudience().get(0));
        return userId;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

}
