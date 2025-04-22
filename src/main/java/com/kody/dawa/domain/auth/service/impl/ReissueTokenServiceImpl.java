package com.kody.dawa.domain.auth.service.impl;


import com.kody.dawa.domain.auth.presentation.dto.response.ReissueTokenResponse;
import com.kody.dawa.domain.auth.service.ReissueTokenService;
import com.kody.dawa.global.entity.JwtType;
import com.kody.dawa.global.exception.enums.ExceptionEnum;
import com.kody.dawa.global.security.jwt.JwtProvider;
import com.kody.dawa.global.security.jwt.dto.JwtDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtProvider jwtProvider;

    public ReissueTokenResponse execute(String resolveRefreshToken) {
        Long currentUserId = Long.parseLong(jwtProvider.getIdByRefreshToken(resolveRefreshToken));

        if (!jwtProvider.validateToken(resolveRefreshToken, JwtType.REFRESH_TOKEN)) {
            throw ExceptionEnum.AUTH_EXPIRED_TOKEN.toHttpException();
        }

        JwtDetails newAccessToken = jwtProvider.generateToken(currentUserId, JwtType.ACCESS_TOKEN);
        JwtDetails newRefreshToken = jwtProvider.generateToken(currentUserId, JwtType.REFRESH_TOKEN);

        return new ReissueTokenResponse(
                newAccessToken.getToken(),
                newAccessToken.getExpiredAt(),
                newRefreshToken.getToken(),
                newRefreshToken.getExpiredAt()
        );
    }
}