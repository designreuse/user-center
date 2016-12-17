package com.ychp.club.auth.application;

import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.auth.model.Role;
import com.ychp.club.common.model.Paging;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
public interface AuthorityManager {

    Paging<App> pagingApp(Integer pageNo, Integer pageSize, Map<String,Object> params);

    Paging<Authority> pagingAuthority(Integer pageNo, Integer pageSize, Map<String,Object> params);

    Paging<Role> pagingRole(Integer pageNo, Integer pageSize, Map<String,Object> params);

    Map<String, String> loadAuthorities(Long appId);
}
