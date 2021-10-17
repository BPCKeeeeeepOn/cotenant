package com.youyu.cotenant.web.vm;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 小马小程序授权登录
 *
 * @author wyq
 * @date 2020-11-19
 */
@Data
public class MaAuthVM {
    /**
     * 小程序的一次性授权码
     */
    @NotBlank(message = "必需提供授权码")
    private String code;

    @NotBlank(message = "必需提供iv")
    private String iv;

    @NotBlank(message = "必需提供加密数据")
    private String encryptedData;

}
