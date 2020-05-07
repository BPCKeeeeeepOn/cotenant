package com.youyu.cotenant.web.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserOutVM {

    private String id;

    private String mobile;

    private Integer status;

    @JsonProperty("unread_group_count")
    private int unreadGroupCount; //租房未读消息数量

    @JsonProperty("unread_msg_count")
    private int unreadMsgCount; //聊天未读消息数
}
