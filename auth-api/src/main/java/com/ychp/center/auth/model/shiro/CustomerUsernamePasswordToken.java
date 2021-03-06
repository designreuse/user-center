package com.ychp.center.auth.model.shiro;

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

    private String salt;

    private Integer status;

    private Boolean auto = false;

    public CustomerUsernamePasswordToken() {
        super();
    }

    public CustomerUsernamePasswordToken(String username, String password, String salt, String originPassword, Integer status) {
        this(username, password, salt, originPassword, status, false, null, false);
    }

    public CustomerUsernamePasswordToken(String username,  String password, Integer status, boolean rememberMe) {
        this(username, null, null, password, status, rememberMe, null, true);
    }

    public CustomerUsernamePasswordToken(String username, String password, String salt, String originPassword, Integer status, String host) {
        this(username, password, salt, originPassword, status, false, host, false);
    }

    public CustomerUsernamePasswordToken(String username, String password, String salt, String originPassword, Integer status, boolean rememberMe) {
        this(username, password, salt, originPassword, status, rememberMe, null, false);
    }

    public CustomerUsernamePasswordToken(String username, String password, String salt, String originPassword, Integer status, boolean rememberMe, String host, boolean auto) {
        super(username, password, rememberMe, host);
        this.originPassword = originPassword != null ? originPassword.toCharArray():null;
        this.salt = salt;
        this.status = status;
        this.auto = auto;
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
