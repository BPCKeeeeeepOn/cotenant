package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.youyu.cotenant.common.CotenantConstants;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class ChatMessageVM {

    @JsonProperty("send_user_id")
    private String sendUserId;

    @JsonProperty("receive_user_id")
    private String receiveUserId;

    private String content;

    @JsonProperty("send_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime sendTime;
}
