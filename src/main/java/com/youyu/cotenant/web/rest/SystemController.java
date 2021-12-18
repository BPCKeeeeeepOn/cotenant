package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.SystemService;
import com.youyu.cotenant.utils.RedisUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private RedisUtils redisUtils;

    @DeleteMapping("/cache/{key}/delete")
    public ResponseResult delCache(@PathVariable("key") String key) {
        systemService.delCache(key);
        return ResponseResult.success();
    }

    @PostMapping("/cache/{key}/add")
    public ResponseResult addCache(@PathVariable("key") String key, @RequestBody Map<String, String> params) {
        if (MapUtils.isNotEmpty(params)) {
            String value = params.get("value");
            systemService.addCache(key, value, 24 * 30);
        }
        return ResponseResult.success();
    }

    @GetMapping("/college/list")
    public ResponseResult getCollege(@RequestParam("city") String city) {
        return ResponseResult.success().body(systemService.getCollege(city));
    }

    /**
     * 提示用户更新
     *
     * @return
     */
    @GetMapping("/update")
    public ResponseResult updateSys() {
        return ResponseResult.success().body(systemService.sysUpdate());
    }

    /**
     * 是否隐藏
     * @return
     */
    @GetMapping("/hideValue")
    public ResponseResult getHideValue() {
        String hideValue = redisUtils.getCache("hide_value");
        Map<String, String> result = new HashMap<>();
        result.put("value", hideValue);
        return ResponseResult.success().body(result);
    }
}
