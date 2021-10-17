package com.youyu.cotenant.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.config.WxMaConfiguration;
import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.jwt.JWTUtil;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.vm.MaAuthVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.youyu.cotenant.common.ResultCode.DEFAULT_ERROR;

/**
 * @author bpc
 */
@Service
@Slf4j
public class AuthService {

    @Autowired
    UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    public void auth(String appId, MaAuthVM requestBody, HttpServletResponse response) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appId);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(requestBody.getCode());

            //获取到小程序授权获得的手机号
            WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(session.getSessionKey(), requestBody.getEncryptedData(), requestBody.getIv());
            log.info("for {} get wechat phoneNumberInfo is {}", requestBody, phoneNoInfo);
            String phoneNumber = phoneNoInfo.getPhoneNumber();
            if (StringUtils.isBlank(phoneNumber)) {
                throw new BizException(ResponseResult.fail(DEFAULT_ERROR));
            }
            CotenantUser user = userService.selectUserByUserName(phoneNumber);
            if (user == null) {
                //首次登录需要新增一条用户信息
                user = new CotenantUser();
                user.setMobile(phoneNumber);
                userService.insertUser(user);
            }

            String token = JWTUtil.generateToken(phoneNumber, null, null);
            //将 JWT写入body
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Access-Control-Expose-Headers", "Authentication-Info");
            response.setHeader("Authentication-Info", token);

            //保存在缓存中，用于后续刷新token
            redisUtils.putCache(phoneNumber, token);

            PrintWriter printWriter = response.getWriter();
            printWriter.write(new ObjectMapper().writeValueAsString(ResponseResult.success()));
            printWriter.flush();
            printWriter.close();

        } catch (Exception e) {
            log.error("获取小程序session失败, 原因: {}", e.getMessage(), e);
            throw new BizException(ResponseResult.fail(DEFAULT_ERROR));
        }
    }

}