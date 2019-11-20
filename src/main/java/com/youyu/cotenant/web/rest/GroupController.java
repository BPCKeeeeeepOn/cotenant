package com.youyu.cotenant.web.rest;


import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.GroupService;
import com.youyu.cotenant.web.rest.vm.group.GroupInVM;
import com.youyu.cotenant.web.rest.vm.group.GroupQueryInVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @GetMapping("/list")
    public ResponseResult list(GroupQueryInVM groupQueryInVM) {
        return ResponseResult.success().body(groupService.list(groupQueryInVM));
    }

    @GetMapping("/{id}/detail")
    public ResponseResult detail(@PathVariable("id") Long id) {
        return ResponseResult.success().body(groupService.detail(id));
    }

    @PostMapping("/{id}/join")
    public ResponseResult join(@PathVariable("id") Long id) {
        groupService.join(id);
        return ResponseResult.success();
    }


    @PostMapping("/publish")
    public ResponseResult publish(@Valid @RequestBody GroupInVM groupInVM) {
        groupService.publish(groupInVM);
        return ResponseResult.success();
    }

    @PostMapping("/{id}/reported")
    public ResponseResult reported(@PathVariable("id") Long id, @RequestParam("content") String content) {
        groupService.reported(id, content);
        return ResponseResult.success();
    }

    @GetMapping("/user/{userId}/getGroupId")
    public ResponseResult getGroupId(@PathVariable("userId") Long userId) {
        return ResponseResult.success().body(groupService.getGroupId(userId));
    }
}
