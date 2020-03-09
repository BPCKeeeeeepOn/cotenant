package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.entity.CotenantUserCollege;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCollegeOutVM {

    private String id;

    @JsonProperty("college_id")
    private String collegeId;

    @JsonProperty("college_name")
    private String collegeName;

    private String province;

    private String city;

    private String coordinate;

    @JsonProperty("start_time")
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    private LocalDateTime endTime;

    @JsonProperty("is_default")
    private boolean isDefault;

    public CotenantUserCollege buildCotenantUserCollege() {
        CotenantUserCollege cotenantUserCollege = new CotenantUserCollege();
        cotenantUserCollege.setCotenantCollegeId(Long.valueOf(collegeId));
        cotenantUserCollege.setStartTime(startTime);
        cotenantUserCollege.setEndTime(endTime);
        cotenantUserCollege.setIsDefault(isDefault);
        return cotenantUserCollege;
    }

}
