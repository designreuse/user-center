package com.ychp.center.auth.model.dto;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 17/2/22
 */
@Data
public class RoleAuthByAppDto {

    private String appName;

    private List<RoleAuthDto> roleAuthDtos;
}
