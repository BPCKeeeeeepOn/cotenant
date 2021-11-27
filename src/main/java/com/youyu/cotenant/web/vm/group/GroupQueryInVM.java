package com.youyu.cotenant.web.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.youyu.cotenant.web.vm.BaseVM;
import lombok.Data;

import java.math.BigDecimal;

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

    private BigDecimal startPrice;

    private BigDecimal endPrice;

    private int timeSort;

    private int priceSort;

    private String houseType;

    private String toilteType;

    private String kitchenType;

    @JsonUnwrapped
    private BaseVM baseVM;
}
