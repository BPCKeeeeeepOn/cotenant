package com.youyu.cotenant.web.vm.chat;


import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

@Data
public class CommunicationOutVM {


    private String channel;

    private String nick;

    private String head;

    @JsonUnwrapped
    private List<ChatMessageListOutVM> chatMessageListOutVMList;
}
