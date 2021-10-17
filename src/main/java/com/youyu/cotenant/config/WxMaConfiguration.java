package com.youyu.cotenant.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(WxMaProperties.class)
public class WxMaConfiguration {
    private final WxMaProperties properties;

    private static Map<String, WxMaService> maServices;


    @Autowired
    public WxMaConfiguration(WxMaProperties properties) {
        this.properties = properties;
    }

    public static WxMaService getMaService(String appid) {
        WxMaService wxService = maServices.get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        return wxService;
    }

    @PostConstruct
    public void init() {
        List<WxMaProperties.Config> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new WxRuntimeException("缺少相关配置");
        }

        maServices = configs.stream()
                .map(a -> {
                    WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
//                WxMaDefaultConfigImpl config = new WxMaRedisConfigImpl(new JedisPool());
                    // 使用上面的配置时，需要同时引入jedis-lock的依赖，否则会报类无法找到的异常
                    config.setAppid(a.getAppid());
                    config.setSecret(a.getSecret());
                    config.setToken(a.getToken());
                    config.setAesKey(a.getAesKey());
                    config.setMsgDataFormat(a.getMsgDataFormat());

                    WxMaService service = new WxMaServiceImpl();
                    service.setWxMaConfig(config);
                    return service;
                }).collect(Collectors.toMap(s -> s.getWxMaConfig().getAppid(), a -> a));
    }
}
