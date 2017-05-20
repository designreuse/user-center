package com.ychp.center.auth.infrastructure.impl.realm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.center.auth.model.App;
import com.ychp.center.auth.model.Role;
import com.ychp.center.auth.model.RoleApp;
import com.ychp.center.auth.model.UserRole;
import com.ychp.center.auth.model.mysql.AppRepository;
import com.ychp.center.auth.model.mysql.RoleAppRepository;
import com.ychp.center.auth.model.mysql.RoleRepository;
import com.ychp.center.auth.model.mysql.UserRoleRepository;
import com.ychp.center.auth.model.shiro.CustomerUsernamePasswordToken;
import com.ychp.center.auth.service.RoleAuthorityService;
import com.ychp.center.auth.utils.AuthUtils;
import com.ychp.coding.common.util.CustomerStringUtils;
import com.ychp.coding.common.util.Encryption;
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
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @Autowired
    private RoleAppRepository roleAppRepository;

    @Autowired
    private AppRepository appRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);

        if(!StringUtils.isEmpty(username) && username.contains(CustomerStringUtils.SPLIT_CHARACTER)){
            Long userId = Long.valueOf(username.split(CustomerStringUtils.SPLIT_CHARACTER)[1]);
            UserRole userRole = userRoleRepository.findByUser(userId);
            List<String> roles = userRole.getRoles();


            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRoles(roles);

            List<Role> roleList = roleRepository.findByCodes(roles);
            List<Long> roleIds = Lists.transform(roleList, Role::getId);
            List<Long> appIds;
            if(AuthUtils.isRoot(roles)){
                List<App> apps = appRepository.findListBy(Maps.newHashMap());
                appIds = Lists.transform(apps, App::getId);
            } else {
                List<RoleApp> roleApps = roleAppRepository.findByRoles(roleIds);
                appIds = Lists.transform(roleApps, RoleApp::getAppId);
            }
            List<String> permissions = roleAuthorityService.loadRoleAuthorities(roleIds, appIds);

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

        if(!token.getAuto()) {
            if (token.getPassword() != null || StringUtils.isEmpty(token.getOriginPassword())) {
                String password = String.copyValueOf(token.getPassword());
                String originPassword = String.copyValueOf(token.getOriginPassword());
                if (!Encryption.checkPassword(password, token.getSalt(), originPassword)) {
                    throw new IncorrectCredentialsException("password.error");
                }
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
