package com.ychp.club.auth.infrastructure.impl.realm;

import com.google.common.collect.Lists;
import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.model.Role;
import com.ychp.club.user.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
public class CustomerShiroRealm extends AuthorizingRealm {

    private final UserManager userManager;

    @Autowired
    public CustomerShiroRealm(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        User user = userManager.findByUsername(username);
        if(user != null){
            Role role = new Role();
            role.setName("admin");

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(role.getName());

            List<String> permissions = Lists.newArrayList("user:paging");

            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        User user = userManager.findByUsername(token.getUsername());

        if(user != null){
            return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());
        }

        return null;
    }
}
