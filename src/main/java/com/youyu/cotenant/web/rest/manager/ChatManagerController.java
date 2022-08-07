package com.youyu.cotenant.web.rest.manager;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.ChatService;
import com.youyu.cotenant.service.manager.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_OFFSET;
import static com.youyu.cotenant.common.CotenantConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/m/chat")
public class ChatManagerController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/list")
    public ResponseResult list(@RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_OFFSET) int offset,
                               @RequestParam(required = false, defaultValue = "" + DEFAULT_PAGE_SIZE) int limit,
                               @RequestParam(required = false, name = "sendUserId") Long sendUserId,
                               @RequestParam(required = false, name = "receiveUserId") Long receiveUserId) {
        return ResponseResult.success().body(chatService.chatList(offset, limit, sendUserId, receiveUserId));
    }

}
