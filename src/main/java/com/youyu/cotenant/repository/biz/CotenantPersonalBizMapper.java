package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.vm.group.CotenantListOutVM;
import com.youyu.cotenant.web.vm.group.GroupListOutVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantPersonalBizMapper {

    /**
     * 查询预租/合租列表
     * @param role
     * @param userId
     * @return
     */
    List<GroupListOutVM> selectPersonalGroupList(@Param("role") Integer role,@Param("userId") Long userId);

    /**
     * 查询预租/合租下的团员
     * @param id
     * @return
     */
    List<CotenantListOutVM> selectCotenantList(@Param("id")Long id,@Param("userId") Long userId);



}