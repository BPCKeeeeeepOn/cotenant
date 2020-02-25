package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.web.rest.vm.user.UserCollegeOutVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantUserBizMapper {


    /**
     * 查询用户详情信息
     * @param userId
     * @return
     */
    CotenantUserInfo selectUserDetail(@Param("user_id") Long userId);


    /**
     * 查询用户大学信息
     * @param userId
     * @return
     */
    List<UserCollegeOutVM> selectUserColleges(@Param("user_id") Long userId);

}
