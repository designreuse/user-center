package com.ychp.club.web.controller.cms.apis;

import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Role;
import com.ychp.club.common.model.Paging;
import com.ychp.club.common.util.PageUtils;
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
@RequestMapping("/api/cms")
public class RoleApis {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("roles")
    public Paging<Role> users(@RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        return authorityManager.pagingRole(pageNo, pageSize, null);
    }

    @RequestMapping(value = "/role",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean add(@RequestBody Role role){
        return authorityManager.addRole(role);
    }

    @RequestMapping(value = "/role",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean update(@RequestBody Role role){
        return authorityManager.updateRole(role);
    }

    @RequestMapping(value = "/role",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam(value = "id") Long id){
        return authorityManager.delRole(id);
    }


}
