package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.common.CotenantConstants;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class ChatMessageListOutVM {

    @JsonProperty("receive_user_id")
    private String receiveUserId;

    @JsonProperty("receive_user_name")
    private String receiveUserName;

    @JsonProperty("receive_user_head")
    private String receiveUserHead;

    @JsonProperty("last_content")
    private String lastContent;

    @JsonProperty("last_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime lastTime;
}
