package com.kody.dawa.domain.auth.presentation.dto.response;
import com.kody.dawa.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String access;
    private String refresh;
    private Role role;
}