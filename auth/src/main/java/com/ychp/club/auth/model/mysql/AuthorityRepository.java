package com.ychp.club.auth.model.mysql;

import com.ychp.club.auth.model.Authority;
import com.ychp.club.common.model.mysql.MybatisRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
@Repository
public interface AuthorityRepository extends MybatisRepository<Authority> {

    public List<Authority> findByAppId(Long appId);

}
