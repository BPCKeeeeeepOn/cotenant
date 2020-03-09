package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.entity.CotenantCollege;
import com.youyu.cotenant.entity.CotenantUserCollege;
import com.youyu.cotenant.entity.CotenantUserInfo;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.FULL_DATE_TIME;

@Data
@ToString
public class UserInfoInVM {

    @Size(max = 10, message = "昵称不能超过10个字符")
    @NotBlank(message = "昵称不能为空")
    @JsonProperty("nick_name")
    private String nickName;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_head")
    private String userHead;

    private Integer sex;

    @JsonProperty("college_id")
    private Long collegeId;

    private String coordinate;//学校坐标

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

    @Size(max = 20, message = "兴趣爱好不能超过20个字符")
    private String interest;

    public CotenantUserInfo buildCotenantUser() {
        CotenantUserInfo cotenantUserInfo = new CotenantUserInfo();
        cotenantUserInfo.setNickName(nickName);
        cotenantUserInfo.setUserName(userName);
        cotenantUserInfo.setUserHead(userHead);
        cotenantUserInfo.setSex(sex);
        cotenantUserInfo.setDegree(degree);
        cotenantUserInfo.setStartTime(startTime);
        cotenantUserInfo.setEndTime(endTime);
        cotenantUserInfo.setIdCardUrl(idCardUrl);
        cotenantUserInfo.setDiplomaUrl(diplomaUrl);
        cotenantUserInfo.setInterest(interest);
        return cotenantUserInfo;
    }

    public CotenantUserCollege buildCotenantUserCollege() {
        CotenantUserCollege cotenantUserCollege = new CotenantUserCollege();
        cotenantUserCollege.setId(GeneratorID.getId());
        cotenantUserCollege.setStartTime(startTime);
        cotenantUserCollege.setEndTime(endTime);
        cotenantUserCollege.setIsDefault(true);
        cotenantUserCollege.setCotenantCollegeId(collegeId);
        cotenantUserCollege.setCoordinate(coordinate);
        return cotenantUserCollege;
    }
}
