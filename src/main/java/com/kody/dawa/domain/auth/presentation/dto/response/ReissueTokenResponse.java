package com.kody.dawa.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class ReissueTokenResponse {
    private final String accessToken;
    private final Instant accessTokenExpiredAt;
    private final String refreshToken;
    private final Instant refreshTokenExpiredAt;
}
