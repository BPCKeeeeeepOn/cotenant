package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserOutVM {

    private String id;

    private String mobile;

    private Integer status;

    @JsonProperty("unread_group_count")
    private int unreadGroupCount; //未读消息数量

    @JsonProperty("unread_msg_count")
    private int unreadMsgCount;
}
