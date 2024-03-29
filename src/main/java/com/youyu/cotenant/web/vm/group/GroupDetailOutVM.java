package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupDetailOutVM {

    private String id;

    private String province;

    private String city;

    private String district;

    @JsonProperty("address_name")
    private String addressName;

    @JsonProperty("address_detail")
    private String addressDetail;

    @JsonProperty("address_latitude")
    private String addressLatitude;

    @JsonProperty("address_longitude")
    private String addressLongitude;

    private String title;

    private String leader;

    @JsonProperty("leader_id")
    private String leaderId;

    private Integer sex;

    private String college;

    @JsonProperty("end_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime endTime;

    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

    private String houseType;

    private String toilteType;

    private String kitchenType;

    private BigDecimal housePrice;

    private Integer status;

    @JsonProperty("cotenant_description")
    private String cotenantDescription;

    @JsonProperty("chamber_img_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberImgUrl;

    @JsonProperty("chamber_description")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberDescription;

    @JsonProperty("chamber_video_url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberVideoUrl;

    @JsonProperty("created_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime createdTime;

    @JsonProperty("cotenant_list")
    @JsonUnwrapped
    private List<CotenantListOutVM> cotenantList;

}
