package com.youyu.cotenant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantReportedProposal;
import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CotenantUserExample;
import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantReportedProposalMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.repository.CotenantUserMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.rest.vm.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.CODE_CACHE;
import static com.youyu.cotenant.common.CotenantConstants.UNREAD_MESSAGE_KEY;
import static com.youyu.cotenant.common.CotenantConstants.UNREAD_MSG_COUNT;

@Service
@Slf4j
public class UserService {

    @Autowired
    CotenantUserMapper cotenantUserMapper;

    @Autowired
    CotenantUserInfoMapper cotenantUserInfoMapper;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private CotenantReportedProposalMapper cotenantReportedProposalMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtils redisUtils;

    public CotenantUser selectUserByUserName(String mobile) {
        CotenantUserExample cue = new CotenantUserExample();
        cue.createCriteria().andMobileEqualTo(mobile).andEnabledEqualTo(true);
        List<CotenantUser> userList = cotenantUserMapper.selectByExample(cue);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(NumberUtils.INTEGER_ZERO);
    }

    /**
     * 用户注册
     *
     * @param userRegisterInVM
     * @return
     */
    public int register(UserRegisterInVM userRegisterInVM) {
        String mobile = userRegisterInVM.getMobile();
        String code = userRegisterInVM.getCode();
        String password = userRegisterInVM.getPassword();
        String verificationCode = redisUtils.getCache(CODE_CACHE + mobile);
        if (selectUserByUserName(mobile) != null) {
            throw new BizException(ResponseResult.fail(ResultCode.USER_EXISZTS));
        }
        if (!StringUtils.equalsIgnoreCase(verificationCode, code)) {
            throw new BizException(ResponseResult.fail(ResultCode.SMS_CODE_ERROR));
        }
        CotenantUser cotenantUser = userRegisterInVM.buildCotenantUser();
        cotenantUser.setId(GeneratorID.getId());
        cotenantUser.setPassword(passwordEncoder.encode(password));
        return cotenantUserMapper.insertSelective(cotenantUser);
    }

    /**
     * 查询用户详情
     *
     * @return
     */
    public UserInfoOutVM detail() {
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        String mobile = cotenantUser.getMobile();
        UserInfoOutVM userInfoOutVM = new UserInfoOutVM();
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        if (cotenantUserInfo == null) {
            //返回提示该用户补全信息
            throw new BizException(ResponseResult.fail(ResultCode.USER_INFO_ERROR));
        }
        try {
            String userStr = objectMapper.writeValueAsString(cotenantUserInfo);
            userInfoOutVM = objectMapper.readValue(userStr, UserInfoOutVM.class);
            userInfoOutVM.setMobile(mobile);
        } catch (Exception e) {
            log.error("detail user info failed:", e.getMessage(), e);
        }
        return userInfoOutVM;
    }

    /**
     * 查询个人信息
     *
     * @return
     */
    public UserOutVM info() {
        UserOutVM userOutVM = new UserOutVM();
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        String mobile = cotenantUser.getMobile();
        Long id = cotenantUser.getId();
        String unreadCount = redisUtils.getCache(UNREAD_MESSAGE_KEY + id);
        String unreadMsgCount = redisUtils.getCache(UNREAD_MSG_COUNT + id);
        userOutVM.setId(String.valueOf(id));
        userOutVM.setMobile(mobile);
        userOutVM.setStatus(selectUserStatus(id));
        userOutVM.setUnreadGroupCount(StringUtils.isBlank(unreadCount) ? NumberUtils.INTEGER_ZERO : Integer.valueOf(unreadCount));
        userOutVM.setUnreadMsgCount(StringUtils.isBlank(unreadMsgCount) ? NumberUtils.INTEGER_ZERO : Integer.valueOf(unreadMsgCount));
        return userOutVM;
    }

    /**
     * 编辑/新增个人信息
     *
     * @param userInfoInVM
     * @return
     */
    public void update(UserInfoInVM userInfoInVM) {
        //查询出用户信息
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        if (cotenantUserInfo == null) {
            cotenantUserInfo = userInfoInVM.buildCotenantUser();
            cotenantUserInfo.setUserId(userId);
            cotenantUserInfo.setStatus(CotenantConstants.USER_STATUS.DEFAULT_STATUS);
            cotenantUserInfoMapper.insertSelective(cotenantUserInfo);
        } else {
            CotenantUserInfo ins = userInfoInVM.buildCotenantUser();
            Integer status = cotenantUserInfo.getStatus();
            ins.setCreatedTime(cotenantUserInfo.getCreatedTime());
            ins.setUserId(userId);
            ins.setStatus(cotenantUserInfo.getStatus());
            if (CotenantConstants.USER_STATUS.CANCEL_STATUS == status) {
                ins.setStatus(CotenantConstants.USER_STATUS.DEFAULT_STATUS);
            }
            cotenantUserInfoMapper.updateByPrimaryKeySelective(ins);
        }
    }

    /**
     * 查询个人用户状态
     *
     * @param userId
     * @return
     */
    public Integer selectUserStatus(Long userId) {
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        return cotenantUserInfo == null ? CotenantConstants.USER_STATUS.NOT_USER_STATUS : cotenantUserInfo.getStatus();
    }

    /**
     * 收集用户建议
     * @param proposalInVM
     */
    public void reportedProposal(ProposalInVM proposalInVM) {
        CotenantReportedProposal cotenantReportedProposal = new CotenantReportedProposal();
        cotenantReportedProposal.setContent(proposalInVM.getContent());
        cotenantReportedProposal.setCotenantUserId(currentUserUtils.getCurrUserId());
        cotenantReportedProposal.setProposeType(0);
        cotenantReportedProposal.setId(GeneratorID.getId());
        cotenantReportedProposalMapper.insertSelective(cotenantReportedProposal);
    }
}
