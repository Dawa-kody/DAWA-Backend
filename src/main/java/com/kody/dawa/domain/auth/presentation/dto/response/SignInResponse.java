package com.kody.dawa.domain.auth.presentation.dto.response;

import com.kody.dawa.domain.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
@Getter
@Builder
@AllArgsConstructor
public class SignInResponse {
    private final String accessToken;
    private final Instant accessTokenExpiredAt;
    private final String refreshToken;
    private final Instant refreshTokenExpiredAt;
    private final Role role;
}
