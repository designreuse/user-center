package com.ychp.club.auth.infrastructure.impl.application;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.mysql.AppRepository;
import com.ychp.club.auth.model.mysql.AuthorityRepository;
import com.ychp.club.auth.service.AuthorityService;
import com.ychp.club.common.model.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
@Component
public class AuthorityManagerImpl implements AuthorityManager {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public List<App> pagingApp(Integer pageNo, Integer pageSize, Map<String, Object> params) {
        PageInfo pageInfo = new PageInfo(pageNo, pageSize);
        return appRepository.pagingBy(pageInfo.putIntoMap(params));
    }

    @Override
    public Map<String, String> loadAuthorities(Long appId) {
        return authorityService.loadAuthorities(appId);
    }
}
