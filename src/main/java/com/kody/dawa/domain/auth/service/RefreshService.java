package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;

public interface RefreshService {
    TokenResponse execute(String access, String refresh);
}
