package com.ychp.club.web.controller.cms.apis;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.common.model.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@RestController
@RequestMapping("/api/cms")
public class AppApis {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("/apps")
    public Paging<App> apps(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){
        return authorityManager.pagingApp(pageNo, pageSize, null);
    }

}
