package com.youyu.cotenant.web.rest.manager;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.manager.GroupManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_OFFSET;
import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/m/group")
public class GroupManagerController {

    @Autowired
    private GroupManagerService groupManagerService;


    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_OFFSET) int offset,
                               @RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int limit) {
        return ResponseResult.success().body(groupManagerService.list(limit, offset));
    }

    @GetMapping("/detail/{id}")
    public ResponseResult detail(@PathVariable("id") Long id) {
        return ResponseResult.success().body(groupManagerService.detail(id));
    }

    @PutMapping("/examine/{id}/{status}")
    public ResponseResult verify(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        groupManagerService.examine(id, status);
        return ResponseResult.success();
    }
}
