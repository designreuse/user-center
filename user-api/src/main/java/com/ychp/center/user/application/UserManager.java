package com.ychp.center.user.application;

import com.ychp.coding.common.model.Paging;
import com.ychp.center.user.model.User;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
public interface UserManager {

    User findById(Long id);

    User findByUsername(String username);

    boolean updateStatus(Long userId, Integer status);

    Paging<User> paging(Integer pageNo, Integer pageSize, Map<String, Object> params);

}
