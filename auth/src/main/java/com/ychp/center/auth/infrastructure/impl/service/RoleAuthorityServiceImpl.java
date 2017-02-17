package com.ychp.center.auth.infrastructure.impl.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ychp.center.auth.model.Authority;
import com.ychp.center.auth.model.RoleAuthority;
import com.ychp.center.auth.model.mysql.AuthorityRepository;
import com.ychp.center.auth.model.mysql.RoleAuthorityRepository;
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
    private RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<String> loadRoleAuthorities(Long roleId, List<Long> appIds) {
        List<String> perms = Lists.newArrayList();

        if(appIds == null || appIds.size() == 0){
            return perms;
        }

        if(AuthUtils.isRoot(roleId)) {
            List<Authority> authorities = authorityRepository.findByAppIds(appIds);
            if(authorities != null && !authorities.isEmpty()) {
                perms = Lists.transform(authorities, new Function<Authority, String>() {
                    @Override
                    public String apply(Authority input) {
                        return input.getPermKey();
                    }
                });
            }
        } else {
            List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByAppAndRole(roleId, appIds);

            if(roleAuthorities != null){
                perms = Lists.transform(roleAuthorities, RoleAuthority::getAuthorityKey);
            }
        }
        return perms;
    }
}
