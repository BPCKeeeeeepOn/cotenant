package com.youyu.cotenant.web.rest.manager;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.manager.GroupManagerService;
import com.youyu.cotenant.web.vm.group.BatchGroupInVM;
import com.youyu.cotenant.web.vm.group.GroupInVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_OFFSET;
import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/m/group")
public class GroupManagerController {

    @Autowired
    private GroupManagerService groupManagerService;


    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_OFFSET) int offset,
                               @RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int limit,
                               @RequestParam(required = false, name = "state") Integer state) {
        return ResponseResult.success().body(groupManagerService.list(limit, offset, state));
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

    @PostMapping("/add")
    public ResponseResult addGroup(@RequestBody @Valid GroupInVM groupInVM) {
        groupManagerService.addGroup(groupInVM);
        return ResponseResult.success();
    }

    @PostMapping("/batch/add")
    public ResponseResult batchAddGroup(@RequestBody BatchGroupInVM groups) {
        return ResponseResult.success().body(groupManagerService.batchAddGroup(groups));
    }

    @PutMapping("/edit/{id}")
    public ResponseResult addGroup(@PathVariable("id") Long id, @RequestBody @Valid GroupInVM groupInVM) {
        groupManagerService.editGroup(id, groupInVM);
        return ResponseResult.success();
    }

}
