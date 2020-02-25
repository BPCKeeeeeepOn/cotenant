package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.rest.vm.college.CollegeOutVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantCommonBizMapper {

    /**
     * 查询学校信息
     * @param name
     * @return
     */
    List<CollegeOutVM> selectCollege(@Param("name") String name);

}