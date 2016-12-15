package com.ychp.club.auth.infrastructure.impl.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ychp.club.auth.model.RoleAuthority;
import com.ychp.club.auth.model.mysql.RoleAuthorityRepository;
import com.ychp.club.auth.service.RoleAuthorityService;
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
    private RoleAuthorityRepository roleAuthorityRepository;

    @Override
    public List<String> loadRoleAuthorities(Long roleId, Long appId) {
        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByAppAndRole(roleId, appId);

        List<String> perms = Lists.newArrayList();
        if(roleAuthorities != null){
            perms = Lists.transform(roleAuthorities, RoleAuthority::getAuthorityKey);
        }

        return perms;
    }
}
