package com.youyu.cotenant.service.manager;

import com.github.pagehelper.PageHelper;
import com.youyu.cotenant.repository.biz.CotenantOpinionBizMapper;
import com.youyu.cotenant.web.vm.group.GroupListOutVM;
import com.youyu.cotenant.web.vm.opinion.OpinionOutVM;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OpinionService {


    @Autowired
    private CotenantOpinionBizMapper cotenantOpinionBizMapper;

    /**
     * 查询建议列表
     *
     * @return
     */
    public List<OpinionOutVM> list(Integer limit, Integer offset) {
        List<OpinionOutVM> list =
                PageHelper.offsetPage(offset, limit).doSelectPage(() -> cotenantOpinionBizMapper.selectOpinionList());

        return list;
    }
}
