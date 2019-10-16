package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CotenantUserInfo;
import lombok.Data;

import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class UserInfoInVM {

    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_head")
    private String userHead;

    private Integer sex;

    private String college;

    private String degree;

    @JsonProperty("start_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime endTime;

    @JsonProperty("id_card_url")
    private String idCardUrl;

    @JsonProperty("diploma_url")
    private String diplomaUrl;

    private String interest;

    public CotenantUserInfo buildCotenantUser() {
        CotenantUserInfo cotenantUserInfo = new CotenantUserInfo();
        cotenantUserInfo.setNickName(nickName);
        cotenantUserInfo.setUserName(userName);
        cotenantUserInfo.setUserHead(userHead);
        cotenantUserInfo.setSex(sex);
        cotenantUserInfo.setCollege(college);
        cotenantUserInfo.setDegree(degree);
        cotenantUserInfo.setStartTime(startTime);
        cotenantUserInfo.setEndTime(endTime);
        cotenantUserInfo.setIdCardUrl(idCardUrl);
        cotenantUserInfo.setDiplomaUrl(diplomaUrl);
        cotenantUserInfo.setInterest(interest);
        return cotenantUserInfo;
    }

}
