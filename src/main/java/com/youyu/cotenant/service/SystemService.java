package com.youyu.cotenant.service;

import com.youyu.cotenant.repository.biz.CotenantCommonBizMapper;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.vm.college.CollegeOutVM;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.youyu.cotenant.common.CotenantConstants.DOWNLOAD_URL;

@Service
public class SystemService {

    @Autowired
    private CotenantCommonBizMapper cotenantCommonBizMapper;

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
     * 写入缓存
     *
     * @param key
     * @param value
     * @param time
     */
    public void addCache(String key, String value, long time) {
        mRedisUtils.putCache(key, value, time);
    }

    /**
     * 查询大学名
     *
     * @param name
     * @return
     */
    public List<CollegeOutVM> getCollege(String name) {
        List<CollegeOutVM> list = cotenantCommonBizMapper.selectCollege(name);
        if (!CollectionUtils.isEmpty(list)) {
            CollegeOutVM collegeOutVM = new CollegeOutVM();
            collegeOutVM.setCollegeName("请选择");
            collegeOutVM.setId(NumberUtils.LONG_ZERO);
            list.add(NumberUtils.INTEGER_ZERO, collegeOutVM);
        }
        return list;
    }


    /**
     * 是否更新
     *
     * @return
     */
    public Map<String, String> sysUpdate() {
        Map<String, String> map = new HashMap<>();
        String flag = StringUtils.isNotBlank(mRedisUtils.getCache(SYSTEM_UPDATE_KEY)) ? "1" : "0";
        map.put("flag", flag);
        map.put("download_url", DOWNLOAD_URL);
        return map;
    }
}
