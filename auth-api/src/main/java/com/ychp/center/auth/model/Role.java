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
public class Role extends BaseModel {

    private static final long serialVersionUID = 6979200803842323282L;

    private String name;

    private String code;

}
