package com.youyu.cotenant.web.vm.user;

import com.youyu.cotenant.entity.CotenantUser;
import lombok.Data;

@Data
public class UserRegisterInVM {

    private String mobile;

    private String code;

    private String password;

    public CotenantUser buildCotenantUser(){
        CotenantUser cotenantUser = new CotenantUser();
        cotenantUser.setEnabled(true);
        cotenantUser.setMobile(mobile);
        return cotenantUser;
    }
}
