package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.entity.CotenantGroup;
import lombok.Data;

import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.DEFAULT_STATUS;

@Data
public class GroupInVM {

    private String province;

    private String city;

    private String district;

    private String title;

    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

    private Integer status;

    @JsonProperty("cotenant_description")
    private String cotenantDescription;

    @JsonProperty("chamber_img_url")
    private String chamberImgUrl;

    @JsonProperty("chamber_description")
    private String chamberDescription;

    @JsonProperty("chamber_video_url")
    private String chamberVideoUrl;

    public CotenantGroup buildCotenantGroup() {
        CotenantGroup cotenantGroup = new CotenantGroup();
        cotenantGroup.setProvince(province);
        cotenantGroup.setCity(city);
        cotenantGroup.setDistrict(district);
        cotenantGroup.setTitle(title);
        cotenantGroup.setCotenantCount(cotenantCount);
        cotenantGroup.setCotenantType(cotenantType);
        cotenantGroup.setStatus(DEFAULT_STATUS);
        cotenantGroup.setCotenantDescription(cotenantDescription);
        cotenantGroup.setChamberImgUrl(chamberImgUrl);
        cotenantGroup.setChamberDescription(chamberDescription);
        cotenantGroup.setChamberVideoUrl(chamberVideoUrl);
        return cotenantGroup;
    }


}
