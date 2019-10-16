package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CotenantListOutVM {

    private String id;

    @JsonProperty("user_id")
    private String userId;

    private Integer role;

    @JsonProperty("nick_name")
    private String nickName;

    private String college;

    private String interest;

    private Integer sex;

    private Integer status;

}
