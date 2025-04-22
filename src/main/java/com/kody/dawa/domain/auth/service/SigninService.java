package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.request.SigninRequest;
import com.kody.dawa.domain.auth.presentation.dto.response.SignInResponse;

public interface SigninService {
    SignInResponse execute(SigninRequest request);
}
