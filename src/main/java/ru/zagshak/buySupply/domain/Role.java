package ru.zagshak.buySupply.domain;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;

@Table(name = "user_roles")
public enum Role implements GrantedAuthority {
    USER,ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}