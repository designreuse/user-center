package com.ychp.center.web.controller.apis;

import com.ychp.center.auth.application.AuthorityManager;
import com.ychp.center.auth.model.App;
import com.ychp.coding.common.model.Paging;
import com.ychp.coding.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class AppApis {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("/apps")
    public Paging<App> apps(@RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        return authorityManager.pagingApp(pageNo, pageSize, null);
    }

    @RequestMapping(value = "/app",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean add(@RequestBody App app){
        return authorityManager.addApp(app);
    }

    @RequestMapping(value = "/app",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean update(@RequestBody App app){
        return authorityManager.updateApp(app);
    }

    @RequestMapping(value = "/app",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam(value = "id") Long id){
        return authorityManager.delApp(id);
    }

}
