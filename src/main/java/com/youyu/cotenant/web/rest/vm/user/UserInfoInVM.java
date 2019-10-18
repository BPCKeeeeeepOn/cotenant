package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.youyu.cotenant.entity.CotenantUserInfo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
public class UserInfoInVM {


    @JsonProperty("nick_name")
    private String nickName;


    @Size(max = 10, message = "姓名不能超过10个字符")
    @NotBlank(message = "姓名不能为空")
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_head")
    private String userHead;

    private Integer sex;

    @Size(max = 50, message = "大学名称不能超过50个字符")
    @NotBlank(message = "大学名称不能为空")
    private String college;

    private String degree;

    @NotBlank(message = "入学日期不能为空")
    @JsonProperty("start_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime startTime;

    @NotBlank(message = "毕业日期不能为空")
    @JsonProperty("end_time")
    @JsonFormat(pattern = FULL_DATE_TIME)
    private LocalDateTime endTime;

    @JsonProperty("id_card_url")
    private String idCardUrl;

    @JsonProperty("diploma_url")
    private String diplomaUrl;

    @Size(max = 20, message = "兴趣爱好不能超过20个字符")
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
