package com.ychp.center.user.model;

import lombok.Data;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/17
 */
@Data
public class LoginUser {

    private Long id;

    private String name;

    private String email;

    private String mobile;

    private String stuNo;

    private String qq;

    public static LoginUser makeUser(User user){
        LoginUser loginUser = new LoginUser();
        loginUser.setId(user.getId());
        loginUser.setName(user.getName());
        loginUser.setEmail(user.getEmail());
        loginUser.setMobile(user.getMobile());
        loginUser.setStuNo(user.getStuNo());
        loginUser.setQq(user.getQq());
        return loginUser;
    }

}
