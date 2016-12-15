package com.ychp.club.auth.infrastructure.impl.realm;

import com.google.common.collect.Lists;
import com.ychp.club.auth.model.Role;
import com.ychp.club.auth.model.mysql.RoleRepository;
import com.ychp.club.auth.model.shiro.CustomerUsernamePasswordToken;
import com.ychp.club.auth.service.RoleAuthorityService;
import com.ychp.club.common.util.CustomerStringUtils;
import com.ychp.club.common.util.Encryption;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
public class CustomerShiroRealm extends AuthorizingRealm {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);

        if(!StringUtils.isEmpty(username) && username.contains(CustomerStringUtils.SPLIT_CHARACTER)){
            Long roleId = Long.valueOf(username.split(CustomerStringUtils.SPLIT_CHARACTER)[1]);
            Role role = roleRepository.findById(roleId);

            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole(role.getName());

            List<String> permissions = roleAuthorityService.loadRoleAuthorities(roleId, null);

            simpleAuthorizationInfo.addStringPermissions(permissions);
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomerUsernamePasswordToken token = (CustomerUsernamePasswordToken) authenticationToken;

        if(token == null){
            throw new AuthenticationException("username.or.password.error");
        }

        if(StringUtils.isEmpty(token.getUsername())){
            throw new UnknownAccountException("username.error");
        }

        if(token.getPassword() != null || StringUtils.isEmpty(token.getOriginPassword())){
            String password = String.copyValueOf(token.getPassword());
            String originPassword = String.copyValueOf(token.getOriginPassword());
            if(!Encryption.checkPassword(password, token.getSalt(), originPassword)) {
                throw new IncorrectCredentialsException("password.error");
            }
        }

        if(token.getStatus() == null || Objects.equals(token.getStatus(), -1)){
            throw new LockedAccountException("account.locked");
        }

        //多次登录失败控制
        //ExcessiveAttemptsException

        try {
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
        } catch (Exception e) {
            throw new AuthenticationException("unknown.error");
        }

    }
}
