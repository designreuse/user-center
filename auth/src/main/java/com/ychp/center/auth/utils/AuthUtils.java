package com.ychp.center.auth.utils;

import com.ychp.center.common.util.CustomerStringUtils;
import org.springframework.util.StringUtils;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
public class AuthUtils {

    private static final String BASE_PERM = "anon";
    private static final Long ROOT_ID = 1L;
    private static final String PERM_TEMPLATE = "perms[{0}]";

    private static final String ROLE_TEMPLATE = "roles[{0}]";

    public static String getAuth(String auth, String permKey, String roleKey){
        String fullAuth = auth;
        if(!StringUtils.isEmpty(permKey)){
            fullAuth += "," + CustomerStringUtils.formatString(PERM_TEMPLATE, permKey);
        }

        if(!StringUtils.isEmpty(roleKey)){
            fullAuth += "," + CustomerStringUtils.formatString(ROLE_TEMPLATE, roleKey);
        }

        return fullAuth;
    }

    public static Boolean isRoot(Long roleId){
        return roleId != null && roleId==ROOT_ID;
    }

}
