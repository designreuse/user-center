package com.ychp.center.web.controller.apis;

import com.google.common.collect.Lists;
import com.ychp.center.auth.application.AuthorityManager;
import com.ychp.center.auth.model.dto.UserRoleDto;
import com.ychp.center.user.application.UserManager;
import com.ychp.center.user.model.User;
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
public class UserApis {

    @Autowired
    private UserManager userManager;

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("users")
    public Paging<User> users(@RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        return userManager.paging(pageNo, pageSize, null);
    }

    @RequestMapping("user/status")
    @ResponseBody
    public Boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status){
        return userManager.updateStatus(id, status);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean add(@RequestBody User user){
        return userManager.addDefaultUser(user);
    }

    @RequestMapping(value = "/user",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean update(@RequestBody User user){
        return userManager.updateUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam(value = "id") Long id){
        return userManager.delUser(id);
    }

    @RequestMapping("/user/roles")
    public List<UserRoleDto> roleApps(@RequestParam("userId") Long userId){
        return authorityManager.findRoles(userId);
    }

    @RequestMapping(value = "/user/role", method = RequestMethod.PUT)
    public Boolean grantApp(@RequestParam("userId") Long userId, @RequestParam("roleIds")String roleIds){
        String[] roleIdArr = roleIds.split(",");
        List<Long> roleIdList = Lists.newArrayList();
        for(String roleId : roleIdArr) {
            roleIdList.add(Long.valueOf(roleId));
        }
        return authorityManager.grantRole(userId, roleIdList);
    }

}
