package com.ychp.center.auth.infrastructure.impl.service;

import com.google.common.collect.Lists;
import com.ychp.center.auth.model.Authority;
import com.ychp.center.auth.model.Role;
import com.ychp.center.auth.model.RoleAuthority;
import com.ychp.center.auth.model.mysql.AuthorityRepository;
import com.ychp.center.auth.model.mysql.RoleAuthorityRepository;
import com.ychp.center.auth.model.mysql.RoleRepository;
import com.ychp.center.auth.service.RoleAuthorityService;
import com.ychp.center.auth.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
@Slf4j
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<String> loadRoleAuthorities(Long roleId, List<Long> appIds) {
        List<String> perms = Lists.newArrayList();
        Role role = roleRepository.findById(roleId);

        if(appIds == null || appIds.size() == 0){
            return perms;
        }

        if(AuthUtils.isRoot(Lists.newArrayList(role.getCode()))) {
            List<Authority> authorities = authorityRepository.findByAppIds(appIds);
            if(authorities != null && !authorities.isEmpty()) {
                perms = Lists.transform(authorities, Authority::getPermKey);
            }
        } else {
            List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByRoleIdAndAppIds(roleId, appIds);

            if(roleAuthorities != null){
                perms = Lists.transform(roleAuthorities, RoleAuthority::getAuthorityKey);
            }
        }
        return perms;
    }

    @Override
    public List<String> loadRoleAuthorities(List<Long> roleIds, List<Long> appIds) {
        List<String> perms = Lists.newArrayList();
        List<Role> roles = roleRepository.findByIds(roleIds);
        List<String> roleCodes = Lists.transform(roles, Role::getCode);

        if(appIds == null || appIds.size() == 0){
            return perms;
        }

        if(AuthUtils.isRoot(roleCodes)) {
            List<Authority> authorities = authorityRepository.findByAppIds(appIds);
            if(authorities != null && !authorities.isEmpty()) {
                perms = Lists.transform(authorities, Authority::getPermKey);
            }
        } else {
            List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByRoleIdAndAppIds(roleIds, appIds);

            if(roleAuthorities != null){
                perms = Lists.transform(roleAuthorities, RoleAuthority::getAuthorityKey);
            }
        }
        return perms;
    }
}
