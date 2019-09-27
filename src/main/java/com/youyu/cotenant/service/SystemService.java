package com.youyu.cotenant.service;

import com.youyu.cotenant.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    @Autowired
    private RedisUtils mRedisUtils;

    public void delCache(String key){
        mRedisUtils.delCache(key);
    }
}
