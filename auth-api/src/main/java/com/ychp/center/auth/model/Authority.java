package com.ychp.center.auth.model;

import com.ychp.coding.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/12/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Authority extends BaseModel {

    private static final long serialVersionUID = -8458766060275449272L;

    private Long pid;

    private String name;

    private String auth;

    private String permKey;

    private String roleKey;

    private String url;

    private Long appId;

}
