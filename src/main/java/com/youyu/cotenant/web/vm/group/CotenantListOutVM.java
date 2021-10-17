package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.DATE_TIME;
import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class CotenantListOutVM {

    private String id;

    @JsonProperty("user_id")
    private String userId;

    private Integer role;

    @JsonProperty("nick_name")
    private String nickName;

    private String college;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy")
    private LocalDateTime endTime;

    private String interest;

    private Integer sex;

    private Integer status;

}
