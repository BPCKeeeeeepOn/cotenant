package com.youyu.cotenant.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.result.SmsMessage;
import com.youyu.cotenant.utils.CommonUtils;
import com.youyu.cotenant.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.youyu.cotenant.common.CotenantConstants.CODE_CACHE;

/**
 * 发送短信验证码
 */
@Service
@Slf4j
public class SmsService {

    final String SIGN_NAME = "fansbox";

    final String TEMPLATE_CODE = "SMS_175125962";

    final String ACCESSKEY_ID = "LTAIv2RRVdS1dJJ9";

    final String ACCESSKEY_SECRET = "sqCaWQKftMa7eAkIjbxKYg4RqOi8XI";

    final String SMS_DOMAIN = "dysmsapi.aliyuncs.com";

    final String SMS_ACTION = "SendSms";

    final String SMS_DEFAULT = "default";

    final String SMS_VERSION = "2017-05-25";

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 发送短信验证码
     *
     * @param phoneNumber
     */
    public void sendSms(String phoneNumber) {
        DefaultProfile profile = DefaultProfile.getProfile(SMS_DEFAULT, ACCESSKEY_ID, ACCESSKEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setVersion(SMS_VERSION);
        request.setDomain(SMS_DOMAIN);
        request.setAction(SMS_ACTION);
        String code = CommonUtils.randomNum();
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
        request.putQueryParameter("SignName", SIGN_NAME);
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String result = response.getData();
            log.info("sendSms result info : {}", result);
            SmsMessage smsMessage = objectMapper.readValue(result, SmsMessage.class);
            if (!"OK".equalsIgnoreCase(smsMessage.getCode())) {
                throw new BizException(ResponseResult.fail(ResultCode.SEND_SMS_CODE_FAILED));
            }
            //如果消息发送成功
            redisUtils.putCache(CODE_CACHE + phoneNumber, code);
        } catch (Exception e) {
            log.error("send sms message failed:", e.getMessage(), e);
            throw new BizException(ResponseResult.fail(ResultCode.SEND_SMS_CODE_FAILED));
        }
    }

    /**
     * 验证短信验证码
     *
     * @param mobile
     * @param code
     * @return
     */
    public boolean verifyCode(String mobile, String code) {
        String value = redisUtils.getCache(CODE_CACHE + mobile);
        if (StringUtils.isNotBlank(value) && StringUtils.equals(value, code)) {
            return true;
        }
        return false;
    }
}
