package com.youyu.cotenant.security;

import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.service.SmsService;
import com.youyu.cotenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义身份验证(短信验证码)
 */
@Component
public class CustomSmsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = authentication.getName();
        String code = authentication.getCredentials().toString();
        CotenantUser user = userService.selectUserByUserName(mobile);

        if (smsService.verifyCode(mobile, code)) {
            if (user == null) {
                //首次登录需要新增一条用户信息
                user = new CotenantUser();
                user.setMobile(mobile);
                userService.insertUser(user);
            }
            //这里设置权限和角色
            Collection<GrantedAuthority> authorities = obtionGrantedAuthorities(user);
            //生成令牌
            return new UsernamePasswordAuthenticationToken(mobile, code, authorities);
        } else {
            throw new BadCredentialsException("验证码错误");
        }
    }

    /**
     * 取得用户的权限
     *
     * @param user 用户信息对象
     * @return
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(CotenantUser user) {

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        authSet.add(new SimpleGrantedAuthority("1"));

        return authSet;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
