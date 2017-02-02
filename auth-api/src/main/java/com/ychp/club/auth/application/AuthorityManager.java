package com.ychp.club.auth.application;

import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.auth.model.Role;
import com.ychp.club.auth.model.dto.RoleAppDto;
import com.ychp.club.common.model.Paging;
import org.apache.shiro.cache.CacheManager;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
public interface AuthorityManager {

    CacheManager getCache();

    Paging<App> pagingApp(Integer pageNo, Integer pageSize, Map<String,Object> params);

    Boolean delApp(Long appId);

    Boolean addApp(App app);

    Boolean updateApp(App app);

    Paging<Authority> pagingAuthority(Integer pageNo, Integer pageSize, Map<String,Object> params);

    Boolean delAuthority(Long authorityId);

    Boolean addAuthority(Authority authority);

    Boolean updateAuthority(Authority authority);

    Boolean delRole(Long roleId);

    Boolean addRole(Role role);

    Boolean updateRole(Role role);

    Paging<Role> pagingRole(Integer pageNo, Integer pageSize, Map<String,Object> params);

    List<RoleAppDto> findApps(Long roleId);

    Map<String, String> loadAuthorities(Long appId);


}
