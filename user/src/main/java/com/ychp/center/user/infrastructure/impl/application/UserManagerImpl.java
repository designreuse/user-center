package com.ychp.center.user.infrastructure.impl.application;

import com.ychp.center.common.model.PageInfo;
import com.ychp.center.common.model.Paging;
import com.ychp.center.user.application.UserManager;
import com.ychp.center.user.model.User;
import com.ychp.center.user.model.mysql.UserRepository;
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
        return userRepository.findById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean updateStatus(Long userId, Integer status) {
        userRepository.updateStatusById(userId, status);
        return true;
    }

    public Paging<User> paging(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        Paging<User> userPaging= new Paging<>(pageNo, pageSize);
        List<User> users = userRepository.pagingBy(pageInfo.putIntoMap(params));
        Long total = userRepository.countBy(pageInfo.putIntoMap(params));
        userPaging.setDatas(users);
        userPaging.setTotal(total);
        return userPaging;
    }
}
