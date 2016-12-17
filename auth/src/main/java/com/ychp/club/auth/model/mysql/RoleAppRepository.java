package com.ychp.club.auth.model.mysql;

import com.ychp.club.auth.model.RoleApp;
import com.ychp.club.common.model.mysql.MybatisRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Repository
public interface RoleAppRepository extends MybatisRepository<RoleApp> {

    List<RoleApp> findByRole(@Param("roleId") Long roleId);
}
