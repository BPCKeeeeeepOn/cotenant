package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantUserInfo;
import com.youyu.cotenant.entity.CotenantUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CotenantUserInfoMapper {
    long countByExample(CotenantUserInfoExample example);

    int deleteByExample(CotenantUserInfoExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(CotenantUserInfo record);

    int insertSelective(CotenantUserInfo record);

    List<CotenantUserInfo> selectByExample(CotenantUserInfoExample example);

    CotenantUserInfo selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") CotenantUserInfo record, @Param("example") CotenantUserInfoExample example);

    int updateByExample(@Param("record") CotenantUserInfo record, @Param("example") CotenantUserInfoExample example);

    int updateByPrimaryKeySelective(CotenantUserInfo record);

    int updateByPrimaryKey(CotenantUserInfo record);
}