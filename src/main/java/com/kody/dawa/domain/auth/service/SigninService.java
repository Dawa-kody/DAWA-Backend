package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.SigninRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.TokenResponse;

public interface SigninService {
    TokenResponse execute(SigninRequest request);
}
