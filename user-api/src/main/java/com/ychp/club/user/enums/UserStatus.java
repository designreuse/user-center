package com.ychp.club.user.enums;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/11
 */
public enum UserStatus {
    ENABLE(0, "可用"),
    FORZEN(-1, "冻结");

    private int value;

    private String desc;

    UserStatus(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.desc;
    }

}
