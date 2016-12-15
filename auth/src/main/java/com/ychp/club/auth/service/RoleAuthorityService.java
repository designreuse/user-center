package com.ychp.club.auth.service;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
public interface RoleAuthorityService {

    List<String> loadRoleAuthorities(Long roleId, Long appId);
}
