package com.kody.dawa.global.entity;

import com.kody.dawa.domain.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserCredential {
    private final Long id;
    private final String email;
    private final String encodedPassword;
}