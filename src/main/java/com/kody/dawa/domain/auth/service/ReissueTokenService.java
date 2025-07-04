package com.kody.dawa.domain.auth.service;

import com.kody.dawa.domain.auth.presentation.dto.response.ReissueTokenResponse;
public interface ReissueTokenService {
    ReissueTokenResponse execute(String resolveRefreshToken);
}
