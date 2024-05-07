package com.pw.common.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.pw.common.entity.BaseContext;
import com.pw.common.utils.TokenUtil;
import com.pw.common.utils.emptyJugeUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * 自动填充创建人，创建时间，更新人，更新时间
 */

@Component
public class MybatisHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名
        this.setFieldValByName("createTime", new Date(), metaObject);
        System.out.println("公共字段自动填充[insert]...");
        String userId = String.valueOf(TokenUtil.getTokenUserId());
        if (!emptyJugeUtil.isEmpty(userId)) {
            this.setFieldValByName("createBy", userId, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //属性名
        this.setFieldValByName("updateTime", new Date(), metaObject);
        System.out.println("公共字段自动填充[update]...");
        if (!isEmpty(BaseContext.getLoginUser())) {
            this.setFieldValByName("updateBy", String.valueOf(TokenUtil.getTokenUserId()), metaObject);
        }
    }
}
