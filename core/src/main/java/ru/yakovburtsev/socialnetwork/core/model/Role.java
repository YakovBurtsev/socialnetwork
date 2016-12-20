package ru.yakovburtsev.socialnetwork.core.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * The enum is represent roles in the app.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
