package com.ychp.club.user.specification;

import java.util.Optional;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
public interface UserSpecification {

    public Optional<Long> isLoginNameUnqine(String loginName);

    public Optional<Long> isOuterIdUnqine(Long outerId);

}
