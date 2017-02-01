package com.ychp.club.auth.infrastructure.impl.application;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.enums.AuthType;
import com.ychp.club.auth.infrastructure.impl.cache.CustomerShiroCacheManager;
import com.ychp.club.auth.infrastructure.impl.cache.redis.JedisCache;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.auth.model.Role;
import com.ychp.club.auth.model.mysql.AppRepository;
import com.ychp.club.auth.model.mysql.AuthorityRepository;
import com.ychp.club.auth.model.mysql.RoleRepository;
import com.ychp.club.auth.service.AuthorityService;
import com.ychp.club.common.model.PageInfo;
import com.ychp.club.common.model.Paging;
import com.ychp.club.common.util.Encryption;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
    private CacheManager customerShiroCacheManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public CacheManager getCache() {
        return customerShiroCacheManager;
    }

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
    public Boolean delApp(Long appId) {
        return appRepository.delete(appId) == 1;
    }

    @Override
    public Boolean addApp(App app) {
        String appCode = Encryption.factoryAppCode();
        String secret = Encryption.factoryAppSecret(appCode, app.getName());
        app.setKey(appCode);
        app.setSecret(secret);
        return appRepository.create(app) == 1;
    }

    @Override
    public Boolean updateApp(App app) {
        appRepository.update(app);
        return true;
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
    public Boolean delAuthority(Long authorityId) {
        return authorityRepository.delete(authorityId) == 1;
    }

    @Override
    public Boolean addAuthority(Authority authority) {
        if(AuthType.ANON.getValue().equals(authority.getAuth())){
            authority.setPermKey(null);
            authority.setRoleKey(null);
        } else if(StringUtils.isEmpty(authority.getPermKey()) && StringUtils.isEmpty(authority.getRoleKey())){
            throw new IllegalArgumentException("perm key not empty");
        }
        return authorityRepository.create(authority) == 1;
    }

    @Override
    public Boolean updateAuthority(Authority authority) {
        if(AuthType.ANON.getValue().equals(authority.getAuth())){
            authority.setPermKey(authority.getPermKey() != null ? "" : null);
            authority.setRoleKey(authority.getRoleKey() != null ? "" : null);
        } else if(StringUtils.isEmpty(authority.getPermKey()) && StringUtils.isEmpty(authority.getRoleKey())){
            throw new IllegalArgumentException("perm key not empty");
        }
        authorityRepository.update(authority);
        return true;
    }

    @Override
    public Boolean delRole(Long roleId) {
        return roleRepository.delete(roleId) == 1;
    }

    @Override
    public Boolean addRole(Role role) {
        if(role == null || StringUtils.isEmpty(role.getName())){
            throw new IllegalArgumentException("role not empty");
        }
        return roleRepository.create(role) == 1;
    }

    @Override
    public Boolean updateRole(Role role) {
        if(role == null || StringUtils.isEmpty(role.getName())){
            throw new IllegalArgumentException("role not empty");
        }
        roleRepository.update(role);
        return true;
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
}
