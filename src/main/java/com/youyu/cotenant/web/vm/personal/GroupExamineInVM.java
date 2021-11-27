package com.youyu.cotenant.web.vm.personal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GroupExamineInVM {

    @JsonProperty("group_id")
    private Long groupId;

    @JsonProperty("member_id")
    private Long memberId;

    private Integer status;

}
