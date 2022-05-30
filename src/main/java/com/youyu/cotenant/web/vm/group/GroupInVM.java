package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.youyu.cotenant.entity.CotenantGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.math.BigDecimal;

import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.DEFAULT_STATUS;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupInVM {

    private String province;

    private String city;

    private String district;

    private String addressName;

    private String addressDetail;

    private String addressLatitude;

    private String addressLongitude;

    @Size(max = 20, message = "标题不能超过20个字符")
    @NotBlank(message = "标题不能为空")
    private String title;

    private Integer cotenantCount;

    private Integer cotenantType;

    private String houseType;

    private String toilteType;

    private String kitchenType;

    private BigDecimal housePrice;

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

    private String mobile;

    public CotenantGroup buildCotenantGroup() {
        CotenantGroup cotenantGroup = new CotenantGroup();
        cotenantGroup.setProvince(province);
        cotenantGroup.setCity(city);
        cotenantGroup.setDistrict(district);
        cotenantGroup.setAddressName(addressName);
        cotenantGroup.setAddressDetail(addressDetail);
        cotenantGroup.setAddressLatitude(addressLatitude);
        cotenantGroup.setAddressLongitude(addressLongitude);
        cotenantGroup.setTitle(title);
        cotenantGroup.setCotenantCount(cotenantCount);
        cotenantGroup.setCotenantType(cotenantType);
        cotenantGroup.setHouseType(houseType);
        cotenantGroup.setToilteType(toilteType);
        cotenantGroup.setKitchenType(kitchenType);
        cotenantGroup.setHousePrice(housePrice);
        cotenantGroup.setStatus(DEFAULT_STATUS);
        cotenantGroup.setCotenantDescription(cotenantDescription);
        cotenantGroup.setChamberImgUrl(chamberImgUrl);
        cotenantGroup.setChamberDescription(chamberDescription);
        cotenantGroup.setChamberVideoUrl(chamberVideoUrl);
        return cotenantGroup;
    }

}
