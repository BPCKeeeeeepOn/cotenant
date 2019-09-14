package com.youyu.cotenant.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.entity.CotenantCommunication;
import com.youyu.cotenant.entity.CotenantCommunicationExample;
import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.repository.CotenantCommunicationMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.utils.SpringRedisUtils;
import com.youyu.cotenant.web.rest.vm.chat.ChatMessageOutVM;
import com.youyu.cotenant.web.rest.vm.chat.CommunicationInVM;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChatService {


    @Autowired
    private GoEasy goEasy;

    @Autowired
    private CotenantCommunicationMapper cotenantCommunicationMapper;

    @Autowired
    private CotenantUserInfoMapper cotenantUserInfoMapper;

    @Autowired
    private SpringRedisUtils mSpringRedisUtils;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 建立唯一通讯
     *
     * @param communicationInVM
     */
    public ChatMessageOutVM buildCommunication(CommunicationInVM communicationInVM) throws IOException {
        Long sendUserId = communicationInVM.getSendUserId();
        Long receiveUserId = communicationInVM.getReceiveUserId();
        ChatMessageOutVM chatMessageOutVM = new ChatMessageOutVM();

        //生成channel
        String channel = generateChannel(sendUserId, receiveUserId);
        CotenantCommunicationExample cotenantCommunicationExample = new CotenantCommunicationExample();
        cotenantCommunicationExample.createCriteria().andChannelEqualTo(channel);

        if (mSpringRedisUtils.hasKey(channel)) {
            String communication = mSpringRedisUtils.lIndex(channel, NumberUtils.INTEGER_ZERO);
            chatMessageOutVM = objectMapper.readValue(communication, ChatMessageOutVM.class);
        } else {
            long count = cotenantCommunicationMapper.countByExample(cotenantCommunicationExample);
            if (count == NumberUtils.INTEGER_ZERO) {
                CotenantCommunication cotenantCommunication = new CotenantCommunication();
                cotenantCommunication.setId(GeneratorID.getId());
                cotenantCommunication.setChannel(channel);
                cotenantCommunication.setSendUserId(sendUserId);
                cotenantCommunication.setReceiveUserId(receiveUserId);
                cotenantCommunicationMapper.insertSelective(cotenantCommunication);
            }
            //获取用户昵称头像信息
            CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(receiveUserId);
            String nickName = cotenantUserInfo.getNickName();
            chatMessageOutVM.setSendUserId(String.valueOf(sendUserId));
            chatMessageOutVM.setReceiveUserId(String.valueOf(receiveUserId));
            chatMessageOutVM.setReceiveUserName(nickName);
            mSpringRedisUtils.lLeftPush(channel, objectMapper.writeValueAsString(chatMessageOutVM));
        }
        return chatMessageOutVM;
    }

    /**
     * 获取聊天记录
     *
     * @param channel
     */
    public List<ChatMessageOutVM> getMessage(String channel) {
        List<String> msgList;
        List<ChatMessageOutVM> chatMessageOutVMList = new ArrayList<>();
        try {
            //缓存验证
            if (mSpringRedisUtils.hasKey(channel)) {
                //获取聊天记录
                msgList = mSpringRedisUtils.lRange(channel, NumberUtils.INTEGER_ZERO, mSpringRedisUtils.lLen(channel) - 1);
                for (String msg : msgList) {
                    ChatMessageOutVM chatMessageOutVM = objectMapper.readValue(msg, ChatMessageOutVM.class);
                    chatMessageOutVMList.add(chatMessageOutVM);
                }
            }
        } catch (Exception e) {

        }
        return chatMessageOutVMList;
    }


    /**
     * 获取聊天列表
     */
    public void list() {

    }

    /**
     * 向指定channel推送消息
     *
     * @param channel
     * @param content
     */
    public void send(String channel, String content) {
        goEasy.publish(channel, content, new PublishListener() {

            @Override
            public void onSuccess() {
                //消息发送成功处理
                log.info("发送成功！");
            }

            @Override
            public void onFailed(GoEasyError error) {
                //消息发送异常处理
                log.warn("send message failed,code:{},content:{}, channel:{}", error.getCode(), error.getContent(), channel);
            }

        });
    }

    /**
     * 通讯唯一通道生成策略(A-B，B-A唯一)
     *
     * @param sendUserId
     * @param receiveUserId
     */
    public String generateChannel(Long sendUserId, Long receiveUserId) {
        Long total = sendUserId + receiveUserId;
        return DigestUtils.md5Hex(String.valueOf(total));
    }

}
