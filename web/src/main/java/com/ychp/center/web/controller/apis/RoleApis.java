package com.ychp.center.web.controller.apis;

import com.ychp.center.auth.application.AuthorityManager;
import com.ychp.center.auth.model.Role;
import com.ychp.center.auth.model.dto.RoleAppDto;
import com.ychp.center.common.model.Paging;
import com.ychp.center.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/9
 */
@Slf4j
@RestController
@RequestMapping("/api")
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

    @RequestMapping("/role/appList")
    public List<RoleAppDto> appList(@RequestParam("roleId") Long roleId){
        return authorityManager.findApps(roleId);
    }

}