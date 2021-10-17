package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.AuthService;
import com.youyu.cotenant.web.vm.MaAuthVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * 微信小程序支付接口
 *
 * @author wyq
 */
@Slf4j
@RestController
@RequestMapping("/ma/{appId}")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * @param appId:       传入的小程序id
     * @param requestBody: 一次性授权码和手机信息等
     * @return
     */
    @PostMapping("/auth/login")
    public ResponseResult login(@PathVariable String appId, @Valid @RequestBody MaAuthVM requestBody, HttpServletResponse response) {
        authService.auth(appId, requestBody,response);
        return ResponseResult.success();
    }

}
