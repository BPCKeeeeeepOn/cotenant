package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantCollege;
import com.youyu.cotenant.entity.CotenantCollegeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantCollegeMapper {
    long countByExample(CotenantCollegeExample example);

    int deleteByExample(CotenantCollegeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantCollege record);

    int insertSelective(CotenantCollege record);

    List<CotenantCollege> selectByExample(CotenantCollegeExample example);

    CotenantCollege selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantCollege record, @Param("example") CotenantCollegeExample example);

    int updateByExample(@Param("record") CotenantCollege record, @Param("example") CotenantCollegeExample example);

    int updateByPrimaryKeySelective(CotenantCollege record);

    int updateByPrimaryKey(CotenantCollege record);
}