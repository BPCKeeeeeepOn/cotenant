package com.youyu.cotenant.service.cache;

import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CustomUser;
import com.youyu.cotenant.service.UserService;
import com.youyu.cotenant.service.manager.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 用户信息缓存
 *
 * @author USER
 */
@Component
public class UserInfoCacheService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserManagerService userManagerService;

    /**
     * 获取前台用户缓存
     *
     * @param userName
     * @return
     */
    @Cacheable(cacheNames = "cotenant_user", key = "#userName")
    public CotenantUser getUserCache(String userName) {
        CotenantUser user = userService.selectUserByUserName(userName);
        return user;
    }

    /**
     * 获取后台用户缓存
     * @param userName
     * @return
     */
    @Cacheable(cacheNames = "cotenant_manager_user", key = "#userName")
    public CustomUser getUserManagerCache(String userName) {
        return userManagerService.selectUserByUserName(userName);
    }


    /**
     * 删除前台用户缓存
     *
     * @param userName
     */
    @CacheEvict(cacheNames = "cotenant_user", key = "#userName")
    public void removeCache(String userName) {
        return;
    }

    /**
     * 删除后台用户缓存
     *
     * @param userName
     */
    @CacheEvict(cacheNames = "cotenant_manager_user", key = "#userName")
    public void removeManagerCache(String userName) {
        return;
    }

}
