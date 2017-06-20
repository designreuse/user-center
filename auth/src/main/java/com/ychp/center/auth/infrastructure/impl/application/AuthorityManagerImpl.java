package com.ychp.center.auth.infrastructure.impl.application;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ychp.center.auth.application.AuthorityManager;
import com.ychp.center.auth.enums.AuthType;
import com.ychp.center.auth.model.*;
import com.ychp.center.auth.model.dto.RoleAppDto;
import com.ychp.center.auth.model.dto.RoleAuthByAppDto;
import com.ychp.center.auth.model.dto.RoleAuthDto;
import com.ychp.center.auth.model.dto.UserRoleDto;
import com.ychp.center.auth.model.mysql.*;
import com.ychp.center.auth.service.AuthorityService;
import com.ychp.coding.common.model.PageInfo;
import com.ychp.coding.common.model.Paging;
import com.ychp.coding.common.util.Encryption;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private RoleAppRepository roleAppRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleAuthorityRepository roleAuthorityRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
    public Boolean grantApp(Long roleId, Long[] appIds) {
        if(appIds.length == 0){
            throw new IllegalArgumentException("appIds not empty");
        }

        List<RoleApp> roleApps = roleAppRepository.findByRole(roleId);

        List<Long> deleteIds = Lists.newArrayList();

        List<Long> createIds = Lists.newArrayList(appIds);

        Long appId;
        for(RoleApp roleApp : roleApps){
            appId = roleApp.getAppId();
            deleteIds.add(roleApp.getId());
            if(createIds.contains(appId)) {
                //已存在
                deleteIds.remove(roleApp.getId());
                createIds.remove(appId);
            }
        }

        if(!deleteIds.isEmpty()){
            for(Long id : deleteIds) {
                roleAppRepository.delete(id);
            }
        }

        if(!createIds.isEmpty()){
            RoleApp roleApp;
            for(Long createId : createIds){
                roleApp = new RoleApp();
                roleApp.setRoleId(roleId);
                roleApp.setAppId(createId);
                roleAppRepository.create(roleApp);
            }
        }
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
    public List<RoleAppDto> findApps(Long roleId) {
        List<App> apps = appRepository.findAppList();
        List<RoleApp> roleApps = roleAppRepository.findByRole(roleId);
        Map<Long, RoleApp> roleAppMap = roleApps.stream().filter(Objects::nonNull).collect(Collectors.toMap(RoleApp::getAppId, it -> it));
        if(roleAppMap == null){
            roleAppMap = Maps.newHashMap();
        }

        RoleAppDto roleAppDto;
        List<RoleAppDto> roleAppDtos = Lists.newArrayList();
        for (App app : apps){
            roleAppDto = new RoleAppDto();
            roleAppDto.setAppId(app.getId());
            roleAppDto.setName(app.getName());
            if(roleAppMap.get(app.getId()) == null){
                roleAppDto.setIsGrant(true);
            }
            roleAppDtos.add(roleAppDto);
        }
        return roleAppDtos;
    }

    @Override
    public Map<String, String> loadAuthorities(Long appId) {
        return authorityService.loadAuthorities(appId);
    }

    @Override
    public List<RoleAuthByAppDto> loadRolePerms(Long roleId) {
        List<RoleApp> roleApps = roleAppRepository.findByRole(roleId);
        if(roleApps == null || roleApps.size() == 0){
            return Lists.newArrayList();
        }
        List<Long> appIds = Lists.transform(roleApps, RoleApp::getAppId);
        List<App> apps = appRepository.findByIds(appIds);

        List<Authority> authorities = authorityRepository.findByAppIds(appIds);

        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByRoleIdAndAppIds(roleId, appIds);
        Map<Long, RoleAuthority> roleAuthorityMap = Maps.uniqueIndex(roleAuthorities, RoleAuthority::getAuthorityId);

        Map<Long, List<RoleAuthDto>> roleAuthMap = Maps.newHashMap();

        if(authorities != null){
            List<RoleAuthDto> tmpAuthorities;
            RoleAuthDto roleAuthDto;
            for(Authority authority : authorities){
                roleAuthDto = new RoleAuthDto();
                roleAuthDto.setPermId(authority.getId());
                roleAuthDto.setName(authority.getName());
                if(roleAuthorityMap.get(roleAuthDto.getPermId()) != null){
                    roleAuthDto.setIsGrant(true);
                }
                tmpAuthorities = roleAuthMap.get(authority.getAppId());
                if(tmpAuthorities == null){
                    tmpAuthorities = Lists.newArrayList();
                }
                tmpAuthorities.add(roleAuthDto);
                roleAuthMap.put(authority.getAppId(), tmpAuthorities);
            }
        }

        RoleAuthByAppDto roleAuthByAppDto;
        List<RoleAuthByAppDto> roleAuthByAppDtos = Lists.newArrayList();
        for (App app : apps){
            roleAuthByAppDto = new RoleAuthByAppDto();
            roleAuthByAppDto.setAppName(app.getName());
            roleAuthByAppDto.setRoleAuthDtos(roleAuthMap.get(app.getId()));
            roleAuthByAppDtos.add(roleAuthByAppDto);
        }
        return roleAuthByAppDtos;
    }

    @Override
    public Boolean grantAuthorities(Long roleId, Long[] permIds) {
        if(permIds.length == 0){
            throw new IllegalArgumentException("permIds not empty");
        }

        List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findByRoleId(roleId);

        List<Long> deleteIds = Lists.newArrayList();

        List<Long> createIds = Lists.newArrayList(permIds);

        Long authorityId;
        for(RoleAuthority roleAuthority : roleAuthorities){
            authorityId = roleAuthority.getAuthorityId();
            deleteIds.add(roleAuthority.getId());
            if(createIds.contains(authorityId)) {
                //已存在
                deleteIds.remove(roleAuthority.getId());
                createIds.remove(authorityId);
            }
        }

        if(!deleteIds.isEmpty()){
            for(Long id : deleteIds) {
                roleAuthorityRepository.delete(id);
            }
        }

        if(!createIds.isEmpty()){
            RoleAuthority roleAuthority;
            List<Authority> authorities = authorityRepository.findByIds(createIds);
            Map<Long, Authority> authorityMap = Maps.uniqueIndex(authorities, Authority::getId);
            Authority authority;
            for(Long createId : createIds){
                authority = authorityMap.get(createId);
                roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(roleId);
                roleAuthority.setAppId(createId);
                roleAuthority.setAuthorityId(authority.getId());
                roleAuthority.setAuthorityKey(authority.getPermKey());
                roleAuthority.setAppId(authority.getAppId());
                roleAuthorityRepository.create(roleAuthority);
            }
        }
        return true;
    }

    @Override
    public List<UserRoleDto> findRoles(Long userId) {
        List<Role> roles = roleRepository.findRoleList();
        UserRole userRole = userRoleRepository.findByUser(userId);
        List<String> roleCodes;

        if(userRole != null) {
            roleCodes = userRole.getRoles();
        } else {
            roleCodes = Lists.newArrayList();
        }

        UserRoleDto userRoleDto;
        List<UserRoleDto> userRoleDtos = Lists.newArrayList();
        for (Role role : roles){
            userRoleDto = new UserRoleDto();
            userRoleDto.setRoleId(role.getId());
            userRoleDto.setName(role.getName());
            if(roleCodes.contains(role.getCode())){
                userRoleDto.setIsGrant(true);
            }
            userRoleDtos.add(userRoleDto);
        }
        return userRoleDtos;
    }

    @Override
    public Boolean grantRole(Long userId, List<Long> roleIds) {
        if(roleIds == null || roleIds.isEmpty()){
            throw new IllegalArgumentException("roleId not empty");
        }

        List<Role> roles = roleRepository.findByIds(roleIds);
        List<String> roleCodes = Lists.transform(roles, Role::getCode);
        UserRole userRole = userRoleRepository.findByUser(userId);
        List<String> userRoles = userRole.getRoles();

        for(String roleCode : roleCodes) {
            if(!userRoles.contains(roleCode)) {
                userRoles.add(roleCode);
            }
        }

        userRole.setRoles(userRoles);
        userRoleRepository.update(userRole);
        return true;
    }
}
