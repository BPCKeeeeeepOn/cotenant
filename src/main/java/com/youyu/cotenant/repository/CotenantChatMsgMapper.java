package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantChatMsg;
import com.youyu.cotenant.entity.CotenantChatMsgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantChatMsgMapper {
    long countByExample(CotenantChatMsgExample example);

    int deleteByExample(CotenantChatMsgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantChatMsg record);

    int insertSelective(CotenantChatMsg record);

    List<CotenantChatMsg> selectByExample(CotenantChatMsgExample example);

    CotenantChatMsg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantChatMsg record, @Param("example") CotenantChatMsgExample example);

    int updateByExample(@Param("record") CotenantChatMsg record, @Param("example") CotenantChatMsgExample example);

    int updateByPrimaryKeySelective(CotenantChatMsg record);

    int updateByPrimaryKey(CotenantChatMsg record);
}