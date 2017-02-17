package com.ychp.center.auth.model;

import com.ychp.center.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleAuthority extends BaseModel {

    private static final long serialVersionUID = -8805239526068871930L;

    private Long roleId;

    private Long authorityId;

    private String authorityKey;

    private Long appId;

}
