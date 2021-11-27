package com.youyu.cotenant.security;

import com.youyu.cotenant.entity.CotenantUser;
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
 * 自定义身份验证(账户名密码)
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String phone = authentication.getName();
        String password = authentication.getCredentials().toString();
        CotenantUser user = userService.selectUserByUserName(phone);
        if (user == null)
            throw new BadCredentialsException("该用户不存在");
        if (encoder.matches(password, user.getPassword())) {
            //这里设置权限和角色
            Collection<GrantedAuthority> authorities = obtionGrantedAuthorities(user);
            //生成令牌
            return new UsernamePasswordAuthenticationToken(phone, password, authorities);
        } else {
            throw new BadCredentialsException("密码错误");
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
