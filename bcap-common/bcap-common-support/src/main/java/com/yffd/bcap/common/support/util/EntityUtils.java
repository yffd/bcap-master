package com.yffd.bcap.common.support.util;

import com.yffd.bcap.common.domain.model.entity.EntityObject;
import com.yffd.bcap.common.domain.model.login.LoginInfo;

import java.util.Calendar;

public class EntityUtils {

    private EntityUtils() {
    }

    public static <T extends EntityObject> void initPropsForAdd(T entity, LoginInfo loginInfo) {
        entity.setVersion(0);
        entity.setDelFlag("0");
        entity.setCreateTime(Calendar.getInstance().getTime());
        if(null!=loginInfo) entity.setCreateBy(loginInfo.getUserId());
    }

    public static <T extends EntityObject> void initPropsForUpdate(T entity, LoginInfo loginInfo) {
        entity.setUpdateTime(Calendar.getInstance().getTime());
        if(null!=loginInfo) entity.setUpdateBy(loginInfo.getUserId());
    }

    public static <T extends EntityObject> void initPropsForDelete(T entity, LoginInfo loginInfo) {
        initPropsForUpdate(entity, loginInfo);
        entity.setDelFlag("1");
    }
}
