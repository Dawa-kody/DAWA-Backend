package com.kody.dawa.domain.user.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_HEALTH_TEACHER("ROLE_HEALTH_TEACHER"),
    ROLE_TEACHER("ROLE_TEACHER");

    private final String permission;

}