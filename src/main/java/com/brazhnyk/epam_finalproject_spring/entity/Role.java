package com.brazhnyk.epam_finalproject_spring.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_BLOCKED;

    @Override
    public String getAuthority() {
        return name();
    }
}
