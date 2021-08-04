package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.DATE_TIME;
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

    @JsonProperty("house_price")
    private BigDecimal housePrice;

    @JsonProperty("house_type")
    private String houseType;

    @JsonProperty("cotenant_description")
    private String cotenantDescription;

    private Integer status;

    @JsonProperty("chamber_img_url")
    private String chamberImgUrl;

    @JsonProperty("chamber_description")
    private String chamberDescription;

    @JsonProperty("created_time")
    @JsonFormat(pattern = DATE_TIME)
    private LocalDateTime createdTime;

}
