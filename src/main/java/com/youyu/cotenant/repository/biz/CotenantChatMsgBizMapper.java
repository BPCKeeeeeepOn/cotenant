package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.vm.chat.ChatMessageListOutVM;
import com.youyu.cotenant.web.vm.chat.ChatMessageVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantChatMsgBizMapper {

    List<ChatMessageVM> selectCotenantChatMsgList(@Param("channel")String channel);


    List<ChatMessageListOutVM> selectCommunicationListByUserId(@Param("userId") Long userId);


    List<String> selectChatList();

}