package com.youyu.cotenant.web.vm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author wyq
 * @date 2020-11-16
 * reference: https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html
 */
@Data
public class MaMobileInVM {
    @NotBlank(message = "必需提供iv")
    private String iv;
    
    @NotBlank(message = "必需提供加密数据")
    private String encryptedData;
}
