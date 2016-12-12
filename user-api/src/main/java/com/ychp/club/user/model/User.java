package com.ychp.club.user.model;

import com.ychp.club.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel implements UserDetails {

    private static final long serialVersionUID = 4342165661897688664L;

    private String name;

    private String username;

    private String password;

    private Long outerId;

    private String email;

    private String mobile;

    private String stuNo;

    private String qq;

    private Integer status;

    private Collection<GrantedAuthority>  authorities;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

}
