package com.ychp.club.user.application;

import com.ychp.club.user.model.User;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
public interface UserManager {

    public User findById(Long id);

    public List<User> paging(Integer pageNo, Integer pageSize, Map<String,Object> params);

}
