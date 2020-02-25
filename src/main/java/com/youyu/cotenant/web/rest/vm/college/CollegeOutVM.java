package com.youyu.cotenant.web.rest.vm.college;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CollegeOutVM {

    private Long id;

    @JsonProperty("college_name")
    private String collegeName;

}
