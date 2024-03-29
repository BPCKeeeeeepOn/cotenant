package com.youyu.cotenant.web.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoOutVM {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    private String mobile;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("user_head")
    private String userHead;

    @JsonProperty("user_name")
    private String userName;

    private Integer sex;

    private Integer status;

    private String reason;

    private String college;

    private String degree;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("id_card_url")
    private String idCardUrl;

    @JsonProperty("diploma_url")
    private String diplomaUrl;

    private String interest;

    @JsonProperty("user_type")
    private Integer userType;

    @JsonProperty("id_number")
    private String idNumber;

    @JsonProperty("created_time")
    private LocalDateTime createdTime;
}
