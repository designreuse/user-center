package com.ychp.center.auth.utils;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/31
 */
public class SessionUtils {

    public static final int EXPIRE_SECONDS = 3600;

    public static String getSessionKey(String token){
        return "session:" + token;
    }

    public static String getSessionKey(String space, Object token){
        return "session:" + space + ":" + token.toString();
    }

    public static String getSessionMatchKey(){
        return "session:*";
    }

    public static String getSessionMatchKey(String space){
        return "session:" + space + ":*";
    }

}
