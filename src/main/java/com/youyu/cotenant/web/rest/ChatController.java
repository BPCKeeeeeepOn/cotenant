package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.ChatService;
import com.youyu.cotenant.web.vm.chat.ChatMessageInVM;
import com.youyu.cotenant.web.vm.chat.CommunicationInVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/buildCommunication")
    public ResponseResult buildCommunication(@RequestBody @Valid CommunicationInVM communicationInVM) {
        chatService.buildCommunication(communicationInVM);
        return ResponseResult.success();
    }

    @PostMapping("/send")
    public ResponseResult send(@RequestBody @Valid ChatMessageInVM chatMessageInVM) {
        chatService.send(chatMessageInVM);
        return ResponseResult.success();
    }

    @GetMapping("/getMessage")
    public ResponseResult getMessage(@RequestParam("receive_user_id") Long receiveUserId) {
        return ResponseResult.success().body(chatService.getMessage(receiveUserId));
    }

    @GetMapping("/list")
    public ResponseResult list() {
        return ResponseResult.success().body(chatService.list());
    }

    @GetMapping("/clean/cache")
    public ResponseResult cleanCache() {
        chatService.cleanCache();
        return ResponseResult.success();
    }

}
