package com.ychp.center.user.application;

import com.ychp.center.common.model.Paging;
import com.ychp.center.user.model.User;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
public interface UserManager {

    public User findById(Long id);

    public User findByUsername(String username);

    public boolean updateStatus(Long userId, Integer status);

    public Paging<User> paging(Integer pageNo, Integer pageSize, Map<String,Object> params);

}
