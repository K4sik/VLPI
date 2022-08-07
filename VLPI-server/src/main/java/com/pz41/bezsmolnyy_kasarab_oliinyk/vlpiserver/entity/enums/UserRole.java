package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ROLE_ADMIN, ROLE_STUDENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
