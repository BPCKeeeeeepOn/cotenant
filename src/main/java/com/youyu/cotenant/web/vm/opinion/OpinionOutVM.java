package com.youyu.cotenant.web.vm.opinion;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class OpinionOutVM {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("nick_name")
    private String nickName;

    private String content;

    @JsonProperty("created_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime createdTime;

}
