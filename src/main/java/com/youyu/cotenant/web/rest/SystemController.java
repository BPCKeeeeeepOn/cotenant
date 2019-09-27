package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @DeleteMapping("/cache/{key}/delete")
    public ResponseResult delCache(@PathVariable("key")String key){
        systemService.delCache(key);
        return ResponseResult.success();
    }
}
