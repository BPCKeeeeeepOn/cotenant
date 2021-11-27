package com.youyu.cotenant.web.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommunicationInVM {

    @JsonProperty("receive_user_id")
    private Long receiveUserId;
}
