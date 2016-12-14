package com.ychp.club.auth.application;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
public interface AuthorityManager {

    Map<String, String> loadAuthorities(Long appId);
}
