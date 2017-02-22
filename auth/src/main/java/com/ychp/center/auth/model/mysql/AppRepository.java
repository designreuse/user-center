package com.ychp.center.auth.model.mysql;

import com.ychp.center.auth.model.App;
import com.ychp.coding.common.mysql.MybatisRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
@Repository
public interface AppRepository extends MybatisRepository<App> {

    List<App> findAppList();

}
