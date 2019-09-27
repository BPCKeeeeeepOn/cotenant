package com.youyu.cotenant;

import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CotenantApplicationTests {


    @Autowired
    SystemService systemService;

    @Test
    public void delCache(){
        String key = CotenantConstants.CHAT_RECEIVE_KEY + "8fef6dcd897195a0f356995f7b0135fd";
        systemService.delCache(key);
    }
}
