package com.ychp.club.web.controller.cms.apis;

import com.google.common.collect.Maps;
import com.ychp.club.auth.application.AuthorityManager;
import com.ychp.club.auth.model.App;
import com.ychp.club.auth.model.Authority;
import com.ychp.club.common.model.Paging;
import com.ychp.club.common.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/15
 */
@Slf4j
@RestController
@RequestMapping("/api/cms")
public class AuthorityApis {

    @Autowired
    private AuthorityManager authorityManager;

    @RequestMapping("/perms")
    public Paging<Authority> users(@RequestParam(value = "appId",required = false) Long appId,
                        @RequestParam(value = "pageNo",defaultValue = PageUtils.DEFAULT_PAGE_NO) Integer pageNo,
                        @RequestParam(value = "pageSize",defaultValue = PageUtils.DEFAULT_PAGE_SIZE) Integer pageSize){
        Paging<Authority> authorities = new Paging<>(pageNo, pageSize);
        if(appId != null){
            Map<String, Object> params = Maps.newHashMap();
            params.put("appId", appId);
            authorities = authorityManager.pagingAuthority(pageNo, pageSize, params);
        }
        return authorities;
    }

    @RequestMapping(value = "/perm",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean add(@RequestBody Authority authority){
        return authorityManager.addAuthority(authority);
    }

    @RequestMapping(value = "/perm",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean update(@RequestBody Authority authority){
        return authorityManager.updateAuthority(authority);
    }

    @RequestMapping(value = "/perm",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean del(@RequestParam(value = "id") Long id){
        return authorityManager.delAuthority(id);
    }

}
