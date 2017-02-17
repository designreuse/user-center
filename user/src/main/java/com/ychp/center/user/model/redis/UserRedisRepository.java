package com.ychp.center.user.model.redis;

import com.ychp.center.user.model.User;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
public interface UserRedisRepository {

    public void login(User user, String token);

    public void logout(String token);

}
