package com.youyu.cotenant.web.vm;

import lombok.Data;

import static com.youyu.cotenant.common.CotenantConstants.*;

@Data
public class BaseVM {

    private int limit = DEFAULT_PAGE_SIZE;


    private int offset = DEFAULT_PAGE_OFFSET;

}
