package com.kody.dawa.domain.auth.presentation.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshTokenResponse {
    private String access;
    private String refresh;
}
