package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.entity.CotenantUserCollege;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class UserCollegeInVM {

    private String id;

    @NotBlank(message = "college_id不能为空")
    @JsonProperty("college_id")
    private String collegeId;

    @NotBlank(message = "学校名称不能为空")
    @JsonProperty("college_name")
    private String collegeName;

    private String coordinate;

    @JsonProperty("start_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime endTime;

    @JsonProperty("is_default")
    private boolean isDefault;

    public CotenantUserCollege buildCotenantUserCollege() {
        CotenantUserCollege cotenantUserCollege = new CotenantUserCollege();
        cotenantUserCollege.setCotenantCollegeId(Long.valueOf(collegeId));
        cotenantUserCollege.setCoordinate(coordinate);
        cotenantUserCollege.setStartTime(startTime);
        cotenantUserCollege.setEndTime(endTime);
        cotenantUserCollege.setIsDefault(isDefault);
        return cotenantUserCollege;
    }

}
