package com.ychp.center.user.model;

import com.ychp.coding.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    private static final long serialVersionUID = 4342165661897688664L;

    private String name;

    private String username;

    private String password;

    private String salt;

    private Long roleId;

    private Long outerId;

    private String email;

    private String mobile;

    private String stuNo;

    private String qq;

    private Integer status;

}
