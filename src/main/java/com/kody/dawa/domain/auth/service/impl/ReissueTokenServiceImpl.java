package com.kody.dawa.domain.auth.service.impl;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.kody.dawa.domain.auth.service.ReissueTokenService;
import com.kody.dawa.domain.user.entity.User;
import com.kody.dawa.domain.user.repository.UserRepository;
import com.kody.dawa.global.entity.JwtType;
import com.kody.dawa.global.exception.HttpException;
import com.kody.dawa.global.exception.enums.ExceptionEnum;
import com.kody.dawa.global.security.cookie.CookieProperties;
import com.kody.dawa.global.security.jwt.JwtProvider;
import com.kody.dawa.global.security.jwt.dto.JwtDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final CookieProperties cookieProperties;

    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = extractTokenFromCookies(request, "refresh_token");

        if (refreshToken == null || !jwtProvider.validateToken(refreshToken, JwtType.REFRESH_TOKEN)) {
            throw ExceptionEnum.AUTH_EXPIRED_TOKEN.toHttpException();
        }

        Long currentUserId = Long.parseLong(jwtProvider.getIdByRefreshToken(refreshToken));
        User user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));

        JwtDetails newAccessToken = jwtProvider.generateToken(currentUserId, JwtType.ACCESS_TOKEN);
        JwtDetails newRefreshToken = jwtProvider.generateToken(currentUserId, JwtType.REFRESH_TOKEN);

        setJwtCookie(response, "access_token", newAccessToken.getToken(), newAccessToken.getExpiredAt());
        setJwtCookie(response, "refresh_token", newRefreshToken.getToken(), newRefreshToken.getExpiredAt());
    }

    private void setJwtCookie(HttpServletResponse response, String name, String token, Instant expiry) {
        long maxAgeSeconds = expiry.getEpochSecond() - Instant.now().getEpochSecond();

        ResponseCookie cookie = ResponseCookie.from(name, token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .domain(cookieProperties.getDomain())
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

    private String extractTokenFromCookies(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) return null;
        for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}