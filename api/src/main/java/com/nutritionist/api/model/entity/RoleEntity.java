package com.nutritionist.api.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEntity implements GrantedAuthority {
    ROLE_ADMIN,ROLE_USER;
    public String getAuthority(){
        return name();
    }
}
