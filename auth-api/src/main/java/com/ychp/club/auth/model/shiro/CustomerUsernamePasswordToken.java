package com.ychp.club.auth.model.shiro;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 7852183309727504524L;

    private char[] originPassword;

    private Integer status;

    public CustomerUsernamePasswordToken() {
        super();
    }

    public CustomerUsernamePasswordToken(String username, String password, String originPassword, Integer status) {
        this(username, password, originPassword, status, false, null);
    }


    public CustomerUsernamePasswordToken(String username, String password, String originPassword, Integer status, String host) {
        this(username, password, originPassword, status, false, host);
    }

    public CustomerUsernamePasswordToken(String username, String password, String originPassword, Integer status, boolean rememberMe) {
        this(username, password, originPassword, status, rememberMe, null);
    }

    public CustomerUsernamePasswordToken(String username, String password, String originPassword, Integer status, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
        this.originPassword = originPassword != null?originPassword.toCharArray():null;
        this.status = status;
    }

    public void clear() {
        super.clear();
        if(this.originPassword != null) {
            for(int i = 0; i < this.originPassword.length; ++i) {
                this.originPassword[i] = 0;
            }

            this.originPassword = null;
        }

    }


}
