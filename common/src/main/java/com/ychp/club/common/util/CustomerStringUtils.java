package com.ychp.club.common.util;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
public class CustomerStringUtils {

    public final static String SPLIT_CHARACTER = ",";

    public static String formatString(String template, String ... params){
        String formatStr = template;
        for(int i = 0; i < params.length; i++){
            formatStr = formatStr.replace("{" + i + "}", params[i]);
        }
        return formatStr;
    }
}
