package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoOutVM {

    @JsonProperty("user_id")
    private String userId;

    private String mobile;

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("user_name")
    private String userName;

    private Integer sex;

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

    @JsonProperty("created_time")
    private LocalDateTime createdTime;
}
