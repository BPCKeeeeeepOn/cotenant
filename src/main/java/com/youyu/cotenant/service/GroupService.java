package com.youyu.cotenant.service;

import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantGroup;
import com.youyu.cotenant.entity.CotenantGroupUser;
import com.youyu.cotenant.entity.CotenantGroupUserExample;
import com.youyu.cotenant.entity.CotenantReportedViolation;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantGroupMapper;
import com.youyu.cotenant.repository.CotenantGroupUserMapper;
import com.youyu.cotenant.repository.CotenantReportedViolationMapper;
import com.youyu.cotenant.repository.biz.CotenantGroupBizMapper;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.vm.group.*;
import com.youyu.cotenant.web.vm.user.UserOutVM;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.youyu.cotenant.common.CotenantConstants.COTENANT_TYPE.TYPE_1;
import static com.youyu.cotenant.common.CotenantConstants.COTENANT_TYPE.TYPE_2;
import static com.youyu.cotenant.common.CotenantConstants.EXAMINE_STATUS.PASS;
import static com.youyu.cotenant.common.CotenantConstants.EXAMINE_STATUS.PASS_DEFAULT_STATUS;
import static com.youyu.cotenant.common.CotenantConstants.GROUP_ROLE.LEADER;
import static com.youyu.cotenant.common.CotenantConstants.GROUP_ROLE.MEMBER;
import static com.youyu.cotenant.common.CotenantConstants.UNREAD_GROUP_KEY;
import static com.youyu.cotenant.common.CotenantConstants.USER_TYPE.PERSONAL;
import static com.youyu.cotenant.common.ResultCode.USER_NO_GROUP;
import static com.youyu.cotenant.config.MyBatisConfig.COTENTANT_TRANSACTION_MANAGER;

@Service
public class GroupService {

    @Autowired
    private CotenantGroupBizMapper cotenantGroupBizMapper;

    @Autowired
    private CotenantGroupMapper cotenantGroupMapper;

    @Autowired
    private CotenantGroupUserMapper cotenantGroupUserMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CotenantReportedViolationMapper cotenantReportedViolationMapper;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询聚合页面列表
     *
     * @param groupQueryInVM
     * @return
     */
    public List<GroupListOutVM> list(GroupQueryInVM groupQueryInVM) {
        List<GroupListOutVM> list = cotenantGroupBizMapper.selectGroupList(groupQueryInVM);
        return list;
    }


    /**
     * 查询合租/预租房详情
     *
     * @return
     */
    public GroupDetailOutVM detail(Long id) {
        //查询详情内容
        GroupDetailOutVM groupDetailOutVM = cotenantGroupBizMapper.selectGroupDetail(id);
        //查询组员信息
        List<CotenantListOutVM> cotenantListOutVM = cotenantGroupBizMapper.selectCotenantList(id);
        groupDetailOutVM.setCotenantList(cotenantListOutVM);
        return groupDetailOutVM;
    }

    /**
     * 根据用户id获取租房
     *
     * @param userId
     */
    public Map<String, String> getGroupId(Long userId) {
        Map<String, String> map = new HashMap<>();
        String cotenantGroupId;
        CotenantGroupUserExample cotenantGroupUserExample = new CotenantGroupUserExample();
        cotenantGroupUserExample.createCriteria().andCotenantUserIdEqualTo(userId).andRoleEqualTo(CotenantConstants.GROUP_ROLE.LEADER).andStatusEqualTo(CotenantConstants.EXAMINE_STATUS.PASS);
        List<CotenantGroupUser> cotenantGroupUserList = cotenantGroupUserMapper.selectByExample(cotenantGroupUserExample);
        if (cotenantGroupUserList.size() == 0) {
            throw new BizException(ResponseResult.fail(USER_NO_GROUP));
        }
        cotenantGroupId = String.valueOf(cotenantGroupUserList.get(0).getCotenantGroupId());
        map.put("group_id", cotenantGroupId);
        return map;
    }

    /**
     * 申请加入租房团
     *
     * @param id
     */
    public void join(Long id) {
        int unreadCount = NumberUtils.INTEGER_ONE;
        CotenantGroup cotenantGroup = cotenantGroupMapper.selectByPrimaryKey(id);
        if (Objects.isNull(cotenantGroup)) {
            //没有该租房团
            throw new BizException(ResponseResult.fail(ResultCode.NO_COTENANT_GROUP));
        }
        Long userId = currentUserUtils.getCurrUserId();
        Integer userStatus = userService.selectUserStatus(userId);
        if (userStatus == CotenantConstants.USER_STATUS.NOT_USER_STATUS) {
            //未补全用户信息
            throw new BizException(ResponseResult.fail(ResultCode.USER_INFO_ERROR));
        }
        if (userStatus == CotenantConstants.USER_STATUS.DEFAULT_STATUS) {
            //用户信息待审核
            throw new BizException(ResponseResult.fail(ResultCode.USER_INFO_VERIFY));
        }
        if (userStatus == CotenantConstants.USER_STATUS.CANCEL_STATUS) {
            //审核未通过
            throw new BizException(ResponseResult.fail(ResultCode.UNPASS_USER));
        }
        if (isCotenant(userId)) {
            //参加过合租团
            throw new BizException(ResponseResult.fail(ResultCode.EXIST_COTENANT_GROUP));
        }
        CotenantGroupUserExample example = new CotenantGroupUserExample();
        example.createCriteria().andCotenantGroupIdEqualTo(id);
        long count = cotenantGroupUserMapper.countByExample(example);
        Integer cotenantCount = cotenantGroup.getCotenantCount();
        //nInteger cotenantType = cotenantGroup.getCotenantType();
        if (Objects.equals(cotenantGroup.getCotenantType(), TYPE_1) || Objects.equals(cotenantGroup.getCotenantType(), TYPE_2)) {
            if (count >= cotenantCount) {
                //该租房团入驻人数已满
                throw new BizException(ResponseResult.fail(ResultCode.COTENANT_GROUP_FULL));
            }
        }
        //查询该合租团团长id
        example.clear();
        example.createCriteria().andCotenantGroupIdEqualTo(id).andRoleEqualTo(LEADER);
        Long leaderUserId = cotenantGroupUserMapper.selectByExample(example).get(NumberUtils.INTEGER_ZERO).getCotenantUserId();
        //利用缓存写入未读消息
        String unreadCountStr = redisUtils.getCache(UNREAD_GROUP_KEY + leaderUserId);
        if (StringUtils.isNotBlank(unreadCountStr)) {
            unreadCount = Integer.valueOf(unreadCountStr);
            unreadCount++;
        }
        redisUtils.putCache(UNREAD_GROUP_KEY + leaderUserId, String.valueOf(unreadCount));
        //加入租房团
        CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
        cotenantGroupUser.setId(GeneratorID.getId());
        cotenantGroupUser.setCotenantGroupId(id);
        cotenantGroupUser.setCotenantUserId(userId);
        cotenantGroupUser.setRole(MEMBER);
        cotenantGroupUserMapper.insertSelective(cotenantGroupUser);

    }

    /**
     * 发布房源
     *
     * @param groupInVM
     */
    @Transactional(COTENTANT_TRANSACTION_MANAGER)
    public void publish(GroupInVM groupInVM, Long userId) {

        Integer userStatus = userService.selectUserStatus(userId);
        UserOutVM info = userService.info();
        if (userStatus == CotenantConstants.USER_STATUS.NOT_USER_STATUS) {
            //未补全用户信息
            throw new BizException(ResponseResult.fail(ResultCode.USER_INFO_ERROR));
        }
        if (userStatus == CotenantConstants.USER_STATUS.DEFAULT_STATUS) {
            //用户信息待审核
            throw new BizException(ResponseResult.fail(ResultCode.USER_INFO_VERIFY));
        }
        if (userStatus == CotenantConstants.USER_STATUS.CANCEL_STATUS) {
            //审核未通过
            throw new BizException(ResponseResult.fail(ResultCode.UNPASS_USER));
        }
        if (Objects.equals(PERSONAL, info.getUserType())) {
            if (isCotenant(userId)) {
                //参加过合租团
                throw new BizException(ResponseResult.fail(ResultCode.EXIST_COTENANT_GROUP));
            }
        }
        CotenantGroup cotenantGroup = groupInVM.buildCotenantGroup();
        Long id = GeneratorID.getId();
        cotenantGroup.setId(id);
        //新增团
        cotenantGroupMapper.insertSelective(cotenantGroup);
        CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
        cotenantGroupUser.setId(GeneratorID.getId());
        cotenantGroupUser.setCotenantGroupId(id);
        cotenantGroupUser.setCotenantUserId(userId);
        cotenantGroupUser.setStatus(CotenantConstants.EXAMINE_STATUS.PASS);
        cotenantGroupUser.setRole(LEADER);
        cotenantGroupUserMapper.insertSelective(cotenantGroupUser);
    }

    /**
     * 举报租房团
     *
     * @param groupId
     * @param content
     */
    public void reported(Long groupId, String content) {
        Long userId = currentUserUtils.getCurrUserId();
        //记录举报信息
        CotenantGroup cotenantGroup = cotenantGroupMapper.selectByPrimaryKey(groupId);
        if (cotenantGroup == null) {
            throw new BizException(ResponseResult.fail(ResultCode.NO_COTENANT_GROUP));
        }
        CotenantReportedViolation cotenantReportedViolation = new CotenantReportedViolation();
        cotenantReportedViolation.setId(GeneratorID.getId());
        cotenantReportedViolation.setCotenantGroupId(groupId);
        cotenantReportedViolation.setContent(content);
        cotenantReportedViolation.setCotenantUserId(userId);
        cotenantReportedViolation.setStatus(CotenantConstants.USER_STATUS.DEFAULT_STATUS);
        cotenantReportedViolationMapper.insertSelective(cotenantReportedViolation);
    }

    /**
     * 是否是合租成员
     *
     * @param userId
     * @return
     */
    public boolean isCotenant(Long userId) {
        long count = cotenantGroupBizMapper.selectJoinGroup(userId);
        if (count > NumberUtils.INTEGER_ZERO) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
