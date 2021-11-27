package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.vm.opinion.OpinionOutVM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantOpinionBizMapper {

    List<OpinionOutVM> selectOpinionList();
}
