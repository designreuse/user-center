package com.ychp.center.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.ychp.coding.common.constants.JacksonType;
import com.ychp.coding.common.model.BaseModel;
import com.ychp.coding.common.util.JsonMapper;
import lombok.*;

import java.util.Collections;
import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 17/03/07
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseModel {

    private ObjectMapper objectMapper = JsonMapper.nonDefaultMapper().getMapper();

    @Setter
    @Getter
    private Long userId;

    @Setter
    @JsonIgnore
    private String roleJson;
    @Getter
    private List<String> roles;

    public void setRoleJson(String rolesJson) throws Exception {
        this.roleJson = rolesJson;
        if(Strings.isNullOrEmpty(rolesJson)) {
            this.roles = Collections.emptyList();
        } else {
            this.roles = objectMapper.readValue(rolesJson, JacksonType.LIST_OF_STRING);
        }

    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
        if(roles != null && !roles.isEmpty()) {
            try {
                this.roleJson = objectMapper.writeValueAsString(roles);
            } catch (Exception ignored) { }
        } else {
            this.roleJson = null;
        }

    }


}
