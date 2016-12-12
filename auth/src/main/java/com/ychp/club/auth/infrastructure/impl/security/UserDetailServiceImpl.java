package com.ychp.club.auth.infrastructure.impl.security;

import com.google.common.collect.Lists;
import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.enums.UserStatus;
import com.ychp.club.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/11
 */
@Primary
@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserManager userManager;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userManager.findByUsername(username);

        List<GrantedAuthority> authorities = Lists.newArrayList(new SimpleGrantedAuthority("/user/paging1"));

        user.setAuthorities(authorities);

        //是否可用
        user.setAccountNonExpired(true);

        //是否锁定
        user.setAccountNonLocked(true);

        //密码是否有效
        user.setCredentialsNonExpired(true);

        //用户状态是否有效
        user.setEnabled(Objects.equals(user.getStatus(), UserStatus.ENABLE.getValue()));
        return user;
    }
}
