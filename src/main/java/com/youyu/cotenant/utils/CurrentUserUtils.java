package com.youyu.cotenant.utils;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.service.cache.UserInfoCacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtils {

    @Autowired
    private UserInfoCacheService userInfoCacheService;

    private CurrentUserUtils() {
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @return
     */
    public String getCurrentLoginUserName() {
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
/*        if (StringUtils.isBlank(userName)) {
            throw new BizException(ResponseResult.fail(ResultCode.INVALID_AUTHTOKEN));
        }*/
        return userName;
    }

    /**
     * 获取当前登录用户的userId
     *
     * @return
     */
    public long getCurrUserId() {
        return getCurrUser().getId();
    }

    /**
     * 获取当前登录用户的用户信息
     *
     * @return
     */
    public CotenantUser getCurrUser() {
        return userInfoCacheService.getUserCache(getCurrentLoginUserName());
    }
}
