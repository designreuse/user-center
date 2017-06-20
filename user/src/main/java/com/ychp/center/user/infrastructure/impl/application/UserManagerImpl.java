package com.ychp.center.user.infrastructure.impl.application;

import com.google.common.base.Preconditions;
import com.ychp.center.user.application.UserManager;
import com.ychp.center.user.model.User;
import com.ychp.center.user.model.mysql.UserRepository;
import com.ychp.coding.common.model.PageInfo;
import com.ychp.coding.common.model.Paging;
import com.ychp.coding.common.util.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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

    @Override
    public Boolean addUser(User user) {
        Preconditions.checkArgument(user != null, "user not empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(user.getName()), "user name not empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(user.getUserName()), "user name not empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(user.getPassword()), "user password not empty");
        String salt = Encryption.getSalt();
        user.setSalt(salt);
        user.setPassword(Encryption.Encrypt3DES(user.getPassword(), salt));
        user.setStatus(1);
        return userRepository.create(user) == 1;
    }

    @Override
    public Boolean addDefaultUser(User user) {
        return addUser(user);
    }

    @Override
    public Boolean updateUser(User user) {
        Preconditions.checkArgument(user != null, "user not empty");
        Preconditions.checkArgument(user.getId() != null, "user is not empty");

        User exist = userRepository.findById(user.getId());
        Preconditions.checkArgument(exist != null, "user not exist");

        if(!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(Encryption.Encrypt3DES(user.getPassword(), exist.getSalt()));
        }
        userRepository.update(user);
        return true;
    }

    @Override
    public Boolean delUser(Long userId) {
        Preconditions.checkArgument(userId != null, "user is not empty");

        User exist = userRepository.findById(userId);
        Preconditions.checkArgument(exist != null, "user not exist");
        return userRepository.delete(userId) == 1;
    }
}
