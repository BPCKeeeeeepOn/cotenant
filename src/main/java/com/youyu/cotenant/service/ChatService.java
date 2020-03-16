package com.youyu.cotenant.service;

import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantChatMsg;
import com.youyu.cotenant.entity.CotenantCommunication;
import com.youyu.cotenant.entity.CotenantCommunicationExample;
import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantChatMsgMapper;
import com.youyu.cotenant.repository.CotenantCommunicationMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.repository.biz.CotenantChatMsgBizMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.rest.vm.chat.*;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.UNREAD_MSG_COUNT;

@Service
@Slf4j
public class ChatService {


    @Autowired
    private GoEasy goEasy;

    @Autowired
    private CotenantCommunicationMapper cotenantCommunicationMapper;

    @Autowired
    private CotenantChatMsgMapper cotenantChatMsgMapper;

    @Autowired
    private CotenantUserInfoMapper cotenantUserInfoMapper;

    @Autowired
    private CotenantChatMsgBizMapper cotenantChatMsgBizMapper;

    @Autowired
    private RedisUtils mRedisUtils;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private SystemService systemService;

    /**
     * 建立唯一通讯
     *
     * @param communicationInVM
     */
    public void buildCommunication(CommunicationInVM communicationInVM) {
        //如通道
        Long sendUserId = currentUserUtils.getCurrUserId();
        Long receiveUserId = communicationInVM.getReceiveUserId();
        //生成channel
        String channel = generateChannel(sendUserId, receiveUserId);
        //判断是否存在唯一通信id，如存在不插入，直接返回。如存在插入数据库
        CotenantCommunicationExample example = new CotenantCommunicationExample();
        example.createCriteria().andChannelEqualTo(channel);
        long count = cotenantCommunicationMapper.countByExample(example);
        if (count > 0) {
            return;
        }
        CotenantCommunication cotenantCommunication = new CotenantCommunication();
        cotenantCommunication.setChannel(channel);
        cotenantCommunication.setId(GeneratorID.getId());
        cotenantCommunication.setSendUserId(sendUserId);
        cotenantCommunication.setReceiveUserId(receiveUserId);
        cotenantCommunicationMapper.insertSelective(cotenantCommunication);
    }

    /**
     * 获取聊天记录
     *
     * @param receiveUserId 接收人id
     */
    public ChatMessageOutVM getMessage(Long receiveUserId) {
        Long currUserId = currentUserUtils.getCurrUserId();
        CotenantUserInfo currUser = cotenantUserInfoMapper.selectByPrimaryKey(currUserId);
        CotenantUserInfo receiveUser = cotenantUserInfoMapper.selectByPrimaryKey(receiveUserId);
        String channel = generateChannel(currUserId, receiveUserId);
        String key = CotenantConstants.CHAT_RECEIVE_KEY + channel;
        ChatMessageOutVM chatMessageOutVM = new ChatMessageOutVM();
        List<ChatMessageVM> result = (List<ChatMessageVM>) (Object) mRedisUtils.lGetObjectAll(key);
        if (result.size() == 0) {
            result = cotenantChatMsgBizMapper.selectCotenantChatMsgList(channel);
            if (result.size() == 0) {
                return null;
            }
            mRedisUtils.lRightPushAllObject(key, (List<Object>) (Object) result);
        }
        chatMessageOutVM.setChatMessageVMList(result);
        chatMessageOutVM.setSendUserId(String.valueOf(currUserId));
        chatMessageOutVM.setSendUserName(currUser.getUserName());
        chatMessageOutVM.setSendUserHead(currUser.getUserHead());
        chatMessageOutVM.setReceiveUserId(String.valueOf(receiveUserId));
        chatMessageOutVM.setReceiveUserName(receiveUser.getUserName());
        chatMessageOutVM.setReceiveUserHead(receiveUser.getUserHead());
        return chatMessageOutVM;
    }

    /**
     * 获取聊天列表
     */
    public List<ChatMessageListOutVM> list() {
        Long userId = currentUserUtils.getCurrUserId();
        List<ChatMessageListOutVM> chatMessageListOutVMList;
        chatMessageListOutVMList = cotenantChatMsgBizMapper.selectCommunicationListByUserId(userId);
        return chatMessageListOutVMList;
    }

    /**
     * 发送消息存储持久层，redis中
     *
     * @param chatMessageInVM 发送消息bean
     */
    public void send(ChatMessageInVM chatMessageInVM) {
        Long sendUserId = currentUserUtils.getCurrUserId();
        Long receiveUserId = chatMessageInVM.getReceiveUserId();
        String content = chatMessageInVM.getContent();
        String channel = generateChannel(sendUserId, receiveUserId);
        CotenantChatMsg cotenantChatMsg = new CotenantChatMsg();
        cotenantChatMsg.setChannel(channel);
        cotenantChatMsg.setSendUserId(Long.valueOf(sendUserId));
        cotenantChatMsg.setReceiveUserId(Long.valueOf(receiveUserId));
        cotenantChatMsg.setContent(content);
        cotenantChatMsg.setId(GeneratorID.getId());
        //保存消息
        cotenantChatMsgMapper.insertSelective(cotenantChatMsg);
        //发送消息
        sendSocket(String.valueOf(sendUserId), String.valueOf(receiveUserId), channel, cotenantChatMsg, content);
    }

    /**
     * 向指定channel推送消息
     *
     * @param receiveUserId 接收方的userid
     * @param content
     */
    public void sendSocket(String sendUserId, String receiveUserId, String channel, CotenantChatMsg cotenantChatMsg, String content) {
        goEasy.publish(receiveUserId, content, new PublishListener() {
            @Override
            public void onSuccess() {
                //消息发送成功处理
                log.info("send success!");
                cotenantChatMsg.setSendStatus(NumberUtils.INTEGER_ONE);
                cotenantChatMsg.setReceiveStatus(NumberUtils.INTEGER_ONE);
                cotenantChatMsgMapper.updateByPrimaryKeySelective(cotenantChatMsg);

                //放入缓存
                ChatMessageVM chatMessageVM = new ChatMessageVM();
                chatMessageVM.setSendUserId(sendUserId);
                chatMessageVM.setReceiveUserId(receiveUserId);
                chatMessageVM.setContent(content);
                chatMessageVM.setSendTime(LocalDateTime.now());
                mRedisUtils.lRightPush(CotenantConstants.CHAT_RECEIVE_KEY + channel, chatMessageVM);
                //写入未读消息数缓存
                int unreadCount = org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
                String unreadCountStr = mRedisUtils.getCache(UNREAD_MSG_COUNT + receiveUserId);
                if (StringUtils.isNotBlank(unreadCountStr)) {
                    unreadCount = Integer.valueOf(unreadCountStr);
                    unreadCount++;
                }
                mRedisUtils.putCache(UNREAD_MSG_COUNT + receiveUserId, String.valueOf(unreadCount));
            }

            @Override
            public void onFailed(GoEasyError error) {
                //消息发送异常处理
                log.warn("send message failed,code:{},content:{}, channel:{}", error.getCode(), error.getContent(), receiveUserId);
                throw new BizException(ResponseResult.fail(ResultCode.SEND_MSG_ERROR));
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

    /**
     * 清理所有聊天信息的缓存
     */
    public void cleanCache() {
        //查询出所有聊天信息
        List<String> channelList = cotenantChatMsgBizMapper.selectChatList();
        channelList.stream().forEach(channel -> systemService.delCache("chat_receive_key_" + channel));
    }

}