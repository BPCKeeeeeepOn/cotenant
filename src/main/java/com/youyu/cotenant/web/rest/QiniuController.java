package com.youyu.cotenant.web.rest;

import com.qiniu.util.Auth;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.result.QiniuTokenVM;
import com.youyu.cotenant.utils.qiniuupload.QiNiuProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
@RequestMapping("/qiniu")
public class QiniuController {

    @Autowired
    private QiNiuProperties qiNiuProperties;

    @GetMapping("/token")
    public ResponseResult getQiniuToken() {

        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        String key = "cotenant_" + localTime + "_" + new Random().nextInt(100000);
        String token = auth.uploadToken(qiNiuProperties.getBucket(), null);
        return ResponseResult.success().body(new QiniuTokenVM(key, token));
    }

}
