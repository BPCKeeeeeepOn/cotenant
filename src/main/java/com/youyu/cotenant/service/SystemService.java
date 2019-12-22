package com.youyu.cotenant.service;

import com.youyu.cotenant.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SystemService {

    @Autowired
    private RedisUtils mRedisUtils;

    final static String SYSTEM_UPDATE_KEY = "SYSTEM_UPDATE_KEY";


    /**
     * 删除指定缓存
     *
     * @param key
     */
    public void delCache(String key) {
        mRedisUtils.delCache(key);
    }


    /**
     * 是否更新
     * @return
     */
    public Map<String, String> sysUpdate() {
        Map<String, String> map = new HashMap<>();
        String flag;
        if (StringUtils.isNotBlank(mRedisUtils.getCache(SYSTEM_UPDATE_KEY))) {
            flag = "1";
        } else {
            flag = "0";
        }
        map.put("flag", flag);
        map.put("download_url", "https://service.dcloud.net.cn/build/download/f4187b60-24af-11ea-b536-91ba8bd01347");
        return map;
    }
}
