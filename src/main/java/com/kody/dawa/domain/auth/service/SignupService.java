package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.SignupRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;

public interface SignupService {
    TokenResponse execute(SignupRequest request);
}
