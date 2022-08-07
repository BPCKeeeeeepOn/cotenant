package com.youyu.cotenant.web.vm.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class ChatListOutVM {

    @JsonProperty("id")
    private String id;

    @JsonProperty("send_user_id")
    private String sendUserId;

    @JsonProperty("receive_user_id")
    private String receiveUserId;

    @JsonProperty("send_user_nick_name")
    private String sendUserNickname;

    @JsonProperty("receive_user_nick_name")
    private String receiveUserNickname;

    @JsonProperty("send_user_name")
    private String sendUserName;

    @JsonProperty("receive_user_name")
    private String receiveUserName;

    @JsonProperty("receive_user_head")
    private String receiveUserHead;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime createdTime;
}
