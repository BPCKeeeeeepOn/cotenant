package com.youyu.cotenant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.*;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantReportedProposalMapper;
import com.youyu.cotenant.repository.CotenantUserCollegeMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.repository.CotenantUserMapper;
import com.youyu.cotenant.repository.biz.CotenantUserBizMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.vm.user.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.UNREAD_GROUP_KEY;
import static com.youyu.cotenant.common.CotenantConstants.UNREAD_MSG_COUNT;
import static com.youyu.cotenant.common.CotenantConstants.USER_STATUS.NOT_LOGIN;

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
    private CotenantUserCollegeMapper cotenantUserCollegeMapper;

    @Autowired
    private CotenantUserBizMapper cotenantUserBizMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SmsService smsService;

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
        if (selectUserByUserName(mobile) != null) {
            throw new BizException(ResponseResult.fail(ResultCode.USER_EXISZTS));
        }
        if (!smsService.verifyCode(mobile, code)) {
            throw new BizException(ResponseResult.fail(ResultCode.SMS_CODE_ERROR));
        }
        CotenantUser cotenantUser = userRegisterInVM.buildCotenantUser();
        cotenantUser.setId(GeneratorID.getId());
        cotenantUser.setPassword(passwordEncoder.encode(password));
        return cotenantUserMapper.insertSelective(cotenantUser);
    }


    public void insertUser(CotenantUser cotenantUser) {
        cotenantUser.setId(GeneratorID.getId());
        cotenantUser.setEnabled(true);
        cotenantUserMapper.insertSelective(cotenantUser);
    }

    /**
     * 重置密码
     *
     * @param userPasswordInVM
     */
    public void resetPassword(UserPasswordInVM userPasswordInVM) {
        String mobile = userPasswordInVM.getMobile();
        String code = userPasswordInVM.getCode();
        String password = userPasswordInVM.getPassword();
        CotenantUser cotenantUser = selectUserByUserName(mobile);
        if (cotenantUser == null) {
            throw new BizException(ResponseResult.fail(ResultCode.NOT_USER));
        }

        if (!smsService.verifyCode(mobile, code)) {
            throw new BizException(ResponseResult.fail(ResultCode.SMS_CODE_ERROR));
        }
        cotenantUser.setPassword(passwordEncoder.encode(password));
        cotenantUserMapper.updateByPrimaryKeySelective(cotenantUser);

    }

    /**
     * 查询个人信息
     *
     * @return
     */
    public UserOutVM info() {
        UserOutVM userOutVM = new UserOutVM();
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        if (cotenantUser == null) {
            userOutVM.setStatus(NOT_LOGIN);
            return userOutVM;
        }
        String mobile = cotenantUser.getMobile();
        Long id = cotenantUser.getId();
        String unreadCount = redisUtils.getCache(UNREAD_GROUP_KEY + id);
        String unreadMsgCount = redisUtils.getCache(UNREAD_MSG_COUNT + id);
        userOutVM.setId(String.valueOf(id));
        userOutVM.setMobile(mobile);
        userOutVM.setStatus(selectUserStatus(id));
        userOutVM.setUnreadGroupCount(StringUtils.isBlank(unreadCount) ? NumberUtils.INTEGER_ZERO : Integer.valueOf(unreadCount));
        userOutVM.setUnreadMsgCount(StringUtils.isBlank(unreadMsgCount) ? NumberUtils.INTEGER_ZERO : Integer.valueOf(unreadMsgCount));
        return userOutVM;
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
        CotenantUserInfo cotenantUserInfo = cotenantUserBizMapper.selectUserDetail(userId);
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
     * 编辑/新增个人信息
     *
     * @param userInfoInVM
     * @return
     */
    @Transactional
    public void update(UserInfoInVM userInfoInVM) {
        //查询出用户信息
        //需要修改大学信息
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        if (cotenantUserInfo == null) {
            cotenantUserInfo = userInfoInVM.buildCotenantUser();
            cotenantUserInfo.setUserId(userId);
            cotenantUserInfo.setStatus(CotenantConstants.USER_STATUS.DEFAULT_STATUS);
            cotenantUserInfoMapper.insertSelective(cotenantUserInfo);
            //首次保存学校信息
            CotenantUserCollege cotenantUserCollege = userInfoInVM.buildCotenantUserCollege();
            cotenantUserCollege.setCotenantUserId(userId);
            cotenantUserCollegeMapper.insertSelective(cotenantUserCollege);
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
     * 获取用户大学信息
     *
     * @return
     */
    public List<UserCollegeOutVM> getColleges() {
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        List<UserCollegeOutVM> userCollegeVMList;
        userCollegeVMList = cotenantUserBizMapper.selectUserColleges(userId);
        return userCollegeVMList;
    }

    /**
     * 保存大学信息
     *
     * @param userCollegeVM
     */
    @Transactional
    public void saveCollege(UserCollegeInVM userCollegeVM) {
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        boolean isDefault = userCollegeVM.isDefault();
        //判断该学校是否存在
        CotenantUserCollegeExample cotenantUserCollegeExample = new CotenantUserCollegeExample();
        cotenantUserCollegeExample.createCriteria().andCotenantUserIdEqualTo(userId);
        List<CotenantUserCollege> cotenantUserCollegeList = cotenantUserCollegeMapper.selectByExample(cotenantUserCollegeExample);
        if (CollectionUtils.isEmpty(cotenantUserCollegeList)) {
            isDefault = true;
        } else {
            if (isDefault) {
                //更新之前的学校默认状态成false
                cotenantUserCollegeList.stream().forEach(item -> {
                    item.setIsDefault(false);
                    cotenantUserCollegeMapper.updateByPrimaryKey(item);
                });
            }
        }
        CotenantUserCollege cotenantUserCollege = userCollegeVM.buildCotenantUserCollege();

        Long id = GeneratorID.getId();
        cotenantUserCollege.setId(id);
        cotenantUserCollege.setCotenantUserId(userId);
        cotenantUserCollege.setIsDefault(isDefault);
        cotenantUserCollegeMapper.insertSelective(cotenantUserCollege);
    }

    /**
     * 更新大学信息
     *
     * @param userCollegeVM
     */
    public void updateCollege(UserCollegeInVM userCollegeVM) {
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        Long id = Long.valueOf(userCollegeVM.getId());
        boolean isDefault = userCollegeVM.isDefault();
        CotenantUserCollegeExample cotenantUserCollegeExample = new CotenantUserCollegeExample();
        cotenantUserCollegeExample.createCriteria().andCotenantUserIdEqualTo(userId);
        List<CotenantUserCollege> cotenantUserCollegeList = cotenantUserCollegeMapper.selectByExample(cotenantUserCollegeExample);
        if (cotenantUserCollegeList.size() < 2) {
            if (!isDefault) {
                throw new BizException(ResponseResult.fail(ResultCode.USER_COLLEGE_NOT_IS_DEFAULT));
            }
        } else {
            if (cotenantUserCollegeMapper.selectByPrimaryKey(id).getIsDefault() && !isDefault) {
                throw new BizException(ResponseResult.fail(ResultCode.USER_COLLEGE_MUST_IS_DEFAULT));
            }
        }

        if (isDefault) {
            //更新之前的学校默认状态成false
            cotenantUserCollegeList.stream().forEach(item -> {
                item.setIsDefault(false);
                cotenantUserCollegeMapper.updateByPrimaryKey(item);
            });
        }

        CotenantUserCollege cotenantUserCollege = userCollegeVM.buildCotenantUserCollege();
        cotenantUserCollege.setId(id);
        cotenantUserCollegeMapper.updateByPrimaryKeySelective(cotenantUserCollege);
    }

    /**
     * 删除用户大学
     *
     * @param id
     */
    public void deleteCollege(Long id) {
        CotenantUser cotenantUser = currentUserUtils.getCurrUser();
        Long userId = cotenantUser.getId();
        CotenantUserCollege cotenantUserCollege = cotenantUserCollegeMapper.selectByPrimaryKey(id);
        CotenantUserCollegeExample cotenantUserCollegeExample = new CotenantUserCollegeExample();
        cotenantUserCollegeExample.createCriteria().andCotenantUserIdEqualTo(userId);
        List<CotenantUserCollege> cotenantUserCollegeList = cotenantUserCollegeMapper.selectByExample(cotenantUserCollegeExample);
        if (cotenantUserCollegeList.size() < 2) {
            throw new BizException(ResponseResult.fail(ResultCode.USER_COLLEGE_MUST_ONE));
        }
        boolean isDefault = cotenantUserCollege.getIsDefault();
        if (isDefault) {
            cotenantUserCollegeExample = new CotenantUserCollegeExample();
            cotenantUserCollegeExample.createCriteria().andCotenantUserIdEqualTo(userId).andIdNotEqualTo(id);
            CotenantUserCollege updateCotenantUserCollege = cotenantUserCollegeMapper.selectByExample(cotenantUserCollegeExample).get(0);
            updateCotenantUserCollege.setIsDefault(true);
            cotenantUserCollegeMapper.updateByPrimaryKey(updateCotenantUserCollege);
        }

        cotenantUserCollegeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询个人用户状态
     *
     * @param userId
     * @return
     */
    public Integer selectUserStatus(Long userId) {
        if (userId == null) {
            return NOT_LOGIN;
        }
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        return cotenantUserInfo == null ? CotenantConstants.USER_STATUS.NOT_USER_STATUS : cotenantUserInfo.getStatus();
    }

    /**
     * 收集用户建议
     *
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
