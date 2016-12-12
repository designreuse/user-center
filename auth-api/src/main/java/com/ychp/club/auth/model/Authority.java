package com.ychp.club.auth.model;

import com.ychp.club.common.model.BaseModel;
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

    private String name;

    private String url;

    private String appId;

}
