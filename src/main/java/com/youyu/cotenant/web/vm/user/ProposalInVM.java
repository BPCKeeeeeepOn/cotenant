package com.youyu.cotenant.web.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProposalInVM {

    @JsonProperty("proposal_type")
    private int proposalType;

    private String content;
}
