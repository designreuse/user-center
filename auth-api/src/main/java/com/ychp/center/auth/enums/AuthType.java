package com.ychp.center.auth.enums;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/14
 */
public enum AuthType {
    ANON("anon", "白名单"),
    AUTHC("authc", "权限");

    private String value;

    private String desc;

    private AuthType(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.desc;
    }
}
