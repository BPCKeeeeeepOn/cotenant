package com.youyu.cotenant.service.manager;

import com.github.pagehelper.PageHelper;
import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.*;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantGroupMapper;
import com.youyu.cotenant.repository.CotenantGroupUserMapper;
import com.youyu.cotenant.repository.biz.CotenantGroupBizMapper;
import com.youyu.cotenant.repository.biz.CotenantUserBizMapper;
import com.youyu.cotenant.service.GroupService;
import com.youyu.cotenant.service.UserService;
import com.youyu.cotenant.utils.CommonUtils;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.dto.AddressLocationDTO;
import com.youyu.cotenant.web.vm.group.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

import static com.youyu.cotenant.common.CotenantConstants.COTENANT_TYPE.TYPE_3;
import static com.youyu.cotenant.common.CotenantConstants.GROUP_ROLE.LEADER;
import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.CANCEL_STATUS;
import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.groupStatus;

@Service
@Slf4j
public class GroupManagerService {

    @Autowired
    private CotenantGroupBizMapper cotenantGroupBizMapper;

    @Autowired
    private CotenantGroupMapper groupMapper;

    @Autowired
    private CotenantGroupUserMapper cotenantGroupUserMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private CotenantUserBizMapper cotenantUserBizMapper;

    @Autowired
    private CotenantGroupMapper cotenantGroupMapper;


    /**
     * 查询租房列表
     *
     * @param limit
     * @param offset
     * @return
     */
    public List<GroupListOutVM> list(Integer limit, Integer offset, Integer state) {
        List<GroupListOutVM> list =
                PageHelper.offsetPage(offset, limit).doSelectPage(() -> cotenantGroupBizMapper.selectManagerGroupList(state));
        return list;
    }

    /**
     * 查询合租/预租房详情
     *
     * @return
     */
    public GroupDetailOutVM detail(Long id) {
        //查询详情内容
        GroupDetailOutVM groupDetailOutVM = cotenantGroupBizMapper.selectManagerGroupDetail(id);
        //查询组员信息
        List<CotenantListOutVM> cotenantListOutVM = cotenantGroupBizMapper.selectCotenantList(id);
        groupDetailOutVM.setCotenantList(cotenantListOutVM);
        return groupDetailOutVM;
    }

    /**
     * 审核租房团
     *
     * @param groupId
     * @param status
     */
    public void examine(Long groupId, Integer status) {
        CotenantGroup cotenantGroup = groupMapper.selectByPrimaryKey(groupId);
        if (cotenantGroup == null) {
            throw new BizException(ResponseResult.fail(ResultCode.NO_COTENANT_GROUP));
        }
        if (!groupStatus.contains(status)) {
            throw new BizException(ResponseResult.fail(ResultCode.PARAMS_ERROR));
        }
        cotenantGroup.setStatus(status);
        groupMapper.updateByPrimaryKeySelective(cotenantGroup);

        //更改租房下所有成员状态
        if (Objects.equals(status, CANCEL_STATUS)) {
            CotenantGroupUserExample cotenantGroupUserExample = new CotenantGroupUserExample();
            cotenantGroupUserExample.createCriteria().andCotenantGroupIdEqualTo(groupId);
            CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
            cotenantGroupUser.setStatus(CotenantConstants.EXAMINE_STATUS.CANCEL);
            cotenantGroupUserMapper.updateByExampleSelective(cotenantGroupUser, cotenantGroupUserExample);
        }
    }

    /**
     * 发布房源
     *
     * @param groupInVM
     */
    public void addGroup(GroupInVM groupInVM) {
        CotenantUser currUser = currentUserUtils.getCurrUser();
        String mobile = currUser.getMobile();
        CotenantUser cotenantUser = userService.selectUserByUserName(mobile);
        if (Objects.isNull(cotenantUser)) {
            throw new BizException(ResponseResult.fail(ResultCode.ERROR_CODE_100900));
        }
        CotenantUserInfo cotenantUserInfo = cotenantUserBizMapper.selectUserDetail(cotenantUser.getId());
        if (Objects.isNull(cotenantUserInfo) || !Objects.equals(cotenantUserInfo.getUserType(), CotenantConstants.USER_TYPE.LANDLORD)) {
            throw new BizException(ResponseResult.fail(ResultCode.ERROR_CODE_100900));
        }
        if (StringUtils.isBlank(groupInVM.getAddressDetail())) {
            throw new BizException(ResponseResult.fail(ResultCode.PARAMS_ERROR));
        }
        AddressLocationDTO addressLocationDTO = CommonUtils.geoMapCode(groupInVM.getAddressDetail());
        if (Objects.isNull(addressLocationDTO)) {
            throw new BizException(ResponseResult.fail(ResultCode.ADDRESS_LOCATION_NOT_FOUND));
        }
        groupInVM.setAddressName(addressLocationDTO.getFormattedAddress());
        groupInVM.setAddressDetail(addressLocationDTO.getFormattedAddress());
        groupInVM.setAddressLongitude(addressLocationDTO.getAddressLongitude());
        groupInVM.setAddressLatitude(addressLocationDTO.getAddressLatitude());
        groupInVM.setProvince(addressLocationDTO.getProvince());
        groupInVM.setCity(addressLocationDTO.getCity());
        groupInVM.setDistrict(addressLocationDTO.getDistrict());
        groupService.publish(groupInVM, cotenantUser.getId());
    }

    /**
     * 批量添加
     *
     * @param batchGroupInVM
     */
    public List<Map<String, Object>> batchAddGroup(BatchGroupInVM batchGroupInVM) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<GroupInVM> groups = batchGroupInVM.getGroups();
        if (CollectionUtils.isEmpty(groups)) {
            throw new BizException(ResponseResult.fail(ResultCode.DEFAULT_ERROR));
        }
        Long userId = 598189994836963328L;
        for (int i = 0; i < groups.size(); i++) {
            Map<String, Object> error = new HashMap<>();
            GroupInVM groupInVM = groups.get(i);
            AddressLocationDTO addressLocationDTO = CommonUtils.geoMapCode(groupInVM.getAddressDetail());
            if (Objects.isNull(addressLocationDTO)) {
                error.put("index", i + 2);
                error.put("message", "地址坐标查询失败，请确认地址");
                result.add(error);
                continue;
            }
            if (StringUtils.isBlank(groupInVM.getTitle()) || groupInVM.getTitle().length() > 20) {
                error.put("index", i + 2);
                error.put("message", "标题为空，或者标题长度大于20");
                result.add(error);
                continue;
            }
            if (StringUtils.isBlank(groupInVM.getTitle()) || groupInVM.getTitle().length() > 20) {
                error.put("index", i + 2);
                error.put("message", "标题为空，或者标题长度大于20");
                result.add(error);
                continue;
            }
            if (Objects.isNull(groupInVM.getHousePrice())) {
                error.put("index", i + 2);
                error.put("message", "价格不能为空");
                result.add(error);
                continue;
            }
            groupInVM.setAddressName(addressLocationDTO.getFormattedAddress());
            groupInVM.setAddressDetail(addressLocationDTO.getFormattedAddress());
            groupInVM.setAddressLongitude(addressLocationDTO.getAddressLongitude());
            groupInVM.setAddressLatitude(addressLocationDTO.getAddressLatitude());
            groupInVM.setProvince(addressLocationDTO.getProvince());
            groupInVM.setCity(addressLocationDTO.getCity());
            groupInVM.setDistrict(addressLocationDTO.getDistrict());
            groupInVM.setCotenantType(TYPE_3);
            CotenantGroup cotenantGroup = groupInVM.buildCotenantGroup();
            Long id = GeneratorID.getId();
            cotenantGroup.setId(id);
            //新增团
            groupMapper.insertSelective(cotenantGroup);
            CotenantGroupUser cotenantGroupUser = new CotenantGroupUser();
            cotenantGroupUser.setId(GeneratorID.getId());
            cotenantGroupUser.setCotenantGroupId(id);
            cotenantGroupUser.setCotenantUserId(userId);
            cotenantGroupUser.setStatus(CotenantConstants.EXAMINE_STATUS.PASS);
            cotenantGroupUser.setRole(LEADER);
            cotenantGroupUserMapper.insertSelective(cotenantGroupUser);
        }
        return result;
    }

    /**
     * 编辑房源
     *
     * @param groupInVM
     */
    public void editGroup(Long id, GroupInVM groupInVM) {
        CotenantGroup cotenantGroup = groupMapper.selectByPrimaryKey(id);
        if (Objects.isNull(cotenantGroup)) {
            throw new BizException(ResponseResult.fail(ResultCode.NO_COTENANT_GROUP));
        }
        if (StringUtils.isBlank(groupInVM.getAddressDetail())) {
            throw new BizException(ResponseResult.fail(ResultCode.PARAMS_ERROR));
        }
        AddressLocationDTO addressLocationDTO = CommonUtils.geoMapCode(groupInVM.getAddressDetail());
        if (Objects.isNull(addressLocationDTO)) {
            throw new BizException(ResponseResult.fail(ResultCode.ADDRESS_LOCATION_NOT_FOUND));
        }
        groupInVM.setAddressDetail(addressLocationDTO.getFormattedAddress());
        groupInVM.setAddressLongitude(addressLocationDTO.getAddressLongitude());
        groupInVM.setAddressLatitude(addressLocationDTO.getAddressLatitude());
        groupInVM.setProvince(addressLocationDTO.getProvince());
        groupInVM.setCity(addressLocationDTO.getCity());
        groupInVM.setDistrict(addressLocationDTO.getDistrict());
        BeanUtils.copyProperties(groupInVM, cotenantGroup);
        groupMapper.updateByPrimaryKeySelective(cotenantGroup);
    }
}
