package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantUserCollege;
import com.youyu.cotenant.entity.CotenantUserCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantUserCollegeMapper {
    long countByExample(CotenantUserCollegeExample example);

    int deleteByExample(CotenantUserCollegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantUserCollege record);

    int insertSelective(CotenantUserCollege record);

    List<CotenantUserCollege> selectByExample(CotenantUserCollegeExample example);

    CotenantUserCollege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantUserCollege record, @Param("example") CotenantUserCollegeExample example);

    int updateByExample(@Param("record") CotenantUserCollege record, @Param("example") CotenantUserCollegeExample example);

    int updateByPrimaryKeySelective(CotenantUserCollege record);

    int updateByPrimaryKey(CotenantUserCollege record);
}