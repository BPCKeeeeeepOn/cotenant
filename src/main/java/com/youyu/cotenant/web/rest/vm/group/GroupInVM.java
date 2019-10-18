package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.entity.CotenantGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.DEFAULT_STATUS;

@Data
public class GroupInVM {

    private String province;

    private String city;

    private String district;

    @Size(max = 20, message = "标题不能超过20个字符")
    @NotBlank(message = "标题不能为空")
    private String title;


    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

    private Integer status;

    @Size(max = 200, message = "个人描述不能超过200个字符")
    @NotBlank(message = "个人描述不能为空")
    @JsonProperty("cotenant_description")
    private String cotenantDescription;

    @JsonProperty("chamber_img_url")
    private String chamberImgUrl;

    @Size(max = 200, message = "房间不能超过200个字符")
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
