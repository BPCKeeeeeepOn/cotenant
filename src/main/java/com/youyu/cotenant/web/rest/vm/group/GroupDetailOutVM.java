package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupDetailOutVM {

    private String id;

    private String province;

    private String city;

    private String district;

    private String title;

    private String leader;

    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

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
    private LocalDateTime createdTime;

    @JsonProperty("cotenant_list")
    @JsonUnwrapped
    private List<CotenantListOutVM> cotenantList;

}
