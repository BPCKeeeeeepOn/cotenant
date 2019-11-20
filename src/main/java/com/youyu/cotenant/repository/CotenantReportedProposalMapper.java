package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantReportedProposal;
import com.youyu.cotenant.entity.CotenantReportedProposalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantReportedProposalMapper {
    long countByExample(CotenantReportedProposalExample example);

    int deleteByExample(CotenantReportedProposalExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantReportedProposal record);

    int insertSelective(CotenantReportedProposal record);

    List<CotenantReportedProposal> selectByExample(CotenantReportedProposalExample example);

    CotenantReportedProposal selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantReportedProposal record, @Param("example") CotenantReportedProposalExample example);

    int updateByExample(@Param("record") CotenantReportedProposal record, @Param("example") CotenantReportedProposalExample example);

    int updateByPrimaryKeySelective(CotenantReportedProposal record);

    int updateByPrimaryKey(CotenantReportedProposal record);
}