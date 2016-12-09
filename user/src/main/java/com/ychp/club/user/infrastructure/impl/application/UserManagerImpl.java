package com.ychp.club.user.infrastructure.impl.application;

import com.ychp.club.common.model.PageInfo;
import com.ychp.club.user.application.UserManager;
import com.ychp.club.user.model.User;
import com.ychp.club.user.model.mysql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserService userService;

    public User findById(Long id) {
        return null;
    }

    public List<User> paging(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return userRepository.pagingBy(pageInfo.putIntoMap(params));
    }
}
