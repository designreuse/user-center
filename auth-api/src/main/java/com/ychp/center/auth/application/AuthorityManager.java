package com.ychp.center.auth.application;

import com.ychp.center.auth.model.App;
import com.ychp.center.auth.model.Authority;
import com.ychp.center.auth.model.Role;
import com.ychp.center.auth.model.dto.RoleAppDto;
import com.ychp.center.auth.model.dto.RoleAuthByAppDto;
import com.ychp.coding.common.model.Paging;
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

    Boolean grantApp(Long roleId, Long[] appIds);

    Paging<Role> pagingRole(Integer pageNo, Integer pageSize, Map<String,Object> params);

    List<RoleAppDto> findApps(Long roleId);

    Map<String, String> loadAuthorities(Long appId);

    List<RoleAuthByAppDto> loadRolePerms(Long roleId);

    Boolean grantAuthorities(Long roleId, Long[] permIds);
}
