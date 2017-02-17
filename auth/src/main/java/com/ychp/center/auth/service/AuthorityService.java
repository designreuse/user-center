package com.ychp.center.auth.service;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
public interface AuthorityService {

    Map<String, String> loadAuthorities(Long appId);
}
