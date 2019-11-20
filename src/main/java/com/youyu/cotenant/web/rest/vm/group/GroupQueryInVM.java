package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.youyu.cotenant.web.rest.vm.BaseVM;
import lombok.Data;

@Data
public class GroupQueryInVM {

    private String college;

    private Integer sex;

    @JsonProperty("cotenant_type")
    private Integer cotenantType;

    private String province;

    private String city;

    private String district;

    @JsonProperty("cotenant_count")
    private Integer cotenantCount;

    @JsonUnwrapped
    private BaseVM baseVM;
}
