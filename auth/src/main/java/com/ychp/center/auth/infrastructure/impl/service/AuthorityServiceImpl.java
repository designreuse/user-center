package com.ychp.center.auth.infrastructure.impl.service;

import com.google.common.collect.Maps;
import com.ychp.center.auth.model.Authority;
import com.ychp.center.auth.model.mysql.AuthorityRepository;
import com.ychp.center.auth.service.AuthorityService;
import com.ychp.center.auth.utils.AuthUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
@Slf4j
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Map<String, String> loadAuthorities(Long appId) {
        Map<String, String> filterChainDefinitionMap = Maps.newHashMap();
        List<Authority> authorities = authorityRepository.findByAppId(appId);
        for(Authority authority : authorities){
            filterChainDefinitionMap.put(authority.getUrl(), AuthUtils.getAuth(authority.getAuth(), authority.getPermKey(), authority.getRoleKey()));
        }
        return filterChainDefinitionMap;
    }
}
