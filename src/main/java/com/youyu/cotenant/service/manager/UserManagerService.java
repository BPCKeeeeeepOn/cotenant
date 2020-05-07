package com.youyu.cotenant.service.manager;

import com.github.pagehelper.PageHelper;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.entity.CustomUser;
import com.youyu.cotenant.entity.CustomUserExample;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.repository.CustomUserMapper;
import com.youyu.cotenant.repository.biz.CotenantUserBizMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.web.rest.manager.vm.UserManagerOutVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.USER_STATUS.userStatus;

@Service
@Slf4j
public class UserManagerService {

    @Autowired
    private CustomUserMapper customUserMapper;

    @Autowired
    private CotenantUserBizMapper cotenantUserBizMapper;

    @Autowired
    private CotenantUserInfoMapper cotenantUserInfoMapper;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    public CustomUser selectUserByUserName(String username) {
        CustomUserExample cue = new CustomUserExample();
        cue.createCriteria().andUsernameEqualTo(username).andEnabledEqualTo(true);
        List<CustomUser> userList = customUserMapper.selectByExample(cue);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(NumberUtils.INTEGER_ZERO);
    }

    public UserManagerOutVM info() {
        UserManagerOutVM userManagerOutVM = new UserManagerOutVM();
        CustomUser customUser = currentUserUtils.getManagerCurrUser();
        userManagerOutVM.setRole(1);
        userManagerOutVM.setUsername(customUser.getUsername());
        return userManagerOutVM;
    }

    public List<CotenantUserInfo> list(Integer limit, Integer offset) {
        List<CotenantUserInfo> list =
                PageHelper.offsetPage(offset, limit).doSelectPage(() -> cotenantUserBizMapper.selectAllUser());
        return list;
    }

    /**
     * 审核用户状态
     *
     * @param userId
     * @param status
     */
    public void examine(Long userId, Integer status) {
        CotenantUserInfo cotenantUserInfo = cotenantUserInfoMapper.selectByPrimaryKey(userId);
        if (cotenantUserInfo == null) {
            throw new BizException(ResponseResult.fail(ResultCode.NOT_USER));
        }
        if (!userStatus.contains(status)) {
            throw new BizException(ResponseResult.fail(ResultCode.PARAMS_ERROR));
        }
        cotenantUserInfo.setStatus(status);
        cotenantUserInfoMapper.updateByPrimaryKeySelective(cotenantUserInfo);
    }
}
