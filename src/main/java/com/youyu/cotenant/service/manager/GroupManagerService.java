package com.youyu.cotenant.service.manager;

import com.github.pagehelper.PageHelper;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantGroup;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantGroupMapper;
import com.youyu.cotenant.repository.biz.CotenantGroupBizMapper;
import com.youyu.cotenant.web.vm.group.CotenantListOutVM;
import com.youyu.cotenant.web.vm.group.GroupDetailOutVM;
import com.youyu.cotenant.web.vm.group.GroupListOutVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.groupStatus;

@Service
@Slf4j
public class GroupManagerService {

    @Autowired
    private CotenantGroupBizMapper cotenantGroupBizMapper;

    @Autowired
    private CotenantGroupMapper groupMapper;


    /**
     * 查询租房列表
     *
     * @param limit
     * @param offset
     * @return
     */
    public List<GroupListOutVM> list(Integer limit, Integer offset) {
        List<GroupListOutVM> list =
                PageHelper.offsetPage(offset, limit).doSelectPage(() -> cotenantGroupBizMapper.selectManagerGroupList());
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
    }

}
