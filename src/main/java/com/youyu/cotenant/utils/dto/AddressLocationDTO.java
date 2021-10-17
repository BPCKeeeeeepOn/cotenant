package com.youyu.cotenant.utils.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class AddressLocationDTO {

    private String formattedAddress;

    private String country;

    private String province;

    private String city;

    private String district;

    private String location;

    private String addressLatitude;

    private String addressLongitude;

    public String getAddressLongitude() {
        if (StringUtils.isNotBlank(location)) {
            return location.split(",")[0];
        }
        return addressLongitude;
    }

    public String getAddressLatitude() {
        if (StringUtils.isNotBlank(location)) {
            return location.split(",")[1];
        }
        return addressLatitude;
    }
}
