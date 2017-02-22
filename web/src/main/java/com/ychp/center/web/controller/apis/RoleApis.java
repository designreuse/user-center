package com.ychp.center.web.controller.apis;

import com.ychp.center.auth.application.AuthorityManager;
import com.ychp.center.auth.model.Role;
import com.ychp.center.auth.model.dto.RoleAppDto;
import com.ychp.center.auth.model.dto.RoleAuthByAppDto;
import com.ychp.coding.common.model.Paging;
import com.ychp.coding.common.util.PageUtils;
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

    @RequestMapping(value = "/role", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam(value = "id") Long id){
        return authorityManager.delRole(id);
    }

    @RequestMapping("/role/apps")
    public List<RoleAppDto> roleApps(@RequestParam("roleId") Long roleId){
        return authorityManager.findApps(roleId);
    }

    @RequestMapping(value = "/role/app", method = RequestMethod.PUT)
    public Boolean grantApp(@RequestParam("roleId") Long roleId, @RequestParam("appIds")Long[] appIds){
        return authorityManager.grantApp(roleId, appIds);
    }

    @RequestMapping(value = "/role/perms", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<RoleAuthByAppDto> RolePerms(@RequestParam("roleId") Long roleId){
        return authorityManager.loadRolePerms(roleId);
    }

    @RequestMapping(value = "/role/perms", method = RequestMethod.PUT)
    public Boolean grantAuthorities(@RequestParam("roleId") Long roleId, @RequestParam("permIds")Long[] permIds){
        return authorityManager.grantAuthorities(roleId, permIds);
    }


}
