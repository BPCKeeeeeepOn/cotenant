package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.rest.vm.group.CotenantListOutVM;
import com.youyu.cotenant.web.rest.vm.group.GroupDetailOutVM;
import com.youyu.cotenant.web.rest.vm.group.GroupListOutVM;
import com.youyu.cotenant.web.rest.vm.group.GroupQueryInVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantGroupBizMapper {


    /**
     * 查询预租/合租列表
     *
     * @param groupQueryInVM
     * @return
     */
    List<GroupListOutVM> selectGroupList(@Param("groupQueryInVM") GroupQueryInVM groupQueryInVM);


    /**
     * 查询预租/合租详情
     *
     * @param id
     * @return
     */
    GroupDetailOutVM selectGroupDetail(@Param("id") Long id);

    /**
     * 查询预租/合租下的团员
     *
     * @param id
     * @return
     */
    List<CotenantListOutVM> selectCotenantList(@Param("id") Long id);

    /**
     * 查询该团
     * @param groupId
     * @return
     */
    Long selectGroupLeader(@Param("groupId") Long groupId);

    /**
     * 查询是否加入该团
     * @param userId
     * @return
     */
    Long selectJoinGroup(@Param("userId")Long userId);

}