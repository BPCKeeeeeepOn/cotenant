package com.youyu.cotenant.service;

import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.config.MyBatisConfig;
import com.youyu.cotenant.entity.*;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantGroupMapper;
import com.youyu.cotenant.repository.CotenantGroupUserMapper;
import com.youyu.cotenant.repository.biz.CotenantGroupBizMapper;
import com.youyu.cotenant.repository.biz.CotenantPersonalBizMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.web.rest.vm.group.CotenantListOutVM;
import com.youyu.cotenant.web.rest.vm.group.GroupDetailOutVM;
import com.youyu.cotenant.web.rest.vm.group.GroupListOutVM;
import com.youyu.cotenant.web.rest.vm.personal.GroupExamineInVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonalService {

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private CotenantPersonalBizMapper cotenantPersonalBizMapper;

    @Autowired
    private CotenantGroupBizMapper cotenantGroupBizMapper;

    @Autowired
    private CotenantGroupMapper cotenantGroupMapper;

    @Autowired
    private CotenantGroupUserMapper cotenantGroupUserMapper;

    /**
     * 查询我的合租任务
     *
     * @param role
     * @return
     */
    public List<GroupListOutVM> list(Integer role) {
        //查询出用户信息
        Long userId = currentUserUtils.getCurrUserId();
        List<GroupListOutVM> list = cotenantPersonalBizMapper.selectPersonalGroupList(role, userId);
        return list;
    }

    /**
     * 查询合租详情
     *
     * @param groupId
     * @return
     */
    public GroupDetailOutVM detail(Long groupId) {
        //查询出用户信息
        Long userId = currentUserUtils.getCurrUserId();
        //查询详情内容
        GroupDetailOutVM groupDetailOutVM = cotenantGroupBizMapper.selectGroupDetail(groupId);
        List<CotenantListOutVM> list = cotenantPersonalBizMapper.selectCotenantList(groupId, userId);
        groupDetailOutVM.setCotenantList(list);
        return groupDetailOutVM;
    }

    /**
     * 解散租房团
     *
     * @param groupId
     */
    @Transactional(value = MyBatisConfig.COTENTANT_TRANSACTION_MANAGER)
    public void dismiss(Long groupId) {
        Long userId = currentUserUtils.getCurrUserId();
        if (!isGroupLeader(groupId, userId)) {
            //您无权限操作
            throw new BizException(ResponseResult.fail(ResultCode.NO_PERMISSION_OPERATION));
        }
        //更改租房团状态
        CotenantGroup cotenantGroup = new CotenantGroup();
        cotenantGroup.setId(groupId);
        cotenantGroup.setStatus(CotenantConstants.GROUP_STATUS.CANCEL_STATUS);
        cotenantGroupMapper.updateByPrimaryKeySelective(cotenantGroup);
        //更改租房下所有成员状态
        CotenantGroupUserExample cotenantGroupUserExample = new CotenantGroupUserExample();
        cotenantGroupUserExample.createCriteria().andCotenantGroupIdEqualTo(groupId);
        CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
        cotenantGroupUser.setStatus(CotenantConstants.EXAMINE_STATUS.CANCEL);
        cotenantGroupUserMapper.updateByExampleSelective(cotenantGroupUser, cotenantGroupUserExample);
    }

    /**
     * 租房成员审核
     *
     * @param groupExamineInVM
     */
    public void examine(GroupExamineInVM groupExamineInVM) {
        Long groupId = groupExamineInVM.getGroupId();
        Long memberId = groupExamineInVM.getMemberId();
        Integer status = groupExamineInVM.getStatus();
        Long userId = currentUserUtils.getCurrUserId();
        if (!isGroupLeader(groupId, userId)) {
            //您无权限操作
            throw new BizException(ResponseResult.fail(ResultCode.NO_PERMISSION_OPERATION));
        }
        //更改成员状态
        CotenantGroupUserExample cotenantGroupUserExample = new CotenantGroupUserExample();
        cotenantGroupUserExample.createCriteria().andCotenantGroupIdEqualTo(groupId).andCotenantUserIdEqualTo(memberId);
        CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
        cotenantGroupUser.setStatus(status);
        cotenantGroupUserMapper.updateByExampleSelective(cotenantGroupUser, cotenantGroupUserExample);

    }

    /**
     * 取消租房(团员)
     *
     * @param groupId
     */
    public void cancel(Long groupId) {
        Long userId = currentUserUtils.getCurrUserId();
        //更改成员状态
        CotenantGroupUserExample cotenantGroupUserExample = new CotenantGroupUserExample();
        cotenantGroupUserExample.createCriteria().andCotenantGroupIdEqualTo(groupId).andCotenantUserIdEqualTo(userId);
        CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
        cotenantGroupUser.setStatus(CotenantConstants.EXAMINE_STATUS.CANCEL);
        cotenantGroupUserMapper.updateByExampleSelective(cotenantGroupUser, cotenantGroupUserExample);
    }

    public boolean isGroupLeader(Long groupId, Long userId) {
        //查询该租房团是否属于登录用户
        Long leaderId = cotenantGroupBizMapper.selectGroupLeader(groupId);
        if (userId.equals(leaderId)) {
            return true;
        }
        return false;
    }

}
