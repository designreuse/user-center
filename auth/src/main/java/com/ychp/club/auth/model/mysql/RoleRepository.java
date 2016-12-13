package com.ychp.club.auth.model.mysql;

import com.ychp.club.auth.model.Role;
import com.ychp.club.common.model.mysql.MybatisRepository;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Repository
public interface RoleRepository extends MybatisRepository<Role> {

}
