package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserPasswordInVM {

    @Size(max = 11, message = "手机号格式不正确")
    @NotBlank(message = "手机号不能为空")
    @JsonProperty("mobile")
    private String mobile;

    @Size(max = 6, message = "验证码格式不正确")
    @NotBlank(message = "验证码不能为空")
    @JsonProperty("code")
    private String code;

    @Size(max = 16, message = "密码不能超过16位")
    @NotBlank(message = "密码不能为空")
    @JsonProperty("password")
    private String password;
}
