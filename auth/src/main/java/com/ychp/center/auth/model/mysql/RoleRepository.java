package com.ychp.center.auth.model.mysql;

import com.ychp.center.auth.model.Role;
import com.ychp.coding.common.mysql.MybatisRepository;
import org.springframework.stereotype.Repository;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Repository
public interface RoleRepository extends MybatisRepository<Role> {

}
