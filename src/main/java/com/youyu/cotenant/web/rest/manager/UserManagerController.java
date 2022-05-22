package com.youyu.cotenant.web.rest.manager;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.manager.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_OFFSET;
import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/m/user")
public class UserManagerController {

    @Autowired
    private UserManagerService userManagerService;

    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.success().body(userManagerService.info());
    }


    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_OFFSET) int offset,
                               @RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int limit,
                               @RequestParam(required = false, name = "certificate") String certificate,
                               @RequestParam(required = false, name = "state") Integer state,
                               @RequestParam(required = false, name = "userType") Integer userType,
                               @RequestParam(required = false, name = "userId") Long userId) {
        return ResponseResult.success().body(userManagerService.list(limit, offset, certificate, state, userType, userId));
    }

    @PutMapping("/examine/{id}/{status}/{reason}")
    public ResponseResult verify(@PathVariable("id") Long id, @PathVariable("status") Integer status, @PathVariable("reason") String reason) {
        userManagerService.examine(id, status, reason);
        return ResponseResult.success();
    }

}
