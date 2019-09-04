package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.UserService;
import com.youyu.cotenant.web.rest.vm.user.UserInfoInVM;
import com.youyu.cotenant.web.rest.vm.user.UserRegisterInVM;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     *
     * @param userRegisterInVM
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody UserRegisterInVM userRegisterInVM) {
        int result = userService.register(userRegisterInVM);
        return result == NumberUtils.INTEGER_ZERO ? ResponseResult.fail() : ResponseResult.success();
    }

    /**
     * info
     *
     * @return
     */
    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.success().body(userService.info());
    }

    /**
     * 查询用户个人资料
     *
     * @return
     */
    @GetMapping("/detail")
    public ResponseResult detail() {
        return ResponseResult.success().body(userService.detail());
    }

    /**
     * 完善/更新个人资料
     * @param userInfoInVM
     * @return
     */
    @PutMapping("/update")
    public ResponseResult update(@Valid @RequestBody UserInfoInVM userInfoInVM) {
        userService.update(userInfoInVM);
        return ResponseResult.success();
    }

}