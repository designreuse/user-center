package com.ychp.club.auth.infrastructure.impl.application;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.auth.model.Role;
import com.ychp.club.auth.model.mysql.AppRepository;
import com.ychp.club.auth.model.mysql.AuthorityRepository;
import com.ychp.club.auth.model.mysql.RoleRepository;
import com.ychp.club.auth.service.AuthorityService;
import com.ychp.club.common.model.PageInfo;
import com.ychp.club.common.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
@Component
public class AuthorityManagerImpl implements AuthorityManager {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public Paging<App> pagingApp(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        Paging<App> appPaging= new Paging<>(pageNo, pageSize);
        List<App> apps = appRepository.pagingBy(pageInfo.putIntoMap(params));
        Long total = appRepository.countBy(pageInfo.putIntoMap(params));
        appPaging.setDatas(apps);
        appPaging.setTotal(total);
        return appPaging;
    }

    @Override
    public Paging<Authority> pagingAuthority(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        if(params == null || params.size() == 0){
            return new Paging<>();
        }
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        Paging<Authority> authorityPaging= new Paging<>(pageNo, pageSize);
        List<Authority> authorities = authorityRepository.pagingBy(pageInfo.putIntoMap(params));
        Long total = authorityRepository.countBy(pageInfo.putIntoMap(params));
        authorityPaging.setDatas(authorities);
        authorityPaging.setTotal(total);
        return authorityPaging;
    }

    @Override
    public Paging<Role> pagingRole(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        Paging<Role> rolePaging= new Paging<>(pageNo, pageSize);
        List<Role> roles = roleRepository.pagingBy(pageInfo.putIntoMap(params));
        Long total = roleRepository.countBy(pageInfo.putIntoMap(params));
        rolePaging.setDatas(roles);
        rolePaging.setTotal(total);
        return rolePaging;
    }

    @Override
    public Map<String, String> loadAuthorities(Long appId) {
        return authorityService.loadAuthorities(appId);
    }

    @Override
    public Boolean delApp(Long appId) {
        return appRepository.delete(appId) == 1;
    }
}
