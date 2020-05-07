package com.youyu.cotenant.security;

import com.youyu.cotenant.entity.CustomUser;
import com.youyu.cotenant.service.manager.UserManagerService;
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
public class CustomCmsAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        CustomUser user = userManagerService.selectUserByUserName(username);
        if (user == null)
            throw new BadCredentialsException("该用户不存在");
        if (encoder.matches(password, user.getPassword())) {
            //这里设置权限和角色
            Collection<GrantedAuthority> authorities = obtionGrantedAuthorities(user);
            //生成令牌
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
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
    private Set<GrantedAuthority> obtionGrantedAuthorities(CustomUser user) {

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        authSet.add(new SimpleGrantedAuthority("1"));

        return authSet;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
