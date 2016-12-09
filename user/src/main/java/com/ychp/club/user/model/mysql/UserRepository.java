package com.ychp.club.user.model.mysql;

import com.ychp.club.common.model.mysql.MybatisRepository;
import com.ychp.club.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
@Repository
public interface UserRepository extends MybatisRepository<User> {

    public void updatePasswordById(Long id, String password);

    public void updateStatusById(Long id, Integer status);

    public User findByLoginName(String loginName);

}
