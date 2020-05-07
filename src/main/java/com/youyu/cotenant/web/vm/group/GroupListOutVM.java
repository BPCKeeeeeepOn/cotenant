package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class GroupListOutVM {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String province;

    private String city;

    private String district;

    private String address;

    private String title;

    @JsonProperty("leader_id")
    private String leaderId;

    private String leader;

    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

    private Integer status;

    @JsonProperty("chamber_img_url")
    private String chamberImgUrl;

    @JsonProperty("created_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime createdTime;

}
