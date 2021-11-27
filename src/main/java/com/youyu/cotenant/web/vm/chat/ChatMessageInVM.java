package com.youyu.cotenant.web.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChatMessageInVM {

    @JsonProperty("receive_user_id")
    private Long receiveUserId;

    @Size(max = 200, message = "发送内容不能超过200个字符")
    @NotBlank(message = "发送内容不能为空")
    private String content;

}
