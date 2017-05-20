package com.ychp.center.auth.model.mysql;

import com.ychp.center.auth.model.UserRole;
import com.ychp.coding.common.mysql.MybatisRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Repository
public interface UserRoleRepository extends MybatisRepository<UserRole> {

    UserRole findByUser(@Param("userId") Long userId);
}
